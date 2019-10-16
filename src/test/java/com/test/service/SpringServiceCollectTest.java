package com.test.service;


import com.meng.apm.service.monitor.collects.SpringServiceCollects;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import org.junit.Test;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;

public class SpringServiceCollectTest {

	@Test
	public void isTargetTest() throws NotFoundException {
		String className = "com.test.service.SpringServiceCollectTest$StringServiceMock";
		ClassLoader loader = SpringServiceCollectTest.class.getClassLoader();
		ClassPool pool = new ClassPool(true);
		CtClass ctclass = pool.get(className);
	//	Assert.isTrue(SpringServiceCollects.INSTANCE.isTarget(className,
	//			loader, ctclass));
	}

	@Test
	public void transformTest() throws Exception {
//		 System.setProperty("$bit_server","http://123.56.21.219:8860/receive");
//	        System.setProperty("$bit_key","c4f3508aee6058f3");
//	        System.setProperty("$bit_secret","966eedc1903454b8");
		SpringServiceCollects ins = SpringServiceCollects.INSTANCE;
		ClassLoader loader = SpringServiceCollectTest.class.getClassLoader();
		String className = "com.test.service.SpringServiceCollectTest$StringServiceMock";
		byte[] classfileBuffer = null;
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(loader));
		CtClass cclass = pool.get(className);
		ins.transform(loader, className, classfileBuffer, cclass);
		Class cla = cclass.toClass();
		StringServiceMock mock = new StringServiceMock();
		mock.sayHello("hanmeme");
		Thread.sleep(2000);
	}

	//@Service
	public static class StringServiceMock {
		public void sayHello(String name) {
			System.out.println("hello" + name);
		}
	}

}
