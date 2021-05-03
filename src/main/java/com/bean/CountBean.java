package com.bean;

import org.springframework.stereotype.Component;

@Component
public class CountBean {
	
	int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
