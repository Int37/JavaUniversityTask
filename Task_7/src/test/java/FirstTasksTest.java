import InputOutput.InputOutput;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.Arrays;
import java.util.Deque;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class FirstTasksTest {

    @Test
    public void testWriteToByteStream() throws IOException {
        byte[] expected = {0,0,0,1, 0,0,0,2,  0,0,0,3, 0,0,0,4};
        int[] inrArray= {1, 2, 3, 4};
        int[] actual;

        ByteArrayOutputStream buf = new ByteArrayOutputStream(13);
        InputOutput.writeToBinaryStream(buf, inrArray);


        assertArrayEquals(expected, buf.toByteArray());
        //InputOutput.readFromBinaryStream(buf2,actual1);
        //assertArrayEquals(excepted1,actual1);
    }
    @Test
    public void readAnWriteCharTest() throws IOException {
        int[] actual = {1, 2, 4, 3, 1, 0, 12, 0, 0, 42, 9, 1, 4};
        Integer[] actual1 = {1, 2, 4, 3, 1, 0, 12, 0, 0, 42, 9, 1, 4};
        Integer[] excepted1= {0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0};
       CharArrayWriter a = InputOutput.writeToCharStream(actual);
        CharArrayWriter outStream = new CharArrayWriter();
        outStream.append("1 2 4 3 1 0 12 0 0 42 9 1 4 ");
        outStream.flush();
       assertArrayEquals(a.toCharArray(),outStream.toCharArray());
        CharArrayReader b = new CharArrayReader("1 2 4 3 1 0 12 0 0 42 9 1 4 ".toCharArray());
        InputOutput.readFromCharStream(b,excepted1);
        assertArrayEquals(actual1,excepted1);



    }


    @Test
    public void readFromPositionTest() throws IOException {
        int[] expected = { 3, 4, 5, 6, 7, 8, 9, 0};

        int[] res = InputOutput.readFromPosition("./src/test/java/file1.txt", 4);
        for (int i = 0; i < expected.length ; i++) {
            assertEquals(expected[i],res[i]);

        }

    }

    @Test
    void getListOfFilesTest() throws IOException {
        File[] files = {new File("./src/test/java/file1.txt"),
                new File("./src/test/java/file2.mp3"),
                new File("./src/test/java/file3.txt"),
                new File("./src/test/java/sampleFolder"),
                new File("./src/test/java/emptyFolder")};
        for (int i = 0; i < 3; i++) {
            files[i].createNewFile();
        }
        files[3].mkdir();
        files[4].mkdir();

        File internalFile = new File("./src/test/java/sampleFolder/internalFile.txt");
        internalFile.createNewFile();
        File dir = Mockito.mock(File.class);

        Deque expected = Mockito.mock(Deque.class);
        Mockito.when(expected.removeLast()).thenReturn("file3.txt", "file1.txt");

        for (File str : InputOutput.filesWithExtension("TestData", "txt")) {
            assertEquals(expected.removeLast(), str.getName());
        }
    }

    @Test
    void regExMatchFilesTest() throws IOException {
        File folder = new File("RegExTest");
        folder.mkdir();

        File[] files = {new File("RegExTest/q2.txt"),
                new File("RegExTest/q.txt"),
                new File("RegExTest/q.hs"),
                new File("RegExTest/qq.txt"),
                new File("RegExTest/qqqq.txt"),
                new File("RegExTest/.txt"),
                new File("RegExTest/.d"),
                new File("RegExTest/.ddd"),
                new File("RegExTest/1.d"),
                new File("RegExTest/1.ddd"),
                new File("RegExTest/22.135")
        };

        for (File f : files) {
            f.createNewFile();
        }
        File innerFolder = new File("RegExTest/innerFolder");
        innerFolder.mkdir();

        Deque expected1 = Mockito.mock(Deque.class);
        Mockito.when(expected1.removeLast()).thenReturn(files[1].getName(), files[0].getName());
        Deque expected2 = Mockito.mock(Deque.class);
        Mockito.when(expected1.removeLast()).thenReturn(files[1].getName(), files[0].getName());
        Deque expected3 = Mockito.mock(Deque.class);
        Mockito.when(expected1.removeLast()).thenReturn(files[1].getName(), files[0].getName());

        for (File str : InputOutput.regExMatchFiles(folder.getName(), "q*/.txt")) {
            assertEquals(expected1.removeLast(), str.getName());
        }
        for (File str : InputOutput.regExMatchFiles(folder.getPath(), ".?/.d*")) {
            assertEquals(expected2.removeLast(), str.getName());
        }
        for (File str : InputOutput.regExMatchFiles(folder.getPath(), "/.[135]*")) {
            assertEquals(expected3.removeLast(), str.getName());
        }
    }
}