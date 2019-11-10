import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transaction {

	private final static String URL = "jdbc:db2://172.20.2.116:5035/DALLASB";

	private final static String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	private final static String root = "USER06A";
	private final static String password = "USER06A";

	private static Scanner sc = new Scanner(System.in);

	public static void openBill(int id_Bill) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();

		// String sql = "update Bill set status_of_bill = 'Open'";

		// String sql = "Select exists(select BILL_ID FROM BILL where BILL_ID='" +
		// id_Bill + "');";

		String sql = "select bill_id from bill where bill_id='" + id_Bill + "';";
		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			//System.out.println("------ " + name1);
		}
		resultSet1.close();
		int resultSet = 0;
		if (!name1.equals(Integer.toString(id_Bill))) {
			System.out.println("At first you should create a bill \n");
			System.out.println("Input owner's name and surname \n");
			String sql1 = "insert into Bill values('" + id_Bill + "', '" + sc.nextLine() + "', '" + sc.nextLine()
					+ "', '" + "Open" + "');";
			resultSet = st.executeUpdate(sql1);
		} else {

			String sql2 = "update  Bill set status = 'Open' where bill_id = '" + id_Bill + "' ";
			resultSet = st.executeUpdate(sql2);
			System.out.println("Bill is open ");
		}
		con.close();

	}

	public static void closeBill(int id_Bill) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необ€зательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();

		String sql = "select bill_id from bill where bill_id='" + id_Bill + "';";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
		//	System.out.println("------ " + name1);
		}
		resultSet1.close();
		int resultSet = 0;

		String sql1 = "select status from bill where bill_id='" + id_Bill + "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
		//	System.out.println("------ " + name2);
		}
		resultSetStatus.close();

		if (!name1.equals(Integer.toString(id_Bill))) {
			System.out.println("such  bill does not exist \n");
		}

		else if (name2.equals("Close")) {
			System.out.println("This bill is already closed \n");
		} else {
			String sql2 = "update  Bill set status = 'Close' where bill_id = '" + id_Bill + "' ";
			resultSet = st.executeUpdate(sql2);
			System.out.println("The Bill is closed \n");
		}

		con.close();

	}

	public static void putMoney(int id_Bill, int sum) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необ€зательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		String sql = "select bill_id from bill where bill_id='" + id_Bill + "';";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
		//	System.out.println("------ " + name1);
		}
		resultSet1.close();

		String sql1 = "select status from bill where bill_id='" + id_Bill + "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
		//	System.out.println("------ " + name2);
		}
		resultSet1.close();

		int resultSet = 0;
		String sqlCount = "select count(*) from Transaction;";
		ResultSet rsCount = st.executeQuery(sqlCount);
		String nameCount = "";
		while (rsCount.next()) {
			nameCount = rsCount.getString(1);
		}
		int i = 1 + Integer.parseInt(nameCount);

		if (!name1.equals(Integer.toString(id_Bill))) {
			System.out.println("such  bill does not exist \n");
		} else if (name2.equals("Close")) {
			System.out.println("No transactions possible, bill is closed \n");
		}

		else {
			String sql2 = "insert into Transaction values('" + i + "',' " + id_Bill + "', '" + sum + "')";
			resultSet = st.executeUpdate(sql2);
			System.out.println("Put " + sum + " money" + "\n");
		}

		con.close();

	}

	public static void takeMoney(int id_Bill, int sum) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необ€зательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		String sql = "select bill_id from bill where bill_id='" + id_Bill + "';";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
		//	System.out.println("------ " + name1);
		}
		resultSet1.close();

		String sql1 = "select status from bill where bill_id='" + id_Bill + "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
			//System.out.println("------ " + name2);
		}
		resultSet1.close();

		String sql2 = "select sum(sum) from transaction \r\n" + "where id='" + id_Bill + "';";
		ResultSet resultSet = st.executeQuery(sql2);
		String name3 = "";

		while (resultSet.next()) {
			name3 = resultSet.getString(1);
		//	System.out.println("sum " + name3);
		}
		int count = 0;
		try {
			count = Integer.parseInt(name3);
		} catch (NumberFormatException e) {

		}

		String sqlCount = "select count(*) from Transaction;";
		ResultSet rsCount = st.executeQuery(sqlCount);
		String nameCount = "";
		while (rsCount.next()) {
			nameCount = rsCount.getString(1);
		}

		int i = 1 + Integer.parseInt(nameCount);
		if (!name1.equals(Integer.toString(id_Bill))) {
			System.out.println("such  bill does not exist  \n");
		} else if (name2.equals("Close")) {
			System.out.println("No transactions possible, bill is closed \n");
		} else if (sum > count) {
			System.out.println("There isnТt so much money in this bill \n");
		} else {
			String sql3 = "insert into Transaction values('" + i + "',' " + id_Bill + "', '" + -sum + "')";
			System.out.println();
			int resultSet2 = st.executeUpdate(sql3);
			System.out.println("Take " + sum + " money" + "\n");
		}

		con.close();

	}

	public static void showBill(int id_Bill) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необ€зательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		String sql = "select bill_id from bill where bill_id='" + id_Bill + "';";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
		//	System.out.println("------ " + name1);
		}
		resultSet1.close();

		String sql1 = "select status from bill where bill_id='" + id_Bill + "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
		//	System.out.println("------ " + name2);
		}
		resultSet1.close();

		ResultSet resultSet = null;
		if (!name1.equals(Integer.toString(id_Bill))) {
			System.out.println("such  bill does not exist \n");
		} else if (name2.equals("Close")) {
			System.out.println("No transactions possible, bill is closed \n");
		} else {
			String sql2 = "select sum(sum) from transaction \r\n" + "where id='" + id_Bill + "';";
			resultSet = st.executeQuery(sql2);
			while (resultSet.next()) {
				String name3 = resultSet.getString(1);
				System.out.println("sum " + name3);
			}
		}

	}
	
	
	
	
	public static void showAllBills() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необ€зательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		
		String sqlList = "SELECT * FROM BILL";

		ResultSet resultSet = st.executeQuery(sqlList);
	
		System.out.println("  bill_id"+ "     Name"+"       Surname"+"      status");
		while (resultSet.next()) {
			int bill_id = resultSet.getInt("bill_id");
			String name = resultSet.getString("NAME");
			String surName = resultSet.getString("SURNAME");
			String status = resultSet.getString("STATUS");

			System.out.println("    " + resultSet.getInt("bill_id") + "\t     " + resultSet.getString("NAME") + " \t"
					+ resultSet.getString("SURNAME") + "      \t" + resultSet.getString("STATUS") + "\t ");

		}
		
		resultSet.close();
		
		
		
	}
	
	
	
	public static void showAllTransactions() throws SQLException {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необ€зательно
		
		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		
		
		String sqlList1 = "SELECT * FROM TRANSACTION";

		ResultSet resultSet1 = st.executeQuery(sqlList1);

		

		System.out.println("   ID_Transact"+ "   id"+"      sum\n");
		while (resultSet1.next()) {
			int id_transact = resultSet1.getInt("ID_Transact");
			int id = resultSet1.getInt("id");
			int sum = resultSet1.getInt("sum");
			
			
			System.out.println("      " + resultSet1.getInt("ID_Transact") + "\t          " + resultSet1.getInt("id") + "\t"
					+ "  "
					+ resultSet1.getInt("sum") + "\t ");

		}

		resultSet1.close();
		
	}

}
