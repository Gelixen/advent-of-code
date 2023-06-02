package util;

public interface Task {

    default String[] getInputLines() {
        String packageAsAPath = getPackageName().replace(".", "/");
        return FileReader.readInput(packageAsAPath).lines().toArray(String[]::new);
    }

    default String[] getInputLines(String linesSeparator) {
        String packageAsAPath = getPackageName().replace(".", "/");
        return FileReader.readInput(packageAsAPath).split(linesSeparator);
    }

    default String getPackageName() {
        return this.getClass().getPackageName();
    }

}
