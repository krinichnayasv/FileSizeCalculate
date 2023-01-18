
import net.sf.saxon.trans.SymbolicName;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileUtils {

   private static final int KILOBIT = 1024;


    public FileUtils () {        }

    public static long calculateFolderSize(String path) {

        long size = 0L;

        Path paths = Path.of(path);

        try (DirectoryStream < Path > files = Files.newDirectoryStream(paths)) {
                for (Path p : files) {
                    if (Files.isRegularFile(p)) {
                        size += Files.size(p);
                    } else {
                    size += calculateFolderSize(p.toString());
                    }
                }
            }
         catch (Exception ex) {ex.getStackTrace();}


//       или:
//        Path folder = Paths.get(path);
//        long size = 0L;
//        try (Stream<Path> walk = Files.walk(folder)) {
//            size = walk.filter(Files::isRegularFile).mapToLong(paths -> {
//                        try {
//                            return Files.size(paths);
//                        } catch (IOException ex) {
//                            ex.getStackTrace();
//                            return 0;
//                        }
//                    }).sum();
//        } catch (IOException ex) {
//            ex.getStackTrace();
//        }

        return size;
    }


    public static String getCorrectSize (long size) {
        String folderSize = "";
        double fileSize = 0.0;

        if (size < 1024 && size > 0) {
            folderSize = size + " B";
            return folderSize;
        } else {
            int n = 0;
            int check = (int) (size / KILOBIT);
            while (check > 1 && n <= 3) {
                n++;
                check = (int)(check / KILOBIT);
            }
            if (n == 1) {
                fileSize = (double) ((int) Math.round(size * 100 / KILOBIT)) / 100;
                folderSize = fileSize + " Kb";
                return folderSize;
            } else if (n == 2) {
                fileSize = (double) ((int) Math.round(size * 100 / Math.pow(KILOBIT, 2))) / 100;
                folderSize = fileSize + " Mb";
                return folderSize;
            } else {
                fileSize = (double) ((int) Math.round(size * 100 / Math.pow(KILOBIT, 3))) / 100;
                folderSize = fileSize + " Gb";
                return folderSize;
            }
        }
    }


}
