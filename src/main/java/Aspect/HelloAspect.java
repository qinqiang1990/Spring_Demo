package Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class HelloAspect {

	@Pointcut("execution(* Aspect.Hello.main(int))")
	public void cutController() {
	}

	@Around("cutController()")
	public Object record(ProceedingJoinPoint point,int i) throws Throwable {
		point.proceed();
		System.out.println("================================================================");
		System.out.println(i);
		System.out.println("================================================================");
		return point.proceed();
	}

}