package ColorPicker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class fileWriter {

    static String IDEA16Path = "C:\\Users\\Lorenz\\.IdeaIC2016\\config\\";
    static String OptionsPath = "\\options\\";
    static String colorsFolderPath = "colors\\";
    static String fileName = "laf";
    static String xmlFileType = ".xml";
    static String iclsfileType = ".icls";
    static int i = 0;
    static String filePath = null;


    static File f = new File(File.separator);

    public static File fWConstructor() throws IOException {
        boolean addNum = false;
        Path path = Paths.get(IDEA16Path + colorsFolderPath + fileName + iclsfileType); //should get user's path
        try {
            Files.createDirectories(path.getParent());
            f = new File(File.separator + IDEA16Path + colorsFolderPath + fileName + iclsfileType);

            if (f.exists()) {
                throw new IOException();
            } else {
                f.createNewFile();
                System.out.println("New Color Scheme File Created");
                addNum = false;
                filePath = IDEA16Path + colorsFolderPath + fileName + iclsfileType;
            }
            i++;
        } catch (IOException e) {
            while (Files.exists(path)) {
                i++;
                try {
                    f = new File(File.separator + IDEA16Path + colorsFolderPath + fileName + " (" + i + ")" + iclsfileType);
                    if (f.exists()) {
                        throw new IOException();
                    } else {
                        f.createNewFile();
                        filePath = IDEA16Path + colorsFolderPath + fileName + " (" + i + ")" + iclsfileType;
                    }
                } catch (IOException e1) {
                    continue;
                }
                System.out.println("File with that name already existed. New Color Scheme file created");
                addNum = true;
                break;
            }
        }
        System.out.println("The filepath is: " + filePath);

        fileWriter.write(colorSourceText.createSourceText(addNum));
        return f;
    }


    public static void write(String toWrite) throws IOException {
        try {
            BufferedWriter p = new BufferedWriter
                    (new FileWriter(f, false));
            p.write(toWrite);
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }


    public static void writeLafFile(Boolean white) {

        Path path = Paths.get(IDEA16Path);
        try {
            Files.createDirectories(path.getParent());
            f = new File(File.separator + IDEA16Path + OptionsPath + fileName + xmlFileType);



        } catch (IOException e) {
            e.printStackTrace();
        }
        if (white) {
            try {
                write("<application>\n" +
                        "  <component name=\"LafManager\">\n" +
                        "    <laf class-name=\"com.intellij.ide.ui.laf.darcula.DarculaLaf\" />\n" +
                        "  </component>\n" +
                        "</application>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                write("<application>\n" +
                        "  <component name=\"LafManager\">\n" +
                        "    <laf class-name=\"com.intellij.ide.ui.laf.IntelliJLaf\" />\n" +
                        "  </component>\n" +
                        "</application>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



