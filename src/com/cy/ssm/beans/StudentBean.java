package com.cy.ssm.beans;

import java.io.Serializable;

public class StudentBean implements Serializable {

	
	private static final long serialVersionUID = -2682305557890221059L;
	private String student_id;
	private String student_name;
	private String sex;
	private String college;
	private String field;
	private String classes;
	private String grade;
	private String id_no;
	private String nation;
	private String birthday;
	private String location;
	private String schooling;
	/**
	 * @return the nation
	 */
	public String getNation() {
		return nation;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the schooling
	 */
	public String getSchooling() {
		return schooling;
	}
	/**
	 * @param schooling the schooling to set
	 */
	public void setSchooling(String schooling) {
		this.schooling = schooling;
	}
	/**
	 * @param nation the nation to set
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	public StudentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the college
	 */
	public String getCollege() {
		return college;
	}
	/**
	 * @param college the college to set
	 */
	public void setCollege(String college) {
		this.college = college;
	}
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the classes
	 */
	public String getClasses() {
		return classes;
	}
	/**
	 * @param classes the classes to set
	 */
	public void setClasses(String classes) {
		this.classes = classes;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the id_no
	 */
	public String getId_no() {
		return id_no;
	}
	/**
	 * @param id_no the id_no to set
	 */
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public StudentBean(String student_id, String student_name, String sex, String college, String field, String classes,
			String grade, String id_no,String nation,String birthday, String location,String schooling) {
		super();
		this.student_id = student_id;//学号
		this.student_name = student_name;//学生姓名
		this.sex = sex;//性别
		this.college = college;//学院
		this.field = field;//专业
		this.classes = classes;//班级
		this.grade = grade;//学级
		this.id_no = id_no;//证件号
		this.nation=nation;//民族
		this.birthday = birthday;//生日
		this.location = location;//所在校区
		this.schooling=schooling;//学制
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentBean [student_id=" + student_id + ", student_name=" + student_name + ", sex=" + sex
				+ ", college=" + college + ", field=" + field + ", classes=" + classes + ", grade=" + grade + ", id_no="
				+ id_no + ",nation="+nation+",birthday="+birthday+",location="+location+",schooling="+schooling+"]";
	}
	
}

