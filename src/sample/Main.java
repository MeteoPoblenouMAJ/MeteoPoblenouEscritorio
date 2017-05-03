package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MeteoPoblenou");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Scene scene = new Scene(new Group(), 500, 400);
        scene.getStylesheets().add("src/style.css");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
