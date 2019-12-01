package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transaction {

	private final static String URL = "jdbc:mysql://localhost/Bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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
		} 

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();

		// String sql = "update Bill set status_of_bill = 'Open'";

		//String sql = "Select exists(select BILL_ID FROM BILL where BILL_ID='" + id_Bill + "');";

		String sql = "select id_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			System.out.println("------ " + name1);
		}
		resultSet1.close();
		int resultSet = 0;
		if (!name1.equals(Integer.toString(id_Bill))) {
			System.out.println("Сначало создайте счет \n");
			System.out.println("Введите Имя и Фамилию владельца \n");
			String sql1 = "insert into Bill values('" + id_Bill + "', '" + sc.nextLine() + "', '" + sc.nextLine()
					+ "', '" + "Open" + "');";
			resultSet = st.executeUpdate(sql1);
		} else {

			String sql2 = "update  Bill set status_of_bill = 'Open' where id_bill = '" + id_Bill + "' ";
			resultSet = st.executeUpdate(sql2);
		//	System.out.println("Счет открыт "+ resultSet);
		}
		//System.out.println(name1);
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

		String sql = "select id_bill from bill where id_bill='"+id_Bill+ "';";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			//System.out.println("------ " + name1);
		}
		resultSet1.close();
		int resultSet = 0;
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
		//	System.out.println("------ " + name2);
		}
		resultSetStatus.close();	
		
		 if (!name1.equals(Integer.toString(id_Bill))) {
			System.out.println("Такого счета не существует \n"+name1);
		}
	
		
		else if(name2.equals("Close")) {
			System.out.println("Этот счет уже закрыт \n"+ name2);
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
		String sql = "select id_bill from bill where id_bill='"+id_Bill+ "';";


		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
		//	System.out.println("------ " + name1);
		}
		resultSet1.close();
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
			//System.out.println("------ " + name2);
		}
		resultSet1.close();	
		
		int resultSet = 0;
		String sqlCount = "select count(*) from Transaction;";
		ResultSet rsCount = st.executeQuery(sqlCount);
		String nameCount = "";
		while(rsCount.next()) {
			nameCount = rsCount.getString(1);
		}
		int i = 1+Integer.parseInt(nameCount);
		
		if (!name1.equals(Integer.toString(id_Bill))) {
		System.out.println("Такого счета не существует \n");
		}	
		else if(name2.equals("Close")) {
			System.out.println("Любые транзакции невозможны,счет закрыт \n");
		}
		
		else {
			String sql2 = "insert into Transaction values('" + i + "', '" + sum + "', ' " +id_Bill + "')";
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
		String sql = "select id_bill from bill where id_bill='"+id_Bill+ "';";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			//System.out.println("------ " + name1);
		}
		resultSet1.close();
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
			//System.out.println("------ " + name2);
		}
		resultSet1.close();	
		
		
		
		
		String sql2 = "select sum(sum) from transaction \r\n" + 
				"where id_transact_bill='"+id_Bill+"';";
		ResultSet resultSet = st.executeQuery(sql2);
		String name3 = "";
		
		
		while(resultSet.next()) {
			 name3 = resultSet.getString(1);
	//		System.out.println("sum "+ name3);
		}
		
		int count = 0;
		try {
	    count =Integer.parseInt(name3);
		}
		catch(NumberFormatException e) {
			
		}
		
		String sqlCount = "select count(*) from Transaction;";
		ResultSet rsCount = st.executeQuery(sqlCount);
		String nameCount = "";
		while(rsCount.next()) {
			nameCount = rsCount.getString(1);
		}
		
		int i = 1+Integer.parseInt(nameCount);
		if (!name1.equals(Integer.toString(id_Bill))) {
		System.out.println("Такого счета не существует \n");
		}	
		else if(name2.equals("Close")) {
			System.out.println("Любые транзакции невозможны,счет закрыт \n");
		}
		
		
		
		else if(sum>count) {
			System.out.println("На этом счете нету столько денег \n");
		}
		else {
			String sql3 = "insert into Transaction values('" + i + "', '" + -sum + "','" +id_Bill + "')";
			System.out.println();
			int resultSet2 = st.executeUpdate(sql3);
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
		String sql = "select id_bill from bill where id_bill='"+id_Bill+ "';";

		ResultSet resultSet1 = st.executeQuery(sql);
		String name1 = "";
		while (resultSet1.next()) {
			name1 = resultSet1.getString(1);
			//System.out.println("------ " + name1);
		}
		resultSet1.close();
		
		String sql1 = "select status_of_bill from bill where id_bill='"+id_Bill+ "';";
		ResultSet resultSetStatus = st.executeQuery(sql1);
		String name2 = "";
		while (resultSetStatus.next()) {
			name2 = resultSetStatus.getString(1);
		//	System.out.println("------ " + name2);
		}
		resultSet1.close();	
		
		ResultSet resultSet = null;
		if (!name1.equals(Integer.toString(id_Bill))) {
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
	
	
	public static void showBills() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необязательно

		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		
		String sqlList = "SELECT * FROM BILL";

		ResultSet resultSet = st.executeQuery(sqlList);
		System.out.println("  id_bill"+ "     Name"+"       Surname"+"      status_of_bill");
		while (resultSet.next()) {
			int bill_id = resultSet.getInt("id_bill");
			String name = resultSet.getString("NAME");
			String surName = resultSet.getString("SURNAME");
			String status = resultSet.getString("STATUS_of_bill");

			System.out.println("    " + resultSet.getInt("id_bill") + "\t     " + resultSet.getString("NAME") + " \t"
					+ resultSet.getString("SURNAME") + "      \t" + resultSet.getString("STATUS_of_bill") + "\t ");

		}
		
		resultSet.close();
		
		
		
	}
	
	
	
	public static void showTransactions() throws SQLException {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // необязательно
		
		Connection con = DriverManager.getConnection(URL, root, password);
		Statement st = con.createStatement();
		
		
		String sqlList1 = "SELECT * FROM TRANSACTION";

		ResultSet resultSet1 = st.executeQuery(sqlList1);

		
		System.out.println("   ID"+ "   id_transact_bill"+"   sum\n");
		while (resultSet1.next()) {
			int id_transact = resultSet1.getInt("ID");
			int sum1 = resultSet1.getInt("sum");
			int id = resultSet1.getInt("id_transact_bill");
			
			
			System.out.println("   " + resultSet1.getInt("ID") + "\t     " + resultSet1.getInt("id_transact_bill") + "\t"
					+ "           "
					+ resultSet1.getInt("sum") + "\t ");

		}

		resultSet1.close();
		
	}
	
	
}
