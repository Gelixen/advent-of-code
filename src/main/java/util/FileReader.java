package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    private FileReader() {}

    public static String readInput(String day) {
        String resource = ClassLoader.getSystemClassLoader()
                .getResource(day + "/input.txt")
                .getFile();
        String path = new File(resource).getAbsolutePath();

        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
