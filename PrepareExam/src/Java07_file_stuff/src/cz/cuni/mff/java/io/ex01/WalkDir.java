package Java07_file_stuff.src.cz.cuni.mff.java.io.ex01;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkDir {
    public static void printDirContent(String p) {
        Path path = Path.of(p);
        if (Files.isDirectory(path)) {
            try {
                Files.list(path).forEach(System.out::println);
            } catch (IOException ex) {
                System.out.println("IOException occurred");
            }
        } else {
            System.out.println("Given path is not a directory.");
        }
    }

    public static void printDirWithSubdirs(String p) {
        Path path = Path.of(p);
        if (Files.isDirectory(path)) {
            try {
                Files.walkFileTree(path, new FileVisitor<>() {
                    private int spaces = 0;
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        for (int i = 0; i < spaces; i++) {
                            System.out.print(" ");
                        }
                        System.out.println(dir.getFileName());
                        spaces += 2;
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        for (int i = 0; i < spaces; i++) {
                            System.out.print(" ");
                        }
                        System.out.println(file.getFileName());
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        spaces -= 2;
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException ex) {
                System.out.println("IOException occurred");
            }
        } else {
            System.out.println("Given path is not a directory.");
        }
    }

    public static void main(String[] args) {
        printDirWithSubdirs(".");
    }
}
