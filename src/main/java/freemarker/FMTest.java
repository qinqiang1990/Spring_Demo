package freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.xml.sax.InputSource;

import freemarker.core.ParseException;
import freemarker.ext.dom.NodeModel;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FMTest {

	@Test
	public void testOutC() throws Exception {
		// 创建Freemarker配置实例
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(FMTest.class, "/templates");

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
		Template t1 = cfg.getTemplate("c.ftl");

		// 显示生成的数据
		Writer out = new OutputStreamWriter(System.out);
		t1.process(root, out);
		out.flush();

	}

	@Test
	public void testOut() throws Exception {
		// 创建Freemarker配置实例
		Configuration cfg = new Configuration();
		// cfg.setDirectoryForTemplateLoading(new
		// File("target/classes/templates"));
		cfg.setClassForTemplateLoading(FMTest.class, "/templates");

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
		Template t1 = cfg.getTemplate("a.ftl");

		// 显示生成的数据
		Writer out = new OutputStreamWriter(System.out);
		t1.process(root, out);
		out.flush();

	}

	@Test
	public void testFile() throws Exception {
		// 创建Freemarker配置实例
		Configuration cfg = new Configuration();
		// cfg.setDirectoryForTemplateLoading(new
		// File("target/classes/templates"));
		cfg.setClassForTemplateLoading(FMTest.class, "/templates");

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
		Template t1 = cfg.getTemplate("a.ftl");

		// 显示生成的数据
		FileWriter out = new FileWriter(FMTest.class.getResource(
				"/templates/a.txt").getPath());
		t1.process(root, out);
		out.flush();

	}

	@Test
	public void testXML() throws Exception {
		// 创建Freemarker配置实例
		Configuration cfg = new Configuration();
		// cfg.setDirectoryForTemplateLoading(new
		// File("target/classes/templates"));
		cfg.setClassForTemplateLoading(FMTest.class, "/templates");

		// 创建数据模型
		Map root = new HashMap();

		InputStream in = FMTest.class
				.getResourceAsStream("/templates/field.xml");
		// 下面是FreeMarker的输入接口，这里与上面的in流建立关系，
		// 以便读取ddd.xml文件
		InputSource ins = new org.xml.sax.InputSource(in);

		// 特别注意此时的doc,这个符号将是，FreeMarker模板中取数的根！！！！！
		// 这里读取xml文件，并处理成root对象
		root.put("doc", NodeModel.parse(ins));

		List<FieldEntity> ls=new ArrayList<FieldEntity>();
		// 获取方法的数组
		Method[] methodList =Student.class.getDeclaredMethods();
		for (Method m : methodList) {
			if(m.isAnnotationPresent(Field.class))
			{
				Field f=m.getAnnotation(Field.class);
				ls.add(new FieldEntity(f.id(),f.name()));
			}
			
		}
		root.put("ls", ls);

		// 加载模板文件
		Template t1 = cfg.getTemplate("xml.ftl");

		// 显示生成的数据
		Writer out = new OutputStreamWriter(System.out);
		t1.process(root, out);
		out.flush();

		// 显示生成的数据
		/*
		 * FileWriter out = new
		 * FileWriter(FMTest.class.getResource("/templates/a.txt").getPath());
		 * t1.process(root, out); out.flush();
		 */
	}

}