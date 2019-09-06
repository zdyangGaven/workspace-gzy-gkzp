package com.nsoft.gkzp.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.nsoft.gkzp.syscore.service.ServiceException ;

/**公共方法
 * 日期、时间相关操作
 * @author zdyang
 *
 */
public class DataFormat {

	/**
	 * 日期格式常量
	 */
	public static final int DT_YYYY_MM_DD 				= 1; //yyyy-MM-dd
	public static final int DT_YYYYMMDDHHMMSS 			= 2; //yyyyMMddHHmmss
	public static final int DT_YYYYMMDD_HHMMSS_SSSS  	= 5; //yyyy-MM-dd HH:mm:ss.S
	public static final int DT_YYYYMMDD 				= 6; //yyyyMMdd
	public static final int DT_YYYYMMDD_HHMMSS 			= 8; //yyyy-MM-dd HH:mm:ss
	public static final int DT_YYYYMMDD_HHMM 			= 9; //yyyy-MM-dd HH:mm
	
	/**
	 * 日期格式常量对应的字符串
	 * @param type
	 * @return
	 */
	public static String getDateConvertString(int type)
	{
		String strFormatString = "";
		switch(type)
		{
			case DataFormat.DT_YYYY_MM_DD:
				strFormatString = "yyyy-MM-dd";
				break;
			case DataFormat.DT_YYYYMMDDHHMMSS:
				strFormatString = "yyyyMMddHHmmss";
				break;
			case DataFormat.DT_YYYYMMDD_HHMMSS_SSSS:
				strFormatString = "yyyy-MM-dd HH:mm:ss.S";
				break;
			case DataFormat.DT_YYYYMMDD:
				strFormatString = "yyyyMMdd";
				break;
			case DataFormat.DT_YYYYMMDD_HHMMSS:
				strFormatString = "yyyy-MM-dd HH:mm:ss";
				break;
			case DataFormat.DT_YYYYMMDD_HHMM:
				strFormatString = "yyyy-MM-dd HH:mm";
				break;
			default:
				strFormatString = "yyyy-MM-dd";
				break;
		}
		return strFormatString;
	}
	
	/**
	 * 解析格式化的字符串，转化为数值，例如：12,345.00转化为12345
	 * 
	 * @param text
	 *            被格式化的数值
	 * @return
	 */
	public static double parseNumber(String text)
	{
		int index = text.indexOf ( "," ) ;
		String sbNumber = "" ;
		while (index != -1)
		{
			sbNumber += text.substring ( 0 , index ) ;
			text = text.substring ( index + 1 , text.length ( ) ) ;
			index = text.indexOf ( "," ) ;
		}
		sbNumber += text ;
		return Double.parseDouble ( sbNumber ) ;
	}
	
    /**
     * 格式化数字，例如：12345转化为12345
     * 
     * @param dValue
     *            被格式化的数值
     * @param iScale
     *            小数点后保留位数,不足补0
     * @return
     */
    public static String formatNumber(double dValue, int iScale)
    {
        if(Double.isNaN(dValue))
        {
            return "";
        }
//        if(dValue<0)
//        {
//        	dValue=0;
//        }
        DecimalFormat df = null;
        StringBuffer sPattern = new StringBuffer("##0");
        if(iScale > 0)
        {
            sPattern.append(".");
            for (int i = 0; i < iScale; i++)
            {
                sPattern.append("0");
            }
        }
        df = new DecimalFormat(sPattern.toString());
        return df.format(dValue);
    }	
    
    public static String formatAmount(double dValue){
        return formatNumber(dValue, 2);
    }
    
    public static String formatRate(double dValue){
        return formatNumber(dValue, 6);
    }
    
