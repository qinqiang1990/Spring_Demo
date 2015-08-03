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

@WebServlet(name = "FtlServlet", urlPatterns = "/ftl")
public class FtlServlet extends HttpServlet {

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
		root.put("id", "location");

		// map/list容器
		List list = new ArrayList();
		list.add("中国-北京");
		list.add("中国-上海");
		list.add("美国-纽约");
		root.put("data", list);

		// 加载模板文件
		Template t = cfg.getTemplate("select.ftl");

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

}
