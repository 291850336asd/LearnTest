package com.meng.datastrcture;

public class MyLinkedList {

	int size=0;
	LinkNode head=null;

	public int size()
	{
		return size;
	}

	public void add(Object value)
	{
		LinkNode newNode = new LinkNode(value);
		if(head==null)//第一次添加
		{
			head = newNode;
		}
		else
		{
			LinkNode temp = head;//当前节点
			while(temp.getNext()!=null)
			{
				temp = temp.getNext();//当前节点向后移动
			}
			//循环结束：temp表示最后一个节点
			temp.setNext(newNode);
		}
		size++;
	}

	public void set(int index,Object value)
	{
		LinkNode temp = head;
		for(int i=0;i<index;i++)
		{
			temp=temp.getNext();
		}
		//temp定位到指定索引位置
		temp.setValue(value);
	}

	public Object get(int index)
	{
		LinkNode temp = head;
		for(int i=0;i<index;i++)
		{
			temp=temp.getNext();
		}
		//temp定位到指定索引位置
		return temp.getValue();
	}

	public void clear()
	{
		head = null;
		size=0;
	}

	public void removeAt(int index)
	{
		if(index==0)//删除头元素
		{
			head =head.getNext();
		}
		else
		{
			//找到删除元素的前一个元素
			LinkNode temp = head;
			for(int i=0;i<index-1;i++)
			{
				temp=temp.getNext();
			}
			//循环结束，temp表示删除元素的前一个元素
			temp.setNext(temp.getNext().getNext());
		}
		size--;
	}
}