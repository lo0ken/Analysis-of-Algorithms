import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
	// write your code here
        Node root =  generateTree( 25, 1);

        //getTimeForMethod(Main::recDFS, root);
        //System.out.println(getTimeForMethod( Main::recDFS, root));

        getTimeForMethod(Main::contDFS, root);
        System.out.println(getTimeForMethod( Main::contDFS, root));

    }

    public static long getTimeForMethod(Function<Node, Integer> function, Node root) {
        int repeats = 30;
        //Instant start = Instant.now();
        long start = System.nanoTime();
        for (int i = 0; i < repeats; i++) {
            function.apply(root);
        }
        long end = System.nanoTime();
        //Instant end = Instant.now();
        //return Duration.between(start, end).toNanos() / repeats;
        return (end - start) / 10;
    }

    public static Node generateTree(int height, int level) {
        Node root = new Node(level);

        if (level == height)
            return root;

        root.left = generateTree(height, level + 1);
        root.right = generateTree(height, level + 1);

        return root;
    }

    public static int recDFS(Node root){
        if (root.left!=null) recDFS(root.left);
        if (root.right!=null) recDFS(root.right);

        return 0;
    }

    public static int contDFS(Node top){
        Stack<Node> stack = new Stack<> ();
        while (top!=null || !stack.empty()){
            if (!stack.empty()){
                top=stack.pop();
            }
            while (top!=null){
                if (top.right!=null) stack.push(top.right);
                top=top.left;
            }
        }
        return 0;
    }


    public static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
