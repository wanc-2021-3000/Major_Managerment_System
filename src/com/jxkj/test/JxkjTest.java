package com.jxkj.test;

import java.util.ArrayList;
import java.util.HashMap;

public class JxkjTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       String str="16材料化学1班(分析质检)";
       
       int start=str.lastIndexOf("班");
       String str1=str.substring(start-1,start+1);
       System.out.println(str1);
       
  
		//获取学院和学号
		ArrayList<HashMap<String, String>> a = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<5000;i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			if(i<1000) {

				map.put("sid", "1000"+i);
				map.put("class","tiyu");
				
				
			}
			if(i>1000&&i<2000) {

				map.put("sid", "2000"+i);
				map.put("class","wuli");
				//map.put("room","");

			}
			if(i>2000&&i<3000) {

				map.put("sid", "3000"+i);
				map.put("class","shuxue");
				//map.put("room","");

			}
			if(i>3000&&i<4000) {

				map.put("sid", "4000"+i);
				map.put("class","meishu");
				//map.put("room","");

			}
			if(i>4000) {

				map.put("sid", "4000"+i);
				map.put("class","wenxue");
				//map.put("room","");

			}
			map.put("room","");
			map.put("time","");
		}
		
		//获取教室、时间和教室可容纳人数
		//firstday
		
		
		//second
		
		
		//third
	}

}
