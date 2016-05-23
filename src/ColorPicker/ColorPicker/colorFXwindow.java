package ColorPicker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class colorFXwindow extends Application {
    public static Color userColor;
    public static boolean hasUserSelectedNewColor = false;

    public static void colorPickerWindow(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new HBox(40), 250, 150);
        HBox box = (HBox) scene.getRoot();
        final ColorPicker colorPicker = new ColorPicker();
       // colorPicker.setValue(Color.RED);
        colorPicker.autosize();


        colorPicker.setOnAction((ActionEvent t) -> {
            userColor = colorPicker.getValue();
            hasUserSelectedNewColor = true;
           JavaFXgui.textNotifier("This will change the background color of the editor window to " + userColor);

        });
        final Text text = new Text("Please select the background color of your IntelliJ's main editor window");
        box.getChildren().addAll(colorPicker, text);

        stage.setScene(scene);
        stage.show();
    }




}

