package com.meng.apm.service;


//import org.springframework.stereotype.Service;

//@Service
public class UserServiceImpl {
	
	public void addName(String name) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println(name);
	}

	public String getName() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hanmemei";
	}

	public String getEror() {
		int i = 1/0;
		return "hanmemei";
	}

	public static void main(String[] args) throws InterruptedException {
		UserServiceImpl stringServiceMock = new UserServiceImpl();
		System.out.println(stringServiceMock.getClass().getAnnotations());
		stringServiceMock.addName("sdsd");
		stringServiceMock.getName();
		stringServiceMock.getEror();
	}
}
