package com.youo.bookmanage.security;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//专门用来限定某个自定义注解能够被应用在哪些Java元素上面
@Target({ElementType.METHOD})								// 方法注解
//用来修饰自定义注解的生命力。注解的生命周期有三个阶段：1、Java源文件阶段；2、编译到class文件阶段；3、运行期阶段。
@Retention(RetentionPolicy.RUNTIME)							// 运行时
public @interface Role {

    // 拥有哪个角色
    String[] roles() default "2";

}
