package com.meng.datastrcture;


public class MyArrayList {

	Object[] objs = new Object[4];

	int size=0;//集合的大小

	public int size()
	{
		return size;
	}

	//添加
	public void add(Object value)
	{
		//放不放的
		if(size>=objs.length)
		{
			Object[] temp = new Object[objs.length*2];
			//搬家
			for (int i = 0; i < objs.length; i++) {
				temp[i]=objs[i];
			}
			objs=temp;
		}
		objs[size]=value;
		size++;
	}

	public void set(int index,Object value) throws Exception
	{
		if(index<0 || index>=size)
		{
			throw new Exception("超出范围");
		}
		objs[index]=value;
	}

	public Object get(int index) throws Exception
	{
		if(index<0 || index>=size)
		{
			throw new Exception("超出范围");
		}
		return objs[index];
	}

	public void clear()
	{
		size=0;
		objs=new Object[4];
	}

	public void removeAt(int index)throws Exception
	{
		if(index<0 || index>=size)
		{
			throw new Exception("超出范围");
		}

		for(int i=index+1;i<size;i++)
		{
			objs[i-1]=objs[i];
		}
		size--;
	}

}
