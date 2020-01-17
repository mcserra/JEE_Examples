package com.interceptors;


import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@PingInterceptor
@Priority(Interceptor.Priority.APPLICATION)
public class PingInterceptorImpl {

    @Inject
    private PersonManipulator personGenerator;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        ctx.setParameters(new Object[]{
            personGenerator.getPerson(ctx.getParameters()[0].toString())
        });
        return ctx.proceed();
    }


}
