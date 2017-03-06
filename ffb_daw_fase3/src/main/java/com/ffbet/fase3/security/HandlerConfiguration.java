/**
 * 
 */
package com.ffbet.fase3.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Marco
 *
 */
@Configuration
public class HandlerConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
		
	}

}


class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		if (modelAndView != null && isDefinitiveUrl(request)){
			modelAndView.addObject("token", token.getToken());
		}
			
	}
	
	public Boolean isDefinitiveUrl(HttpServletRequest request) {
		// URL request from browser
		String requestedUri = request.getRequestURI();
		// IsCorrect if the last char of requestedUri isn't a "/"
		boolean isCorrect = !(requestedUri.lastIndexOf("/") == requestedUri.length() - 1);
		

		return isCorrect;

	}
	
	
}

