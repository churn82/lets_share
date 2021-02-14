package com.kh.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.ToAlertException;


@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {

    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//비로그인 사용자 권한 관리
		//Session의 user 프로퍼티 유무 여부로 판단
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String[] uriArr = httpRequest.getRequestURI().split("/");
		if(uriArr.length > 0) { //분기를 한번 잡아주자
			switch(uriArr[1]) {
			case "group" : 
				switch(uriArr[2]) {
				case "view" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}
				case "form" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}
				case "register" :
					if(session.getAttribute("user") == null) {
						request.setAttribute("msg", "그룹에 가입하려면 로그인을 하셔야 합니다.");
						request.setAttribute("url", "/member/login");
						request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
						.forward(request, response);
					}
				}
			case "member" :
				switch(uriArr[2]) {
				case "mypage" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}
				case "modify" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}
				}
			case "report" :
				switch(uriArr[2]) {
				case "detail" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}
				case "form" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}
				}
			}
			
			
			
				
		}
		chain.doFilter(request, response);
	}	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
