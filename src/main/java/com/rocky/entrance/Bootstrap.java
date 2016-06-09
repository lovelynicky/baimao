package com.rocky.entrance;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.rocky.springcontext.Context;

/**
 * Created by liluoqi on 16/5/28.
 */
public class Bootstrap {
    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(Context.class);
        applicationContext.registerShutdownHook();
    }
}
