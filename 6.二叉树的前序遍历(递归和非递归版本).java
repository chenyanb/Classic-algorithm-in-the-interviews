难易程度：★★

重要性：★★★★★

树结构是面试中的考察的重点，而树的遍历又是树结构的基础。中序遍历的非递归版本要求重点理解掌握。

/**
     * 非递归版本的中序遍历
     * node指向待处理的节点，在中序遍历中如果要输出一个节点，要么该节点没有左孩子，要么该节点的左子树已经全部输出了。
     *所以：
     *1.当node为null时，表示暂时没有新节点处理，此时出栈一个节点（表明该节点没有左子树或者左子树全部处理了）；
     *    这时只需要继续处理右子树即可， 中序是“左根右”：我们先入栈 根节点 ，如果有左节点则入栈左节点，
     *    否则出栈根节点（没有左节点则输出遍历根节点），之后处理右子树
     *2.当node不为null时，将node入栈，并将node指向node.left，表明要处理当前节点必须先处理左子树节点
     * 
     * @param root：根节点
     */
    public static List<Integer> midOrderTraverse(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<Integer>();

        if (root == null)
            return res;

        Stack<TreeNode> aux = new Stack<TreeNode>();
        TreeNode node = root;//node指向待处理节点

        while (node != null || !aux.isEmpty()) {
            while (node != null) {
              //当前节点不为null，将当前节点入栈等到该节点的左子树全部处理完后在处理当前节点
                aux.add(node);
                node = node.left;//先处理左孩子节点
            }
            TreeNode temp = aux.pop();
            res.add(temp.val);//node没有左孩子，则输出当前node节点
            node = temp.right;//处理node的右子树
        }
        return res;
    }
    
/**
     * 中序遍历，递归版本
     * @param root
     * @return
     */
    public static ArrayList<Integer> midOrderTraverse(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();

        midOrderTraverse(root, res);
        return res;
    }

    private static void midOrderTraverse(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return;

        preOrder(root.left);
        res.add(root.val);
        preOrder(root.right);
    }
