package com.cy.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.ssm.mapper.UserMapper;
import com.cy.ssm.beans.GradeAndFieldListBean;
import com.cy.ssm.beans.PthBmBean;
import com.cy.ssm.beans.StudentBean;
import com.cy.ssm.beans.UserBean;
import com.cy.ssm.beans.UserBmBean;
import com.cy.ssm.service.ILoginService;
@Service
public class LoginServiceImpl implements ILoginService{
	
	@Resource
	private UserMapper um;


	@Override
	public UserBean Login(String username, String password) {
		return um.login(username, password);
	}
	
	@Override
	public boolean registry(UserBmBean user) {
		 try {
			um.RegistryUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return true;
	}
	@Override
	public boolean edit(UserBmBean user) {
		 try {
			um.editUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return true;
	}
	@Override
	public StudentBean check(StudentBean user) {
		 try {
			return um.checkBmDetail(user.getId_no(),user.getStudent_id(),user.getStudent_name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return null;
		}
	}
	@Override
	public List<UserBmBean> query() {
		// TODO Auto-generated method stub
		
		try {
			return um.selectBmList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public int insertStudent(List<StudentBean> user) {
		
		try {
			return um.batchInsertStudent(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<GradeAndFieldListBean> queryCollege() {
		// TODO Auto-generated method stub
		try {
			return um.selectCollegeList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GradeAndFieldListBean> queryGrade() {
		// TODO Auto-generated method stub
		try {
			return um.selectGradeList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<GradeAndFieldListBean> queryField() {
		// TODO Auto-generated method stub
		try {
			return um.selectFieldList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void arrangetrain() {
		// TODO Auto-generated method stub
		//安排培训教室和人员
		//目前有的数据 --学生的人数，学生期望的培训位置 0 不培训 1红谷滩新校区 2老校区
		//教室的信息 红谷滩的教室明细，老校区的教室明细，以及每个教室可以容纳的人数。
		//排教室的时候将同一个班级的学生排一起。
		//培训以天为单位，每天的教室都可以复用。
		
		//以教室作为基准来分配学生，不够的话提示。先做个总体判断总数，如果总数比对不上的话则直接提示报错。
		//教室多没关系，学生多就要提示。
		
		//学生以map来存，更新map的classroomid和time，表示学生在哪天培训。学生分校区来取
		
		//教室结构人数list<map>来存放，map存放id和人数。
		//天数直接从数据库中取出，进行循环。
		
		
		
		//写下流程
		//1.根据校区区分，红谷滩校区和老校区，取对应的学生集合。
		
		//2.根据校区，取教室安排的天数集合。
		
		//3.根据每个校区的天数集合，取个汇总可容纳总人数，和每个校区总人数比较。多则无妨，少则中断提示。
		
		//4.循环天数，取每一天每个教室可容纳人数，记录数组。
		
		//5.根据教室数量循环，学生数减去对应教室人数，然后分别更新数据库学生培训教室情况，直到今天的教室数耗尽位置。
	}

	@Override
	public UserBmBean querybmdetail(UserBmBean ubb) {
		// TODO Auto-generated method stub
		try {
			return um.selectBmDetail(ubb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public UserBmBean querypxdetail(String student_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertStudentBaoMing(List<UserBmBean> user) {
		// TODO Auto-generated method stub

		try {
			return um.batchInsertStudentBaoMing(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	}

	@Override
	public String checkPthInTime(PthBmBean PthBmBean) {
		// TODO Auto-generated method stub
		try {
			return um.SelectPthInTime(PthBmBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