    public static String formatDate(Date date, int type) throws ServiceException
    {
		if(date == null){
			return "";
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern(DataFormat.getDateConvertString(type));
		return dateFormat.format(date);
    }	

	/**
	 * @throws ParseException 
	 * 功能：用于不同种格式的转换Timestamp
	 */
    public static Timestamp parseTimestamp(String timeStr, int type) throws ServiceException
	{
		Timestamp timestamp = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		
		try {
			if(timeStr == null || timeStr.equals("")){
				return null;
			}
			
			dateFormat.applyPattern(DataFormat.getDateConvertString(type));
			timestamp = new Timestamp(dateFormat.parse(timeStr).getTime());
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("转换Timestamp日期错误");
		}
		return timestamp;
	}
    
	/**
	 * @throws ParseException 
	 * 功能：用于不同种格式的转换Timestamp
	 */
    public static Date parseDate(String timeStr, int type) throws ServiceException
	{
    	Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		
		try {
			if(timeStr == null || timeStr.equals("")){
				return null;
			}
			
			dateFormat.applyPattern(DataFormat.getDateConvertString(type));
			date = new Date(dateFormat.parse(timeStr).getTime());
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("转换date日期错误");
		}
		return date;
	}
	
	/**
	 * 格式化数字，例如：12345转化为12,345
	 * 
	 * @param dValue
	 *            被格式化的数值
	 * @param iScale
	 *            小数点后保留位数,不足补0
	 * @return
	 */
	public static String formatAmount ( double dValue , int iScale )
	{
		DecimalFormat df = null ;
		StringBuffer sPattern = new StringBuffer ( ",##0" ) ;
		String temp = "";
		if (iScale > 0)
		{
			sPattern.append ( "." ) ;
			for (int i = 0; i < iScale; i++)
			{
				sPattern.append ( "0" ) ;
			}
		}
		/*if(dValue!=0.00){*/
		   df = new DecimalFormat ( sPattern.toString ( ) ) ;
		   temp = df.format ( dValue );
		/*}
		else
		{
			temp = "0.00";
		}*/
	
		return temp;
	}

	/**
	 * convert Timestamp to string "YYYYMMDDHHMMSS"
	 * @param ts
	 * @return
	 */
	static public String getDateTimeString ( Timestamp ts )
	{
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		/*
		return calendar.get ( Calendar.YEAR ) + "-"
				+ (calendar.get ( Calendar.MONTH ) + 1) + "-"
				+ calendar.get ( Calendar.DATE ) + " "
				+ calendar.get ( Calendar.HOUR_OF_DAY ) + ":"
				+ calendar.get ( Calendar.MINUTE ) + ":"
				+ calendar.get ( Calendar.SECOND ) ;
		*/

		String strMonth = String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		if (strMonth.length ( ) == 1)
		{
			strMonth = "0" + strMonth ;
		}
		String strDay = String.valueOf ( calendar.get ( Calendar.DATE ) ) ;
		if (strDay.length ( ) == 1)
		{
			strDay = "0" + strDay ;
		}
		String strHour = String.valueOf ( calendar.get ( Calendar.HOUR_OF_DAY ) ) ;
		if (strHour.length ( ) == 1)
		{
		    strHour = "0" + strHour ;
		}
		String strMinute = String.valueOf ( calendar.get ( Calendar.MINUTE ) ) ;
		if (strMinute.length ( ) == 1)
		{
		    strMinute = "0" + strMinute ;
		}
		String strSecond = String.valueOf ( calendar.get ( Calendar.SECOND ) ) ;
		if (strSecond.length ( ) == 1)
		{
		    strSecond = "0" + strSecond ;
		}
		return calendar.get ( Calendar.YEAR ) + "" + strMonth + "" + strDay + "" + strHour + "" + strMinute + "" + strSecond;
		//return ts.toString();
	}
	
	/**
	 * convert Timestamp to string "yyyy-mm-dd"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getDateString ( Timestamp ts )
	{
		Date dt = null ;
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		String strMonth = String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		if (strMonth.length ( ) == 1)
		{
			strMonth = "0" + strMonth ;
		}
		String strDay = String.valueOf ( calendar.get ( Calendar.DATE ) ) ;
		if (strDay.length ( ) == 1)
		{
			strDay = "0" + strDay ;
		}
		return calendar.get ( Calendar.YEAR ) + "-" + strMonth + "-" + strDay ;
	}
	
	/**
	 * 格式化数字，例如：将5转化为4位字符，则得到0005
	 * 
	 * @param lValue
	 *            被格式化的数值
	 * @param nWidth
	 *            需要转换的位数
	 * @return
	 */
	public static String formatInt ( long lValue , int nWidth )
	{
		String strReturn = "" + lValue ;
		int initLength = strReturn.length ( ) ;
		for (int i = nWidth; i > initLength; i--)
		{
			strReturn = "0" + strReturn ;
		}
		return strReturn ;
	}

	/**
	 * 开始计算实际间隔日期
	 * add by Forest
	 * @return
	 */
	static public int getIntervalDays(Date tsStart, Date tsEnd)
	{
		int lIntervalDays = 0;
		
		while (!tsStart.after(tsEnd))
		{
			tsStart = getNextDate(tsStart,1);
			lIntervalDays++;
		}
		return lIntervalDays;
	}
	
	/**
	 * 得到下几天
	 * 
	 * @param tsDate
	 */
	public static Date getNextDate(Date tsDate, int nDay)
	{
		if (null == tsDate)
			return null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(tsDate);
		calendar.add(Calendar.DATE , nDay);
		Date resDate = calendar.getTime();
		return resDate;
	}
	
	/**
	 * 得到下几年
	 * 
	 * @param tsDate
	 */
	static public Date getNextYear(Date tsDate, int nYear)
	{
		if (null == tsDate)
			return null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(tsDate);
		calendar.add (Calendar.YEAR , nYear);
		Date resDate = calendar.getTime();
		return resDate;
	}
	
	/**
	 * 获得字符串的字节长度，其中一个汉字长度为2
	 * @param s
	 * @return
	 */
	static public  int  getWordCount(String s)
    {

		int  length  =   0 ;
		if(s != null )
		{
			 for ( int  i  =   0 ; i  <  s.length(); i ++ )
		       {
		            int  ascii  =  Character.codePointAt(s, i);
		            if (ascii  >=   0   &&  ascii  <= 255 )
		               length ++ ;
		            else 
		              length  +=   2 ;
		               
		       }
		}
       
        return  length;

      
   } 
	
	/**
	 * 是否包含汉字
	 * @param s
	 * @return
	 */
	static public  boolean  hasChinese(String s)
    {

		boolean  has  =   false ;
		if(s != null )
		{
			 for ( int  i  =   0 ; i  <  s.length(); i ++ )
		       {
		            int  ascii  =  Character.codePointAt(s, i);
		            if (!(ascii  >=   0   &&  ascii  <= 255 ))
		            {
		            	has = true ;
		            }
	               
		       }
		}
        return  has;

      
   } 
	

	/** 
     * Java中1个char类型的变量可存储任意编码的1个字符，如1个ASC码和或1个中文字符， 
     * 例如：含有3个ASC和含有3个汉字字符的字符串长度是一样的： "1ac".length()==3;  "你好a".length()=3; 
     * 但上述两个字符串所占的byte是不一样的，前者是3，后者是5（1个汉字2byte）。 
     * 请编写函数: 
     *     public static String leftStr(String source, int maxByteLen) 
     * 从source中取最大maxByteLen个byte的子串。 
     * 当最后一个byte恰好为一个汉字的前半个字节时，舍弃此byte。例如： 
     *     String str="我LRW爱JAVA"; 
     *     leftStr(str,1,-1)==""; 
     *     leftStr(str,2,-1)=="我"; 
     *     leftStr(str,4,-1)=="我LR"; 
     *     leftStr(str,11,-1)=="我LRW"; 
     * 当最后一个byte恰好为一个汉字的前半个字节时，补全汉字（多取一个字节）。例如： 
     *     String str="我LRW爱JAVA"; 
     *     leftStr(str,1,1)=="我"; 
     *     leftStr(str,2,1)=="我"; 
     *     leftStr(str,4,1)=="我LR"; 
     *     leftStr(str,11,1)=="我LRW爱"; 
     * 
     *  @param  source 原始字符串 
     *  @param  maxByteLen 截取的字节数 
     *  @param  flag 表示处理汉字的方式。1表示遇到半个汉字时补全，-1表示遇到半个汉字时舍弃 
     *  @return 截取后的字符串 
     */ 

	public static  String leftStr ( String source,  int  maxByteLen,  int  flag )
	{ 
        String returnStr = "";
		if ( source !=  null  && maxByteLen > 0 )
		{ 
	        
	        byte []  bStr = source.getBytes () ; 
	        if ( maxByteLen >= bStr.length ) 
	        {
	        	returnStr = source; 
	        }	        	
	        else
	        {
	        	String cStr =  new  String ( bStr, maxByteLen -  1 ,  2 ) ; 
		        if ( cStr.length ()  ==  1  && source.contains ( cStr )){ 
		            maxByteLen += flag; 
		        } 
		        returnStr = new  String ( bStr,  0 , maxByteLen ) ; 
	        }
	        
		}
        return returnStr;
    } 
	
	public static  String rightStr ( String source,  int beginIndex, int  flag )
	{ 
		String returnStr = "";
		
		if ( source !=  null  && beginIndex >= 0 )
		{ 
			byte []  bStr = source.getBytes () ; 
			if (beginIndex <  bStr.length ) 
	        {
				if(beginIndex > 0)
				{
					String beginStr =  new  String ( bStr, beginIndex -  1 ,  2 ) ; 
			        if ( beginStr.length ()  ==  1  && source.contains ( beginStr )){ 
			        	beginIndex +=  flag; 
			        } 
				}

				returnStr = new  String ( bStr, beginIndex ,  bStr.length-beginIndex ) ;
	        	
			
	        }	        	
	        
        }
        

        return  returnStr ; 
    }
	
	public static  String leftStr ( String source,  int maxByteLen )
	{
		return leftStr(source, maxByteLen, -1);
	}
	
	public static  String rightStr ( String source,  int beginIndex )
	{
		return rightStr(source, beginIndex, -1);
	}
	public static  String bSubString ( String source,  int beginIndex, int endIndex )
	{
		return bSubString(source, beginIndex, endIndex, -1 );
	}
	
	
	
	public static  String bSubString ( String source,  int beginIndex, int endIndex,  int  flag )
	{ 
		String returnStr = "";
		endIndex = endIndex -1;
		if ( source !=  null  && beginIndex >= 0 && endIndex >0 && (beginIndex <= endIndex))
		{ 
			byte []  bStr = source.getBytes () ; 
//			System.out.println("bStr.length=" + bStr.length);
			if (beginIndex <  bStr.length ) 
	        {
				if(beginIndex > 0)
				{
					String beginStr =  new  String ( bStr, beginIndex -  1 ,  2 ) ; 
//					System.out.println("beginStr=" + beginStr);
			        if ( beginStr.length ()  ==  1  && source.contains ( beginStr )){ 
			        	beginIndex +=  flag; 
			        } 
				}
				if(endIndex >= bStr.length-1)
	        	{
	        		returnStr = new  String ( bStr, beginIndex ,  bStr.length-beginIndex ) ;
	        	}
	        	else
	        	{
	        		String endStr =  new  String ( bStr, endIndex  ,  2 ) ; 
//	        		System.out.println("endStr = " + endStr);
			        if ( endStr.length ()  ==  1  && source.contains ( endStr )){ 
			        	endIndex += flag; 
			        } 
//			        System.out.println("beginIndex=" + beginIndex +"    endIndex=" + endIndex );
	        		returnStr = new  String ( bStr, beginIndex ,  endIndex-beginIndex+1 ) ;
	        		
	        	}
	        }	        	
	        
        }
//		System.out.println("returnStr=" + returnStr );

        return  returnStr ; 
    } 
	
	/**
	 * 取两时间段的天数,例如：2003-12-02到2003-12-01为一天
	 * 
	 * @param dtBegin
	 *            开始日期(Date类型)
	 * @param dtEnd
	 *            结束日期(Date类型)
	 * @return
	 */
	public static long getTime(java.sql.Date dtBegin, java.sql.Date dtEnd) {
		Calendar temp = Calendar.getInstance();
		temp.setTime(dtBegin);
		temp.set(Calendar.HOUR, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		long lBegin = temp.getTime().getTime();
		temp.setTime(dtEnd);
		temp.set(Calendar.HOUR, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		long lEnd = temp.getTime().getTime();
		long lTime = (lEnd - lBegin) / (24 * 60 * 60 * 1000);
		return lTime;
	}

	/**
	 * 取两时间段的天数,例如：2003-12-02到2003-12-01为一天
	 * 
	 * @param dtBegin
	 *            开始日期(Date类型)
	 * @param dtEnd
	 *            结束日期(Date类型)
	 * @return
	 */
	public static long getTime(Date dtBegin, Date dtEnd) {
		Calendar temp = Calendar.getInstance();
		temp.setTime(dtBegin);
		temp.set(Calendar.HOUR, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		long lBegin = temp.getTime().getTime();
		temp.setTime(dtEnd);
		temp.set(Calendar.HOUR, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		long lEnd = temp.getTime().getTime();
		long lTime = (lEnd - lBegin) / (24 * 60 * 60 * 1000);
		return lTime;
	}
	
    public static String formatString(String arg0)
    {
		if(arg0 == null){
			return "";
		}
		else {
			return arg0;
		}
    }	
/*
	public static void main(String[] args){
		try {
			//System.out.println(DataFormat.parseDate("", 1));
			//System.out.println(DataFormat.getCurrentUpperDate());
//			System.out.println(DataFormat.leftStr("8我的erqwr",4,-1));
//			System.out.println(DataFormat.leftStr("8我的erqwr",-1,-1));
//			System.out.println(DataFormat.leftStr("",4,-1));
//			System.out.println(DataFormat.leftStr("8我的erqwr",4,1));
//			
			System.out.println(("8我的erqwr").substring(0,8));
			
//			System.out.println(DataFormat.bSubString("8我的erqwr",0,4,-1));  //8我
//			System.out.println(DataFormat.bSubString("8我的erqwr",0,-1,-1));	//
//			System.out.println(DataFormat.bSubString("",0,4,-1));
//			System.out.println(DataFormat.bSubString("8我的erqwr",0,4,1));	//8我
			
			
//			System.out.println(DataFormat.bSubString("8我的erqwr",0,5,-1));	//8我
//			System.out.println(DataFormat.bSubString("8我的erqwr",1,4,-1));	//我
//			System.out.println(DataFormat.bSubString("8我的erqwr",2,5,-1));	//我
//			System.out.println(DataFormat.bSubString("8我的erqwr",0,5,1));	//8我的
//			System.out.println(DataFormat.bSubString("8我的erqwr",1,5,1));	//我的
//			System.out.println(DataFormat.bSubString("8我的erqwr",2,4,1));	//
//			System.out.println(DataFormat.bSubString("8我的erqwr",2,5,1));	//的
			
			String tempStr = "A2.09877777777620110913094149119877777777611091311100010001                    1                              Z0000000000";
			System.out.println(DataFormat.bSubString(tempStr,0,10,-1));		//8我的erq日
			System.out.println(DataFormat.bSubString(tempStr,0,9,-1));		//8我的erq
			System.out.println(DataFormat.bSubString(tempStr,2,11,-1));		//我的erq日
			System.out.println(DataFormat.bSubString(tempStr,2,10,1));		//的erq日
			System.out.println(DataFormat.bSubString(tempStr,2,11,1));		//的erq日
			System.out.println(DataFormat.bSubString(tempStr,1,10,1));		//我的erq日
			System.out.println(DataFormat.bSubString("8我的erq日",1,11,1));		//我的erq日
			String  ff = "8我的erq日d饿";
			System.out.println( ff.length() > 8 ? ff.substring(0, 8) : ff);		//我的erq日
			
			
//			System.out.println(DataFormat.hasChinese("asdf7*&^433"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/

}
