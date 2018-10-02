难易程度：★★
重要性：★★★★★

 树结构是面试中的考察的重点，而树的遍历又是树结构的基础。
 
 一个专注应届生互联网求职分享的公众号“菜鸟名企梦”

//先序遍历，递归版本
public static ArrayList<Integer> preOrder(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
         Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode tem = s.pop();// 根节点出栈
             // 把根节点加入结果集 前序遍历顺序：根、左，右 所以对应的进栈顺序为：右、左（根节点进栈后马上出栈了）
            res.add(tem.val);
            if (tem.right != null)
                s.push(tem.right);
            if (tem.left != null)
                s.push(tem.left);
        }
        return res;
    }
    
    /**
     * 先序遍历，递归版本
     * @param root
     * @return
     */
    public static ArrayList<Integer> preOrder(TreeNode root){
        ArrayList<Integer> res = new ArrayList<Integer>();
         preOrder(root,res);
        return res;
    }
    private static void preOrder(TreeNode root,ArrayList<Integer> res) {
        if(root==null)  return;
         res.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
