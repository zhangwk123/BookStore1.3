package com.zwk.bookstore.book.domain;

import com.zwk.bookstore.categary.domain.Categary;

public class Book {

	private String bid;
	private String bname;
	private double price;
	private String author;
	private String image;
	private Categary categary;
	private boolean del;
	private int kucun;
	
	public int getKucun() {
		return kucun;
	}
	public void setKucun(int kucun) {
		this.kucun = kucun;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Categary getCategary() {
		return categary;
	}
	public void setCategary(Categary categary) {
		this.categary = categary;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price + ", author=" + author + ", image=" + image
				+ ", categary=" + categary + ", del=" + del + ", kucun=" + kucun + "]";
	}
	
}
