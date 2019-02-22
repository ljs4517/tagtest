package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Model.Customer;
import Model.Management;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable{
	public Stage mainStage;
	@FXML private Button t1BtnRectify1;
	@FXML private Button t1BtnEnroll1;
	@FXML private Button t1BtnDelete1;
	@FXML private Button t2BtnRectify2;
	@FXML private Button t2BtnEnroll2;
	@FXML private Button t2BtnDelete2;
	@FXML private BarChart barChart;
	@FXML private Tab tab3;
	@FXML private TableView<Customer> t1TableView;
	@FXML private TableView<Management> t2TableView;
	ArrayList<Customer> dbArrayList;
	ArrayList<Management> dbArrayList2;
	
	ObservableList<Customer> t1ListData=FXCollections.observableArrayList();
	ObservableList<Customer>t1TableData=FXCollections.observableArrayList();
	ObservableList<Management> t2ListData=FXCollections.observableArrayList();
	ObservableList<Management>t2TableData=FXCollections.observableArrayList();
	//private Customer selectCustomer;
	private int selectCustomerIndex;
	private Customer selectCustomer;
	private int selectManagementIndex;
	private Management selectManagement;
	private boolean editFlag = false;
	private boolean flag=false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setT1TableView();
		//회원관리 화면 수정버튼
		t1BtnRectify1.setOnAction((e)->{handlerRectify1Action();});
		//회원관리 화면 등록버튼
		t1BtnEnroll1.setOnAction((e)->{handlerEnroll1Action();});
		//회원관리 화면 삭제버튼
		t1BtnDelete1.setOnAction((e)->{handlerDelete1Ation();});
		//재고관리 화면 수정버튼
		t2BtnRectify2.setOnAction((e)->{handlerRectify2Ation();});
		
		//재고관리 화면 등록버튼
		t2BtnEnroll2.setOnAction((e)->{handlerEnroll2Ation();});
		//재고관리 화면 삭제버튼
		t2BtnDelete2.setOnAction((e)->{handlerDelete2Ation();});
		//차트화면을 보여주는 버튼
		tab3.setOnSelectionChanged((e)->{handlerBarChartAtion();});
		
	}


	private void setT1TableView() {
		TableColumn tcGuestNumber=t1TableView.getColumns().get(0);
		tcGuestNumber.setCellValueFactory(new PropertyValueFactory<>("customerNumber"));
		
		TableColumn tcName=t1TableView.getColumns().get(1);
		tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn tcGender=t1TableView.getColumns().get(2);
		tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		TableColumn tcBirthDate=t1TableView.getColumns().get(3);
		tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		
		TableColumn tcJoinDate=t1TableView.getColumns().get(4);
		tcJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
		
		TableColumn tcPhoneNumber=t1TableView.getColumns().get(5);
		tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		
		TableColumn tcGoodsName=t2TableView.getColumns().get(0);
		tcGoodsName.setCellValueFactory(new PropertyValueFactory<>("goodsName"));
		
		TableColumn tcSupply=t2TableView.getColumns().get(1);
		tcSupply.setCellValueFactory(new PropertyValueFactory<>("supply"));
		
		TableColumn tcSelling=t2TableView.getColumns().get(2);
		tcSelling.setCellValueFactory(new PropertyValueFactory<>("selling"));
		
		TableColumn tcSellingAmount=t2TableView.getColumns().get(3);
		tcSellingAmount.setCellValueFactory(new PropertyValueFactory<>("sellingAmount"));
		
		TableColumn tcShelflife=t2TableView.getColumns().get(4);
		tcShelflife.setCellValueFactory(new PropertyValueFactory<>("shelflife"));
		
		TableColumn tcWarehousingDate=t2TableView.getColumns().get(5);
		tcWarehousingDate.setCellValueFactory(new PropertyValueFactory<>("warehousingDate"));
		
		
		t1TableView.setItems(t1ListData);
		dbArrayList=CustomerDAO.getStudentTotalData();
		for(Customer customer:dbArrayList) {
			t1ListData.add(customer);
		}
		
			t2TableView.setItems(t2ListData);
			dbArrayList2=ManagementDAO.getManagementTotalData();
			for(Management management:dbArrayList2) {
				t2ListData.add(management);
			}
		}





	//1번째 화면 수정창
	private void handlerRectify1Action() {
		selectCustomer=t1TableView.getSelectionModel().getSelectedItem();
		selectCustomerIndex=t1TableView.getSelectionModel().getSelectedIndex();
		try {
		Stage modifyStage1=new Stage();
		FXMLLoader Rectify1=new FXMLLoader(getClass().getResource("../View/management1.fxml"));
		Parent parent;
		parent = Rectify1.load();
		ObservableMap<String, Object> fxmlNamespace=Rectify1.getNamespace();
		Button t1BtnClose=(Button)parent.lookup("#t1BtnClose");//취소버튼=t1BtnClose의 경로를 지정해주지 않았기에Button을 형변환해준다 
		Button t1BtnEnroll=(Button)parent.lookup("#t1BtnEnroll");//등록버튼=t1BtnEnroll의 경로를 지정해주지 않았기에Button을 형변환해준다
		TextField t1GuestNumber=(TextField)parent.lookup("#t1GuestNumber");
		TextField t1Name=(TextField)parent.lookup("#t1Name");
		RadioButton t1Man=(RadioButton)parent.lookup("#t1Man");
		RadioButton t1Woman=(RadioButton)parent.lookup("#t1Woman");
		DatePicker t1BirthDate=(DatePicker)parent.lookup("#t1BirthDate");
		DatePicker t1JoinDate=(DatePicker)parent.lookup("#t1JoinDate");
		TextField t1PhoneNumber=(TextField)parent.lookup("#t1PhoneNumber");
		ToggleGroup group=(ToggleGroup)fxmlNamespace.get("group");
		t1BtnEnroll.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Customer customer = new Customer(t1GuestNumber.getText(),t1Name.getText(),group.getSelectedToggle().getUserData().toString(),t1BirthDate.getValue().toString(),t1JoinDate.getValue().toString(),t1PhoneNumber.getText());

				int count = CustomerDAO.updateCustomerData(customer);
				if (count != 0) {
					t1ListData.remove(selectCustomerIndex);
					t1ListData.add(selectCustomerIndex, customer);
					int arrayIndex = dbArrayList.indexOf(selectCustomer);
					dbArrayList.set(arrayIndex, customer);
					// 초기화 버튼을 눌렀을때 함수 콜
					RootController.callAlert("수정완료 : " + selectCustomer.getName() + "님이 수정되었습니다.");
				
				}
			}
			});
		Scene scene=new Scene(parent);
		modifyStage1.setScene(scene);
		modifyStage1.close();
		modifyStage1.show();
		t1BtnClose.setOnAction((e)->{//취소버튼=함수를 따로 만들필요없이 함수안에서 close();로 닫아준다
			modifyStage1.close();
		});
	} catch (IOException e) {
	}
		
	
	}
	//1번째 화면 등록창
	private void handlerEnroll1Action() {
		try {
			Stage addStage1=new Stage();
			FXMLLoader Enroll1=new FXMLLoader(getClass().getResource("../View/management1.fxml"));
			Parent parent;
			parent = Enroll1.load();
			ObservableMap<String, Object> fxmlNamespace=Enroll1.getNamespace();
			Button t1BtnClose=(Button)parent.lookup("#t1BtnClose");
			Button t1BtnEnroll=(Button)parent.lookup("#t1BtnEnroll");
			TextField t1GuestNumber=(TextField)parent.lookup("#t1GuestNumber");
			TextField t1Name=(TextField)parent.lookup("#t1Name");
			DatePicker t1BirthDate=(DatePicker)parent.lookup("#t1BirthDate");
			DatePicker t1JoinDate=(DatePicker)parent.lookup("#t1JoinDate");
			TextField t1PhoneNumber=(TextField)parent.lookup("#t1PhoneNumber");
			ToggleGroup group=(ToggleGroup)fxmlNamespace.get("group");
			
			t1BtnEnroll.setOnAction(new EventHandler<ActionEvent>() {	
				@Override
				public void handle(ActionEvent event) {
					Customer customer=new Customer(t1GuestNumber.getText(), t1Name.getText(), group.getSelectedToggle().getUserData().toString(), t1BirthDate.getValue().toString(), t1JoinDate.getValue().toString(), t1PhoneNumber.getText());			
					t1ListData.add(customer);
					int count=CustomerDAO.insertCustomerData(customer);
					if(count!=0) {
						RootController.callAlert("입력성공 : 입력성공했습니다.");
					}
				}
			});
			Scene scene=new Scene(parent);
			addStage1.setScene(scene);
			addStage1.close();
			addStage1.show();
			t1BtnClose.setOnAction((e)->{
				addStage1.close();
			});
		} catch (IOException e) {
		}
	}

	//1번째 화면 삭제버튼
		private void handlerDelete1Ation() {
			selectCustomer=t1TableView.getSelectionModel().getSelectedItem();
			selectCustomerIndex=t1TableView.getSelectionModel().getSelectedIndex();
			int count = CustomerDAO.deleteCustomerData(selectCustomer.getCustomerNumber());
			if (count != 0) {
				t1ListData.remove(selectCustomerIndex);
				dbArrayList.remove(selectCustomer);
				RootController.callAlert("삭제완료 :  삭제되었습니다.");
			} else {
				return;
			}
		}

		
	//2번째 화면 수정창
	private void handlerRectify2Ation() {
		selectManagement=t2TableView.getSelectionModel().getSelectedItem();
		selectManagementIndex=t2TableView.getSelectionModel().getSelectedIndex();
		try {
		Stage modifyStage2=new Stage();
		FXMLLoader Rectify2=new FXMLLoader(getClass().getResource("../View/management2.fxml"));
		Parent parent;
		parent = Rectify2.load();
		ObservableMap<String, Object> fxmlNamespace=Rectify2.getNamespace();
		Button t2BtnClose=(Button)parent.lookup("#t2BtnClose");//취소버튼=t1BtnClose의 경로를 지정해주지 않았기에Button을 형변환해준다 
		Button t2BtnEnroll=(Button)parent.lookup("#t2BtnEnroll");//등록버튼=t1BtnEnroll의 경로를 지정해주지 않았기에Button을 형변환해준다
		TextField t2ProductName=(TextField)parent.lookup("#t2ProductName");
		TextField t2Provision=(TextField)parent.lookup("#t2Provision");
		TextField t2Sale=(TextField)parent.lookup("#t2Sale");
		TextField t2Quantity=(TextField)parent.lookup("#t2Quantity");
		DatePicker t2ShedDate=(DatePicker)parent.lookup("#t2ShedDate");
		DatePicker t2ExpirationDate=(DatePicker)parent.lookup("#t2ExpirationDate");
		t2BtnEnroll.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Management management = new Management(t2ProductName.getText(),t2Provision.getText(),t2Sale.getText(),t2Quantity.getText(),t2ShedDate.getValue().toString(),t2ExpirationDate.getValue().toString());
				int count = ManagementDAO.updateManagementData(management);
				if (count != 0) {
					t2ListData.remove(selectManagementIndex);
					t2ListData.add(selectManagementIndex, management);
					int arrayIndex = dbArrayList2.indexOf(selectManagement);
					dbArrayList2.set(arrayIndex, management);
					// 초기화 버튼을 눌렀을때 함수 콜
					RootController.callAlert("수정완료 : " + selectManagement + " 수정되었습니다.");
				}
			}
			});
		Scene scene=new Scene(parent);
		modifyStage2.setScene(scene);
		modifyStage2.show();
		t2BtnClose.setOnAction((e)->{//취소버튼=함수를 따로 만들필요없이 함수안에서 close();로 닫아준다
			modifyStage2.close();
		});
	} catch (IOException e) {
	}
		
	
	}
	
	//2번째 화면 등록창
	private void handlerEnroll2Ation() {
		try {
			Stage addStage2=new Stage();
			FXMLLoader Enroll2=new FXMLLoader(getClass().getResource("../View/management2.fxml"));
			Parent parent;
			parent = Enroll2.load();
			ObservableMap<String, Object> fxmlNamespace=Enroll2.getNamespace();
			Button t2BtnClose=(Button)parent.lookup("#t2BtnClose");
			Button t2BtnEnroll=(Button)parent.lookup("#t2BtnEnroll");
			TextField t2ProductName=(TextField)parent.lookup("#t2ProductName");
			TextField t2Provision=(TextField)parent.lookup("#t2Provision");
			TextField t2Sale=(TextField)parent.lookup("#t2Sale");
			TextField t2Quantity=(TextField)parent.lookup("#t2Quantity");
			DatePicker t2ShedDate=(DatePicker)parent.lookup("#t2ShedDate");
			DatePicker t2ExpirationDate=(DatePicker)parent.lookup("#t2ExpirationDate");
			
			t2BtnEnroll.setOnAction(new EventHandler<ActionEvent>() {	
				@Override
				public void handle(ActionEvent event) {
					Management management=new Management(t2ProductName.getText(), t2Provision.getText(), t2Sale.getText(), t2Quantity.getText(), t2ShedDate.getValue().toString(), t2ExpirationDate.getValue().toString());			
					t2ListData.add(management);
					int count=ManagementDAO.insertManagementData(management);
					if(count!=0) {
						RootController.callAlert("입력성공 : 입력성공했습니다.");
						flag=false;
					}
				}
			});
			Scene scene=new Scene(parent);
			addStage2.setScene(scene);
			addStage2.close();
			addStage2.show();
			t2BtnClose.setOnAction((e)->{
				addStage2.close();
			});
		} catch (IOException e) {
		}
	}
	
	//2번째 화면 삭제버튼
	private void handlerDelete2Ation() {
		selectManagement=t2TableView.getSelectionModel().getSelectedItem();
		selectManagementIndex=t2TableView.getSelectionModel().getSelectedIndex();
		int count = ManagementDAO.deleteManagementData2(selectManagement.getGoodsName());
		if (count != 0) {
			t2ListData.remove(selectManagementIndex);
			dbArrayList2.remove(selectManagement);
			RootController.callAlert("삭제완료 :  삭제되었습니다.");
		} else {
			return;
		}
	}
	//차트화면을 보여주는 버튼
		private void handlerBarChartAtion() {
			if(flag==false) {
				XYChart.Series seriesScore=new XYChart.Series<>();
				seriesScore.setName("재고현황");
				ObservableList scoreList=FXCollections.observableArrayList();
				for(int i=0;i<t2ListData.size();i++) {
					scoreList.add(new XYChart.Data<>(t2ListData.get(i).getGoodsName(),Integer.parseInt(t2ListData.get(i).getSellingAmount())));
				}
				seriesScore.setData(scoreList);
				barChart.setAnimated(false);
				barChart.getData().add(seriesScore);
				
				flag=true;
			}
			
		}
			}
