package AnnotationDemo;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BookInfo {
	public String bookInfo() default "This is a book!";

}
