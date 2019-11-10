import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		String user = "USER06A";
		String password = "USER06A";
		String databaseURL = "jdbc:db2://172.20.2.116:5035/DALLASB";
	//	 String databaseURL="jdbc:db2://localhost:5035/DALLASB";
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection(databaseURL, user, password);
			if (conn != null) {
				System.out.println("Connected to the database");
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Could not find DB2 driver class");
			ex.printStackTrace();

		}

		statement = conn.createStatement();

		boolean check = true;
		while (check) {
			System.out.println("What are you going to do?\n" + "1)Open the bill\n" + "2)Close the bill\n"
					+ "3)Put money on the bill\n" + "4)Take money from the bill\n" + "5)Show the sum\n"
					+ "6)Show all bills\n"+"7)Show all transactions\n"
					+ "0)Finish\n ");
			int temp = sc.nextInt();

			switch (temp) {
			case 1: {
				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();

				Transaction.openBill(bill);

				break;
			}
			case 2: {
				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();
				Transaction.closeBill(bill);
				break;
			}

			case 3: {
				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();
				System.out.println("Input sum which you want to put :\n");
				int sum = sc.nextInt();
				Transaction.putMoney(bill, sum);
				break;
			}

			case 4: {

				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();
				System.out.println("Input sum which you want to take :\n");
				int sum = sc.nextInt();
				Transaction.takeMoney(bill, sum);

				break;
			}

			case 5: {
				System.out.println("Input the number of bill :\n ");
				int bill = sc.nextInt();
				Transaction.showBill(bill);

				break;
			}
			
			case 6:{
				Transaction.showAllBills();
				break;
			}
			
			case 7:{
				Transaction.showAllTransactions();
				break;
			}

			default: {
				System.out.println("exit");
				check = false;

			}
			}

		}
		


	}

}
