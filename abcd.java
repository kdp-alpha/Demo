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
