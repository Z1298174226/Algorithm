package AnnotationDemo;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BookProvider {
	public String bookProvider() default "";

}
