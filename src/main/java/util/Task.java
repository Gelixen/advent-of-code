package util;

@FunctionalInterface
public interface Task {

    default String[] getInputLines() {
        return getInputLines("\n");
    }

    default String[] getInputLines(String linesSeparator) {
        String packageAsAPath = getPackageName().replace(".", "/");
        return FileReader.readInput(packageAsAPath).split(linesSeparator);
    }

    String getPackageName();

}
