package sortDemo;

import java.io.InputStream;

public class ClassLoaderDemo {
	public static void main(String[] args){
	ClassLoader myLoader = new ClassLoader(){
		
		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException{
			String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
			try{
				InputStream is = getClass().getResourceAsStream(fileName);
				if(is == null)
					return super.loadClass(name);
				byte[] b = new byte[is.available()];
				is.read(b);
				return defineClass(name,b,0,b.length);
			}catch(Exception ex){
				throw new ClassNotFoundException();
			}
		}
	 };
	 try{
		 Object obj = myLoader.loadClass("sortDemo.ClassLoaderDemo").newInstance();
		 System.out.println(obj instanceof ClassLoaderDemo);
		 System.out.println(obj.getClass().getClassLoader());
		 Object obj1 = Class.forName("sortDemo.ClassLoaderDemo").newInstance();
		 System.out.println(obj1 instanceof ClassLoaderDemo);
		 System.out.println(obj1.getClass().getClassLoader());
		 
	 }catch(ClassNotFoundException ex){
		 
	 }catch(IllegalAccessException ex){
		 
	 }catch(InstantiationException ex){
		 
	 }
	}

}
