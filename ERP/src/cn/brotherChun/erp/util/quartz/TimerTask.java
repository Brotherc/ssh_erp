package cn.brotherChun.erp.util.quartz;

import java.util.Date;
import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import cn.brotherChun.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.brotherChun.erp.util.format.FormatUtil;

//定时任务
public class TimerTask {
	private GoodsEbi goodsEbi;
	private MailSender mailSender;
	
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}


	//货物使用频度维护
	public void goodsUseNumUpdate(){
		//查询所有货物的使用频度，然后修改对应的货物值
		goodsEbi.goodsUseNumUpdate();
	}
	
	public void outGoodsNum(){
		//创建简单邮件消息对象（不带附件等附属消息）
		SimpleMailMessage smm=new SimpleMailMessage();
		//发送方：必须配置的与配置文件相同
		smm.setFrom("brotherchun12138@126.com");
		//设置邮件标题
		smm.setSubject("仓库预警通知["+FormatUtil.formatDate(System.currentTimeMillis())+"]");
		//设置发送时间
		smm.setSentDate(new Date());
		//接收人
		smm.setTo("chunge12138@126.com");
		//文本信息
		List<Object[]> info=goodsEbi.getWarmInfo();
		
		StringBuilder sb=new StringBuilder();
		for(Object obj[]:info){
			if(obj[1]!=null&&obj[1].toString().equals("1")){
				//大于上限，发送预警消息
				sb.append("商品【");
				sb.append(obj[0]);
				sb.append("】已经满仓，请停止下订货单\r\n");
				continue;
			}
			if(obj[2]!=null&&obj[2].toString().equals("1")){
				//低于下限，发送预警消息
				sb.append("商品【");
				sb.append(obj[0]);
				sb.append("】需要补货，请及时下订单\r\n");
			}
		}
		smm.setText(sb.toString());
		//发送邮件
		mailSender.send(smm);
		
	}
}
