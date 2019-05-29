package entity;

public class OrderDetail {
		private int id;
		private int itemId;
		private int orderId;
		private int quantity;
		
		public OrderDetail(int id, int itemId, int orderId, int quantity) {
			this.id = id;
			this.itemId = itemId;
			this.orderId = orderId;
			this.quantity = quantity;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
}
