
package com.meng.jvm;

import java.util.ArrayList;
import java.util.List;

public class Demo02 {

	public static void main(String[] args) throws InterruptedException {
		List<Person> persons = new ArrayList<>();
		int tmp = 10;
		while(true){
			tmp ++;
			Person p = new Person();
			p.setAge(tmp);
			p.setName("nihao : " + tmp);
			persons.add(p);
			System.out.println(p);
			Thread.sleep(5);
			
		}
	}
}
