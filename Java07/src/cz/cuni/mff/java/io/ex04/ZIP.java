package cz.cuni.mff.java.io.ex04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIP {

    public static void createArchive(Path dir) {
        Path path = dir.resolveSibling(dir.getFileName().toString() + ".zip");
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(path)))) {
            zos.setComment("Created with Java");
            Files.list(dir).forEach(p -> {
                if (Files.isDirectory(p)) {
                    System.out.println("Skipping subdir " + p.getFileName());
                } else {
                    System.out.println("Saving file " + p.getFileName());
                    try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(p))) {
                        zos.putNextEntry(new ZipEntry(p.getFileName().toString()));
                        int c;
                        while((c = in.read()) != -1) {
                            zos.write(c);
                        }
                    } catch (IOException ex) {
                        System.out.println("IOException occurred");
                    }
                }
            });
        } catch (IOException ex) {
          System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        createArchive(Path.of("C:\\Users\\tamas\\Documents\\CUNIMFF\\Java\\Java07\\src"));
    }
}
