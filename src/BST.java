public class BST {

    static class Node {
        int key;
        Node left,right;

        Node(int key) {
            this.key = key;
        }
    }

    Node root;

    public void insert(int key){
        Node newNode = new Node(key);
        if(root == null){
            root = newNode;
            return;
        }
        Node cur = root;
        while(true){
            if(key == cur.key) {
                return;
            }

            if(key < cur.key){
                if(cur.left == null) {
                    cur.left = newNode;
                    return;
                }
                cur = cur.left;
            }
            else {
                if(cur.right == null){
                    cur.right = newNode;
                    return;
                }
                cur = cur.right;
            }
        }
    }

    public boolean search(int key){
        Node cur = root;
        while(cur!= null) {
            if(key == cur.key){
                return true;
            }
            if(key < cur.key) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }
        return false;
    }

    public void delete(int key){
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, int key){
        if (node == null) return null;

        if (key < node.key){
            node.left = deleteNode(node.left, key);
        }
        else if (key > node.key){
            node.right = deleteNode(node.right, key);
        }
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node min = node.right;
            while(min.left != null) {
                min = min.left;
            }
            node.key = min.key;
            node.right = deleteNode(node.right, min.key);
        }
        return node;
    }
}
