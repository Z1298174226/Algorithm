package AnnotationDemo;

import java.lang.reflect.Field;
public class BookConfigration {
	public static void bookConfigration(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			if(clazz.isAnnotationPresent(BookName.class)){
				BookName name = clazz.getAnnotation(BookName.class);
				System.out.println(name.bookName());
				System.out.println("----------------------->");
			}
			if(field.isAnnotationPresent(BookName.class)){
				BookName name = field.getAnnotation(BookName.class);
				System.out.println(name.bookName());
			}
			if(field.isAnnotationPresent(BookType.class)){
				BookType type = field.getAnnotation(BookType.class);
				System.out.println(type.bookType());
			}
			if(field.isAnnotationPresent(BookProvider.class)){
				BookProvider provider = field.getAnnotation(BookProvider.class);
				System.out.println(provider.bookProvider());
			}
			if(field.isAnnotationPresent(BookInfo.class)){
				BookInfo info = field.getAnnotation(BookInfo.class);
				System.out.println(info.bookInfo());
			}
		}
	}

}
