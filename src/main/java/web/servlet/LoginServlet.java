package web.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import freemarker.Address;
import freemarker.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	// 负责管理FreeMarker模板的Configuration实例
	private Configuration cfg = null;

	public void init() throws ServletException {
		// 创建一个FreeMarker实例
		cfg = new Configuration();
		// 指定FreeMarker模板文件的位置
		cfg.setServletContextForTemplateLoading(getServletContext(),
				"/WEB-INF/templates");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 创建数据模型
		Map root = new HashMap();
		root.put("user", "老高");

		// javabean可以自动转化！类似于我们前面学习的EL表达式！
		User u = new User();
		u.setUname("老马");
		root.put("user1", u);
		User u2 = new User("老张", new Address("中国", "北京"));
		root.put("user2", u2);

		// map/list容器
		List list = new ArrayList();
		list.add(new Address("中国", "北京"));
		list.add(new Address("中国", "上海"));
		list.add(new Address("美国", "纽约"));
		root.put("lst", list);

		// 普通标量??
		root.put("num0", 18);
		root.put("b2", true);
		root.put("date1", new Date());
		root.put("random", new Random().nextInt(100));
		root.put("htm2", "<b>粗体</b>");

		// 加载模板文件
		Template t = cfg.getTemplate("a.ftl");

		resp.setContentType("text/html; charset=" + t.getEncoding());
		Writer out = resp.getWriter();
		// 合并数据模型和模板，并将结果输出到out中
		try {
			t.process(root, out); // 往模板里写数据
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		// req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String error = null;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
		} catch (AuthenticationException e) {
			// 其他错误，比如锁定，如果想单独处理请单独catch处理
			error = "其他错误：" + e.getMessage();
		}

		if (error != null) {// 出错了，返回登录页面
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,
					resp);
		} else {// 登录成功
			req.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(
					req, resp);
		}
	}
}
