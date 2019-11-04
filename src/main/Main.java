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
		} // необязательно
		String url = "jdbc:mysql://localhost/Bank?useUnicode=" + "true&useJDBCCompliantTimezoneShift="
				+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection con = DriverManager.getConnection(url, "root", "Bottomass1375");
		Statement st = con.createStatement();

		boolean check = true;
		while (check) {
			System.out.println("What are you going to do?\n" + "1)Open the bill\n" + "2)Close the bill\n"
					+ "3)Put money on the bill\n" + "4)Take money from the bill\n" + "5)Show the sum\n"
					+ "6)Finish\n ");
			String temp = sc.nextLine();

			switch (temp) {
			case "1": {
				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();

				String sql = "Select exists(select id_bill from Bill where id_bill='" + bill + "');";

				ResultSet resultSet1 = st.executeQuery(sql);
				String name1 = "";
				while (resultSet1.next()) {
					name1 = resultSet1.getString(1);
					System.out.println("------ " + name1);
				}
				resultSet1.close();

				if (!name1.equals("1")) {
					System.out.println("Сначало создайте счет \n");
					System.out.println("Введите Имя и Фамилию владельца \n");
					Transaction.createBill(bill, sc.nextLine(), sc.nextLine());
				} else {
					Transaction.openBill(bill);
				}

				break;
			}
			// case "2": {
			// System.out.println("Input the number of bill :\n ");
			//
			// String sql2 = "update Bill SET STATUS = Close";
			// break;
			// }
			//
			// case "3": {
			// // if()
			// String sql3 = "insert into transaction values('" + 0 + "', '" + sc.nextInt()
			// + "')";
			// break;
			// }
			//
			// case "4": {
			//
			// break;
			// }
			//
			// case "5": {
			//
			// break;
			// }

			default: {
				check = false;
				System.out.println("exit");
			}
			}

		}

		//
		// int id = 1;
		// int sum1 = 200;
		//
		//
		// String sqlInsert = "insert into transaction values('" +id+ "', '" +sum+ "')";
		//
		//
		// int x = statement.executeUpdate(sqlInsert);
		//
		// if (x > 0)
		// {
		// System.out.println("Successfully Inserted");
		// }
		// else
		// {
		// System.out.println("Insert Failed");
		// }

		//
		// String sqlInsert = "insert into bill values('" +bill_id+ "', '" +name+
		// "', '" +surName+ "', '" +status+ "')";
		//
		// int x = statement.executeUpdate(sqlInsert);
		//
		// if (x > 0)
		// {
		// System.out.println("Successfully Inserted");
		// }
		// else
		// {
		// System.out.println("Insert Failed");
		// }

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
		//
		//
		// String exists = "if exists(select * from BILL \r\n" +
		// "where Name = 'Vasya' and BILL_ID = 1)\r\n" +
		// "begin\r\n" +
		// " PRINT 'Column Exists in given table'\r\n" +
		// "end\r\n" +
		// "else\r\n" +
		// " PRINT 'Column does not Exist in given table'";
		//
		// ResultSet resultSet1 = statement.executeQuery(sqlList);
		//
		//
		// while (resultSet1.next()) {
		// String name1 = resultSet1.getString(1);
		// System.out.println("------ "+name1);
		//
		// }

		// String sqlList1 = "SELECT * FROM TRANSACTION";
		//
		// ResultSet resultSet1 = statement.executeQuery(sqlList);
		//
		//
		//
		//
		// while (resultSet1.next()) {
		// id = resultSet1.getInt("ID");
		//
		// sum = resultSet1.getInt("SUM");
		//
		//
		// System.out.println(" " + resultSet1.getInt("ID") + ",\t "
		// + resultSet1.getInt("SUM"));
		//
		// }
		//
		// resultSet1.close();

	}

}
