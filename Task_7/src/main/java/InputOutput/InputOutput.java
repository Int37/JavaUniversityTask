package InputOutput;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputOutput {

    //1
    public static void writeToBinaryStream(OutputStream os, @NotNull int[] arr) throws IOException {
        try (DataOutputStream outStream = new DataOutputStream(os)) {
            for (int item : arr)
                outStream.writeInt(item);
        }
    }

    public static void readFromBinaryStream(InputStream stream, @NotNull int[] arr) throws IOException {
        try (DataInputStream inStream = new DataInputStream(stream)) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = inStream.readInt();
             }
        }
    }

    //2
    @NotNull
    public static CharArrayWriter writeToCharStream(@NotNull int[] arr) {
        CharArrayWriter outStream = new CharArrayWriter();
        for (int item : arr)
            outStream.append(Integer.toString(item)).append(" ");
        outStream.flush();
        return outStream;
    }

    public static void readFromCharStream(Reader stream, @NotNull Integer[] arr) {
        Scanner scanner = new Scanner(stream);
        for (int i = 0; i < arr.length; i++) {
            if (!scanner.hasNext())
                return;
            arr[i] = Integer.parseInt(scanner.next());
        }
    }

    //3
    @NotNull
    public static int[] readFromPosition(String path, int pos) throws IOException {
        /*StringBuilder out = new StringBuilder();
        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            file.seek(pos);
            int ch = file.read();
            while (ch != -1) {
                out.append((char) ch);
                ch = file.read();
            }
        }
        List<Integer> res =new ArrayList<>();
        String[] strings= out.toString().split( " ");
        for (String string:strings
             ) {
            if(string.length()>0) {
                res.add(Integer.valueOf(string));
            }


        }



        return (Integer[] ) res.toArray();


        String content = Files.lines(Paths.get(String.valueOf(path))).reduce("", String::concat);
        content = content.substring(pos);
        String[] strings = content.split(" ");
        List<Integer> res = new ArrayList<>();
        for (String string : strings
        ) {
            if (string.length() > 0) {
                res.add(Integer.valueOf(string));
            }

        }

        return (Integer[] ) res.toArray();*/

        List<Integer> numbers = new ArrayList<Integer>();

        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            file.seek(pos);
            int ch = file.read();
            StringBuilder out= new StringBuilder();
            while (ch != -1) {
                out.append((char) ch);
                ch = file.read();
            }


            for (String part : out.toString().split("\\s")) {
                numbers.add(Integer.parseInt(part));
            }



        }

        int[] res = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            res[i] = numbers.get(i);

        }
        return res;
    }
//4
    @NotNull
    public static List<File> filesWithExtension(String catalog, String extension) {
        File curCatalog = new File(catalog);
        if (!curCatalog.isDirectory())
            throw new IllegalArgumentException("Не каталог!");
        File[] listFile = curCatalog.listFiles();
        if (listFile == null)
            return new ArrayList<>();
        List<File> out = new ArrayList<>(listFile.length);
        for (File f : listFile) {
            if (f.isFile() && f.getName().endsWith(extension))
                out.add(f);
        }
        return out;
    }

    //5
    @NotNull
    public static List<File> regExMatchFiles(String catalog, String regEx) {
        File curCatalog = new File(catalog);
        File[] listFile = curCatalog.listFiles();
        if (listFile == null)
            throw new IllegalArgumentException("Передан не каталог!");
        List<File> out = new ArrayList<>(listFile.length);
        // Заходим в рекурсию
        addFilesInDir(curCatalog, regEx, out);
        return out;
    }

    private static void addFilesInDir(@NotNull File curCatalog, String regEx, List<File> list) {
        File[] listFile = curCatalog.listFiles();
        for (File f : listFile) {
            if (Pattern.matches(regEx, f.getName()))
                list.add(f);
            if (f.isDirectory())
                addFilesInDir(f, regEx, list);
        }
    }
}