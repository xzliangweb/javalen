package com.example.itheima_reggie_take.filter;

import com.alibaba.fastjson.JSON;
import com.example.itheima_reggie_take.common.BaseContext;
import com.example.itheima_reggie_take.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //    路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        log.info("拦截的请求,{}", requestURI);
//        String[] urls = new String[]{
//        "/employee/login",
//        "/employee/logout",
//        "/backend/**",
//        "/front/**",
//        "/user/sendMsg",
//        "/user/login","/user/**"};
        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        //        判断本地请求是否需要处理
        boolean check = check(urls, requestURI);

        //        如果不需要处理则直接放行
        if (check) {
            filterChain.doFilter(request, response);
            return;
        }

        //    4-1     判断登陆状态，如果已登陆则直接放行。
        if (request.getSession().getAttribute("employee") != null) {

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

//            long id = Thread.currentThread().getId();
//            log.info("线程id:{}", id);
            filterChain.doFilter(request, response);
            return;
        }


        //4-2、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request,response);
            return;
        }


//  4-2
//        if (request.getSession().getAttribute("user") != null) {
//            Long userId = (Long) request.getSession().getAttribute("user");
//            BaseContext.setCurrentId(userId);
//            filterChain.doFilter(request, response);
//            return;
//        }

        //        如果未登录返回未登录结果。通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * 匹配路径 检查是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }


}
