难易程度：★★

重要性：★★★★★

树结构是面试中的考察的重点，而树的遍历又是树结构的基础。非递归的前序遍历算法思路可以借鉴。

一个专注应届生互联网求职分享的公众号“菜鸟名企梦”

/**
     * 后序遍历 非递归
     * 
     * 后序遍历顺序：左右根 -> 变换：先获得根右左的遍历顺序，再反转（ 根右左的顺序可以通过栈即可实现）
     * 
     * @param root
     */
    private static ArrayList<Integer> postOrder(TreeNode root) {
        if(root==null)  return new ArrayList<Integer>();

        // 存储："根右左"的遍历顺序
        Stack<Integer> reverseRes = new Stack<Integer>();

        Stack<TreeNode> s = new Stack<TreeNode>();//辅助栈，保存待遍历的节点
        s.add(root);

        while (!s.isEmpty()) {
            TreeNode tem = s.pop();
            reverseRes.push(tem.val);//存储:"根右左"的遍历顺序,先入"根"节点

         // “右左”的遍历顺序，所以在栈(LIFO)中对应的就是：先进"左"，再进"右"
            if (root.left != null)
                s.push(root.left);
            if (root.right != null)
                s.push(root.right);
        }

        ArrayList<Integer> res = new ArrayList<Integer>();//获得“根左右”的遍历序列
        while (!s.isEmpty()) {
            res.add(reverseRes.pop());
        }
        return res;
    }
    
    /**
     * 后序遍历  递归版本
     * @param root
     * @return
     */
    public static ArrayList<Integer> postOrder(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();

        preOrder(root, res);
        return res;
    }

    private static void postOrder(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return;

        preOrder(root.left);
        preOrder(root.right);
        res.add(root.val);
    }
