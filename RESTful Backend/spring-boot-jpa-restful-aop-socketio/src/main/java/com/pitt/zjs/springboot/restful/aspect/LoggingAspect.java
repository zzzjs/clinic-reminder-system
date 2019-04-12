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
@Order(3)
public class LoggingAspect {
	
	@Pointcut("execution(* com.pitt.zjs.springboot.restful.rest.DoctorRestController.doctorLogin(..))")
	private void forDoctorLogin() {}
	
	@Pointcut("execution(* com.pitt.zjs.springboot.restful.rest.DoctorRestController.patientLogin(..))")
	private void forPatientLogin() {}

	@Around("forPatientLogin()")
	public String unWrapPatientRequest(final ProceedingJoinPoint pjp) throws Throwable {
	    Object[] args = pjp.getArgs();
		Gson gson = new Gson();
		Request request = Codec.decoder(((String) args[0]));
		PushResponse response = new PushResponse(request.getReqEvent(),request.getSeqId(),request.getAction(),null);
		if (request.getReqEvent() != 20 ) {
			response.setRespEvent(999);
			return gson.toJson(response);
		}
		args[0] = request.getReqJson();
		Object result = pjp.proceed(args);
		if (result == null) {
			response.setRespEvent(990);
		} else {
			response.setRespJson(((String)result));
		}
		return gson.toJson(response);
	}
	
	@Around("forDoctorLogin()")
	public String unWrapDoctorRequest(final ProceedingJoinPoint pjp) throws Throwable {
	    Object[] args = pjp.getArgs();
		Gson gson = new Gson();
		Request request = Codec.decoder(((String) args[0]));
		PushResponse response = new PushResponse(request.getReqEvent(),request.getSeqId(),request.getAction(),null);
		if (request.getReqEvent() != 100 ) {
			response.setRespEvent(999);
			return gson.toJson(response);
		}
		args[0] = request.getReqJson();
		Object result = pjp.proceed(args);
		if (result == null) {
			response.setRespEvent(990);
		} else {
			response.setRespJson(((String)result));
		}
		return gson.toJson(response);
	}
	
}
