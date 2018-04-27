import java.util.ArrayList;
import java.util.List;

public class AvlTree<T extends Comparable <T>> {
	T data;
	List<AvlTree<T>> children;
	int leftTreeHeight, rightTreeHeight;
	
	public static boolean checkAvl() {
		
	}
	
	public static void makeAvl() {
		
	}
	
	public AvlTree(T data) {
		this.data = data;
		this.leftTreeHeight = -1;
		this.rightTreeHeight = -1;
		this.children = new ArrayList<>();
	}
	
	public void rotate(StringBuffer s) {
		
	}
	
	public void rotationHelper(AvlTree<T> newN) {
		StringBuffer rot = new StringBuffer();
		if(this.data.compareTo(newN.data) == 1) {
			rot.append("l");
			if(this.children.get(0).data.compareTo(newN.data) == 1) {
				rot.append("l");
			}
			else
				rot.append("r");
		}
		else {
			rot.append("r");
			if(this.children.get(1).data.compareTo(newN.data) == 1) {
				rot.append("l");
			}
			else
				rot.append("r");
		}
		rotate(rot);
	}
	
	public int addNode(AvlTree<T> newN) {
		if(root.data.compareTo(newN.data) == 1) {
			if(root.children.get(0) != null)
			{
				int ret = addNode(root.children.get(0));
				if(ret == 1) {
					int childHeight = Math.max(root.children.get(0).leftTreeHeight,root.children.get(0).rightTreeHeight);
					
					if((childHeight+1) != this.leftTreeHeight)
					{
						this.leftTreeHeight = childHeight+1;
						int diff = Math.abs(this.leftTreeHeight-this.rightTreeHeight);
						if(diff > 1) {
							this.rotationHelper(newN);
						}
						return 1;
					}
					else
						return 0;
				}
				else {
					return 0;
				}
			}
			else 
			{
				root.children.set(0, this);
				return 1;
			}
		}
		else{
			if(root.children.get(1) != null)
			{
				addNode(root.children.get(1) , newN);
				rightTreeHeight++;
			}
			else
			{
				root.children.set(1, this);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AvlTree<Integer> tree = new AvlTree<Integer>(1);
		AvlTree<Integer> newNode = new AvlTree<Integer>(2);
		tree.addNode(newNode);
	}

}
