package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import impl.Transaction;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost/Bank?useUnicode=" + "true&useJDBCCompliantTimezoneShift="
				+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection con = DriverManager.getConnection(url, "root", "Bottomass1375");
		Statement st = con.createStatement();

		boolean check = true;
		
		while (check) {
			System.out.println("What are you going to do?\n" + "1)Open the bill\n" + "2)Close the bill\n"
					+ "3)Put money on the bill\n" + "4)Take money from the bill\n" + "5)Show the sum\n"
					+ "0)Finish\n ");
			String temp = sc.nextLine();

			switch (temp) {
			case "1": {
				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();

				Transaction.openBill(bill);

				break;
			}
			case "2": {
				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();
Transaction.closeBill(bill);
				break;
			}

			 case "3": {
				 System.out.println("Input the number of bill :\n ");
					int bill = sc.nextInt();
				 System.out.println("Input sum which you want to put :\n");
				int sum = sc.nextInt();
			 Transaction.putMoney(bill, sum);
			 break;
			 }
			
			 case "4": {
			
				 System.out.println("Input the number of bill :\n ");
					int bill = sc.nextInt();
				 System.out.println("Input sum which you want to take :\n");
				int sum = sc.nextInt();
			 Transaction.takeMoney(bill, sum);
				 
				 
			 break;
			 }
			
			 case "5": {
				 System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();
				 Transaction.showBill(bill);
				 
				 
			 break;
			 }

			default: {
				System.out.println("exit");
				check = false;
				
			}
			}

		}

		

		String sqlList = "SELECT * FROM BILL";

		ResultSet resultSet = st.executeQuery(sqlList);

		while (resultSet.next()) {
			int bill_id = resultSet.getInt("id_bill");
			String name = resultSet.getString("NAME");
			String surName = resultSet.getString("SURNAME");
			String status = resultSet.getString("STATUS_OF_bill");

			System.out.println(" " + resultSet.getInt("id_bill") + ",\t " + resultSet.getString("NAME") + ",\t "
					+ resultSet.getString("SURNAME") + ",\t " + resultSet.getString("STATUS_OF_bill") + ",\t ");

		}
		//
		resultSet.close();
		System.out.println("\n");
		

		 String sqlList1 = "SELECT * FROM TRANSACTION";
		
		 ResultSet resultSet1 = st.executeQuery(sqlList1);
		
		
		
		
		 while (resultSet1.next()) {
		 int id = resultSet1.getInt("id");
		
		int sum1 = resultSet1.getInt("sum");
		int id_bill = resultSet1.getInt("id_transact_bill");
		//
		//
		 System.out.println(" " + resultSet1.getInt("id") + ",\t "
		 + resultSet1.getInt("sum")+",\t " +resultSet1.getInt("id_transact_bill") + ",\t " );
		
		 }
		
		 resultSet1.close();

	}

}
