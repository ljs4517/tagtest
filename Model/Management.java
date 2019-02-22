package Model;

public class Management {
	private String goodsName;
	private String supply;
	private String selling;
	private String sellingAmount;
	private String shelflife;
	private String warehousingDate;
	public Management(String goodsName, String supply, String selling, String sellingAmount, String shelflife,
			String warehousingDate) {
		super();
		this.goodsName = goodsName;
		this.supply = supply;
		this.selling = selling;
		this.sellingAmount = sellingAmount;
		this.shelflife = shelflife;
		this.warehousingDate = warehousingDate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSupply() {
		return supply;
	}
	public void setSupply(String supply) {
		this.supply = supply;
	}
	public String getSelling() {
		return selling;
	}
	public void setSelling(String selling) {
		this.selling = selling;
	}
	public String getSellingAmount() {
		return sellingAmount;
	}
	public void setSellingAmount(String sellingAmount) {
		this.sellingAmount = sellingAmount;
	}
	public String getShelflife() {
		return shelflife;
	}
	public void setShelflife(String shelflife) {
		this.shelflife = shelflife;
	}
	public String getWarehousingDate() {
		return warehousingDate;
	}
	public void setWarehousingDate(String warehousingDate) {
		this.warehousingDate = warehousingDate;
	}
	
}
