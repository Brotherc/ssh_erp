package cn.brotherChun.erp.util.quartz;

import java.util.Date;
import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cn.brotherChun.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.brotherChun.erp.util.format.FormatUtil;

public class TimerTask {
	private GoodsEbi goodsEbi;
	private MailSender mailSender;
	
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}


	public void goodsUseNumUpdate(){
		goodsEbi.goodsUseNumUpdate();
	}
	
	public void outGoodsNum(){
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom("brotherchun12138@126.com");
		smm.setSubject("仓库预警通知["+FormatUtil.formatDate(System.currentTimeMillis())+"]");
		smm.setSentDate(new Date());
		smm.setTo("chunge12138@126.com");
		List<Object[]> info=goodsEbi.getWarmInfo();
		
		StringBuilder sb=new StringBuilder();
		for(Object obj[]:info){
			if(obj[1]!=null&&obj[1].toString().equals("1")){
				sb.append("商品【");
				sb.append(obj[0]);
				sb.append("】已经满仓，请停止下订货单\r\n");
				continue;
			}
			if(obj[2]!=null&&obj[2].toString().equals("1")){
				sb.append("商品【");
				sb.append(obj[0]);
				sb.append("】需要补货，请及时下订单\r\n");
			}
		}
		smm.setText(sb.toString());
		mailSender.send(smm);
		
	}
}
