/**
 * 
 */
package com.meng.jvm;


public class ParentClass {

	public static int age = 20;
	public static final int ageFinal = 20;
	static{
		System.out.println("ParentClass init...");
	}
	
	public static void testStaticMethod(){
		System.out.println("ParentClass.testStaticMethod...");
	}
	
	public void testCommonMethod(){
		System.out.println("ParentClass.testCommonMethod...");
	}
}
