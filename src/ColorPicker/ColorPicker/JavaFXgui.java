package ColorPicker;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ColorPicker.colorFXwindow.userColor;
import static ColorPicker.fileWriter.fWConstructor;
import static ColorPicker.fileWriter.writeLafFile;


public class JavaFXgui extends Application implements EventHandler<ActionEvent> {
    private Button editorButton = new Button();
    static Text notificationText = new Text(50, 50, "");
    private static Desktop desktop = Desktop.getDesktop();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Change up your IntelliJ");
        primaryStage.setWidth(300);
        primaryStage.setHeight(250);


        VBox vbox = new VBox();
        vbox.setLayoutX(20);
        vbox.setLayoutY(20);

        editorButton.setText("Set Editor Color");
        editorButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new colorFXwindow().start(new Stage());
            }
        });

        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Dark");
        RadioButton rb2 = new RadioButton("Light");
        rb1.setUserData("Dark");
        rb2.setUserData("Light");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    if (group.getSelectedToggle().getUserData().equals("Light")) {
                        writeLafFile(false);

                    } else {
                        writeLafFile(true);
                    }

                }
            }
        });

        JavaFxFileChooser(primaryStage, vbox);
        Button confirm = new Button("Apply");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (userColor != null) {
                    createFile();
                }
            }
        });


        vbox.getChildren().add(editorButton);
        vbox.getChildren().add(rb1);
        vbox.getChildren().add(rb2);
        vbox.getChildren().add(confirm);
        vbox.getChildren().add(notificationText);

        vbox.setSpacing(20);
        ((Group) scene.getRoot()).getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void createFile() {
        try {
            fWConstructor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == editorButton) {
        }

    }

    public static void textNotifier(String text) {
        notificationText = new Text(50, 50, text);
    }

    public static void JavaFxFileChooser(Stage primaryStage, VBox vBox) {
        final FileChooser fileChooser = new FileChooser();

        final Button openButton = new Button("Open a Picture...");
        final Button openMultipleButton = new Button("Open Pictures...");

        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(primaryStage);
                        if (file != null) {
                            openFile(file);
                        }
                    }
                });

        openMultipleButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        List<File> list =
                                fileChooser.showOpenMultipleDialog(primaryStage);
                        if (list != null) {
                            for (File file : list) {
                                openFile(file);
                            }
                        }
                    }
                });
        vBox.getChildren().addAll(openButton, openMultipleButton);
    }

    private static void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    JavaFXgui.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

}