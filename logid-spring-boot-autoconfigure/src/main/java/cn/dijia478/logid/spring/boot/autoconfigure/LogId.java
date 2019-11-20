package cn.dijia478.logid.spring.boot.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LogId的注解，用于切面捕获
 *
 * @author dijia478
 * @date 2019-11-20 9:44
 */
@Retention(RetentionPolicy.RUNTIME) // 标记的注释由JVM保留，因此运行时环境可以使用它
@Target(ElementType.METHOD) // 可以应用于方法级注释
@Documented // 无论何时使用指定的注释，都应使用Javadoc工具记录这些元素
public @interface LogId {

}
