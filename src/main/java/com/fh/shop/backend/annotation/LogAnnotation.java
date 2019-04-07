package com.fh.shop.backend.annotation;

import java.lang.annotation.*;

//什么地方用
@Target(ElementType.METHOD)
//什么时间自定义注解有效
@Retention(RetentionPolicy.RUNTIME)
//注解是否将包含在JavaDoc中
@Documented
public @interface LogAnnotation {
    public String value() default "";
}
