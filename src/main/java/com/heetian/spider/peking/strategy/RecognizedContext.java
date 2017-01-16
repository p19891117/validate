package com.heetian.spider.peking.strategy;

import java.awt.image.BufferedImage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.heetian.spider.ocr.exception.ImageConverte;

/**
 * 识别程序context类，单例
 * @author tst
 *
 */
public class RecognizedContext {
	private static Logger logger = LoggerFactory.getLogger(RecognizedContext.class);
	public static final ApplicationContext ioc = new ClassPathXmlApplicationContext("recognized.xml");
	public static final String saveName = "RecognizedContext_Result";
	private static final RecognizedContext context = new RecognizedContext();
	private RecognizedContext(){}
	public static RecognizedContext newInstance() {
		return context;
	}
	/**
	 * 
	 * @param picName
	 * @param picType
	 * @return 字符数组：0为内容，（1为currentId，2为circleNumber：更新成功或者失败需要用的）
	 * @throws ImageConverte
	 */
	public String[] recognized(String recognizedName,BufferedImage image,String picName,String picType) throws ImageConverte,BeansException{
		Recognized recognized = (Recognized) ioc.getBean(recognizedName);
		recognized.success(1);
		recognized.fail(1);
		return new String[]{recognized.recognition(image,picName, picType),recognizedName,Recognized.fail};
	}
	public void update(String[] result){
		if(StringUtils.isBlank(result[1]))
			return;
		try {
			Recognized recognized = (Recognized) ioc.getBean(result[1]);
			if(recognized==null)
				return;
			if(Recognized.succ.equals(result[2])){
				recognized.fail(-1);
			}else if(Recognized.fail.equals(result[2])){
				recognized.success(-1);
			}
		} catch (Exception e) {
			logger.error("更新验证码破解程序状态失败",e);
		}
	}
}