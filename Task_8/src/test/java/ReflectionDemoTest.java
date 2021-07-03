import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ReflectionDemoTest {
    Human man;

    @Before
    public void setUp() throws Exception {
        man = new Human("chel", "smitt", "oleg", new GregorianCalendar(5, Calendar.JUNE, 4));
    }

    @Test
    public void countHumanTest() {
        ArrayList<?> humanOrNo = new ArrayList<>(Arrays.asList(4,
                new Human("Oleg", "Ghtyfnoy", "Alexanich", new GregorianCalendar()),
                new String[]{"human", "HuMaN"},
                man, 456, -0.234657));
        assertEquals(2, ReflectionDemo.countHuman(humanOrNo));
    }

    @Test
    public void namesOfPublicMethodsTest() {
        assertEquals(new ArrayList<>(Arrays.asList("equals", "getClass", "getDateOfBirth", "getMiddleName", "getName", "getSurname",
                "hashCode", "notify", "notifyAll", "setDateOfBirth", "setMiddleName", "setName", "setSurname", "toString", "wait", "wait", "wait")),
                ReflectionDemo.namesOfPublicMethods(man));
    }

    @Test
    public void namesOfAllSuperTest() {
        assertEquals(new ArrayList<>(Collections.singletonList("java.lang.Object")), ReflectionDemo.namesOfAllSuper(man));
    }

    @Test
    public void executeTest() {
        final int[] numEx = {0};
        class Ex implements Executable {
            @Override
            public void execute() {
                numEx[0]++;
            }
        }
        ArrayList<?> ExOrNo = new ArrayList<>(Arrays.asList(
                new Ex(), man,
                new String[]{"human", "HuMaN"},
                -456, 0.234657,
                new Ex(), new Ex()));
        assertTrue(ReflectionDemo.execute(ExOrNo) == numEx[0] && numEx[0] == 3);
    }

    @Test
    public void gettersAndSettersTest() {
        assertEquals(new ArrayList<>(Arrays.asList("getClass", "getDateOfBirth", "getMiddleName", "getName", "getSurname",
                "setDateOfBirth", "setMiddleName", "setName", "setSurname")),
                ReflectionDemo.gettersAndSetters(man));
    }
}