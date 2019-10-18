package com.nsoft.gkzp.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验数据公共类
 * @author zdyang
 * @date 2019.09.10
 */
public class CheckDataType {


	public static boolean isN(String str) {
		boolean is = true;

		if(str != null && str.length() > 0)
			is = str.matches("[0-9]*");
				
		return is;
	}

	/**
	 * 校验是否包含汉字
	 * @param str
	 * @return flg   true为包含汉字，false不包含汉字
	 */
	public static boolean isChinese(String str) {
		boolean flg = false;
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(str);
		if (matcher.find()){
			flg = true;
		}
		return flg;
	}


	/**
	 * 校验是否包含数字、字母、特殊字符(~!@#$%^&*()_+|<>,.?/:;'[]{}\)
	 * @param str
	 * @return flg   true为汉字，false不是汉字
	 */
	public static boolean matcheData(String str,int type) {
		boolean flg = false;
		if(type == 1){ //判断数据是否包含数字：包含返回true，不包含返回false
			flg = str.matches(".*\\d+.*");
		}else if(type == 2){//判断数据是否包含字母：包含返回true，不包含返回false
			flg = str.matches(".*[a-zA-Z]+.*") ;
		}else if(type == 3){//判断数据是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回true，不包含返回false
			flg = str.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ;
		}

		return flg;
	}

	/**
	 * 用户名校验 必须是4-10位字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含） 不能以数字开头
	 * @param str
	 * @return flg   true，false
	 */
	public static boolean checkName(String str) {
		boolean flg = false;
		String regExp = "^[^0-9][\\w_]{3,9}$";
		if(str.matches(regExp)) {
			flg = true;
		}else {
			flg = false;
		}
		return flg;
	}



	public static void main(String[] args){
		String aa = "_a23_56";
		boolean b = checkName(aa);


	}


}
