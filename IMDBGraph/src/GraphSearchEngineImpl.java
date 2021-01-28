import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine {

	public List<Node> findShortestPath(Node s, Node t) {
		HashMap<Node, Integer> distanceFromStart = new HashMap<Node, Integer>();
		HashSet<Node> visitedNodes = new HashSet<Node>();
		ArrayList<Node> nodesToVisit = new ArrayList<Node>();
		ArrayList<Node> path = new ArrayList<Node>();
		// first visit the starting node
		nodesToVisit.add(s);
		distanceFromStart.put(s, 0);
		Node node = s;
		boolean pathFound = false; // stores whether a path was actually found
		while (nodesToVisit.size() > 0) {
			node = nodesToVisit.remove(0);
			visitedNodes.add(node);
			if (node == t) {
				pathFound = true;
				break;
			}
			// Loops through neighbors of the node being visited at the moment and
			// adds the neighbor to the list of nodes to visit in the future if
			// the neighbor has not already been visited or is already on the list
			// of nodes to visit in the future
			Iterator iterator = node.getNeighbors().iterator();
			while (iterator.hasNext()) {
				Node neighbor = (Node) iterator.next();
				if (!visitedNodes.contains(neighbor) && !nodesToVisit.contains(neighbor)) {
					nodesToVisit.add(neighbor);
					distanceFromStart.put(neighbor, distanceFromStart.get(node) + 1);
				}
			}
		}
		if (pathFound) {
			path.add(node);
			retracePath(visitedNodes, distanceFromStart, path, node);
			return path;
		}
		return null;
	}
	
	/**
	 * Retraces the path from the target node to the start node
	 * @param visitedNodes nodes visited by the BFS
	 * @param distances distances from start node
	 * @param path the path from start node to target node
	 * @param cursor the current node being retraced from
	 */
	private void retracePath(HashSet<Node> visitedNodes, HashMap<Node, Integer> distances, ArrayList<Node> path, Node cursor) {
		Iterator iterator = visitedNodes.iterator();
		Node tempNode = cursor; // Doesn't really matter what this is initialized to. It will change no matter what.
		while (iterator.hasNext()) {
			tempNode = (Node) iterator.next();
			if (tempNode.getNeighbors().contains(cursor) &&
				(distances.get(tempNode) == distances.get(cursor) - 1)) {
				path.add(0, tempNode);
				break;
			}
		}
		// Keeps retracing path until it reaches start node
		if (distances.get(tempNode) != 0) {
			retracePath(visitedNodes, distances, path, tempNode);
		}
	}

}
