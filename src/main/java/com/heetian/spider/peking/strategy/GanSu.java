package com.heetian.spider.peking.strategy;

import java.awt.image.BufferedImage;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 四川省验证码识别程序
 * @author tst
 *
 */
@Component(value="com.heetian.spider.peking.strategy.GanSu")
public class GanSu extends AbstractRecognized{
	public GanSu(){
		forbiddenStencil();
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		return new BufferedImage[]{img};
	}
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		return ResultProcess.newInstance(ValidateType.count, new String[]{"0","+","0"});
	}
}
