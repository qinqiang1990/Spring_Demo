package chapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

public class ConverterDemo {

	// 设置属性
	@Test
	public void test1() throws Exception {
		Person p = new Person();
		BeanUtils.setProperty(p, "name", "xiazdong");
		BeanUtils.setProperty(p, "age", 20);
		System.out.println(p.toString());
	}

	// 自定义转换器
	@Test
	public void test2() throws Exception {
		Person p = new Person();
		ConvertUtils.register(new Converter() {

			public Object convert(Class type, Object value) {
				// TODO Auto-generated method stub
				if (value == null) {
					return null;
				}
				if (!(value instanceof String)) {
					throw new ConversionException("conversion error");
				}
				String str = (String) value;
				if (str.trim().equals("")) {
					return null;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sdf.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

			}

		}, Date.class);
		BeanUtils.setProperty(p, "birthday", "2011-10-10");
		System.out.println(p);
	}

	// 使用内置的转换器
	@Test
	public void test3() throws Exception {
		Person p = new Person();
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		BeanUtils.setProperty(p, "birthday", "2011-10-10");
		System.out.println(p);
	}

	// 使用内置的转换器
	@Test
	public void test4() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xiazdong");
		map.put("age", "20");
		map.put("birthday", "2010-10-10");
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		Person p = new Person();
		BeanUtils.populate(p, map);
		System.out.println(p);
	}
}
