package ColorPicker;

import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Created by Lorenz on 2016-04-10.
 */
public class colorSourceText {

    static String newSchemeTemplate = null;

    public static String createSourceText(boolean addFileNum) {

        String colorText = colorChangeText(colorFXwindow.userColor);
        String numAdder = "";
        if (addFileNum) {
            numAdder = " (" + fileWriter.i + ")";
        }
        newSchemeTemplate = "<scheme name=\"" + fileWriter.fileName + numAdder +
                "\" version=\"" + 142 + "\" parent_scheme=\"Default\">\n" +
                "  <option name=\"LINE_SPACING\" value=\"1.0\" />\n" +
                "  <option name=\"EDITOR_FONT_SIZE\" value=\"19\" />\n" +
                "  <option name=\"CONSOLE_FONT_NAME\" value=\"Monospaced\" />\n" +
                "  <option name=\"CONSOLE_FONT_SIZE\" value=\"12\" />\n" +
                "  <option name=\"EDITOR_FONT_NAME\" value=\"Monospaced\" />\n" +
                colorText +
                "</scheme>";

        return newSchemeTemplate;
    }

    private static String colorChangeText(Color color) {
        return "<attributes>\n" +
                "    <option name=\"TEXT\">\n" +
                "      <value>\n" +
                "        <option name=\"FOREGROUND\" value=\"0\" />\n" +
                "        <option name=\"BACKGROUND\" value=\"" + color + "\" />\n" +
                "      </value>\n" +
                "    </option>\n" +
                "  </attributes>\n";
    }
}
