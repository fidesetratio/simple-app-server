package com.app.logs;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.app.services.LogServices;

@Component
public class LogInterceptor implements HandlerInterceptor {
    
    @Autowired
    LogServices loggingService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                             Object handler) {
    
        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())) {
            loggingService.logRequest(request, null);
        }
    
    	if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.POST.name())) {
            loggingService.logRequest(request, null);
        }
    

    	if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.PUT.name())) {
            loggingService.logRequest(request, null);
        }
    	

    	if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.DELETE.name())) {
            loggingService.logRequest(request, null);
        }
    
    	loggingService.logResponse(request, response, handler);
        return true;
    }
}