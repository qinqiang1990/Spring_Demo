package spring.mvc;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringmvcUtils {
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}
}
