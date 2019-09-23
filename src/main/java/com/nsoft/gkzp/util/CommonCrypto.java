package com.nsoft.gkzp.util;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.nsoft.gkzp.syscore.config.MyDefinedUtil;
import com.nsoft.gkzp.syscore.service.ServiceException ;
import com.nsoft.gkzp.system.sysuser.entity.SysUser ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author zdyang
 * 公共加密方法
 * @date 2019.08.30
 */
@Component
public class CommonCrypto
{

	@Autowired
	private MyDefinedUtil myDefinedUtil;
	public  String deskey = "D93F76F8175EB1A300000000";
	/**
	 * 用于明文密码SHA
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	public  String encryptSHAEncoderLogin(String password) throws ServiceException {
		StringBuffer temp = new StringBuffer();
		String strSHAEncoder = "";
		try
		{

			temp.append("@");
			temp.append(password);
			temp.append("@");
			temp.append(deskey);
			MessageDigest sha = MessageDigest.getInstance("SHA");
			sha.update(temp.toString().getBytes(myDefinedUtil.SYSTEM_ENCODING));
			byte[] shaDigest = sha.digest();
			strSHAEncoder = byteToHex(shaDigest);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("SHA加密失败");
		}
		return strSHAEncoder;
	}


    private  String byteToHex(byte[] buffer)
    {
        StringBuffer sb = new StringBuffer(buffer.length * 3);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
        }
        return sb.toString();
    }


    /**
     * 取得HMAC密钥
     * @return
     * @throws ServiceException
     */
    private  String getMacKey() throws ServiceException
    {
    	String strMacKey = "";
    	try
    	{
	        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
	        SecretKey secretKey = keyGenerator.generateKey();
	        BASE64Encoder base64 = new BASE64Encoder();
	        strMacKey  = base64.encode(secretKey.getEncoded());
    	}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("取得HMAC密钥失败");
		}
		return strMacKey;
    }
    
    /**
     * MD5加密
     * @param arg0
     * @return
     * @throws ServiceException
     */
    public  String encryptMD5Encoder(String arg0) throws ServiceException 
    {
    	String strMD5Encoder = "";
    	try
    	{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(arg0.getBytes(myDefinedUtil.SYSTEM_ENCODING));
            byte[] md5Digest = md5.digest();
            strMD5Encoder = byteToHex(md5Digest);
    	}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("MD5加密失败");
		}
		return strMD5Encoder;
    }
    
    /**
     * SHA加密
     * @param arg0
     * @return
     * @throws ServiceException
     */
    public  String encryptSHAEncoder(String arg0) throws ServiceException 
    {
    	String strSHAEncoder = "";
    	try
    	{
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(arg0.getBytes(myDefinedUtil.SYSTEM_ENCODING));
            byte[] shaDigest = sha.digest();
            strSHAEncoder = byteToHex(shaDigest);
    	}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("SHA加密失败");
		}
		return strSHAEncoder;
    }
    

    /**
     * MAC加密
     * @return
     * @throws ServiceException
     */
    public  String encryptHMACEncoder(String arg0, String key) throws ServiceException
    {
    	String strHMACEncoder = "";
    	try
    	{
	        byte[] bkey = new BASE64Decoder().decodeBuffer(key);
	        SecretKey secretKey = new SecretKeySpec(bkey, "HmacMD5");
	        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
	        mac.init(secretKey);
	        strHMACEncoder = byteToHex(mac.doFinal(arg0.getBytes(myDefinedUtil.SYSTEM_ENCODING)));
    	}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("SHA加密失败");
		}
		return strHMACEncoder;
    }


}