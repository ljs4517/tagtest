package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Customer;
import Model.Management;
import javafx.fxml.FXMLLoader;

public class ManagementDAO {
	 public static ArrayList<Management> dbArrayList2 = new ArrayList<>();
	
	
	//1. ��� ����ϴ� �Լ�
	   public static int insertManagementData(Management management) {
	      //1.1 ����Ÿ���̽��� ���������̺� �Է��ϴ� ������
	      StringBuffer insertManagement = new StringBuffer();
	      insertManagement.append("insert into managementtbl ");
	      insertManagement.append("(goodsName,supply,selling,sellingAmount,shelflife,warehousingDate) ");
	      insertManagement.append("values ");
	      insertManagement.append("(?,?,?,?,?,?) ");
	      //1.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
	      Connection con = null;
	      //1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
	      PreparedStatement psmt=null;
	      int count=0;
	      try {
	         con = DBUtility.getConnection();
	         psmt = con.prepareStatement(insertManagement.toString());
	         //1.4 �������� ���� ����Ÿ�� �����Ѵ�.
	         psmt.setString(1, management.getGoodsName());
	         psmt.setString(2, management.getSupply());
	         psmt.setString(3, management.getSelling());
	         psmt.setString(4, management.getSellingAmount());
	         psmt.setString(5, management.getShelflife());
	         psmt.setString(6, management.getWarehousingDate());
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
	public static ArrayList<Management> getManagementTotalData(){
	   //2.1 ����Ÿ���̽��� ���̺� �ִ� ���ڵ带 ��� �������� ������
	         String selectManagement = "select * from managementtbl";
	         //2.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
	         Connection con = null;
	         //2.3 �������� �����ؾ��� Statement�� �������Ѵ�.
	         PreparedStatement psmt=null;
	         //2.4 �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
	         ResultSet rs = null;
	         try {
	            con = DBUtility.getConnection();
	            psmt = con.prepareStatement(selectManagement);
	      
	            //1.5 ��������Ÿ�� ������ �������� �����Ѵ�.(������ ġ�� ��)
	            //executeQuery() �������� �����ؼ� ����� �����ö� ����ϴ� ������
	            //executeUpdate() ��������  �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
	            rs = psmt.executeQuery();
	            if(rs==null) {
	         	   RootController.callAlert("select ���� : select �������� �����߽��ϴ�.");
	               return null;
	            }
	            while(rs.next()) {
	            	Management management = new Management(rs.getString(1),rs.getString(2),
	                     rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
	               dbArrayList2.add(management);
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
	   
	   return dbArrayList2;
	}



	public static int deleteManagementData2(String goodsName) {
		 //3.1 ����Ÿ���̽��� ���̺� �ִ� ���ڵ带 �����ϴ� ������
		   String deleteManagement = "delete from Managementtbl where goodsName = ? ";
		   //3.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
		   Connection con = null;
		   //3.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		   PreparedStatement psmt=null;
		   //3.4 �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
		   int count=0;
		   try {
		      con = DBUtility.getConnection();
		      psmt = con.prepareStatement(deleteManagement);
		      psmt.setString(1, goodsName);
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



	public static int updateManagementData(Management management) {
		 //1.1 ����Ÿ���̽��� ���̺� �����ϴ� ������
        StringBuffer updateManagement = new StringBuffer();
        updateManagement.append("update managementtbl set ");
        updateManagement.append("supply=?,selling=?,sellingAmount=?,shelflife=?,warehousingDate=? where goodsName=?");
        //1.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
        Connection con = null;
        //1.3 �������� �����ؾ��� Statement�� �������Ѵ�.
        PreparedStatement psmt=null;
        int count=0;
        try {
           con = DBUtility.getConnection();
           psmt = con.prepareStatement(updateManagement.toString());
           //1.4 �������� ���� ����Ÿ�� �����Ѵ�.
           psmt.setString(1, management.getSupply());
           psmt.setString(2, management.getSelling());
           psmt.setString(3, management.getSellingAmount());
           psmt.setString(4, management.getShelflife());
           psmt.setString(5, management.getWarehousingDate());
           psmt.setString(6, management.getGoodsName());
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

	public static ArrayList<Management> getStudentTotalData(){
		      //2.1 ����Ÿ���̽��� ���̺� �ִ� ���ڵ带 ��� �������� ������
		            String selectManagement = "select * from managementtbl";
		            //2.2 ����Ÿ���̽� Connection�� �����;� �Ѵ�.
		            Connection con = null;
		            //2.3 �������� �����ؾ��� Statement�� �������Ѵ�.
		            PreparedStatement psmt=null;
		            //2.4 �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
		            ResultSet rs = null;
		            try {
		               con = DBUtility.getConnection();
		               psmt = con.prepareStatement(selectManagement);
		         
		               //1.5 ��������Ÿ�� ������ �������� �����Ѵ�.(������ ġ�� ��)
		               //executeQuery() �������� �����ؼ� ����� �����ö� ����ϴ� ������
		               //executeUpdate() ��������  �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
		               rs = psmt.executeQuery();
		               if(rs==null) {
		                 RootController.callAlert("select ���� : select �������� �����߽��ϴ�.");
		                  return null;
		               }
		               while(rs.next()) {
		            	   Management management = new Management(rs.getString(1),rs.getString(2),
		                        rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
		                  dbArrayList2.add(management);
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
		      
		      return dbArrayList2;
	}
}