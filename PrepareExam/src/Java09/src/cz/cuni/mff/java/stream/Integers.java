package Java09.src.cz.cuni.mff.java.stream;

import java.util.Arrays;

public class Integers {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};

        Arrays.stream(arr).max().ifPresentOrElse(System.out::println, () -> System.out.println("No maximum"));
    }
}
