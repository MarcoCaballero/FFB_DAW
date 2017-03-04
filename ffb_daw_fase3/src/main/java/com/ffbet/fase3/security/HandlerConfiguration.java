/**
 * 
 */
package com.ffbet.fase3.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
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
		registry.addInterceptor(new ResourcesContextHandlerInterceptor());
		
	}

}

class ResourcesContextHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && isDefinitiveUrl(request)){
			modelAndView.addObject("resources", checkResourcesContext(request));
		
			
		}
		

	}

	/**
	 * 
	 * Checks the resources context and concatenates as many "../" as necessary
	 * to the end of this string.
	 * 
	 * @param request
	 * @return (String) The correct path to resources context, in static folder.
	 */
	public String checkResourcesContext(HttpServletRequest request) {
		// URL request from browser
		String requestedUri = request.getRequestURI();
		String[] words = requestedUri.split("/");
		int directoriesForward = words.length;
		String resources = "";

		for (int i = 1; i < directoriesForward; i++) {
			resources = resources.concat("../");

		}
		return resources;

	}
	
	/**
	 * Method {@linkplain check_url()} to get the correct template from similar
	 * URLs. Considering wrong path = "/page/" or /page/method/", a good path
	 * looks like "/page", "page/method"
	 * 
	 * @param request
	 * @param template_path
	 * @return (String) The correct path if wrong or 'template_path' if the URLs
	 *         is okey.
	 */
	public Boolean isDefinitiveUrl(HttpServletRequest request) {
		// URL request from browser
		String requestedUri = request.getRequestURI();
		// IsCorrect if the last char of requestedUri isn't a "/"
		boolean isCorrect = !(requestedUri.lastIndexOf("/") == requestedUri.length() - 1);
		

		return isCorrect;

	}
}
