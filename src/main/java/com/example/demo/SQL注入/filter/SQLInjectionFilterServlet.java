package com.example.demo.SQL注入.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @program:  防止SQL注入
 * @description:
 * @author: xiuchao Song
 * @create: 2019-12-28 15:38
 **/
@WebFilter(urlPatterns = "/*", filterName = "SQLInjection", initParams = { @WebInitParam(name = "regularExpression", value = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)") })
@SuppressWarnings("all")
public class SQLInjectionFilterServlet implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SQLInjectionFilterServlet.class);

    private String regularExpression;

    public final static String XSS_ERROR_PATH = "/WEB-INF/views/common/error.jsp";

    public SQLInjectionFilterServlet() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        regularExpression = filterConfig.getInitParameter("regularExpression");
        log.info("######### regularExpression={}", regularExpression);
    }

    /*
     * 如果需要输入“'”、“;”、“--”这些字符 可以考虑将这些字符转义为html转义字符 “'”转义字符为：&#39; “;”转义字符为：&#59;
     * “--”转义字符为：&#45;&#45;
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        requestUrl = requestUrl.substring(requestUrl.indexOf(contextPath) + contextPath.length());// 获取剥离contextPath的url路径

        Map parametersMap = request.getParameterMap();
        Iterator it = parametersMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String[] value = (String[]) entry.getValue();
            for (int i = 0; i < value.length; i++) {
                if (null != value[i] && value[i].matches(regularExpression)) {
                    log.info("#疑似SQL注入攻击！参数名称：{}；录入信息:{}", entry.getKey(), value[i]);
                    RequestDispatcher rd = request.getRequestDispatcher(XSS_ERROR_PATH);
                    rd.forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {

    }

}