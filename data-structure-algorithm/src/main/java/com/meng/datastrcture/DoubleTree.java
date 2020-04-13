package com.meng.datastrcture;

public class DoubleTree {
    TreeNode root;

    public void add(int value){
        TreeNode newNode = new TreeNode(value);
        if(root == null){
            root = newNode;
        } else {
            TreeNode temp = root;
            while (true){
                if(temp.getValue() > value){
                    if(temp.getLeft() == null){
                        temp.setLeft(newNode);
                        break;
                    } else {
                        temp = temp.getLeft();
                    }
                } else {
                    if(temp.getRight()==null)
                    {
                        temp.setRight(newNode);
                        break;
                    }
					else
                    {
                        temp=temp.getRight();
                    }
                }
            }
        }
    }


    public void show(){
        showNode(root);
    }


    //中序遍历
    private void showNode(TreeNode pnode){
        if(pnode.getLeft() != null){
            showNode(pnode.getLeft());
        }
        System.out.println(pnode.getValue());
        if(pnode.getRight()!=null)
        {
            showNode(pnode.getRight());
        }




    }



}
