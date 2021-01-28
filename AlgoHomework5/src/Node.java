
public class Node implements Comparable<Node> {
	
	private int nodeNum;
	private int penultimateNodeNum;
	private int shortestDistance;
	
	public Node(int nodeNum) {
		this.nodeNum = nodeNum;
		shortestDistance = Integer.MAX_VALUE;
		penultimateNodeNum = -1;
	}
	
	@Override
	public int compareTo(Node o) {
		if (this.getShortestDistance() < o.getShortestDistance()) {
			return 1;
		}
		else if (this.getShortestDistance() > o.getShortestDistance()) {
			return -1;
		}
		return 0;
	}
	
	public int getShortestDistance() {
		return shortestDistance;
	}
	
	public void setShortestDistance(int distance) {
		this.shortestDistance = distance;
	}
	
	public int getNum() {
		return nodeNum;
	}
	
	public void setPenultimateNode(int nodeNum) {
		penultimateNodeNum = nodeNum;
	}
	
	public int getPenultimateNodeNum() {
		return penultimateNodeNum;
	}
}
