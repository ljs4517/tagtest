
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
     
      
      //1. ȸ�� ����ϴ� �Լ�
      public static int insertCustomerData(Customer customer) {
         //1.1 ����Ÿ���̽��� ȸ���������̺� �Է��ϴ� ������
         StringBuffer insertCustomer = new StringBuffer();
         insertCustomer.append("insert into customertbl ");
         insertCustomer.append("(customerNumber,name,gender,birthday,joinDate,phoneNumber) ");
         insertCustomer.append("values ");
         insertCustomer.append("(?,?,?,?,?,?) ");
         //1.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
         Connection con = null;
         //1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
         PreparedStatement psmt=null;
         int count=0;
         try {
            con = DBUtility.getConnection();
            psmt = con.prepareStatement(insertCustomer.toString());
            //1.4 �������� ���� ����Ÿ�� �����Ѵ�.
            psmt.setString(1, customer.getCustomerNumber());
            psmt.setString(2, customer.getName());
            psmt.setString(3, customer.getGender());
            psmt.setString(4, customer.getBirthday());
            psmt.setString(5, customer.getJoinDate());
            psmt.setString(6, customer.getPhoneNumber());
            //1.5 ��������Ÿ�� ������ �������� �����Ѵ�.
            count = psmt.executeUpdate();
            if(count==0) {
               RootController.callAlert("���� ���� ���� : ���� �������� �����߽��ϴ�.");
               return count;
            }
         } catch (SQLException e) {
        	 RootController.callAlert("���� ���� : ����Ÿ���̽� ������ �����߽��ϴ�.");
         } finally {
            //1.6 �ڿ���ü�� �ݾ��ش�.
            try {
               if(psmt != null) { psmt.close(); }
               if(con != null) { con.close(); }
            } catch (SQLException e) { 
            	RootController.callAlert("�ڿ� �ݱ� ���� : psmt, con �ݱ� ����.");
            }
         }
         return count;
      }   

   

   //2. ���̺��� ��ü������ ��� �������� �Լ�
   public static ArrayList<Customer> getStudentTotalData(){
      //2.1 ����Ÿ���̽��� ���̺� �ִ� ���ڵ带 ��� �������� ������
            String selectCustomer = "select * from customertbl";
            //2.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
            Connection con = null;
            //2.3 �������� �����ؾ��� Statement�� �������Ѵ�.
            PreparedStatement psmt=null;
            //2.4 �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
            ResultSet rs = null;
            try {
               con = DBUtility.getConnection();
               psmt = con.prepareStatement(selectCustomer);
         
               //1.5 ��������Ÿ�� ������ �������� �����Ѵ�.(������ ġ�� ��)
               //executeQuery() �������� �����ؼ� ����� �����ö� ����ϴ� ������
               //executeUpdate() ��������  �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
               rs = psmt.executeQuery();
               if(rs==null) {
            	   RootController.callAlert("select ���� : select �������� �����߽��ϴ�.");
                  return null;
               }
               while(rs.next()) {
            	   Customer customer = new Customer(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                  dbArrayList1.add(customer);
               }
            } catch (SQLException e) {
            	RootController.callAlert("���� ���� : ����Ÿ���̽� ������ �����߽��ϴ�.");
            } finally {
               //1.6 �ڿ���ü�� �ݾ��ش�.
               try {
                  if(psmt != null) { psmt.close(); }
                  if(con != null) { con.close(); }
               } catch (SQLException e) { 
                  RootController.callAlert("�ڿ� �ݱ� ���� : psmt, con �ݱ� ����.");
               }
            }
      
      return dbArrayList1;
   }

  //3. ���̺�信�� ������ ���ڵ带 ����Ÿ���̽����� �����ϴ� �Լ�
   public static int deleteCustomerData(String no) {
      //3.1 ����Ÿ���̽��� ���̺� �ִ� ���ڵ带 �����ϴ� ������
      String deleteCustomer = "delete from customertbl where customerNumber = ? ";
      //3.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
      Connection con = null;
      //3.3 �������� �����ؾ��� Statement�� �������Ѵ�.
      PreparedStatement psmt=null;
      //3.4 �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
      int count=0;
      try {
         con = DBUtility.getConnection();
         psmt = con.prepareStatement(deleteCustomer);
         psmt.setString(1, no);
         //3.5 ��������Ÿ�� ������ �������� �����Ѵ�.(������ ġ�� ��)
         //executeQuery() �������� �����ؼ� ����� �����ö� ����ϴ� ������
         //executeUpdate() ��������  �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
         count = psmt.executeUpdate();
         if(count==0) {
        	 RootController.callAlert("delete ���� : delete �������� �����߽��ϴ�.");
            return count;
         }
      } catch (SQLException e) {
         RootController.callAlert("���� ���� : ����Ÿ���̽� ������ �����߽��ϴ�.");
      } finally {
         //3.6 �ڿ���ü�� �ݾ��ش�.
         try {
            if(psmt != null) { psmt.close(); }
            if(con != null) { con.close(); }
         } catch (SQLException e) { 
        	 RootController.callAlert("�ڿ� �ݱ� ���� : psmt, con �ݱ� ����.");
         }
      }
      return count;
   }
   
  //4. ���̺�信�� ������ ���ڵ带 ����Ÿ���̽� ���̺� �����ϴ��Լ�.
   public static int updateCustomerData(Customer customer) {
      //1.1 ����Ÿ���̽��� Customer���̺� �����ϴ� ������
            StringBuffer updateCustomer = new StringBuffer();
            updateCustomer.append("update customertbl set ");
            updateCustomer.append("name=?,gender=?,birthday=?,joinDate=?,phoneNumber=? where customerNumber=? ");
         
            //1.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
            Connection con = null;
            //1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
            PreparedStatement psmt=null;
            int count=0;
            try {
               con = DBUtility.getConnection();
               psmt = con.prepareStatement(updateCustomer.toString());
               //1.4 �������� ���� ����Ÿ�� �����Ѵ�.
               psmt.setString(1, customer.getName());
               psmt.setString(2, customer.getGender());
               psmt.setString(3, customer.getBirthday());
               psmt.setString(4, customer.getJoinDate());
               psmt.setString(5, customer.getPhoneNumber());
                psmt.setString(6, customer.getCustomerNumber());
               
               //1.5 ��������Ÿ�� ������ �������� �����Ѵ�.
                
               count = psmt.executeUpdate();
               if(count==0) {
            	   RootController.callAlert("���� ���� ���� : ���� �������� �����߽��ϴ�.");
                  return count;
               }
            } catch (SQLException e) {
            	RootController.callAlert("���� ���� : ����Ÿ���̽� ������ �����߽��ϴ�.");
            } finally {
               //1.6 �ڿ���ü�� �ݾ��ش�.
               try {
                  if(psmt != null) { psmt.close(); }
                  if(con != null) { con.close(); }
               } catch (SQLException e) { 
                  RootController.callAlert("�ڿ� �ݱ� ���� : psmt, con �ݱ� ����.");
               }
            }
      return count;
   }
 
}