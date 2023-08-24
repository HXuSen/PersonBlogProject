package com.hxsstu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: SystemLog
 * Package: com.hxsstu.annotation
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-11:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog {
    String businessName();
}
