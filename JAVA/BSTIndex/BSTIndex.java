/*
 THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
 CODE WRITTEN BY OTHER STUDENTS. Dong Sun Yoon;
 */
class Node{
	MovieInfo data;
	Node left, right;
	
	public Node(){	}
	
	public Node (MovieInfo data){
		this.data = data;
	}
}
public class BSTIndex{
	private Node root;	
	
	public void insert(MovieInfo data) {
		if(data == null) { 
			return;
		}
		root = insert(root, data);
	}
	public Node insert(Node x, MovieInfo data) {
		if(x == null){
			return new Node(data);
		}
		int c = data.shortName.compareToIgnoreCase(x.data.shortName);
		if(c < 0) 		x.left = insert(x.left, data);
		else if(c > 0)	x.right = insert(x.right, data); 
		else			x.data = data;
		return x;
	}
	
	public MovieInfo findExact(String key){
		Node x = root;
		
		while (x != null){
			int c = key.compareToIgnoreCase(x.data.shortName);
			if (c < 0) 		x = x.left;
			else if (c> 0)	x = x.right;
			else 			return 	x.data;
		}
		return null;
	}

	public MovieInfo findPrefix(String prefix){
		Node x = root;
			
		while (x != null){
			String str = x.data.shortName.toLowerCase();
			String com = prefix.toLowerCase();
			String thi = com.substring(0, com.length()-1);
			if (str.startsWith(thi))
				return x.data;
			else{				
				int c = com.compareToIgnoreCase(str);
				if (c<0) 						x = x.left;
				else if (c>0) 					x = x.right;
				else if (str.contains(thi))		return x.data;
				else							return null;
			}
		}
		return null;
	}

}
