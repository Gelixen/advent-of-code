package _2022.day7;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
public class NoSpaceLeftOnDevice implements SolvableTask {

    private static final String COMMAND_SPLITTER = " ";
    private static final String EXECUTABLE_COMMAND_IDENTIFIER = "$";
    private static final String DIR_COMMAND_IDENTIFIER = "dir";
    private static final String CHANGE_DIRECTORY_COMMAND_IDENTIFIER = "cd";
    private static final String SHOW_FILES_COMMAND_IDENTIFIER = "ls";
    private static final int MAX_ALLOWED_DIR_SIZE = 100_000;

    public static void main(String[] args) {
        new NoSpaceLeftOnDevice().solve();
    }

    @Override
    public void solve() {
        String[] inputLines = getInputLines();

        List<DirectoryWithTotalSize> directoriesWithTotalSize = handleInputData(inputLines);

        long sumOfLessThanAllowedSizeDirs = directoriesWithTotalSize.stream()
                .filter(directoryWithSize -> directoryWithSize.totalSize() <= MAX_ALLOWED_DIR_SIZE)
                .mapToLong(DirectoryWithTotalSize::totalSize)
                .sum();
        
        log.info(String.valueOf(sumOfLessThanAllowedSizeDirs));
    }

    private List<DirectoryWithTotalSize> handleInputData(String[] inputLines) {
        Map<String, List<String>> subDirectoriesMap = new HashMap<>();
        Map<String, Long> directoriesSizeMap = new HashMap<>();

        String currentDir = "/";

        for (String line : inputLines) {
            String[] parts = line.split(COMMAND_SPLITTER);
            switch (parts[0]) {
                case EXECUTABLE_COMMAND_IDENTIFIER -> {
                    String executableCommand = parts[1];
                    switch (executableCommand) {
                        case CHANGE_DIRECTORY_COMMAND_IDENTIFIER -> {
                            String directoryName = parts[2];
                            if (directoryName.equals("..")) {
                                currentDir = currentDir.replaceAll("\\.[a-z]+$", "");
                            } else {
                                if (!directoryName.equals("/")) {
                                    currentDir += "." + directoryName;
                                }
                            }
                        }
                        case SHOW_FILES_COMMAND_IDENTIFIER -> {
                            subDirectoriesMap.putIfAbsent(currentDir , new ArrayList<>());
                            directoriesSizeMap.putIfAbsent(currentDir, 0L);
                        }
                    }
                }
                case DIR_COMMAND_IDENTIFIER -> {
                    String directoryName = parts[1];
                    subDirectoriesMap.get(currentDir).add(currentDir + "." + directoryName);
                }
                // File
                default -> {
                    String fileSize = parts[0];
                    long totalSize = directoriesSizeMap.get(currentDir) + Long.parseLong(fileSize);
                    directoriesSizeMap.replace(currentDir, totalSize);
                }
            }
        }

        log.info(String.valueOf(subDirectoriesMap));
        log.info(String.valueOf(directoriesSizeMap));

        List<DirectoryWithTotalSize> directoriesWithSubDirectoriesSizes = subDirectoriesMap.keySet().stream()
                .map(directory -> {
                    long sizeWithSubDirectories = countWithSubDirectories(subDirectoriesMap, directoriesSizeMap, directory);
                    return new DirectoryWithTotalSize(directory, sizeWithSubDirectories);
                }).toList();

        log.info(String.valueOf(directoriesWithSubDirectoriesSizes));
        return directoriesWithSubDirectoriesSizes;
    }

    private long countWithSubDirectories(Map<String, List<String>> subDirectoriesMap,
                                         Map<String, Long> directoriesSizeMap,
                                         String root) {

        long parentDirectorySize = directoriesSizeMap.get(root);
        long subDirectoriesSizeSum = subDirectoriesMap.get(root).stream()
                .map(subDirectories -> countWithSubDirectories(subDirectoriesMap, directoriesSizeMap, subDirectories))
                .mapToLong(i -> i)
                .sum();
        
        return parentDirectorySize + subDirectoriesSizeSum;
    }

}