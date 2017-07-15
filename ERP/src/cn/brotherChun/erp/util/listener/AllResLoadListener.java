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
		ServletContext sct = event.getServletContext();
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(sct);
		ResEbi resEbi = (ResEbi) context.getBean("resEbi");
		List<ResModel> resTemp = resEbi.getAll();
		
		StringBuffer sb=new StringBuffer();
		for(ResModel res:resTemp){
			sb.append(res.getUrl());
			sb.append(",");
		}
		sct.setAttribute("resAll", sb.toString());
	}

}
