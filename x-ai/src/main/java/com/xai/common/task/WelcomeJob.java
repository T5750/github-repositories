package com.xai.common.task;//package com.xai.common.task;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Component;
//
//import com.xai.oa.domain.Response;
//
//@Component
//public class WelcomeJob implements Job{
//	@Autowired
//	SimpMessagingTemplate template;
//
//    @Override
//    public void execute(JobExecutionContext arg0) throws JobExecutionException {
//    	template.convertAndSend("/topic/getResponse", new Response("欢迎使用AI平台管理系统" ));
//    }
//
//}