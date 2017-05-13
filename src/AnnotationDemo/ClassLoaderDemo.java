package AnnotationDemo;


import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderDemo {
	public static void main(String[] args){
		ClassLoader myLoader = new ClassLoader(){
			@Override
			public Class<?> loadClass(String fileName) throws ClassNotFoundException{
				String name = fileName.substring(fileName.lastIndexOf(".") + 1) + ".class";
				try{
					InputStream in = getClass().getResourceAsStream(name);
					if(in == null){
						return super.loadClass(fileName);
					}
					else{
						byte[] b = new byte[in.available()];
						in.read(b);
						return defineClass(fileName,b,0,b.length);
					}
				}catch(IOException ex){
					throw new ClassNotFoundException();
				}
			}
		};
		try{
		Object obj = myLoader.loadClass("AnnotationDemo.ClassLoaderDemo").newInstance();
		Object obj_1 =ClassLoaderDemo.class.newInstance(); 
		System.out.println(obj instanceof ClassLoaderDemo);
		System.out.println(obj_1 instanceof ClassLoaderDemo);
		System.out.println(obj.getClass().getClassLoader());
		System.out.println(obj_1.getClass().getClassLoader());
		}catch(Exception e){
			
		}
	}

}
