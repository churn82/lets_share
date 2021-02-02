package com.kh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		HttpServletResponse res = (HttpServletResponse)response;
		
<<<<<<< HEAD
		//모든 요청에 대한 응답헤더가 적용이 되기 때문에 정적 리소스인 html이나 css, js도
		//text/html로 컨텐츠타입을 가진다. css를 브라우저에서 html로 인식하기 때문에
		//css가 정상적으로 동작하지 않게 된다.
=======
		//모든 요청에 대한 응답헤더가 적용이되기 때문에 정적 리소스인 html이나 css, JS도
		//text/html로 컨텐츠 타입을 가지게 된다. css를 브라우저가 html파일로 인식하기 때문에 css 코드가 정상적으로 동작하지 않게 된다.
		//res.setHeader("content-type", "text/html; charset=UTF-8");
>>>>>>> ae460feb3b60af0324e323b9deeecdeb908da7a5
		
		//res.setHeader("content-type", "text/html; charset=UTF-8");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

