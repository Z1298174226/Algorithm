package AnnotationDemo;

import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BookType {
	public static enum Booktype{
		scient,art,children,medician
	}
	public Booktype bookType() default Booktype.art;

}
