package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transaction {

	private final static String URL = "jdbc:mysql://localhost/Bank?useUnicode=" + "true&useJDBCCompliantTimezoneShift="
			+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String root = "root";
	private final static String password = "Bottomass1375";

	private static Scanner sc = new Scanner(System.in);

	public static void openBill(int id_Bill) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необязательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();

		// String sql = "update Bill set status_of_bill = 'Open'";

		String sql = "Select exists(select id_bill from Bill where id_bill='" + id_Bill + "');";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			System.out.println("------ " + name1);
		}
		resultSet1.close();
		int resultSet = 0;
		if (!name1.equals("1")) {
			System.out.println("Сначало создайте счет \n");
			System.out.println("Введите Имя и Фамилию владельца \n");
			String sql1 = "insert into Bill values('" + id_Bill + "', '" + sc.nextLine() + "', '" + sc.nextLine()
					+ "', '" + "Open" + "');";
			resultSet = st.executeUpdate(sql1);
		} else {

			String sql2 = "update  Bill set status_of_bill = 'Open' where id_bill = '" + id_Bill + "' ";
			resultSet = st.executeUpdate(sql2);
			System.out.println("Счет открыт ");
		}
		con.close();

	}

	public static void closeBill(int id_Bill) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необязательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();

		String sql = "Select exists(select id_bill from Bill where id_bill='" + id_Bill + "');";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			System.out.println("------ " + name1);
		}
		resultSet1.close();
		int resultSet = 0;
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
			System.out.println("------ " + name2);
		}
		resultSetStatus.close();	
		
		if (!name1.equals("1")) {
			System.out.println("Такого счета не существует \n");
		}
	
		
		else if(name2.equals("Close")) {
			System.out.println("Этот счет уже закрыт \n");
		}
		else {
			String sql2 = "update  Bill set status_of_bill = 'Close' where id_bill = '" + id_Bill + "' ";
			resultSet = st.executeUpdate(sql2);
			System.out.println("Счет закрыт \n");
		}

		con.close();

	}

	public static void putMoney(int id_Bill, int sum) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необязательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		String sql = "Select exists(select id_bill from Bill where id_bill='" + id_Bill + "');";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			System.out.println("------ " + name1);
		}
		resultSet1.close();
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
			System.out.println("------ " + name2);
		}
		resultSet1.close();	
		
		int resultSet = 0;
		if (!name1.equals("1")) {
		System.out.println("Такого счета не существует \n");
		}	
		else if(name2.equals("Close")) {
			System.out.println("Любые транзакции невозможны,счет закрыт \n");
		}
		
		else {
			String sql2 = "insert into Transaction values('" + 5 + "', '" + sum + "','" +id_Bill + "')";
			resultSet = st.executeUpdate(sql2);
			System.out.println("Положено "+sum+ " денег"+ "\n");
		}
		
		con.close();
		
		
		
	}

	public static void takeMoney(int id_Bill, int sum) throws SQLException {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необязательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		String sql = "Select exists(select id_bill from Bill where id_bill='" + id_Bill + "');";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			System.out.println("------ " + name1);
		}
		resultSet1.close();
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
			System.out.println("------ " + name2);
		}
		resultSet1.close();	
		
		
		
		
		String sql2 = "select sum(sum) from transaction \r\n" + 
				"where id_transact_bill='"+id_Bill+"';";
		ResultSet resultSet = st.executeQuery(sql2);
		String name3 = "";
		
		while(resultSet.next()) {
			 name3 = resultSet.getString(1);
			System.out.println("sum "+ name3);
		}
		
		
		if (!name1.equals("1")) {
		System.out.println("Такого счета не существует \n");
		}	
		else if(name2.equals("Close")) {
			System.out.println("Любые транзакции невозможны,счет закрыт \n");
		}
		else if(sum>Integer.parseInt(name3)) {
			System.out.println("На этом счете нету столько денег \n");
		}
		else {
			String sql3 = "insert into Transaction values('" + 3 + "', '" + -sum + "','" +id_Bill + "')";
			int resultSet2 = st.executeUpdate(sql2);
			System.out.println("Снято "+sum+ " денег"+ "\n");
		}
		
		con.close();
		
		
	}

	public static void showBill(int id_Bill) throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необязательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		String sql = "Select exists(select id_bill from Bill where id_bill='" + id_Bill + "');";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			System.out.println("------ " + name1);
		}
		resultSet1.close();
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
			System.out.println("------ " + name2);
		}
		resultSet1.close();	
		
		ResultSet resultSet = null;
		if (!name1.equals("1")) {
		System.out.println("Такого счета не существует \n");
		}	
		else if(name2.equals("Close")) {
			System.out.println("Любые транзакции невозможны,счет закрыт \n");
		}
		else {
			String sql2 = "select sum(sum) from transaction \r\n" + 
					"where id_transact_bill='"+id_Bill+"';";
			resultSet = st.executeQuery(sql2);
			while(resultSet.next()) {
				String name3 = resultSet.getString(1);
				System.out.println("sum "+ name3);
			}
		}
	
		
	}

}
