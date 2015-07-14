package chapter5;

import java.util.Date;

public class Person {
	private String name;
	private int age;
	private Date birthday;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "name = " + this.name + " age = " + this.age + " birthday = "
				+ this.birthday;
	}
}
