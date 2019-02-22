package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RootController implements Initializable{
	public Stage stage;
	@FXML private TextField  textId;
	@FXML private PasswordField textPassword;
	@FXML private Button btnLogin;
	@FXML private Button BtnClose;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//1.Login버튼
		btnLogin.setOnAction((e)->{handlerLoginAction();});
		//2.Close버튼
		BtnClose.setOnAction((e)->{handlerCloseAction();});
	}

//1.Login버튼
private void handlerLoginAction() {
	if(!(textId.getText().equals("root")&&textPassword.getText().equals("123"))) {	
		callAlert("로그인 실패 : 다시한번 입력바랍니다.");
		textId.clear();
		textPassword.clear();
		return;
	}
	try {
		Stage mainstage=new Stage();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../View/main2.fxml"));
		Parent parent = loader.load();
		MainController mainController=loader.getController();
		mainController.mainStage=mainstage;
		callAlert("로그인 성공 : 환영합니다.");
		Scene scene=new Scene(parent);
		mainstage.setScene(scene);
		stage.close();
		mainstage.show();
	} catch (IOException e) {
		
	}
	
}
//2.Close버튼
private void handlerCloseAction() {
	Platform.exit();
}



//경고callAllt
public static void callAlert(String call) {
	Alert alert= new Alert(AlertType.INFORMATION);
	alert.setTitle("경고");
    alert.setHeaderText(call.substring(0,call.lastIndexOf(":")));
    alert.setHeaderText(call.substring(0,call.lastIndexOf(":"))+1);
    alert.setContentText(call);
    alert.showAndWait();
}
}