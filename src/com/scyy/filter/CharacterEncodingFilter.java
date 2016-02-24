package com.scyy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by LYH on 2016/2/24.
 * 功能：字符编码过滤器，设置为UTF-8
 */
public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }
}
