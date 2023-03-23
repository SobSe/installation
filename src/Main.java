import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите папку установки игры");
        String installDirectory = scanner.nextLine();
        scanner.close();

        Installer installer = new Installer(installDirectory);
        installer.install();
    }
}