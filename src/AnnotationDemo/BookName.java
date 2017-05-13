package AnnotationDemo;

import java.lang.annotation.*;
@Target({ElementType.FIELD , ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BookName {
	
	public String bookName() default "";

}
