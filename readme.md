Description
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

bstdlloriginalbst

We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.

bstdllreturndll

Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

bstdllreturnbst

![alt text](![alt text](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png))


![image](https://user-images.githubusercontent.com/85121133/185751555-d06ac53e-032a-480b-a76b-96fefdf1d65c.png)


----------------------------------------------------------------------------------------------------------------
```java
public class Solution {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        return morris_order(root);
    }
    public TreeNode rightmost(TreeNode ln,TreeNode curr){
        if(ln.right!=null && ln.right!=curr){
            ln = ln.right;
        }

        return ln;
    }
    public TreeNode morris_order(TreeNode root){
        TreeNode curr = root;
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        while(curr!=null){
            TreeNode ln = curr.left;
            if(ln==null){
                prev.right = curr;
                curr.left = prev;
                prev = prev.right;

                curr = curr.right;
            }
            else{
                TreeNode rightN = rightmost(ln, curr);
                if(rightN.right==null){
                    rightN.right = curr;
                    curr = curr.left;
                }
                else{
                        rightN.right = null;
                        prev.right = curr;
                        curr.left = prev;
                        prev = prev.right;

                        curr = curr.right;

                }
            }
        }
        TreeNode head = dummy.right;
        dummy.right = head.left = null;

        head.left = prev;
        prev.right = head;

        return head;
    }
}
```
