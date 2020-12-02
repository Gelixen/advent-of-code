package util;

public interface Task {

    default String[] getInputLines() {
        String packageName = getPackageName();
        return FileReader.readInput(packageName).split("\n");
    }

    String getPackageName();

}
