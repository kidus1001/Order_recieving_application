package entity;

public class Order {
	String foodItem;
	int quantity;
	String orderTime;
	String description;
	public Order (String foodItem, int quantity, String orderTime, String description) {
		this.foodItem = foodItem;
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.description = description; 
	}
	public String getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
