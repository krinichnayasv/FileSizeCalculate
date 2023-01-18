import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //   String folderPath = "C:/SWSETUP";

        FileUtils fileUtils = new FileUtils();
        System.out.println("Введите путь до папки:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        try {
            System.out.println(fileUtils.getCorrectSize(fileUtils.calculateFolderSize(path)));
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }
}
