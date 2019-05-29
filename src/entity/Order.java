package entity;

public class Order {
	private int id;
	private String time;
	private int total;
	private int employeeID;
	
	public Order(int id , String time , int total, int employeeID) {
		this.id = id;
		this.time = time;
		this.total = total;
		this.employeeID = employeeID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
}	
