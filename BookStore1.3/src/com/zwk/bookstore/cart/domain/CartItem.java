package com.zwk.bookstore.cart.domain;

import java.math.BigDecimal;

import com.zwk.bookstore.book.domain.Book;

public class CartItem {

	private Book book;
	private int count;//书数量
	public double getSubtotal()
	{
		BigDecimal b1 = new BigDecimal(count+"");
		BigDecimal b2 = new BigDecimal(book.getPrice()+"");
		
		return b1.multiply(b2).doubleValue();
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartItem [count=" + count + "]";
	}
	
}
