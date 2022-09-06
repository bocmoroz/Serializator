package proxy;

import java.io.File;

public class Search {

    public static boolean searchInFolder(boolean isZip, String folder, String fileName) {
        File path = new File(folder);

        String fileNameWithType = isZip ? fileName + ".zip" : fileName + ".bin";

        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory() && file.getName().equals(fileNameWithType)) {
                    return true;
                }
            }
        }

        return false;
    }
}
