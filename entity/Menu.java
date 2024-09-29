package entity;

public class Menu {
	String foodItem;
	int price;
	public Menu (String foodItem, int price) {
		this.foodItem = foodItem;
		this.price = price;
	}
	public String getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
