package com.cy.ssm.service;


import java.util.List;

import com.cy.ssm.beans.GradeAndFieldListBean;
import com.cy.ssm.beans.PthBmBean;
import com.cy.ssm.beans.StudentBean;
import com.cy.ssm.beans.UserBean;
import com.cy.ssm.beans.UserBmBean;

public interface ILoginService {

	public UserBean Login(String username,String password);
	
	public boolean registry(UserBmBean usbm);
	public boolean edit(UserBmBean usbm);
	
	public List<UserBmBean> query();
	
	public UserBmBean querybmdetail(UserBmBean ubb);

	public UserBmBean querypxdetail(String student_id);

	
	public int insertStudent(List<StudentBean> user);
	
	public List<GradeAndFieldListBean> queryCollege();

	
	public List<GradeAndFieldListBean> queryGrade();

	
	public List<GradeAndFieldListBean> queryField();

	public void arrangetrain();

	StudentBean check(StudentBean user);
	
	public int insertStudentBaoMing(List<UserBmBean> user);
	
	public String checkPthInTime(PthBmBean PthBmBean);

}
