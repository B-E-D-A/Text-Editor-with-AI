package org.hse.brina.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.hse.brina.Main;
import richtext.RichTextDemo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainWindowController управляет главным окном приложения,
 * отображает документы, доступные пользователю.
 */
public class MainWindowController implements Initializable {
    @FXML
    public HBox imageHBox;
    @FXML
    private Button Enter;
    @FXML
    private TextField EnterIdField;
    @FXML
    private Button collab;
    @FXML
    private Button create_new;
    @FXML
    private Button my;

    private void openButton(Stage stage, String fxmlView) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlView));
        Parent sceneLoader = loader.load();
        Scene scene = new Scene(sceneLoader, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
    }

    @FXML
    private void openCreateButton() {
        Stage stage = (Stage) create_new.getScene().getWindow();
        RichTextDemo richTextWindow = new RichTextDemo();
        richTextWindow.start(stage);
    }


    @FXML
    private void openCollabButton() {
        Stage stage = (Stage) collab.getScene().getWindow();
        try {
            openButton(stage, "/org/hse/brina/views/collab-view.fxml");
        } catch (IOException e) {
            System.err.println("Scene configuration file not found. " + e.getMessage());
        }
    }

    @FXML
    private void openMyProdButton() {
        Stage stage = (Stage) my.getScene().getWindow();
        try {
            openButton(stage, "/org/hse/brina/views/my-projects-view.fxml");
        } catch (IOException e) {
            System.err.println("Scene configuration file not found. " + e.getMessage());
        }
    }

    @FXML
    private void openEnterButton() {
        Stage stage = (Stage) Enter.getScene().getWindow();
        try {
            openButton(stage, "/org/hse/brina/views/enter-id-collab-view.fxml");
        } catch (IOException e) {
            System.err.println("Scene configuration file not found. " + e.getMessage());
        }
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HBox.setHgrow(imageHBox, Priority.ALWAYS);
    }
}