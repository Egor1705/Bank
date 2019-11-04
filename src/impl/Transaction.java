package impl;

public class Transaction {

	public static void openBill(int id_Bill) {
        
		String sql = "update  Bill set status_of_bill = 'Open'";
		
	   
	}
	
	public static void createBill(int id_Bill,String name,String surname) {
		
		String sql = "insert into Bill values('" +id_Bill+ "', '" +name+ "', '" +surname+ "', '" +"Open" + "')";
		
		
	}

	public static void closeBill(int id_Bill) {

		String sql = "update  Bill set status_of_bill = 'Close'";
		
	}

	public static void putMoney(int id_Bill, int sum) {

		String sql = "insert into Transaction values('" +id_Bill+ "', '"  +sum+  "')";
	}

	public static void takeMoney(int id_Bill, int sum) {
		String sql = "insert into Transaction values('" +id_Bill+ "', '"  +sum+  "')";
	}

	public static void showBill(int id_Bill) {

	}

}
