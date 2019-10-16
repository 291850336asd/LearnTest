package com.meng.apm.javassist.methodtime;

public class BitStringUtil {
	/**
	 * 已添拼接的方式添加字符串
	 * 
	 * @param length
	 * @return
	 */
	public String addString(int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += (char) (i % 26 + 'a');
		}
		return result;
	}

	/**
	 * 已追加的方式添加字符串
	 * 
	 * @param length
	 * @return
	 */
	public String buildString(int length) {
		StringBuilder inst = new StringBuilder();
		for (int i = 0; i < length; i++) {
			inst.append((char) (i % 26 + 'a'));
		}
		return inst.toString();
	}

	public static void main(String[] args) {
		BitStringUtil util = new BitStringUtil();
		long begin = System.currentTimeMillis();
		util.addString(10000);
		System.out.println(System.currentTimeMillis() - begin);
		begin = System.currentTimeMillis();
		util.buildString(10000);
		System.out.println(System.currentTimeMillis() - begin);
	}
}
