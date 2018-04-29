import java.io.File;
import java.util.UUID;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {
        File currentDirectory = getCurrentDirectory();
        File[] files = currentDirectory.listFiles();
        System.out.println("Started renaming files in the directory" + currentDirectory.getAbsolutePath());
        System.out.println("The number of files is " + files.length);
        Stream.of(files).forEach(file -> renameFile(file));
    }

    private static File getCurrentDirectory() {
        return new File(".");
    }

    private static void renameFile(File target) {
        if (canBeRenamed(target)) {
            String newName = getNewName();
            File newFile = new File(newName);
            System.out.println(String.format("\n Renaming target file %s to  %s", target.getName(), newName));
            target.renameTo(newFile);
        }
    }

    private static String getNewName() {
        // Generating some short unique name
        return UUID.randomUUID().toString() + ".jpg";
    }

    private static boolean canBeRenamed(File target) {
        // We don't need to rename directories
        if (target.isDirectory()) {
            return false;
        }
        String fileName = target.getName();
        int indexOfDot = fileName.lastIndexOf(".");
        if (indexOfDot >= 0) {
            // Getting file extension
            String extension = fileName.substring(indexOfDot + 1);
            if (extension.trim().isEmpty()) {
                return true;
            }
        // If dot isn't found index is -1. This case works only for files without extension
        } else {
            return true;
        }
        // Default behavior: no renaming
        return false;
    }

}
