package com.cy.ssm.beans;

public class UserBmBean {
	
	private static final long serialVersionUID = -2682305557890221059L;
	private String sex;
	private String nation;
	private String career;//是否师范生
	private String student_id;//学号
	private String born_province;//用来判断是报名还是修改
	private String born_country;
	private String born_city;
	private String live_province;
	private String live_city;
	private String live_country;
	private String phoneno;
	private String certificate_amount;
	private String material_amount;
	private String book_amount;
	private String other_amount;
	private  String post_code;//存了流水号
	private String address;
	private String birthday;//存了报名批次
	private String test_degree;
	private String student_name;//用户名
	private String id_no;//证件号
	private String grade;
	private String college;
	private String field;
	private String train;
	private String id_no_query;
 
	/**
	 * @return the student_name
	 */
	public String getStudent_name() {
		return student_name;
	}
	/**
	 * @param student_name the student_name to set
	 */
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	/**
	 * @return the id_no_query
	 */
	public String getId_no_query() {
		return id_no_query;
	}
	/**
	 * @param id_no_query the id_no_query to set
	 */
	public void setId_no_query(String id_no_query) {
		this.id_no_query = id_no_query;
	}
	/**
	 * @param born_city the born_city to set
	 */
	public void setBorn_city(String born_city) {
		this.born_city = born_city;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getBorn_province() {
		return born_province;
	}
	public void setBorn_province(String born_province) {
		this.born_province = born_province;
	}
	public String getBorn_country() {
		return born_country;
	}
	public void setBorn_country(String born_country) {
		this.born_country = born_country;
	}
	public String getBorn_city() {
		return born_city;
	}
	public void setBory_city(String bory_city) {
		this.born_city = bory_city;
	}
	public String getLive_province() {
		return live_province;
	}
	public void setLive_province(String live_province) {
		this.live_province = live_province;
	}
	public String getLive_city() {
		return live_city;
	}
	public void setLive_city(String live_city) {
		this.live_city = live_city;
	}
	public String getLive_country() {
		return live_country;
	}
	public void setLive_country(String live_country) {
		this.live_country = live_country;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getCertificate_amount() {
		return certificate_amount;
	}
	public void setCertificate_amount(String certificate_amount) {
		this.certificate_amount = certificate_amount;
	}
	public String getMaterial_amount() {
		return material_amount;
	}
	public void setMaterial_amount(String material_amount) {
		this.material_amount = material_amount;
	}
	public String getBook_amount() {
		return book_amount;
	}
	public void setBook_amount(String book_amount) {
		this.book_amount = book_amount;
	}
	public String getOther_amount() {
		return other_amount;
	}
	public void setOther_amount(String other_amount) {
		this.other_amount = other_amount;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTest_degree() {
		return test_degree;
	}
	public void setTest_degree(String test_degree) {
		this.test_degree = test_degree;
	}
	public String getStudent_username() {
		return student_name;
	}
	public void setStudent_username(String student_username) {
		this.student_name = student_username;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getTrain() {
		return train;
	}
	public void setTrain(String train) {
		this.train = train;
	}
	
	
	public UserBmBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserBmBean(String sex, String nation, String career, String student_id, String born_province,
			String born_country, String born_city, String live_province, String live_city, String live_country,
			String phoneno, String certificate_amount, String material_amount, String book_amount, String other_amount,
			String post_code, String address, String birthday, String test_degree, String student_username,
			String id_no, String grade, String college, String field, String train,String id_no_query) {
		super();
		this.sex = sex;
		this.nation = nation;
		this.career = career;
		this.student_id = student_id;
		this.born_province = born_province;
		this.born_country = born_country;
		this.born_city = born_city;
		this.live_province = live_province;
		this.live_city = live_city;
		this.live_country = live_country;
		this.phoneno = phoneno;
		this.certificate_amount = certificate_amount;
		this.material_amount = material_amount;
		this.book_amount = book_amount;
		this.other_amount = other_amount;
		this.post_code = post_code;
		this.address = address;
		this.birthday = birthday;
		this.test_degree = test_degree;
		this.student_name = student_username;
		this.id_no = id_no;
		this.grade = grade;
		this.college = college;
		this.field = field;
		this.train = train;
		this.id_no_query=id_no_query;
	}
	@Override
	public String toString() {
		return "UserBmBean [sex=" + sex + ", nation=" + nation + ", career=" + career + ", student_id=" + student_id
				+ ", born_province=" + born_province + ", born_country=" + born_country + ", bory_city=" + born_city
				+ ", live_province=" + live_province + ", live_city=" + live_city + ", live_country=" + live_country
				+ ", phoneno=" + phoneno + ", certificate_amount=" + certificate_amount + ", material_amount="
				+ material_amount + ", book_amount=" + book_amount + ", other_amount=" + other_amount + ", post_code="
				+ post_code + ", address=" + address + ", birthday=" + birthday + ", test_degree=" + test_degree
				+ ", student_username=" + student_name + ", id_no=" + id_no + ", grade=" + grade + ", college="
				+ college + ", field=" + field + ", train=" + train + ", id_no_query=" + id_no_query+"]";
	}
	public String getStrList() {
		
		return  student_name + "|" +   id_no + "|" +sex + "|" + nation + "|"+ grade + "|"
				+ college + "|" + field + "|"  + career + "|" + born_province + "|" + born_city
				+ "|"+ born_country + "|"  + live_province + "|" + live_country
				+ "|"  + live_city + "|"+ phoneno + "|" + certificate_amount + "|"
				+ material_amount + "|" + book_amount + "|" + other_amount + "|"
				+ post_code + "|" + address + "|" + birthday + "|"+ student_id
				+ "|"  +college+"|"+ test_degree+"|"+train+"|";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
