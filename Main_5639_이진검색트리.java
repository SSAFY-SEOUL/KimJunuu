package day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Node {
	int num;
	Node left, right;

	public Node(int num) {
		this.num = num;
	}

	void insert(int num) {
		if (num < this.num) {
			if (this.left == null) {
				this.left = new Node(num);
				return;
			}
			this.left.insert(num);
			return;
		}
		if (this.right == null) {
			this.right = new Node(num);
			return;
		}
		this.right.insert(num);

	}

}

public class Main_5639_이진검색트리 {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		List<Integer> preOrderedList = new ArrayList<Integer>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		Node root = new Node(Integer.parseInt(br.readLine()));
		String str;
		while (true) {
			str = br.readLine();
			if (str == null) {
				break;
			}
			if(str.isEmpty()) {
				break;
			}
			root.insert(Integer.parseInt(str));			
		}
		
		postOrder(root);
		System.out.println(sb);
		// printPostOrderedList(1);
	}
	
	private static void postOrder(Node node) {
		if(node.left != null) {
			postOrder(node.left);
		}
		if(node.right != null) {
			postOrder(node.right);
		}
		sb.append(node.num + "\n");
	}
	

}
