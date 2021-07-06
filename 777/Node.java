
public class Node implements Comparable<Node> {

	private int nodeID;
	private boolean isFixedNode;
	public Integer nodeDegree;
	public Integer node400G = 0;
	public Integer nodeTraffic = 0;
	
	
	
	public int compareTo(Node node){
//		return this.nodeDegree.compareTo(node.nodeDegree);
		return this.node400G.compareTo(node.node400G);
//		return this.nodeTraffic.compareTo(node.nodeTraffic);
	}
		
	public Node(int nodeID){
		this.nodeID = nodeID;
		isFixedNode = false;
	}
	
	public int getNodeID(){
		return nodeID;
	}
	
	public boolean getIsFixedNode(){
		return isFixedNode;
	}
	
	public void setFixedNode(){
		isFixedNode = true;
	}
	
	public void setFlexNode(){
		isFixedNode = false;
	}
	
	public static void main(String[] args){
		Node node = new Node(1);
		System.out.println("the node ID is " + node.getNodeID());
		System.out.println("the node is a fixed node? " + node.getIsFixedNode());
		node.setFixedNode();
		System.out.println("the node is a fixed node? " + node.getIsFixedNode());
	}

}