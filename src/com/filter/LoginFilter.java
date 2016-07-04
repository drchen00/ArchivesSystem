package com.filter;

import com.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by drc on 16-5-3.
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        if (path.contains("/login.html")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (path.contains("/assets")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (path.contains("/login.action")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(path.contains("register")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (session.getAttribute(Constants.getUserID()) == null) {
            response.sendRedirect("login.html");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
