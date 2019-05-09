import java.util.*;
import java.io.*;

public class BST {
	private BNode<Character> root;
	public String[] codes;
	private int numberOfChars = 0;
	
	//CONSTRUCTORS
	public BST() {
		root = null;
	}//end default constructor
	
	public BST(Character rootData) {
		root = new BNode<>(rootData);
	}//end constructor
	
	public BST(Character rootData, BST leftTree, BST rightTree) {
		setTree(rootData);
	}//end constructor
	
	public void setTree(Character rootData) {
		root = new BNode<>(rootData);
	}//end setTree
	
	public void setTree(Character rootData, BST leftTree, BST rightTree) {
		root  = new BNode<>(rootData);
		root.setLeftChild(leftTree.root);
		root.setRightChild(rightTree.root);
	}//end setTree
	
	//Traversal
	public void traverse() {
		traverse(root);
	}
	public void traverse(BNode<Character> curr) {
		if(curr != null) {
			traverse(curr.getLeftChild());
			System.out.println(curr.getData());
			traverse(curr.getRightChild());
		}
	}//end traverse
	
	public BNode<Character> getRoot(){
		return root;
	}
	
	//Tree building method
	public BNode<Character> recBuildTree(BufferedReader br, String s, int line) throws IOException{
		if(s.charAt(0) == 'I') {
			BNode<Character> curr = new BNode<Character>('\0');
			if(line == 0) //Special case for first (root) node
				root = curr;
			curr.setLeftChild(recBuildTree(br, br.readLine(), line++));
			curr.setRightChild(recBuildTree(br, br.readLine(), line++));
			return curr;
		}else if(s.charAt(0) == 'L') {
			numberOfChars++;
			return new BNode<Character>(s.charAt(2)); //create leaf node
		}
		return new BNode<Character>();
	}
	//just a small method to initate the table to the right size
	public void initTable() {
		codes = new String[numberOfChars];
	}

	public void huffCode() { //lil no-arguments call
		initTable();
		StringBuilder out = new StringBuilder();
		huffCode(root, out);
	}
	//Fills array with huffman codes
	private void huffCode(BNode<Character> curr, StringBuilder output) {
		if(curr != null) {
			huffCode(curr.getLeftChild(), output.append('0'));
			output.deleteCharAt(output.length() - 1);
			if(curr.getData() != '\0') {
				//System.out.println(output);
				//System.out.println(curr.getData() - 65);
				codes[curr.getData() - 65] = output.toString();
			}
			huffCode(curr.getRightChild(), output.append('1'));
			output.deleteCharAt(output.length() - 1);
		}
	}
	
	public char[] allChars() { //returns array of all characters valid for this tree
		char[] retval = new char[codes.length];
		for(int i = 0; i < codes.length; i++)
			retval[i] = (char) (i+65);
		return retval;
	}
	
	//print table method
 	public void printTable() {
		for(int i = 0; i < codes.length; i++)
			System.out.println((char) (i + 65) + ": " + codes[i]);
	}
	
	
	//Decode method
	public StringBuilder decode(String s) {
		BNode<Character> curr = root;
		StringBuilder retval = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '0') {
				curr = curr.getLeftChild();
			}else if(s.charAt(i) == '1') {
				curr = curr.getRightChild();
			}
			if(curr.getData() != '\0') {
				retval.append(curr.getData());
				curr = root;
			}
		}
		return retval;
	}
	
	//Encode a string
	public StringBuilder encode(String s) {
		StringBuilder retval = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			retval.append(codes[s.charAt(i) - 65]);
		}
		return retval;
	}
}


