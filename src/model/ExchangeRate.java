package model;

import java.sql.Date;

public class ExchangeRate {
	private String name;
	private Date date = null;
	private Float buyCash;
	private Float buySpot;
	private Float sellCash;
	private Float sellSpot;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getBuyCash() {
		return buyCash;
	}
	public void setBuyCash(Float buyCash) {
		this.buyCash = buyCash;
	}
	public Float getBuySpot() {
		return buySpot;
	}
	public void setBuySpot(Float buySpot) {
		this.buySpot = buySpot;
	}
	public Float getSellCash() {
		return sellCash;
	}
	public void setSellCash(Float sellCash) {
		this.sellCash = sellCash;
	}
	public Float getSellSpot() {
		return sellSpot;
	}
	public void setSellSpot(Float sellSpot) {
		this.sellSpot = sellSpot;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ExchangeRate(String name, Float buyCash, Float buySpot, Float sellCash, Float sellSpot) {
		super();
		this.name = name;
		this.buyCash = buyCash;
		this.buySpot = buySpot;
		this.sellCash = sellCash;
		this.sellSpot = sellSpot;
	}
	public ExchangeRate() {
		super();
		
	}
	
	public String toString() {
		return "ExchangeRate [name=" + name + ", buyCash=" + buyCash + ", buySpot=" + buySpot + ", sellCash=" + sellCash
				+ ", sellSpot=" + sellSpot + ", date=" + date + "]";
	}
	
	
	
}
