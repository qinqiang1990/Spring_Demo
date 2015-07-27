package velocity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Test;

public class VelocityUtil {
	@Test
	public void renderClass() {
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			// 进行初始化操作
			ve.init();
			// 加载模板，设定模板编码
			Template t = ve.getTemplate("velocity/Hello.vm", "gbk");
			// 设置初始化数据
			VelocityContext context = new VelocityContext();
			context.put("name", "张三");
			context.put("project", "Jakarta");
			// 设置输出
			StringWriter writer = new StringWriter();
			// 将环境数据转化输出
			t.merge(context, writer);
			// 简化操作
			// ve.mergeTemplate("test/velocity/simple1.vm", "gbk", context,
			// writer );
			System.out.println(writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void renderString() throws ResourceNotFoundException,
			ParseErrorException, Exception {

		Properties p = new Properties();
		try {
			p.load(this.getClass().getResourceAsStream("/velocity.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Velocity.init(p);
		VelocityContext context = new VelocityContext();

		context.put("name", "王五");
		context.put("project", "Jakarta");

		Template template = Velocity.getTemplate("velocity/Hello.vm", "GBK");
		StringWriter writer = new StringWriter();
		template.merge(context, writer);

		PrintWriter filewriter = new PrintWriter(new FileOutputStream(
				"Hello.html"), true);

		filewriter.println(writer.toString());
		filewriter.close();

	}
}
