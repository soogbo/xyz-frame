package xyz.java.main.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

/**
 * 按之字形顺序打印二叉树 
 * 
 * @author shisp
 * @date 2019-1-21 17:42:27
 */
public class PrintBinaryTree {
    
        public static ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
                int layer = 1;
                //s1存奇数层节点
                Stack<TreeNode> s1 = new Stack<TreeNode>();
                s1.push(pRoot);
                //s2存偶数层节点
                Stack<TreeNode> s2 = new Stack<TreeNode>();
                 
                ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
                 
                while (!s1.empty() || !s2.empty()) {
                    if (layer%2 != 0) {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        while (!s1.empty()) {
                            TreeNode node = s1.pop();
                            if(node != null) {
                                temp.add(node.val);
                                System.out.print(node.val + " ");
                                s2.push(node.left);
                                s2.push(node.right);
                            }
                        }
                        if (!temp.isEmpty()) {
                            list.add(temp);
                            layer++;
                            System.out.println();
                        }
                    } else {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        while (!s2.empty()) {
                            TreeNode node = s2.pop();
                            if(node != null) {
                                temp.add(node.val);
                                System.out.print(node.val + " ");
                                s1.push(node.right);
                                s1.push(node.left);
                            }
                        }
                        if (!temp.isEmpty()) {
                            list.add(temp);
                            layer++;
                            System.out.println();
                        }
                    }
                }
                return list;
            }
    
    
    /**
     * 方法2：优化
     * 
     * 大家的实现很多都是将每层的数据存进ArrayList中，偶数层时进行reverse操作，
     * 在海量数据时，这样效率太低了。
     * （我有一次面试，算法考的就是之字形打印二叉树，用了reverse，
     * 直接被鄙视了，面试官说海量数据时效率根本就不行。）
     *
     * 下面的实现：不必将每层的数据存进ArrayList中，偶数层时进行reverse操作，直接按打印顺序存入
     * 思路：利用Java中的LinkedList的底层实现是双向链表的特点。
     *     1)可用做队列,实现树的层次遍历
     *     2)可双向遍历,奇数层时从前向后遍历，偶数层时从后向前遍历
     */
    public ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (pRoot == null) {
            return ret;
        }
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(null);//层分隔符
        queue.addLast(pRoot);
        boolean leftToRight = true;
         
        while (queue.size() != 1) {
            TreeNode node = queue.removeFirst();
            if (node == null) {//到达层分隔符
                Iterator<TreeNode> iter = null;
                if (leftToRight) {
                    iter = queue.iterator();//从前往后遍历
                } else {
                    iter = queue.descendingIterator();//从后往前遍历
                }
                leftToRight = !leftToRight;
                while (iter.hasNext()) {
                    TreeNode temp = (TreeNode)iter.next();
                    list.add(temp.val);
                }
                ret.add(new ArrayList<Integer>(list));
                list.clear();
                queue.addLast(null);//添加层分隔符
                continue;//一定要continue
            }
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
         
        return ret;
    }
}

