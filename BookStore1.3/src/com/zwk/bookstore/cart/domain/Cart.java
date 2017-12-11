package com.zwk.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//购物车条目=商品+数量
	//购物车=N个购物车条目
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();	
	public double getTotal()
	{
		//double total = 0;
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem:map.values())
		{
			BigDecimal b1 = new BigDecimal(cartItem.getSubtotal()+"");
			total = total.add(b1);
		}
		return total.doubleValue();
	}

	public void add(CartItem cartItem)
	{
		if(map.containsKey(cartItem.getBook().getBid()))
		{
			CartItem _cartItem = map.get(cartItem.getBook().getBid());
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
			map.put(cartItem.getBook().getBid(),_cartItem);
	    }else{
		    map.put(cartItem.getBook().getBid(),cartItem);
		}
	}
	public void clear(){
		map.clear();
	}
	public void delete(String bid)
	{
		map.remove(bid);
	}
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//查询cartItem个数
	public int queryCount(CartItem cartItem)
	{
		CartItem _cartItem = null;
		if(map.containsKey(cartItem.getBook().getBid()))
		{
			_cartItem = map.get(cartItem.getBook().getBid());
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}
		return _cartItem.getCount();
	}
}
