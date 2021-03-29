package Java09.src.cz.cuni.mff.java.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileLines {

    public static void main(String[] args) {
        try {
            Stream<String> lines = Files.lines(Paths.get("C:\\Users\\tamas\\Documents\\CUNIMFF\\Java\\PrepareExam\\src\\Java09\\src\\cz\\cuni\\mff\\java\\stream\\FileLines.java"));
            //System.out.println(lines.filter(s -> s.length()> 80).count());
            //lines.filter(s -> s.length() > 80).forEach(System.out::println);
            lines.sorted((a, b) -> a.length() - b.length()).forEach(System.out::println);
            //lines.sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
