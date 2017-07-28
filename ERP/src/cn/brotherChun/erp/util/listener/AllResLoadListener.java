package cn.brotherChun.erp.util.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.brotherChun.erp.auth.res.business.ebi.ResEbi;
import cn.brotherChun.erp.auth.res.vo.ResModel;

public class AllResLoadListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent event) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		//读取所有资源信息，放入SerlvetContext范围
		//使用spring的上下文对象
		ServletContext sct = event.getServletContext();
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(sct);
		ResEbi resEbi = (ResEbi) context.getBean("resEbi");
		List<ResModel> resTemp = resEbi.getAll();
		
		StringBuffer sb=new StringBuffer();
		for(ResModel res:resTemp){
			sb.append(res.getUrl());
			sb.append(",");
		}
		//放入SerlvetContext范围中
		sct.setAttribute("resAll", sb.toString());
	}

}
