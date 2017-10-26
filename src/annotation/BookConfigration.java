package annotation;

import java.lang.reflect.Field;

public class BookConfigration {
    public static void bookConfigration(Class<?> clazz){
    	Field[] fields = clazz.getDeclaredFields();
    	for(Field field : fields){
    		if(field.isAnnotationPresent(BookName.class)){
    			BookName bookName = field.getAnnotation(BookName.class);
    			System.out.println(bookName.bookName());
    		}
    		if(field.isAnnotationPresent(BookType.class)){
    			BookType bookType = field.getAnnotation(BookType.class);
    			System.out.println(bookType.bookType());
    		}
    	}
    }
}
