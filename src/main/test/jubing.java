import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by Administrator on 2016/11/23.
 */
public class jubing {
    public static void main(String[] args) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = null;
        try {
            mh = lookup.findVirtual(String.class, "substring", type);
            String str = (String) mh.invokeExact("Hello World", 1, 3);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
