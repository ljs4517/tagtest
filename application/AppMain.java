package application;

import Controller.RootController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMain extends Application{
   public static Stage stage;
   
   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception {
      FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../View/login.fxml")); //login.fxml¿¡ ¿¬°á
      Parent root= fxmlLoader.load();
      RootController rootController= fxmlLoader.getController();
      rootController.stage=stage;
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

}