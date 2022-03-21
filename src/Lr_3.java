import java.io.*;
import java.util.*;

public class Lr_3 {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        System.out.println("Введите подстроку для поиска в файле");
        String podStr = new Scanner(System.in).nextLine();
        System.out.println("Введите название файла для считывания ");

        try (FileReader reader = new FileReader(new Scanner(System.in).nextLine())) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                map.put(str, countPodstr(str, podStr));
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        map.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                forEach(x -> {
                    builder.append(x.getKey()).append("\n");
                });

        System.out.println(builder.toString());
        System.out.println("Введите название файла для сохранения");

        try {
            saveFromFile(builder);
        }
        catch (ErrorWrite e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int countPodstr(String str, String podStr) {
        int count = 0;
        int index = 0;

        while (str.indexOf(podStr, index) != -1) {
            count++;
            if ((str.indexOf(podStr, index) + podStr.length()) < (str.length() - 1)) {
                index = str.indexOf(podStr, index) + podStr.length();
            }
            else {
                break;
            }
        }
        return count;
    }

    public static void saveFromFile(StringBuilder builder) throws ErrorWrite, IOException {
        FileWriter writer = new FileWriter(new Scanner(System.in).nextLine());
        writer.write(builder.toString());
        writer.close();
    }

    public class ErrorWrite extends Exception {

        public ErrorWrite(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            return " ErrorWrite ";
        }
    }
}


