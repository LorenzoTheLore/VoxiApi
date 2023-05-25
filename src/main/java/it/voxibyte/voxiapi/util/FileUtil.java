package it.voxibyte.voxiapi.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static boolean checkFile(File file) {
        if(!file.exists()) {
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
                return true;
            } catch (IOException e) {
                System.out.println("Failed to create file " + file.getName());
            }
        }
        return false;
    }
}
