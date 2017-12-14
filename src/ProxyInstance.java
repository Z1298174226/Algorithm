import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by qtfs on 2017/12/5.
 */
public class ProxyInstance implements InvocationHandler{

    private Object proxied;
    public ProxyInstance(Object proxied) {
       this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("function"))
            System.out.println("AfterProxy EnumDemo!");
        return method.invoke(proxied, args);
    }

    public InterfaceDemo bind() {
        return (InterfaceDemo) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{InterfaceDemo.class}, this);
    }

    public static void main(String[] args) {
        ProxyInstance p = new ProxyInstance(new ImplementsDemo());
        p.bind().function();
    }
}
