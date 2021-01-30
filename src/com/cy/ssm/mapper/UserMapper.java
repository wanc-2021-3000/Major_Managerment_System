package com.cy.ssm.mapper;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cy.ssm.beans.GradeAndFieldListBean;
import com.cy.ssm.beans.PthBmBean;
import com.cy.ssm.beans.StudentBean;
import com.cy.ssm.beans.StudentBmTrainBean;
import com.cy.ssm.beans.UserBean;
import com.cy.ssm.beans.UserBmBean;


public interface UserMapper {
	
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Select("select * from t_user where username=#{un} and password=#{pw}")
    @Results({
		
		@Result(id=true,property="id",column="id",javaType=Integer.class),
		@Result(property="username",column="username",javaType=String.class),
		@Result(property="password",column="password",javaType=String.class),
		@Result(property="account",column="account",javaType=Double.class)
	})
	public UserBean login(@Param("un")String username,@Param("pw")String password);
	/**
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Insert("insert into t_user value (null,user.username,user.password,user.account)")
	@Options(useGeneratedKeys=true,keyProperty="user.id")
	public int insertUser(@Param("user")UserBean user) throws Exception;
	
	
	/**
	 * 
	 * @param user
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Update(" update t_user set username=#{u.username},password=#{u.password},account=#{u.account} where id=#{id}")
    public int updateUser (@Param("u")UserBean user,@Param("id")int id) throws Exception;
	
     /**
      * 
      * @param id
      * @return
      * @throws Exception
      */
	@Delete(" delete from t_user where id=#{id}  ")
    public int deleteUser(int id) throws Exception;
	
	
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
	
	@Select(" select * from t_user where id=#{id}")
	@Results({
		
		@Result(id=true,property="id",column="id",javaType=Integer.class),
		@Result(property="username",column="username",javaType=String.class),
		@Result(property="password",column="password",javaType=String.class),
		@Result(property="account",column="account",javaType=Double.class)
	})
    public UserBean selectUserById(int id) throws Exception;
     /**
      *
      * @return
      * @throws Exception
      */
	
	@Select(" select * from t_user")
	@ResultMap("userMap")
    public List<UserBean> selectAllUser() throws Exception;
    
    
    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
   public int batchInsertUser(@Param("users")List<UserBean> user) throws Exception;
   
   /**
    *
    * @param list
    * @return
    * @throws Exception
    */
   public int batchDeleteUser(@Param("list")List<Integer> list) throws Exception;
   
   
   /**
    *
    * @param parma
    * @return
    * @throws Exception
    */
   public List<UserBean> pagerUser(Map<String, Object> parmas) throws Exception;
   
   /**
    * 
    *
    * @param parma
    * @return
    * @throws Exception
    */
    public int countUser(Map<String, Object> parmas) throws Exception;
    
    @Insert("insert into qccsnow.jxkj_pth_table value (#{PthBm.pth_batch_id},#{PthBm.start_date},#{PthBm.start_time},#{PthBm.end_date},#{PthBm.endtime})")
  	public int SetPthTime(@Param("PthBm")PthBmBean PthBmBean) throws Exception;
  	
    @Update("UPDATE qccsnow.jxkj_pth_table t SET t.start_date=#{PthBm.start_date},t.start_time=#{PthBm.start_time},t.end_date=#{PthBm.end_date},t.end_time=#{PthBm.end_time} WHERE t.pth_batch_id=#{PthBm.pth_batch_id}")
  	public int UpdatePthTime(@Param("PthBm")PthBmBean PthBmBean) throws Exception;
  	
    @Delete("DELETE FROM qccsnow.jxkj_pth_table t where t.pth_batch_id=#{PthBm.pth_batch_id}")
  	public int DeletePthTime(@Param("PthBm")PthBmBean PthBmBean) throws Exception;
   
    @Select("SELECT * FROM qccsnow.jxkj_pth_table t where t.pth_batch_id=#{PthBm.pth_batch_id}")
  	public int SelectPthTime(@Param("PthBm")PthBmBean PthBmBean) throws Exception;
  	
    @Select("SELECT t.pth_batch_id FROM qccsnow.jxkj_pth_table t where t.start_date<=#{PthBm.start_date} and t.end_date>=#{PthBm.start_date}")
    @Results({		
		@Result(property="pth_batch_id",column="pth_batch_id",javaType=Integer.class),
	})
  	public String SelectPthInTime(@Param("PthBm")PthBmBean PthBmBean) throws Exception;
  	
    
     
    
    @Insert("insert into qccsnow.jxkj_user_registry value (#{user.student_name},#{user.id_no},#{user.sex},#{user.nation},#{user.grade},#{user.college},#{user.field},#{user.career},#{user.student_id},#{user.born_province},#{user.born_city},#{user.born_country},#{user.live_province},#{user.live_city},#{user.live_country},#{user.phoneno},#{user.certificate_amount},#{user.material_amount},#{user.book_amount},#{user.other_amount},#{user.post_code},#{user.address},#{user.birthday},#{user.test_degree},#{user.train})")
	public int RegistryUser(@Param("user")UserBmBean user) throws Exception;
	
    @Insert("update qccsnow.jxkj_user_registry t set t.sex=#{user.sex},t.address=#{user.address},t.grade=#{user.grade},t.field=#{user.field},t.college=#{user.college},t.nation=#{user.nation},t.career=#{user.career},t.train=#{user.train},t.phoneno=#{user.phoneno} where t.birthday=#{user.birthday} and t.id_no =#{user.id_no}")
	public int editUser(@Param("user")UserBmBean user) throws Exception;
    
	@Select("SELECT * FROM qccsnow.jxkj_user_registry t")
	@ResultMap("studentMap")
    public List<UserBmBean> selectBmList() throws Exception;
    
	@Select("SELECT * FROM qccsnow.jxkj_user_registry t where t.id_no=#{user.id_no_query} and t.birthday=#{user.birthday}")
	@ResultMap("studentMap")
    public UserBmBean selectBmDetail(@Param("user")UserBmBean user) throws Exception;
	
	@Select("SELECT * FROM qccsnow.jxkj_student_table t where t.id_no=#{id_no} and t.student_name=#{student_name} and t.student_id=#{student_id_no} ")
	@ResultMap("studentInfoMap")
    public StudentBean checkBmDetail(@Param("id_no")String id_no,@Param("student_id_no")String student_id_no,@Param("student_name")String student_name) throws Exception;
	
	
	public int batchInsertStudentTrain(@Param("users")List<StudentBmTrainBean> user) throws Exception;

	public int batchInsertStudentTest(@Param("users")List<StudentBmTrainBean> user) throws Exception;
	
	public int batchInsertStudentCert(@Param("users")List<StudentBmTrainBean> user) throws Exception;

	
	public int batchInsertStudentBaoMing(@Param("users")List<UserBmBean> user) throws Exception;

	
	public int batchInsertStudent(@Param("users")List<StudentBean> user) throws Exception;

	@Select("SELECT distinct college FROM qccsnow.jxkj_student_table")
	@ResultMap("collegeMap")
    public List<GradeAndFieldListBean> selectCollegeList() throws Exception;
	
	@Select("SELECT distinct field FROM qccsnow.jxkj_student_table")
	@ResultMap("fieldMap")
    public List<GradeAndFieldListBean> selectFieldList() throws Exception;
    
	
	@Select("SELECT distinct grade FROM qccsnow.jxkj_student_table")
	@ResultMap("gradeMap")
    public List<GradeAndFieldListBean> selectGradeList() throws Exception;
    
    
}
