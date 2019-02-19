/**
 * 
 */
package hong.bus.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Abbey
 *
 */
public class FilterConfig implements HandlerInterceptor {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
//		response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");
//        response.setHeader("XDomainRequestAllowed","1");
		  response.setContentType("textml;charset=UTF-8");
		  response.setHeader("Access-Control-Allow-Origin", "http://localhost:8844"  /*request.getHeader("Origin")*/ );
		  response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,OPTIONS, DELETE");
		  response.setHeader("Access-Control-Max-Age", "0");
		  response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		  response.setHeader("Access-Control-Allow-Credentials", "true");
		  response.setHeader("XDomainRequestAllowed","1");
		  System.out.println("-------------------------------interceptor-------------------------------------");
        
        return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
