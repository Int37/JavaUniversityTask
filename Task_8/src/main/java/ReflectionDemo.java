import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionDemo {

    @Contract(pure = true)
    public static int countHuman(@NotNull List<?> lst) {
        int count = 0;
        for (Object item : lst) {
            if (item instanceof Human)
                count++;
        }
        return count;
    }

    @NotNull
    public static List<String> namesOfPublicMethods(@NotNull Object obj) {
        Method[] methods = obj.getClass().getMethods();
        List<String> out = new ArrayList<>(methods.length);
        for (Method mtd : methods) {
            out.add(mtd.getName());
        }
        out.sort(String::compareToIgnoreCase);
        return out;
    }

    @NotNull
    public static List<String> namesOfAllSuper(@NotNull Object obj) {
        List<String> out = new ArrayList<>();
        Class<?> curClass;
        do {
            curClass = obj.getClass().getSuperclass();
            out.add(curClass.getName());
        }
        while (curClass != Object.class);
        return out;
    }

    public static int execute(@NotNull List<?> lst) {
        int count = 0;
        for (Object item : lst) {
            if (/*contains(item.getClass().getInterfaces(), Executable.class)*/
                /*item instanceof Executable ex*/           // Java 14
                    item instanceof Executable) {
                ((Executable) item).execute();
                count++;
            }
        }
        return count;
    }

    @NotNull
    public static List<String> gettersAndSetters(@NotNull Object obj) {
        Method[] methods = obj.getClass().getMethods();
        List<String> out = new ArrayList<>(methods.length);
        for (Method mtd : methods) {
            if (!Modifier.isStatic(mtd.getModifiers()) &&
                    ((mtd.getReturnType() != void.class && mtd.getParameterCount() == 0 && mtd.getName().startsWith("get")) ||
                            (mtd.getReturnType() == void.class && mtd.getParameterCount() == 1 && mtd.getName().startsWith("set"))))
                out.add(mtd.getName());
        }
        out.sort(String::compareToIgnoreCase);
        return out;
    }
}