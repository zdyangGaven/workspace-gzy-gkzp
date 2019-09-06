package com.nsoft.gkzp.util;

/**
 * 
 * 用于系统的各种常量定义
 * 
 * @author zdyang
 *
 */
public class SysConstant {


	/**
	 * 系统用户类型
	 *
	 * @author leiyang3
	 *
	 */
	public static class SysUserType {

		public static final long ADMONUSER 		= 0;   //系统管理员（后台管理）
		public static final long INTRANETUSER 	= 1;   //内网用户 （招聘者）
		public static final long OUTNETUSER 	= 2;  //外网用户   (应聘者)

		public static final String NAME_ADMONUSER	 = "系统管理员";
		public static final String NAME_INTRANETUSER = "校方用户";
		public static final String NAME_OUTNETUSER 	 = "普通用户";


		public static final String getName(long lCode)
		{
			String strReturn = ""; //初始化返回值
			switch ((int)lCode) {
				case (int) ADMONUSER:
					strReturn = NAME_ADMONUSER;
					break;
				case (int) INTRANETUSER:
					strReturn = NAME_INTRANETUSER;
					break;
				case (int) OUTNETUSER:
					strReturn = NAME_OUTNETUSER;
					break;
			}
			return strReturn;
		}

	}

	public static class RecordStatus
	{
		//表中记录的nStatus
		public static final long VALID = 1; //有效

		public static final long INVALID = 0; //无效(删除)

		public static final String getName(long lCode)
		{
			String strReturn = ""; //初始化返回值
			switch ((int) lCode)
			{
				case (int) VALID:
					strReturn = "有效";
					break;
				case (int) INVALID:
					strReturn = "无效";
					break;
			}
			return strReturn;
		}
	}



}

