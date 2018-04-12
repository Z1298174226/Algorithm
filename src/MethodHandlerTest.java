import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;

/**
 * Created by qtfs on 2018/1/10.
 */
public class MethodHandlerTest {

    static class ClassA{
        public void println() {
            System.out.println("Hello");
        }
    }
    public void println(String s) {
        System.out.println(s);
    }

    public static void main(String[ ] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("icyfenix");
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }
}
