package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminUtility {
	public static void generateBillsForACity() throws ClassNotFoundException, SQLException {
		int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
		Connection con = MyConnection.getInstance().getConnection();
		Statement st = con.createStatement();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String city;
		System.out.print("Enter city to generate bills : ");
		city = sc.nextLine();
		String cityUpper = city.toUpperCase();
		System.out.println("----------------" + cityUpper + " BILL------------------");
		ResultSet result = st.executeQuery(
				" select c.consumerid,b.billid,b.unitsConsumed,c.connectiontype,c.consumername,b.year,b.month,c.area, b.totalAmount from bill b join consumer c on b.consumerid = c.consumerid where c.city = '"
						+ city + "'");
		while (result.next()) {
			System.out.println("Bill Number : " + result.getInt("billid"));
			System.out.println("Consumer id : " + result.getInt("consumerid"));
			System.out.println("Consumer name is : " + result.getString("consumername"));
			System.out.println("Connection Type is : " + result.getString("connectiontype"));
			System.out.println("Year : " + result.getInt("year"));
			System.out.println("Month : " + result.getString("month"));
			System.out.println("Units Consumed : " + result.getInt("unitsconsumed"));
			System.out.println("Total Amount : " + result.getInt("totalAmount"));
			System.out.println("-------------------------------");
			if (result.getString("connectiontype").equalsIgnoreCase("domestic")) {
				int n = 2 * result.getInt("unitsconsumed");
				totalDomesticAmount = totalDomesticAmount + n;
				totalDomesticUnits = totalDomesticUnits + result.getInt("unitsconsumed");
			}
			if (result.getString("connectiontype").equalsIgnoreCase("commercial")) {
				int num = 4 * result.getInt("unitsconsumed");
				totalCommercialAmount = totalCommercialAmount + num;
				totalCommercialUnits = totalCommercialUnits + result.getInt("unitsconsumed");
			}
		}
		System.out.println("Total Domestic units consumed in " + city + " are : " + totalDomesticUnits);
		System.out.println("Total Amount of Domestic units  in " + city + " are : " + totalDomesticAmount);
		System.out.println("Total Commercial units consumed in " + city + " are : " + totalCommercialUnits);
		System.out.println("Total Amount of Commercial units  in " + city + " are : " + totalCommercialAmount);
		System.out.println("Total Units consumed in " + city + " are : " + (totalDomesticUnits + totalCommercialUnits));
		System.out.println("Total Bill Amount in " + city + " is : " + (totalDomesticAmount + totalCommercialAmount));
		System.out.println("---------------------END------------------------");

	}

	public static void generateBillsForAnArea() throws ClassNotFoundException, SQLException {
		int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
		Connection con = MyConnection.getInstance().getConnection();
		Statement st = con.createStatement();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String area;
		System.out.print("Enter area to generate bills : ");
		area = sc.nextLine();
		String areaUpper = area.toUpperCase();
		System.out.println("----------------" + areaUpper + " BILL------------------");
		ResultSet result = st.executeQuery(
				" select c.consumerid,b.billid,b.unitsconsumed,c.connectiontype,c.consumername,b.year,b.month,c.area,b.totalAmount from bill b join consumer c on b.consumerid = c.consumerid where c.area = '"
						+ area + "'");
		while (result.next()) {
			System.out.println("Bill Number : " + result.getInt("billid"));
			System.out.println("Consumer id : " + result.getInt("consumerid"));
			System.out.println("Consumer name is : " + result.getString("consumername"));
			System.out.println("Connection Type is : " + result.getString("connectiontype"));
			System.out.println("Year : " + result.getInt("year"));
			System.out.println("Month : " + result.getString("month"));
			System.out.println("Units Consumed : " + result.getInt("unitsconsumed"));
			System.out.println("Total Amount : " + result.getInt("totalAmount"));
			System.out.println("-------------------------------");
			if (result.getString("connectiontype").equalsIgnoreCase("domestic")) {
				int n = 2 * result.getInt("unitsconsumed");
				totalDomesticAmount = totalDomesticAmount + n;
				totalDomesticUnits = totalDomesticUnits + result.getInt("unitsconsumed");
			}
			if (result.getString("connectiontype").equalsIgnoreCase("commercial")) {
				int num = 4 * result.getInt("unitsconsumed");
				totalCommercialAmount = totalCommercialAmount + num;
				totalCommercialUnits = totalCommercialUnits + result.getInt("unitsconsumed");
			}
		}
		System.out.println("---------------------------------------------------");
		System.out.println("Total Domestic units consumed in " + area + " are : " + totalDomesticUnits);
		System.out.println("Total Amount of Domestic units  in " + area + " are : " + totalDomesticAmount);
		System.out.println("Total Commercial units consumed in " + area + " are : " + totalCommercialUnits);
		System.out.println("Total Amount of Commercial units  in " + area + " are : " + totalCommercialAmount);
		System.out.println("Total Units consumed in " + area + " are : " + (totalDomesticUnits + totalCommercialUnits));
		System.out.println("Total Bill Amount in " + area + " is : " + (totalDomesticAmount + totalCommercialAmount));
		System.out.println("--------------------------END---------------------------");
	}

	public static void addUnitsConsumed() throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Connection con = MyConnection.getInstance().getConnection();
		String sqlInsert = "insert into bill(consumerId,unitsconsumed,year,month) values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sqlInsert);
		System.out.print("Enter the consumerID of consumer : ");
		pst.setInt(1, sc.nextInt());
		System.out.print("Enter the year : ");
		pst.setInt(3, sc.nextInt());
		System.out.print("Enter the month : ");
		pst.setString(4, sc.next());
		sc.nextLine();
		System.out.print("Enter units consumed by consumer : ");
		pst.setInt(2, sc.nextInt());
		pst.executeUpdate();
		System.out.println("Successfully Inserted!!");

	}

	public static void generateBillsForAllCustomers() throws ClassNotFoundException, SQLException {
		Connection con = MyConnection.getInstance().getConnection();
		Statement st = con.createStatement();
		System.out.println("-------------BILLS OF ALL CUSTOMERS-------------");
		ResultSet result = st.executeQuery(
				"select c.consumerid as consumerid,b.billid as bid,b.unitsconsumed as units,b.year as year,b.month as month,c.connectiontype as connection, b.totalAmount as totalAmount from bill b join consumer c on b.consumerid=c.consumerid;");
		while (result.next()) {
			System.out.println("Consumer id : " + result.getInt("consumerid"));
			System.out.println("Bill Number : " + result.getInt("bid"));
			System.out.println("Year : " + result.getInt("year"));
			System.out.println("Month : " + result.getString("month"));
			System.out.println("Connection Type : " + result.getString("connection"));
			System.out.println("Units Consumed : " + result.getInt("units"));
			System.out.println("Total Amount : " + result.getInt("totalAmount"));
			System.out.println("-------------------------------");
//			if (result.getString("connection").equalsIgnoreCase("domestic")) {
////				int totalAmount = 2 * result.getInt("units");
//				System.out.println("Total Amount : " + totalAmount);
//			}
//			if (result.getString("connection").equalsIgnoreCase("commercial")) {
//				int totalAmount = 4 * result.getInt("units");
//				System.out.println("Total Amount : " + totalAmount);
//			}
		}
		System.out.println("-----------------------END-----------------------");
	}

	@SuppressWarnings("resource")
	public static void generateBillByMonth() throws ClassNotFoundException, SQLException {
		int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
		Connection con = MyConnection.getInstance().getConnection();
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the month : ");
		String month = sc.nextLine();
		String monthUpper = month.toUpperCase();
		System.out.println("----------------" + monthUpper + " BILL------------------");
		ResultSet result2 = st.executeQuery(
				"select c.consumerid as consumerid,c.connectiontype as type,b.unitsconsumed as units,c.consumername as name,b.totalAmount from bill b join consumer c on b.consumerid = c.consumerid where b.month = '"
						+ month + "'");
		while (result2.next()) {
			System.out.println("Consumer id : " + result2.getInt("consumerid"));
			System.out.println("Consumer name is : " + result2.getString("name"));
			System.out.println("Connection Type is : " + result2.getString("type"));
			System.out.println("Units Consumed : " + result2.getInt("units"));
			System.out.println("Total Amount : " + result2.getInt("totalAmount"));
			System.out.println("-------------------------------");
			if (result2.getString("type").equalsIgnoreCase("domestic")) {
				int n = 2 * result2.getInt("units");
				totalDomesticAmount = totalDomesticAmount + n;
				totalDomesticUnits = totalDomesticUnits + result2.getInt("units");
			}
			if (result2.getString("type").equalsIgnoreCase("commercial")) {
				int num = 4 * result2.getInt("units");
				totalCommercialAmount = totalCommercialAmount + num;
				totalCommercialUnits = totalCommercialUnits + result2.getInt("units");
			}
		}
		System.out.println("-----------------------------------------------");
		System.out.println("Total Domestic units consumed in " + month + " are : " + totalDomesticUnits);
		System.out.println("Total Amount of Domestic units  in " + month + " are : " + totalDomesticAmount);
		System.out.println("Total Commercial units consumed in " + month + " are : " + totalCommercialUnits);
		System.out.println("Total Amount of Commercial units  in " + month + " are : " + totalCommercialAmount);
		System.out
				.println("Total Units consumed in " + month + " are : " + (totalDomesticUnits + totalCommercialUnits));
		System.out.println("Total Amount in " + month + " is : " + (totalDomesticAmount + totalCommercialAmount));
		System.out.println("-----------------------END-----------------------");
	}

	public static void generateBillByYear() throws ClassNotFoundException, SQLException {
		int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
		Connection con = MyConnection.getInstance().getConnection();
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the year : ");
		int year = sc.nextInt();
		System.out.println("----------------" + year + " BILL------------------");
		ResultSet result2 = st.executeQuery(
				"select c.consumerid as consumerid,c.connectiontype as type,b.unitsconsumed as units,c.consumername as name,b.totalAmount from bill b join consumer c on b.consumerid = c.consumerid where b.year = "
						+ year + "");
		while (result2.next()) {
			System.out.println("Consumer id : " + result2.getInt("consumerid"));
			System.out.println("Consumer name is : " + result2.getString("name"));
			System.out.println("Connection Type is : " + result2.getString("type"));
			System.out.println("Units Consumed : " + result2.getInt("units"));
			System.out.println("Total Amount : " + result2.getInt("totalAmount"));
			System.out.println("-------------------------------");
			if (result2.getString("type").equalsIgnoreCase("domestic")) {
				int n = 2 * result2.getInt("units");
				totalDomesticAmount = totalDomesticAmount + n;
				totalDomesticUnits = totalDomesticUnits + result2.getInt("units");
			}
			if (result2.getString("type").equalsIgnoreCase("commercial")) {
				int num = 4 * result2.getInt("units");
				totalCommercialAmount = totalCommercialAmount + num;
				totalCommercialUnits = totalCommercialUnits + result2.getInt("units");
			}
		}
		System.out.println("------------------------------------------");
		System.out.println("Total Domestic units consumed in " + year + " are : " + totalDomesticUnits);
		System.out.println("Total Amount of Domestic units  in " + year + " are : " + totalDomesticAmount);
		System.out.println("Total Commercial units consumed in " + year + " are : " + totalCommercialUnits);
		System.out.println("Total Amount of Commercial units  in " + year + " are : " + totalCommercialAmount);
		System.out.println("Total Units consumed in " + year + " are : " + (totalDomesticUnits + totalCommercialUnits));
		System.out.println("Total Amount in " + year + " is : " + (totalDomesticAmount + totalCommercialAmount));
		System.out.println("-----------------------END-----------------------");
	}
	
	public static void getAllBills(int id) throws ClassNotFoundException, SQLException {
    	int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
    	Connection con = MyConnection.getInstance().getConnection();
		Statement st = con.createStatement();
		ResultSet result = st.executeQuery("select b.consumerid,b.billid,b.unitsconsumed,b.year,b.month,c.consumername,c.connectiontype,b.totalAmount from bill b join consumer c on b.consumerid = c.consumerid where b.consumerid = "+id+"");
		if (result.next() == false) {
            System.out.println("No record found for this consumer ID.");
            return;
        } else {
        	while (result.next()) {
        		System.out.println("Bill Number : "+result.getInt("billid"));
    			System.out.println("Consumer id : "+result.getInt("consumerid"));
    			System.out.println("Consumer name is : "+result.getString("consumername"));
    			System.out.println("Connection Type is : "+result.getString("connectiontype"));
    			System.out.println("Year : "+result.getInt("year"));
    			System.out.println("Month : "+result.getString("month"));
    			System.out.println("Units Consumed : "+result.getInt("unitsconsumed"));
    			System.out.println("Total Amount : " + result.getInt("totalAmount"));
    			System.out.println("-------------------------------");
    			if(result.getString("connectiontype").equalsIgnoreCase("domestic")) {
    				int n = 2*result.getInt("unitsconsume");
    				totalDomesticAmount = totalDomesticAmount + n;
    				totalDomesticUnits = totalDomesticUnits + result.getInt("unitsconsumed");
    				System.out.println("Total Domestic Units consumed are : "+totalDomesticUnits);
    				System.out.println("Total Amount is : "+totalDomesticAmount);
    			}
    			if(result.getString("connectiontype").equalsIgnoreCase("commercial")) {
    				int num = 4*result.getInt("unitsconsumed");
    				totalCommercialAmount = totalCommercialAmount + num;
    				totalCommercialUnits = totalCommercialUnits + result.getInt("unitsconsumed");
    				System.out.println("Total Commercial Units consumed are : "+totalCommercialUnits);
    				System.out.println("Total Amount is : "+totalCommercialAmount);
    			}
        	}
        }
	}
}
