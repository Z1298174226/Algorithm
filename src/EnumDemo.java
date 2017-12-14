import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;
import java.lang.reflect.Method;
/**
 * Created by qtfs on 2017/12/7.
 */

enum Explore {
    HERE,
    THERE
}
public class EnumDemo {
    public static Set<String> analyze(Class<?> enumClass) {
        for(Type t : enumClass.getGenericInterfaces()) {
            System.out.println(t);
        }
        System.out.println("Base : " + enumClass.getSuperclass());
        Set<String> methods = new TreeSet<String>();
        for(Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }
        System.out.println(methods);
        return methods;

    }

    public static void main(String[] args) {
        for(Explore e : Explore.values())
            System.out.println(e);
        System.out.println(Explore.class);
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println(exploreMethods.containsAll(enumMethods));
        System.out.println(exploreMethods);
    }
}
