package com.meng.apm.javassist.proxy;

/**
 */
public class UserServiceImpl {

	public void getName(String userId) {
		System.out.println("userId : " + userId);
	}

	public String createUser(String name, int id) {
		System.out.println("createUser : " + name + ", id : " + id);
		return name + id;
	}

}
