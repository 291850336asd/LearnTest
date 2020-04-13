package com.meng.datastrcture;

public class LinkNode {

	public LinkNode(Object value)
	{
		this.value=value;
	}

	Object value;//数据
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public LinkNode getNext() {
		return next;
	}
	public void setNext(LinkNode next) {
		this.next = next;
	}
	LinkNode next; //下一个节点的地址(对象引用)
}
