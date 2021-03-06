package util;

public interface Task {

    default String[] getInputLines() {
        return getInputLines("\n");
    }

    default String[] getInputLines(String linesSeparator) {
        String packageAsAPath = getPackageName().replace(".", "/");
        return FileReader.readInput(packageAsAPath).split(linesSeparator);
    }

    default String getPackageName() {
        return this.getClass().getPackageName();
    }

}
