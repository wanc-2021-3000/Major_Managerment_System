package com.cy.ssm.beans;

import java.io.Serializable;

public class StudentBmTrainBean implements Serializable {

	
	private static final long serialVersionUID = -2682305557890221059L;
	private String student_id;
	private String student_name;
	private String id_no;
	private String train_place;
	private String train_time;
	private String cert_place;
	private String cert_time;
	/**
	 * @return the cert_place
	 */
	public String getCert_place() {
		return cert_place;
	}
	/**
	 * @param cert_place the cert_place to set
	 */
	public void setCert_place(String cert_place) {
		this.cert_place = cert_place;
	}
	/**
	 * @return the cert_time
	 */
	public String getCert_time() {
		return cert_time;
	}
	/**
	 * @param cert_time the cert_time to set
	 */
	public void setCert_time(String cert_time) {
		this.cert_time = cert_time;
	}
	/**
	 * @return the train_place
	 */
	public String getTrain_place() {
		return train_place;
	}
	/**
	 * @param train_place the train_place to set
	 */
	public void setTrain_place(String train_place) {
		this.train_place = train_place;
	}
	/**
	 * @return the train_time
	 */
	public String getTrain_time() {
		return train_time;
	}
	/**
	 * @param train_time the train_time to set
	 */
	public void setTrain_time(String train_time) {
		this.train_time = train_time;
	}
	/**
	 * @return the test_place
	 */
	public String getTest_place() {
		return test_place;
	}
	/**
	 * @param test_place the test_place to set
	 */
	public void setTest_place(String test_place) {
		this.test_place = test_place;
	}
	/**
	 * @return the test_time
	 */
	public String getTest_time() {
		return test_time;
	}
	/**
	 * @param test_time the test_time to set
	 */
	public void setTest_time(String test_time) {
		this.test_time = test_time;
	}
	private String test_place;
	private String test_time;
	
	public StudentBmTrainBean() {
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
	public StudentBmTrainBean(String student_id, String student_name,String id_no,String train_place,String train_time,String test_place,String test_time,String cert_place,String cert_time) {
		super();
		this.student_id = student_id;//学号
		this.student_name = student_name;//学生姓名
		
		this.id_no = id_no;//证件号
		this.train_place=train_place;
		this.train_time=train_time;
		this.test_place=test_place;
		this.test_time=test_time;
		this.cert_place=cert_place;
		this.cert_time=cert_time;
		
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentBean [student_id=" + student_id + ", student_name=" + student_name + 
				", train_place=" + train_place + ", train_time=" + train_time + ", test_place=" + test_place + ", test_time=" + test_time+ ", cert_place=" + cert_place + ", cert_time=" + cert_time+"]";
	}
	
}

