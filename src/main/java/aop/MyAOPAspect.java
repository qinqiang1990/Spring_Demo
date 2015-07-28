package aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hfstu.framework.core.utils.spring.SpringContextHolder;
import com.hfstu.weixin.model.User;

@Aspect
@Component
public class MyAOPAspect {
	// AbstractAction的子类被@RequestMapping注解的方法
	//@Around("within(cn.org.sysu.cems.utils.superclass.AbstractAction+) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")

	/*
	 * @Pointcut("within(@org.springframework.stereotype.Controller *)") public
	 * void cutController() { }
	 * 
	 * @Around("cutController()") public Object recordSysLog(ProceedingJoinPoint
	 * point) throws Throwable { System.out
	 * .println("================================================================"
	 * ); return point.proceed(); }
	 * 
	 * @Pointcut(
	 * "execution(public * com.hfstu.weixin.service.impl.UserServiceImpl.getSafeLevel(..))"
	 * ) public void serviceAspect() { }
	 */

	/** 
	 * 前置通知 用于拦截Service层记录用户的操作 
	 * 
	 * @param joinPoint 切点 
	 */
	@After("serviceAspect()")
	public void doAfter(JoinPoint joinPoint) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		User user = (User) session.getAttribute("user");
		// 请求的IP
		String ip = request.getRemoteAddr();
		try {
			// *========控制台输出=========*//
			System.out.println("=====前置通知开始=====");
			System.out.println("请求方法:"
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));

			System.out.println("请求人:" + user.getUsername());
			System.out.println("请求IP:" + ip);

			checkSecurity(joinPoint);
			// *========数据库日志=========*//

			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志

		}
	}

	private void checkSecurity(JoinPoint j) {// 取得代理类传递的参数
		Object obj[] = j.getArgs();
		for (Object o : obj) {
			System.out.println(o);
		}
	}

	@Pointcut("@annotation(com.hfstu.weixin.zaop.MyAOP)")
	public void methodCachePointcut() {
	}

	@Around("methodCachePointcut()")
	public Object around(ProceedingJoinPoint point) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		try {
			String monthRemark = getMthodRemark(point);
			String monthName = point.getSignature().getName();
			String packages = point.getThis().getClass().getName();
			Object[] method_param = point.getArgs();

			System.out.println(packages + " " + monthName + " " + method_param
					+ " " + monthRemark);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar begin = Calendar.getInstance();
		System.out.println("begin:" + df.format(begin));

		Object object = null;
		try {
			object = point.proceed();
			// 业务
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();//回滚
			e.printStackTrace();
		}
		Calendar end = Calendar.getInstance();
		System.out.println("end:" + df.format(end));

		return object;
	}

	// 获取方法的中文备注
	public static String getMthodRemark(ProceedingJoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();

		Class targetClass = Class.forName(targetName);
		Method[] method = targetClass.getMethods();
		String aop = "";
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					MyAOP MyAOPCache = m.getAnnotation(MyAOP.class);
					if (MyAOPCache != null) {
						aop = MyAOPCache.remark();
					}
					break;
				}
			}
		}
		return aop;
	}

	// 	<aop:aspectj-autoproxy proxy-target-class="true" />
}