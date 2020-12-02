package util;

public interface Task {

    default String[] getInputLines() {
        String packageAsAPath = getPackageName().replace(".", "/");
        return FileReader.readInput(packageAsAPath).split("\n");
    }

    String getPackageName();

}
