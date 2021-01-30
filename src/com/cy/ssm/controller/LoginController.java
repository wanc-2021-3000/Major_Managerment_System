package com.cy.ssm.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.ssm.beans.GradeAndFieldListBean;
import com.cy.ssm.beans.PthBmBean;
import com.cy.ssm.beans.StudentBean;
import com.cy.ssm.beans.UserBean;
import com.cy.ssm.beans.UserBmBean;
import com.cy.ssm.service.ILoginService;
import com.jxkj.test.ExcelImpl;



@Controller
public class LoginController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ILoginService loginServiceImpl;
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req,UserBean user){
		log.info(user);
		
		ModelAndView mv = new ModelAndView();
		UserBean u=loginServiceImpl.Login(user.getUsername(), user.getPassword());
	
		if(u != null){
					
			req.getSession().setAttribute("user", u);
			mv.addObject("password", u.getPassword());
			log.info(u.getPassword());
		}
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping("/jxkjsfdx/loginCheck")
	public ModelAndView loginCheck(HttpServletRequest req){
		//log.info(user);
		String pth_batch_id="";
		ModelAndView mv = new ModelAndView();
		
        //先判断一下是否有session，有的话直接进入即可。

		pth_batch_id= (String)req.getSession().getAttribute("pth_batch_id");
		if(pth_batch_id!=null)
		{//有session的话直接跳转
			log.info("id with session is"+pth_batch_id);
			mv.setViewName("jxkjsfdx/manager2");
			return mv;
		}
//判断一下本次报名是否在报名时间内。
		  SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
          System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
          PthBmBean p = new PthBmBean("",df.format(new Date()),"","","");
          pth_batch_id= loginServiceImpl.checkPthInTime(p);
          log.info("pth_id"+pth_batch_id);
       
         
		if(pth_batch_id != null){
			//在的话，在session里加一下本批次的id，用来提交时使用。		
			req.getSession().setAttribute("pth_batch_id", pth_batch_id);
			//mv.addObject("password", u.getPassword());
			log.info("pth_batch_id:"+pth_batch_id);
			mv.setViewName("jxkjsfdx/manager2");
		}
		else
		{//如果没有，则进入通知没有到时间画面。
			mv.setViewName("jxkjsfdx/test");
		}
		
		return mv;
	}
	
	
	@RequestMapping("/check")
	public void check(HttpServletRequest req,HttpServletResponse resp,StudentBean userbm){
		//log.info(user);
		
		//ModelAndView mv = new ModelAndView();
		/*List<UserBmBean> u=loginServiceImpl.query();
		for(int i = 0 ; i < u.size() ; i++) {
			  log.info(((UserBmBean)u.get(i)).toString());
			}*/
		JSONObject object = new JSONObject();

		log.info(userbm.toString());
		//对userbean的数据进行过滤
		StudentBean u=loginServiceImpl.check(userbm);
		if(u==null)		
		{
			//String rest="{\"retcode\":\"0001\"}";
	        object.put("retcode", "0001");

			//表示没查到			
		}else
		{
			//String rest="{\"retcode\":\"0000\"}";

			log.info(u.toString());
			
	        object.put("retcode", "0000");

	        object.put("college", u.getCollege());
	        object.put("grade", u.getGrade());
	        object.put("field", u.getField());
	        object.put("classes", u.getClasses());

	        object.put("student_username", u.getStudent_name());
	        object.put("student_id", u.getStudent_id());
	        
	        object.put("id_no", u.getId_no());
	      
	        object.put("sex", u.getSex());
	        object.put("nation", u.getNation());

		   
			
		}
	       String newJsonString = object.toJSONString();

		 PrintWriter out = null;  

	       resp.setContentType("application/json; charset=utf-8");  
		    resp.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
		    try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    finally {
		    	  out.print(newJsonString);  
		  	    out.flush();  
		  	    out.close(); 
		    }
	}
	
	@RequestMapping("/registry")
	public void baoming(HttpServletRequest req,HttpServletResponse resp,UserBmBean userbm){
		//log.info(user);
		
		//ModelAndView mv = new ModelAndView();
		/*List<UserBmBean> u=loginServiceImpl.query();
		for(int i = 0 ; i < u.size() ; i++) {
			  log.info(((UserBmBean)u.get(i)).toString());
			}*/

		OutputStream out =null;
		try {
			 out=resp.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(userbm.toString());
		//在userbm里增加一个报名时间的内容
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		 String time = df.format(new Date());
		 log.info(time);
         userbm.setPost_code(time);
         String type=userbm.getBorn_province();
         if(type.equals("1")){//baoming
        	 if(loginServiceImpl.registry(userbm))		
     		{
     			String rest="{\"retcode\":\"0000\"}";

     			try {
     				out.write(rest.getBytes());
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
     			
     		}else
     		{
     			String rest="{\"retcode\":\"0001\"}";

     			try {
     			out.write(rest.getBytes());
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
     			
     		}
         }else
         {//edit
        	 if(loginServiceImpl.edit(userbm))		
     		{
     			String rest="{\"retcode\":\"0000\"}";

     			try {
     				out.write(rest.getBytes());
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
     			
     		}else
     		{
     			String rest="{\"retcode\":\"0001\"}";

     			try {
     			out.write(rest.getBytes());
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
     			
     		}
         }
		
	}
	@RequestMapping("/querybmdetail")
	public void querybmdetail(HttpServletRequest req,HttpServletResponse resp,UserBmBean userbm){
		//log.info(user);
		//Map map=req.getParameterMap();
		//log.info("i am here"+id_no_query);
		JSONObject object = new JSONObject();
		//String batchid="";

		//ModelAndView mv = new ModelAndView();
		log.info("i am here"+userbm.toString());
		//判断提交的id不能为空
		if(userbm.getId_no_query().equals(""))
		{
	        object.put("retcode", "0001");

		}else {
			
			UserBmBean u=loginServiceImpl.querybmdetail(userbm);
			log.info(u.toString());
			
	        object.put("retcode", "0000");

	        object.put("college", u.getCollege());
	        object.put("grade", u.getGrade());
	        object.put("field", u.getField());
	        
	        object.put("student_username", u.getStudent_username());
	        object.put("phoneno", u.getPhoneno());
	        object.put("student_id", u.getStudent_id());
	        
	        object.put("id_no", u.getId_no());
	        object.put("career", u.getCareer());//是否师范生
	        object.put("address", u.getAddress());//班级
	        
	        object.put("train", u.getTrain());
	        object.put("sex", u.getSex());
	        object.put("nation", u.getNation());

		}
	
       String newJsonString = object.toJSONString();
		
        //System.err.println(tmpJsonObject2.toJSONString());
        //System.err.println(tmpJsonObject3.toJSONString());

     //   tmpJsonObject4.addAll(tmpJsonObject2);
       // tmpJsonObject4.addAll(tmpJsonObject3);

        System.err.println(newJsonString);

        
	    PrintWriter out = null;  
	    resp.setContentType("application/json; charset=utf-8");  
	    resp.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
	    try {
			out = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    finally {
	    	  out.print(newJsonString);  
	  	    out.flush();  
	  	    out.close(); 
	    }
	   
		//for(int i = 0 ; i < u.size() ; i++) {
		//	  log.info(((UserBmBean)u.get(i)).toString());
		//	}
		//if(u != null){
					
		//	req.getSession().setAttribute("user", u);
		//	mv.addObject("password", u.getPassword());
		//	log.info(u.getPassword());
		//}
		//mv.setViewName("index");
		//return mv;
//		OutputStream out =null;
//		try {
//			 out=resp.getOutputStream();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		log.info(userbm.toString());
//		if(loginServiceImpl.registry(userbm))		
//		{
//			String rest="{\"retcode\":\"0000\"}";
//
//			try {
//				out.write(rest.getBytes());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}else
//		{
//			String rest="{\"retcode\":\"0001\"}";
//
//			try {
//			out.write(rest.getBytes());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			
//		}
	}
	@RequestMapping("/querybm")
	public void querybm(HttpServletRequest req,HttpServletResponse resp,UserBmBean userbm){
		//log.info(user);
		
		//ModelAndView mv = new ModelAndView();
		List<UserBmBean> u=loginServiceImpl.query();
		for(int i = 0 ; i < u.size() ; i++) {
			  log.info(((UserBmBean)u.get(i)).toString());
			}
		//if(u != null){
					
		//	req.getSession().setAttribute("user", u);
		//	mv.addObject("password", u.getPassword());
		//	log.info(u.getPassword());
		//}
		//mv.setViewName("index");
		//return mv;
//		OutputStream out =null;
//		try {
//			 out=resp.getOutputStream();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		log.info(userbm.toString());
//		if(loginServiceImpl.registry(userbm))		
//		{
//			String rest="{\"retcode\":\"0000\"}";
//
//			try {
//				out.write(rest.getBytes());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}else
//		{
//			String rest="{\"retcode\":\"0001\"}";
//
//			try {
//			out.write(rest.getBytes());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			
//		}
	}
	
	@RequestMapping(value="/gotoAction",method=RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request){
       log.info("aaaa");
        if (!file.isEmpty()) {

            String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
            String servletPath = request.getServletPath();//"/gotoAction"
            String scheme = request.getScheme();//"http"
       

            String storePath= "/Users/QCC/Downloads";//存放我们上传的文件路径
          
            String fileName = file.getOriginalFilename();

            File filepath = new File(storePath, fileName);

            if (!filepath.getParentFile().exists()) {

                filepath.getParentFile().mkdirs();//如果目录不存在，创建目录

            }

            try {
                file.transferTo(new File(storePath+File.separator+fileName));//把文件写入目标文件地址

            } catch (Exception e) {

                e.printStackTrace();

                return "error";

            }

            return "success";//返回到成功页面

        }else {

            return "error";//返回到失败的页面
        }

    }
	@RequestMapping(value="/downExcel",method=RequestMethod.GET)
	//获取url链接上的参数
	public @ResponseBody String down(HttpServletResponse response,@RequestParam("id") String id,@RequestParam(value="name",required = false)  String name){
	              try{
	                  //用id来做密码校验
	            	  //密码写死在后台了，写复杂一点。
	            	  String password="WcWc2301!@$56789qqq";
	            	  log.info("my id is "+id);
	            	  log.info("my name is "+name);
	            	  if(id.equals(null))
	            	  {
	            		  return "password must input";
	            	  }
	            	  
	            	  if(!id.equals(password))
	            	  {
	            		  return "password error";
	            	  }
	                  try {
	                      //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
	                      response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name+".xls", "UTF-8"));
	             	      response.setContentType("application/octet-stream;charset=UTF-8");

	                  } catch (UnsupportedEncodingException e1) {
	                      e1.printStackTrace();
	                  }
	                  ServletOutputStream out=response.getOutputStream();
	                  ExcelImpl excleImpl = new ExcelImpl();
	                  String[] titles = { "姓名", "身份证", "性别", "民族" , "工作单位", "职业", "出生所在省", "出生所在城市", "出生所在县(区)", "现居住省", "现居住城市", "现居住县(区)" , "电话", "证书费(元)", "资料费(元)", "教材费(元)","其他费(元)", "邮编", "地址" , "出生年月","考生学号", "所在院系","报考级别","培训地点"}; 
	                  excleImpl.export(titles,loginServiceImpl.query(), out); 
	                 // out.close();
	                  return "success";
	              } catch(Exception e){
	                  e.printStackTrace();
	                  return "导出信息失败";
	              }
	          }
	
	@RequestMapping(value="/uploadExcel",method=RequestMethod.POST)
    public void uploadExcel(@RequestParam("file") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response){

	    InputStream in =null;  
	    List<List<Object>> listob = null;  
	    //MultipartFile file = multipartRequest.getFile("upfile");  
	    if(file.isEmpty()){  
	        try {
				throw new Exception("文件不存在！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }  
	      
	    try {
			in = file.getInputStream();
		    listob = new ExcelImpl().getBankListByExcel(in,file.getOriginalFilename());  

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    List<StudentBean> list = new ArrayList<StudentBean>();  
	  //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出  
	    for (int i = 0; i < listob.size(); i++) {  
	        List<Object> lo = listob.get(i); 
	        String str=(String)lo.get(7);
	        
	        int start=str.lastIndexOf("班");
	        String str1=str.substring(start-1,start+1);
	        StudentBean student = new StudentBean((String)lo.get(0),(String)lo.get(1),(String)lo.get(2),(String)lo.get(5),(String)lo.get(6),str1,(String)lo.get(10),(String)lo.get(9),(String)lo.get(4),(String)lo.get(3),(String)lo.get(11),(String)lo.get(8));
             list.add(student);
	        //log.info("打印信息-->"+family.toString());  
	    }  
	    
        loginServiceImpl.insertStudent(list);

	    PrintWriter out = null;  
	    response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
	    try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    out.print("文件导入成功！");  
	    out.flush();  
	    out.close();  

    }

	@RequestMapping(value="/uploadBaoMing",method=RequestMethod.POST)
    public void uploadBaoMing(@RequestParam("file") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response){

	    InputStream in =null;  
	    List<List<Object>> listob = null;  
	    //MultipartFile file = multipartRequest.getFile("upfile");  
	    if(file.isEmpty()){  
	        try {
				throw new Exception("文件不存在！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }  
	      
	    try {
			in = file.getInputStream();
		    listob = new ExcelImpl().getBankListByExcel(in,file.getOriginalFilename());  

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    List<UserBmBean> list = new ArrayList<UserBmBean>();  
	  //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出  
	    for (int i = 0; i < listob.size(); i++) {  
	        List<Object> lo = listob.get(i); 
			                                                  //2,3,7,22,8,9,10,11,12,13,14,15,16,17,18,19,20,21,24,0,1,4,5,6,26,""

	        UserBmBean student = new UserBmBean((String)lo.get(2),(String)lo.get(3),(String)lo.get(8),(String)lo.get(24),(String)lo.get(9),(String)lo.get(10),(String)lo.get(11),(String)lo.get(12),(String)lo.get(13),(String)lo.get(14),(String)lo.get(15),(String)lo.get(16),(String)lo.get(17),(String)lo.get(18),(String)lo.get(19),(String)lo.get(20),(String)lo.get(7),(String)lo.get(22),(String)lo.get(24),(String)lo.get(0),(String)lo.get(1),(String)lo.get(4),(String)lo.get(5),(String)lo.get(6),(String)lo.get(27),"");
             list.add(student);
	        //log.info("打印信息-->"+family.toString());  
	    }  
	    
        loginServiceImpl.insertStudentBaoMing(list);

	    PrintWriter out = null;  
	    response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
	    try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    out.print("文件导入成功！");  
	    out.flush();  
	    out.close();  

    }
	@RequestMapping(value="/queryGradeList",method=RequestMethod.POST)
    public void queryGradeList(HttpServletRequest request,HttpServletResponse response){
//在这里增加对登陆用户是否在时间范围内的控制，在范围内返回一个patchid，没有的话则返回空，表示没到时间。
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
         System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
         PthBmBean p = new PthBmBean("",df.format(new Date()),"","","");
         String pth_batch_id= loginServiceImpl.checkPthInTime(p);
         log.info("pth_id"+pth_batch_id);
         JSONObject object = new JSONObject();
	    if(pth_batch_id!=null)
	    {
	    	List<GradeAndFieldListBean> college= loginServiceImpl.queryCollege();
			List<GradeAndFieldListBean> grade=loginServiceImpl.queryGrade();
			List<GradeAndFieldListBean> field= loginServiceImpl.queryField();
			
	        object.put("college", college);
	        object.put("grade", grade);
	        object.put("field", field);
	        object.put("batch_id", pth_batch_id);

	    }else
	    {
	    	 object.put("batch_id", "0001");//0001表示报名还没开始
	 		//ModelAndView mv = new ModelAndView();
	    	//mv.setViewName("jxkjsfdx/manager2");
			//return mv;
	    }
		
		

        
        String newJsonString = object.toJSONString();
		
        //System.err.println(tmpJsonObject2.toJSONString());
        //System.err.println(tmpJsonObject3.toJSONString());

     //   tmpJsonObject4.addAll(tmpJsonObject2);
       // tmpJsonObject4.addAll(tmpJsonObject3);

        log.info(newJsonString);

        
	    PrintWriter out = null;  
	    response.setContentType("application/json; charset=utf-8");  
	    response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
	    try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally {
	    	 out.print(newJsonString);  
	 	    out.flush();  
	 	    out.close();	
	    }
	     

    }
}
