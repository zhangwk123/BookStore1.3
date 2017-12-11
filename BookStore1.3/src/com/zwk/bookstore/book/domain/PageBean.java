package com.zwk.bookstore.book.domain;

import java.util.List;

public class PageBean {

	private int pc;
	private int tp;
	private int tr;
	private int ps;
	private List<Book> beanList;
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTp() {
		int tp = tr/ps;
		return tr%ps==0?tp:(tp+1);
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<Book> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<Book> beanList) {
		this.beanList = beanList;
	}
	@Override
	public String toString() {
		return "PageBean [pc=" + pc + ", tp=" + tp + ", tr=" + tr + ", ps=" + ps + ", beanList=" + beanList + "]";
	}
	
	
}
