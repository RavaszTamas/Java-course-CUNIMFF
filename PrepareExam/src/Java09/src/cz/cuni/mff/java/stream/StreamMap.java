package Java09.src.cz.cuni.mff.java.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamMap {

    public static void main(String[] args) {
        try {
            Stream<String> lines = Files.lines(Paths.get("C:\\Users\\tamas\\Documents\\CUNIMFF\\Java\\PrepareExam\\src\\Java09\\src\\cz\\cuni\\mff\\java\\stream\\StreamMap.java"));

            lines.map(s -> s.length()).forEach(System.out::println);
            //lines.mapToInt(String::length).forEach(System.out::println);
            //lines.map(s -> new Object[]{s, s.length()}).forEach(p -> System.out.println(p[1] + ": " + p[0]));
            //lines.map(s -> s.chars().mapToObj(c -> (char) c)).forEach(System.out::println);
            //lines.flatMap(s -> s.chars().mapToObj(c -> (char) c)).forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
