package com.jxkj.test;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cy.ssm.beans.UserBmBean;

public class ExcelImpl {

public void export(String[] titles,List<UserBmBean> u, ServletOutputStream out) throws Exception{
    try{
                     // 第一步，创建一个workbook，对应一个Excel文件
                     HSSFWorkbook workbook = new HSSFWorkbook();
                     
                     // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
                     HSSFSheet hssfSheet = workbook.createSheet("sheet1");
                     
                     // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
                     
                     HSSFRow row = hssfSheet.createRow(0);
                    // 第四步，创建单元格，并设置值表头 设置表头居中
                     HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
                     
                     //居中样式
                     hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
         
                     HSSFCell hssfCell = null;
                     for (int i = 0; i < titles.length; i++) {
                         hssfCell = row.createCell(i);//列索引从0开始
                         hssfCell.setCellValue(titles[i]);//列名1
                         hssfCell.setCellStyle(hssfCellStyle);//列居中显示                
                     }
                     
                   
                      //list.add(UserBmBean1);
                      //list.add(UserBmBean2);
                      //list.add(UserBmBean3);
                      //list.add(UserBmBean4);
             	
                         for (int i = 0; i < u.size(); i++) {
                             row = hssfSheet.createRow(i+1);                
                             UserBmBean UserBmBean = u.get(i);
                             
                             String str=UserBmBean.getStrList();
                             str=str.replace("null", " ");
                             String strlist[]=str.split("\\|");
                             int len=strlist.length;
                             int index=0;
                             int dataindex=0;
                             while(index<24)
                            	 
                             {
                            	 if(index==4)
                            	 {
                            		 row.createCell(index).setCellValue(strlist[dataindex]+strlist[dataindex+1]+strlist[dataindex+2]+strlist[20]);
                                	 index++;
                                	 dataindex=dataindex+3;
                                	 continue;
                            	 }
/*					if (index == 19) {
						row.createCell(index).setCellValue(strlist[dataindex]);
						row.createCell(index + 1).setCellValue(strlist[dataindex]);
						index++;
						dataindex++;
						continue;
					}*/          if(index==23)
					             {//在最后一个循环的时候判断一下len的值，好确定最后一个值是否为空
						             if(len==25)//如果为空
						             {
		                            	 row.createCell(index).setCellValue("");
		                            	 index++;
		                            	 dataindex++;
		                            	 continue;
						             }
						
					             }
                            	 
                            	 row.createCell(index).setCellValue(strlist[dataindex]);
                            	 index++;
                            	 dataindex++;
                             }
                             // 第六步，创建单元格，并设置值
                            /* String  id = null;
                             if(UserBmBean.getStudent_id() != null){
                                     id = UserBmBean.getStudent_id();
                             }
                            row.createCell(0).setCellValue(id);
                             String name = "";
                             if(UserBmBean.getStudent_username() != null){
                                 name = UserBmBean.getStudent_username();
                             }
                            row.createCell(1).setCellValue(name);
                             String password = "";
                             if(UserBmBean.getId_no() != null){
                                 password = UserBmBean.getId_no();
                             }
                             row.createCell(2).setCellValue(password);
                             String age=null;
                             if(UserBmBean.getCollege() !=null){
                                 age = UserBmBean.getCollege();
                             }
                             row.createCell(3).setCellValue(age);*/
                         }
    
                     // 第七步，将文件输出到客户端浏览器
                     try {
                         workbook.write(out);
                         out.flush();
                        out.close();
         
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }catch(Exception e){
                     e.printStackTrace();
                    throw new Exception("导出信息失败！");
                    
                    }
                 }   
 private final static String excel2003L =".xls";    //2003- 版本的excel  
private final static String excel2007U =".xlsx";   //2007+ 版本的excel  

/** 
 * 描述：获取IO流中的数据，组装成List<List<Object>>对象 
 * @param in,fileName 
 * @return 
 * @throws IOException  
 */  
public  List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception{  
    List<List<Object>> list = null;  
      
    //创建Excel工作薄  
    Workbook work = this.getWorkbook(in,fileName);  
    if(null == work){  
        throw new Exception("创建Excel工作薄为空！");  
    }  
    Sheet sheet = null;  
    Row row = null;  
    Cell cell = null;  
      
    list = new ArrayList<List<Object>>();  
    //遍历Excel中所有的sheet  
    for (int i = 0; i < work.getNumberOfSheets(); i++) {  
        sheet = work.getSheetAt(i);  
        if(sheet==null){continue;}  
          
        //遍历当前sheet中的所有行  
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {  
            row = sheet.getRow(j);  
            if(row==null||row.getFirstCellNum()==j){continue;}  
              
            //遍历所有的列  
            List<Object> li = new ArrayList<Object>();  
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
                cell = row.getCell(y); 
                //如果cell里没有值，则插入“”
                if(cell==null)
                {
                	li.add("");
                }else
                {
                    li.add(this.getCellValue(cell));  

                }
            }  
            list.add(li);  
        }  
    } 
    in.close();  
    return list;  
}  
  
/** 
 * 描述：根据文件后缀，自适应上传文件的版本  
 * @param inStr,fileName 
 * @return 
 * @throws Exception 
 */  
public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
    Workbook wb = null;  
    String fileType = fileName.substring(fileName.lastIndexOf("."));  
    if(excel2003L.equals(fileType)){  
        wb = new HSSFWorkbook(inStr);  //2003-  
    }else if(excel2007U.equals(fileType)){  
        wb = new XSSFWorkbook(inStr);  //2007+  
    }else{  
        throw new Exception("解析的文件格式有误！");  
    }  
    return wb;  
}  

/** 
 * 描述：对表格中数值进行格式化 
 * @param cell 
 * @return 
 */  
public  Object getCellValue(Cell cell){  
    Object value = null;  
    DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
    DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
      
    switch (cell.getCellTypeEnum()) {  
    case STRING:  
        value = cell.getRichStringCellValue().getString();  
        break;  
    case NUMERIC:  
        if("General".equals(cell.getCellStyle().getDataFormatString())){  
            value = df.format(cell.getNumericCellValue());  
        }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
            value = sdf.format(cell.getDateCellValue());  
        }else{  
            value = df2.format(cell.getNumericCellValue());  
        }  
        break;  
    case BOOLEAN:  
        value = cell.getBooleanCellValue();  
        break;  
    case FORMULA:  
    	value = cell.getCellFormula();
        break;  
    default:  
        break;  
    }  
    return value;  
}   
}
