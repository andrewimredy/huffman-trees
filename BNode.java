
public class BNode<T> {
	//three basic data
		private T data;
		private BNode<T> leftChild;
		private BNode<T> rightChild;
			
		//constructors
		public BNode() {
			this(null);//call next constructor
		}//end default constructor
		
		public BNode(T newData) {
			this(newData, null, null); //call next
		}//end constructor
		
		public BNode(T newData, BNode<T> newLeft, BNode<T> newRight) {
			data = newData;
			leftChild = newLeft;
			rightChild = newRight;
		}//pretty straightforward
		
		//data methods
		public T getData() {
			return data;
		}
		public void setData(T newData) {
			data = newData;
		}
			
		//node methods
		public void setLeftChild(BNode<T> child) {
			leftChild = child;
		}
		public void setRightChild(BNode<T> child) {
			rightChild = child;
		}
		public BNode<T> getLeftChild(){
			return leftChild;
		}
		public BNode<T> getRightChild(){
			return rightChild;
		}	
		//"has" methods
		public boolean hasLeftChild() {
			if(leftChild != null)
				return true;
			return false;
		}
		public boolean hasRightChild() {
			if(rightChild != null)
				return true;
			return false;
		}
}
