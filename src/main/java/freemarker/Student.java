package freemarker;

public class Student {

	private String sex;
	private String nation;

	@Field(id = "sex", name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Field(id = "nation", name = "nation")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

}
