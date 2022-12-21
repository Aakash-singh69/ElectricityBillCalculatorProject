package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javaFiles.Consumer;

public class ConsumerUtility {
    static Scanner scanner = new Scanner(System.in);

    public static Consumer validateConsumer(int id, String password) throws SQLException, ClassNotFoundException {
        Connection con = MyConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("Select * from consumer where consumerId = ? and password = ?");
        pst.setInt(1, id);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if (rs.next() == false)
            return null;
        return new Consumer(rs.getInt("consumerId"), rs.getString("consumerName"), rs.getString("area"), rs.getString("city"),
                rs.getString("connectionType"), rs.getString("password"));
    }
    

    public static Consumer newRegistration() throws ClassNotFoundException, SQLException {
        System.out.println("*****************  Registration form  ********************* \n");
        System.out.print("Enter your name:");
        String name = scanner.nextLine();
        String [] nameArray=name.split(" ");
		String consumerAlias=nameArray[0]+ String.valueOf((int)(Math.random()*10))+String.valueOf((int)(Math.random()*20))+String.valueOf((int)(Math.random()*30));
        System.out.print("Enter your area: ");
        String area = scanner.nextLine();
        System.out.print("Enter your city: ");
        String city = scanner.nextLine();
        System.out.print("Enter your connection type: ");
        String type = scanner.nextLine();
        System.out.print("Set password: ");
        String password = scanner.next();
        scanner.nextLine();
        int check = ConsumerUtility.registerConsumer(name,consumerAlias, city, area, type, password);
        if (check == 1) {
            System.out.println("Registration Successful!");
            System.out.println("\nGenerating your ConsumerId and Password\n");
            return new Consumer(ConsumerUtility.getConsumerId(consumerAlias, password), name, area, city, type, password);
        }
        return null;
    }

    private static int getConsumerId(String aliasName, String pswd) throws SQLException, ClassNotFoundException {
        Connection con = MyConnection.getInstance().getConnection();
        CallableStatement callable = con.prepareCall("{call getNamePassword(?,?,?)}");
        callable.setString(1, aliasName);
        callable.setString(2, pswd);
        callable.registerOutParameter(3, java.sql.Types.INTEGER);
        callable.executeUpdate();
        int id = callable.getInt(3);
        return id;
    }

    private static int registerConsumer(String consumerName,String consumerAlias, String consumerCity, String consumerArea,
            String connectionType, String password) throws ClassNotFoundException, SQLException {
        System.out.println("\nRegistring Consumer........");
        Connection con = MyConnection.getInstance().getConnection();
        String sqlInsert="insert into Consumer (ConsumerName,consumerAlias,area,city,connectionType,password) value(?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sqlInsert);
		pst.setString(1,consumerName);
		pst.setString(2,consumerAlias);
        pst.setString(3,consumerArea);
        pst.setString(4,consumerCity);
        pst.setString(5,connectionType);
        pst.setString(6,password);
        int rowAffected = pst.executeUpdate();
        pst.clearParameters();
        return rowAffected;
    }

    public static Consumer consumerLogin() throws ClassNotFoundException, SQLException {
        System.out.print("Enter Your Consumer Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Your Password: ");
        String password = scanner.next().toLowerCase();
        scanner.nextLine();
        System.out.println("\nVerifying your details....\n");
        Consumer cobj = validateConsumer(id, password);
        return cobj;
    }

    public static void getBillByMonth(String month, int year, int id) throws ClassNotFoundException, SQLException {
        Connection con = MyConnection.getInstance().getConnection();
        PreparedStatement pst = con
                .prepareStatement("Select * from Bill where month = ? and year = ? and consumerId = ?");
        pst.setString(1, month);
        pst.setInt(2, year);
        pst.setInt(3, id);
        ResultSet rs = pst.executeQuery();

        System.out.println("BillID  UnitsConsumed  Month   Year");
        while (rs.next()) {
            System.out.println(rs.getInt("billId") + "     " + rs.getInt("unitsConsumed") + "    " + rs.getInt("year") + "     "
                    + rs.getString("month"));
        }
    }

    public static void getBillsByYear(int year, int id) throws ClassNotFoundException, SQLException {
	    Connection con = MyConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("Select * from Bill where year = ? and consumerId = ? ");
        pst.setInt(1,year);
        pst.setInt(2, id);
        ResultSet rs = pst.executeQuery();	        
        System.out.println("BillID  UnitsConsumed     Year     Amount");
        while (rs.next()) {
            System.out.println("  "+rs.getInt("billId") + "      " + rs.getInt("unitsConsumed") + "         " + rs.getInt("year")+ "         " + rs.getInt("totalAmount"));
        }
	}


    public static void getAllBills(int id) throws ClassNotFoundException, SQLException {
    	int totalCommercialUnits = 0, totalCommercialAmount = 0;
		int totalDomesticUnits = 0, totalDomesticAmount = 0;
    	Connection con = MyConnection.getInstance().getConnection();
		Statement st = con.createStatement();
		ResultSet result = st.executeQuery("select b.consumerid,b.billid,b.unitsconsumed,b.year,b.month,c.consumername,c.connectiontype, b.totalAmount from bill b join consumer c on b.consumerid = c.consumerid where b.consumerid = "+id+"");
        	while (result.next()) {
        		System.out.println("Bill Number : "+result.getInt("billid"));
    			System.out.println("Consumer id : "+result.getInt("consumerid"));
    			System.out.println("Consumer name is : "+result.getString("consumername"));
    			System.out.println("Connection Type is : "+result.getString("connectiontype"));
    			System.out.println("Year : "+result.getInt("year"));
    			System.out.println("Month : "+result.getString("month"));
    			System.out.println("Units Consumed : "+result.getInt("unitsconsumed"));
    			System.out.println("Total amount : "+result.getInt("totalAmount"));
    			System.out.println("-------------------------------");
    			if(result.getString("connectiontype").equalsIgnoreCase("domestic")) {
    				int n = 2*result.getInt("unitsconsumed");
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
