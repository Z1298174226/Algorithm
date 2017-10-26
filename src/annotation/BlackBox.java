package annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class BlackBox {
	
	static interface Action{
		public void display();
	}
	static class ActionImp implements Action{
		public void display(){
			System.out.println("JDK1.7");
		}
	}
	static class MethodSelect implements InvocationHandler{
		
		Object proxied;
		public MethodSelect(Object proxied){
			this.proxied = proxied;
		}
		
		public  Object bind(){
			return Proxy.newProxyInstance(proxied.getClass().getClassLoader(), 
					proxied.getClass().getInterfaces(),this);
		}
		
		@Override
		public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
			if(arg1.getName().equals("display"))
				System.out.println("JDK8");
			return arg1.invoke(proxied,arg2);
        //			return null;
		}
		
		
	}
	
	public static void main(String[] args){
		Action act = (Action) new MethodSelect(new ActionImp()).bind();
		act.display();
	}
	

}
