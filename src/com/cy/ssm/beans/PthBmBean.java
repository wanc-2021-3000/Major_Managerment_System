package com.cy.ssm.beans;

import java.io.Serializable;

public class PthBmBean implements Serializable {
	
	private static final long serialVersionUID = -2682305557892210329L;
	
	private String pth_batch_id;
	private String start_date;
	private String start_time;
	private String end_date;
	private String end_time;
	/**
	 * @return the pth_batch_id
	 */
	public String getPth_batch_id() {
		return pth_batch_id;
	}
	/**
	 * @param pth_batch_id the pth_batch_id to set
	 */
	public void setPth_batch_id(String pth_batch_id) {
		this.pth_batch_id = pth_batch_id;
	}
	/**
	 * @return the start_date
	 */
	public String getStart_date() {
		return start_date;
	}
	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	/**
	 * @return the start_time
	 */
	public String getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	/**
	 * @return the end_date
	 */
	public String getEnd_date() {
		return end_date;
	}
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	/**
	 * @return the end_time
	 */
	public String getEnd_time() {
		return end_time;
	}
	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PthBmBean(String pth_batch_id, String start_date, String start_time, String end_date, String end_time) {
		super();

		
		this.pth_batch_id = pth_batch_id;//普通话批号
		this.start_date = start_date;//开始日期
		this.start_time = start_time;//开始时间
		this.end_date = end_date;//结束日期
		this.end_time = end_time;//结束时间
		
		
	}
}
