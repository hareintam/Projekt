import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane layout = new StackPane();
        Scene questionScene = new Scene(layout);
        primaryStage.setTitle("Vali üks õige vastusevariant");
        primaryStage.show();

        Label question = new Label();




    }
}
