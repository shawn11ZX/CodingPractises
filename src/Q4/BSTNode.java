package Q4;

public class BSTNode {
    public int k;
    public int v;
    BSTNode left;
    BSTNode right;
    BSTNode parent;


    public BSTNode(int k, BSTNode left, BSTNode right) {
        this.k = k;
        this.v = k;
        this.left = left;
        this.right = right;
    }
    public BSTNode(int k, int v) {
        this.k = k;
        this.v = v;
    }

    public static BSTNode find(BSTNode root, int k) {
        if(root == null)
            return null;
        if (root.k == k)
            return root;
        else if (k <= root.k)
        {
            return find(root.left, k);
        }
        else {
            return find(root.right, k);
        }
    }
    public static BSTNode insert(BSTNode root, int k, int v)
    {
        if (root == null)
            return new BSTNode(k, v);
        else if (k <= root.k) {
            root.left = insert(root.left, k, v);
            if(root.left != null)
                root.left.parent = root;
            return root;
        }
        else {
            root.right = insert(root.right, k, v);
            if(root.right != null)
                root.right .parent = root;
            return root;
        }
    }

    public static int getSize(BSTNode root) {
        if (root == null)
            return 0;
        return 1 + getSize(root.left) + getSize(root.right);
    }

    public static class LargestResult {
        public BSTNode largest;
        public BSTNode newRoot;
    }

    static LargestResult  removeLargest(BSTNode n)
    {
        LargestResult rc = new LargestResult();
        if(n.right == null)
        {
            rc.largest = n;
            rc.newRoot = n.left;
            return rc;
        }
        else {
            BSTNode prev = n;
            BSTNode cur = n.right;
            while(cur.right != null)
            {
                prev = cur;
                cur = cur.right;
            }
            prev.right = cur.left;
            rc.largest = cur;
            rc.newRoot = n;
            return rc;
        }
    }

    public static BSTNode delete(BSTNode root, int k) {
        if (root == null)
            return null;
        else if (root.k == k)
        {
            if (root.left != null && root.right != null)
            {
                LargestResult rc = removeLargest(root.left);
                rc.largest.left = rc.newRoot;
                rc.largest.right = root.right;
                if(rc.largest.right != null)
                    rc.largest.right.parent = root;
                if(rc.largest.left != null)
                    rc.largest.left.parent = root;
                return rc.largest;
            }
            else if(root.left != null) {
                return root.left;
            }
            else if (root.right != null)
            {
                return root.right;
            }
            else {
                return null;
            }
        }
        else if (k <= root.k)
        {
            root.left = delete (root.left, k);
            if(root.left != null)
                root.left.parent = root;
            return root;
        }
        else {
            root.right = delete (root.right, k);
            if(root.right != null)
                root.right.parent = root;
            return root;
        }
    }

    public static BSTNode next(BSTNode node) {
        if (node == null)
            return null;
        if (node.right != null)
        {
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        }
        else {
            BSTNode parent = node.parent;
            while(parent != null)
            {
                if (parent.left == node)
                    return parent;

                node = parent;
                parent = parent.parent;
            }
            return null;
        }
    }
}
