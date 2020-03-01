package rootfunction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RootFunction extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Root of a Function");
        primaryStage.setScene(new Scene(root, 780, 900));
        primaryStage.show();
       // initGraph()
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
