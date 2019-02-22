
   package Controller;

   import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
   import java.sql.SQLException;
   import java.util.ArrayList;

import Model.Customer;
import Model.Management;
import javafx.collections.FXCollections;
   import javafx.collections.ObservableList;

   public class CustomerDAO {
      public static ArrayList<Customer> dbArrayList1 = new ArrayList<>();
     
      
      //1. 회원 등록하는 함수
      public static int insertCustomerData(Customer customer) {
         //1.1 데이타베이스에 회원관리테이블에 입력하는 쿼리문
         StringBuffer insertCustomer = new StringBuffer();
         insertCustomer.append("insert into customertbl ");
         insertCustomer.append("(customerNumber,name,gender,birthday,joinDate,phoneNumber) ");
         insertCustomer.append("values ");
         insertCustomer.append("(?,?,?,?,?,?) ");
         //1.2 데이타베이스 Connection을 가져와야 한다.
         Connection con = null;
         //1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
         PreparedStatement psmt=null;
         int count=0;
         try {
            con = DBUtility.getConnection();
            psmt = con.prepareStatement(insertCustomer.toString());
            //1.4 쿼리문에 실제 데이타를 연결한다.
            psmt.setString(1, customer.getCustomerNumber());
            psmt.setString(2, customer.getName());
            psmt.setString(3, customer.getGender());
            psmt.setString(4, customer.getBirthday());
            psmt.setString(5, customer.getJoinDate());
            psmt.setString(6, customer.getPhoneNumber());
            //1.5 실제데이타를 연결한 쿼리문을 실행한다.
            count = psmt.executeUpdate();
            if(count==0) {
               RootController.callAlert("삽입 쿼리 실패 : 삽입 쿼리문이 실패했습니다.");
               return count;
            }
         } catch (SQLException e) {
        	 RootController.callAlert("삽입 실패 : 데이타베이스 삽입이 실패했습니다.");
         } finally {
            //1.6 자원객체를 닫아준다.
            try {
               if(psmt != null) { psmt.close(); }
               if(con != null) { con.close(); }
            } catch (SQLException e) { 
            	RootController.callAlert("자원 닫기 실패 : psmt, con 닫기 실패.");
            }
         }
         return count;
      }   

   

   //2. 테이블에서 전체내용을 모두 가져오는 함수
   public static ArrayList<Customer> getStudentTotalData(){
      //2.1 데이타베이스에 테이블에 있는 레코드를 모두 가져오는 쿼리문
            String selectCustomer = "select * from customertbl";
            //2.2 데이타베이스 Connection을 가져와야 한다.
            Connection con = null;
            //2.3 쿼리문을 실행해야할 Statement를 만들어야한다.
            PreparedStatement psmt=null;
            //2.4 쿼리문을 실행하고나서 가져와야할 레코드를 담고있는 보자기 객체
            ResultSet rs = null;
            try {
               con = DBUtility.getConnection();
               psmt = con.prepareStatement(selectCustomer);
         
               //1.5 실제데이타를 연결한 쿼리문을 실행한다.(번개를 치는 것)
               //executeQuery() 쿼리문을 실행해서 결과를 가져올때 사용하는 번개문
               //executeUpdate() 쿼리문을  실행해서 테이블에 저장을 할때 사용하는 번개문
               rs = psmt.executeQuery();
               if(rs==null) {
            	   RootController.callAlert("select 실패 : select 쿼리문이 실패했습니다.");
                  return null;
               }
               while(rs.next()) {
            	   Customer customer = new Customer(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                  dbArrayList1.add(customer);
               }
            } catch (SQLException e) {
            	RootController.callAlert("삽입 실패 : 데이타베이스 삽입이 실패했습니다.");
            } finally {
               //1.6 자원객체를 닫아준다.
               try {
                  if(psmt != null) { psmt.close(); }
                  if(con != null) { con.close(); }
               } catch (SQLException e) { 
                  RootController.callAlert("자원 닫기 실패 : psmt, con 닫기 실패.");
               }
            }
      
      return dbArrayList1;
   }

  //3. 테이블뷰에서 선택한 레코드를 데이타베이스에서 삭제하는 함수
   public static int deleteCustomerData(String no) {
      //3.1 데이타베이스에 테이블에 있는 레코드를 삭제하는 쿼리문
      String deleteCustomer = "delete from customertbl where customerNumber = ? ";
      //3.2 데이타베이스 Connection을 가져와야 한다.
      Connection con = null;
      //3.3 쿼리문을 실행해야할 Statement를 만들어야한다.
      PreparedStatement psmt=null;
      //3.4 쿼리문을 실행하고나서 가져와야할 레코드를 담고있는 보자기 객체
      int count=0;
      try {
         con = DBUtility.getConnection();
         psmt = con.prepareStatement(deleteCustomer);
         psmt.setString(1, no);
         //3.5 실제데이타를 연결한 쿼리문을 실행한다.(번개를 치는 것)
         //executeQuery() 쿼리문을 실행해서 결과를 가져올때 사용하는 번개문
         //executeUpdate() 쿼리문을  실행해서 테이블에 저장을 할때 사용하는 번개문
         count = psmt.executeUpdate();
         if(count==0) {
        	 RootController.callAlert("delete 실패 : delete 쿼리문이 실패했습니다.");
            return count;
         }
      } catch (SQLException e) {
         RootController.callAlert("삭제 실패 : 데이타베이스 삭제가 실패했습니다.");
      } finally {
         //3.6 자원객체를 닫아준다.
         try {
            if(psmt != null) { psmt.close(); }
            if(con != null) { con.close(); }
         } catch (SQLException e) { 
        	 RootController.callAlert("자원 닫기 실패 : psmt, con 닫기 실패.");
         }
      }
      return count;
   }
   
  //4. 테이블뷰에서 수정한 레코드를 데이타베이스 테이블에 수정하는함수.
   public static int updateCustomerData(Customer customer) {
      //1.1 데이타베이스에 Customer테이블에 수정하는 쿼리문
            StringBuffer updateCustomer = new StringBuffer();
            updateCustomer.append("update customertbl set ");
            updateCustomer.append("name=?,gender=?,birthday=?,joinDate=?,phoneNumber=? where customerNumber=? ");
         
            //1.2 데이타베이스 Connection을 가져와야 한다.
            Connection con = null;
            //1.3 쿼리문을 실행해야할 Statement를 만들어야한다.
            PreparedStatement psmt=null;
            int count=0;
            try {
               con = DBUtility.getConnection();
               psmt = con.prepareStatement(updateCustomer.toString());
               //1.4 쿼리문에 실제 데이타를 연결한다.
               psmt.setString(1, customer.getName());
               psmt.setString(2, customer.getGender());
               psmt.setString(3, customer.getBirthday());
               psmt.setString(4, customer.getJoinDate());
               psmt.setString(5, customer.getPhoneNumber());
                psmt.setString(6, customer.getCustomerNumber());
               
               //1.5 실제데이타를 연결한 쿼리문을 실행한다.
                
               count = psmt.executeUpdate();
               if(count==0) {
            	   RootController.callAlert("수정 쿼리 실패 : 수정 쿼리문이 실패했습니다.");
                  return count;
               }
            } catch (SQLException e) {
            	RootController.callAlert("수정 실패 : 데이타베이스 수정이 실패했습니다.");
            } finally {
               //1.6 자원객체를 닫아준다.
               try {
                  if(psmt != null) { psmt.close(); }
                  if(con != null) { con.close(); }
               } catch (SQLException e) { 
                  RootController.callAlert("자원 닫기 실패 : psmt, con 닫기 실패.");
               }
            }
      return count;
   }
 
}