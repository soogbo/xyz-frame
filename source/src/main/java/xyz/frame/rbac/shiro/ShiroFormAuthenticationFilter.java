package xyz.frame.rbac.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * ShiroFormAuthenticationFilter
 * 
 * @author shisp
 * @date 2018-7-27 15:26:39
 */
public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (this.isLoginRequest(request, response)) {
			if (this.isLoginSubmission(request, response)) {
				return this.executeLogin(request, response);
			} else {
				return true;
			}
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("{\"code\" : -100 ,\"message\":\"未登录\"}");
			out.flush();
			out.close();
			return false;
		}
	}

}
