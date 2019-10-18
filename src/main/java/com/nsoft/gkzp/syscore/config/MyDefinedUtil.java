package com.nsoft.gkzp.syscore.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自定义配置类 获取config.properties相关参数
 *（ 其他类获取值，请用注解@Autowired 方式 ，否则获取不到值）
 * @author zdyang
 * @date 2019.08.30
 */
@Configuration //标识这个是一个配置类
@PropertySource(value = "classpath:resources/config.properties")
public class MyDefinedUtil {
    @Value("${system.encoding:UTF-8}")  //冒号后的值为没有配置文件时，制动装载的默认值  //下面的属性不能为static类型，否则获取不到值
    public  String SYSTEM_ENCODING;  //#System Encoding

    //文件管理
    @Value("${system.file.folder.img}")
    public  String SYSTEM_FILE_FOLDER_IMG;
}

