/**
 * 
 */
package com.meng.jvm;

public class SubClass extends ParentClass {

	// 初始化代码
	static{
		System.out.println("SubClass init...");
	}
	
	public static void testStaticMethod(){
		System.out.println("SubClass.testStaticMethod...");
	}
	
	public void testCommonMethod(){
		System.out.println("SubClass.testCommonMethod...");
	}
}
