package annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

public @interface BookType {
    public static enum Booktype{
    	Biology,art,history
    }
    
    public Booktype bookType() default Booktype.art;
}
