package memorymanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javassist.ClassPool;

public class MemoryManagement {

    /**
     * Инициализация в статическом блоке OutOfMemoryError выполняется для того,
     * что бы к моменту переполнения PermGen данный клас уже был подгружен в
     * память. Иначе JVM не сможет его создать из-за нехватки места в PermGen и,
     * как следствие, мы не увидем истиную причину ошибки. Вместо нее будет:
     * Exception in thread "main" Exception: java.lang.OutOfMemoryError thrown
     * from the UncaughtExceptionHandler in thread "main"
     */
    static {
        new OutOfMemoryError().printStackTrace();
    }

    public static void main(String[] args) {

        demonstrateJvmParameters();

//        demonstrateJavaHeapSpace();

//        demonstratePermGenSpace();
    }

    /**
     * java.lang.OutOfMemoryError: PermGen space
     */
    private static void demonstratePermGenSpace() {
        try {
            for (int i = 0; i < 100000000; i++) {
                Class clazz = generate("jamp.perm.leak.Generated" + i);
                if (i % 50 == 0) {
                    System.out.println(clazz);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * java.lang.OutOfMemoryError: Java heap space.
     */
    private static void demonstrateJavaHeapSpace() {

        SomeObject someObject = new SomeObject();

        while (true) {
            System.out.println("Running");
            someObject = new SomeObject(someObject);
        }
    }

    private static Class generate(String name) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(name).toClass();
    }

    private static void demonstrateJvmParameters() {
        while (true) {
            noReturning(new Object());
//            byte resScalar = returnScalar(new Object());
//            List<Object> objectList = returnObject(new Object());
        }
    }

    private static void noReturning(Object object) {
        List<Object> objects = new ArrayList<Object>(Arrays.asList(object));
    }

    private static byte returnScalar(Object object) {
        List<Object> objects = new ArrayList<Object>(Arrays.asList(object));
        return 1;
    }

    private static List<Object> returnObject(Object object) {
        List<Object> objects = new ArrayList<Object>(Arrays.asList(object));
        return objects;
    }
}
