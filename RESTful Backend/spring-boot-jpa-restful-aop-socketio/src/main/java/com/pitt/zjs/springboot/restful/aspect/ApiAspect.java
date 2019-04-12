package com.pitt.zjs.springboot.restful.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pitt.zjs.springboot.restful.net.PushResponse;
import com.pitt.zjs.springboot.restful.net.Request;
import com.pitt.zjs.springboot.restful.util.Codec;

@Aspect
@Component
@Order(2)
public class ApiAspect {
	
	@Pointcut("execution(* com.pitt.zjs.springboot.restful.rest.DoctorRestController.upDataReminder(..))")
	private void forUpDataReminders() {}

	@Pointcut("execution(* com.pitt.zjs.springboot.restful.rest.DoctorRestController.addReminder(..))")
	private void forAddReminder() {}


	@Around("forUpDataReminders()")
	public String unWrapUpdataRequest(final ProceedingJoinPoint pjp) throws Throwable {
	    Object[] args = pjp.getArgs();
		Gson gson = new Gson();
		Request request = Codec.decoder(((String) args[0]));
		PushResponse response = new PushResponse(request.getReqEvent(),request.getSeqId(),request.getAction(),null);
		if (request.getReqEvent() != 30 ) {
			response.setRespEvent(999);
			return gson.toJson(response);
		}
		args[0] = request.getReqJson();
		Object result = pjp.proceed(args);
		response.setRespJson(((String)result));
		return gson.toJson(response);
	}
	
	@Around("forAddReminder()")
	public String unWrapAddRequest(final ProceedingJoinPoint pjp) throws Throwable {
	    Object[] args = pjp.getArgs();
		Gson gson = new Gson();
		Request request = Codec.decoder(((String) args[0]));
		PushResponse response = new PushResponse(request.getReqEvent(),request.getSeqId(),request.getAction(),null);
		if (request.getReqEvent() != 110 ) {
			response.setRespEvent(999);
			return gson.toJson(response);
		}
		args[0] = request.getReqJson();
		Object result = pjp.proceed(args);
		response.setRespJson(((String)result));
		return gson.toJson(response);
	}

	
}
