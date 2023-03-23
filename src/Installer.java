import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Formatter;

public class Installer {
    private String installDirectory;
    private StringBuilder logger = new StringBuilder();

    public Installer(String installDirectory) {
        this.installDirectory = installDirectory;
    }

    public void install() {
        createDirectory("src");
        createDirectory("src/main");
        createFile("src/main", "Main.java");
        createFile("src/main", "Utils.java");
        createDirectory("src/test");

        createDirectory("res");
        createDirectory("res/drawables");
        createDirectory("res/vectors");
        createDirectory("res/icons");

        createDirectory("savegames");

        createDirectory("temp");
        createFile("temp", "temp.txt");

        writeLog();
    }

    private void addLog(String massage) {
        Formatter formatter = new Formatter();
        Date date = new Date();
        logger.append(String.format("%1$te.%1$tm.%1$tY %1$tH:%1$tM:%1$tS " + massage, date));
        logger.append(System.lineSeparator());
    }

    private void writeLog() {
        try (FileWriter fileWriter = new FileWriter(installDirectory + "/temp/temp.txt")){
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(logger.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFile(String pathName, String fileName) {
        File file = new File(installDirectory + "/" + pathName, fileName);
        try {
            if (file.createNewFile()) {
                addLog("создание файла " + file.getPath() + " Успех");
            } else {
                addLog("создание файла " + file.getPath() + " уже создан");
            }
        } catch (IOException e) {
            addLog("создание файла " + file.getPath() + " Ошибка " + e.getMessage());
        }
    }

    private void createDirectory(String pathName) {
        File directory = new File(installDirectory + "/" + pathName);
        if (directory.mkdir()) {
            addLog("создание директории " + directory.getPath() + " Успех");
        } else {
            addLog("создание директории " + directory.getPath() + " Уже создана");
        }
    }
}
