package entity;

public class Item {
	
	
	private int itemId, quantity;
	private String name;
	private double sellPrice;

	public Item(int itemId,String name, int quantity,double sellPrice){
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.sellPrice = sellPrice;
		this.name = name;
		//this.imageItemURL = imageItemURL;
	}

	@Override
	public String toString() {
		return "Item [itemID: " + itemId +"name :" + name +"quantity: "+ quantity + "sell price: "+ sellPrice +"]";
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

}
