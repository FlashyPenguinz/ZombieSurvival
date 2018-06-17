package com.flashypenguinz.ZombieSurvival.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.map.Map;

public class PathFinder {

	private static final int MAX_SEARCH_DEPTH = 1000;
	private static final int MOVE_COST = 1;
	
	public static List<Node> pathFind(Map map, float x, float y, float tx, float ty) {
		List<Node> open = new ArrayList<Node>();
		List<Node> closed = new ArrayList<Node>();
		open.clear();
		closed.clear();
		
		Node targetNode = getNode(tx, ty, map.getNodes());
		Node startNode = getNode(x, y, map.getNodes());
		Node currentNode = getNode(x, y, map.getNodes());
		
		currentNode.cost = 0;
		open.add(currentNode);
		
		targetNode.parent = null;
		
		int maxDepth = 0;
		
		if(startNode == targetNode)
			return null;
		
		while(!open.isEmpty() && maxDepth < MAX_SEARCH_DEPTH) {
			Node lowestCost = null;
			for(Node node: open) {
				if(lowestCost == null)
					lowestCost = node;
				else
					if(node.cost+node.heuristic < lowestCost.cost+node.heuristic)
						lowestCost = node;
			}
			currentNode = lowestCost;
			
			if(currentNode.equals(targetNode)) 
				return getPath(startNode, targetNode);
			
			open.remove(currentNode);
			closed.add(currentNode);
			
			for(Node node: getAdjacentNodes(currentNode.getX(), currentNode.getY(), map.getNodes())) {
				if(node.isPassable()&&!closed.contains(node)) {
					int nextStepCost = (int) (currentNode.cost + MOVE_COST);
					if(!open.contains(node)) {
						node.cost = nextStepCost;
						node.heuristic = node.calculateHValue(targetNode);
						node.parent = currentNode;
						maxDepth = Math.max(maxDepth, node.cost);
						open.add(node);
					}
					if(nextStepCost < node.cost) {
						node.cost = nextStepCost;
						node.heuristic = node.calculateHValue(targetNode);
						node.parent = currentNode;
						maxDepth = Math.max(maxDepth, node.cost);
						open.add(node);
					}
				}
			}
		}
		return null;
	}

	private static List<Node> getPath(Node startNode, Node target) {
		List<Node> nodes = new ArrayList<Node>();
		Node current = target;
		while(!current.equals(startNode)) {
			nodes.add(current);
			current = current.parent;
		}
		nodes.add(startNode);
		Collections.reverse(nodes);
		return nodes;
	}
	
	private static List<Node> getAdjacentNodes(int x, int y, Node[][] nodes) {
		List<Node> adjacentNodes = new ArrayList<Node>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if(i == 0 && j == 0)
					continue;
				if(!(i == 0 || j == 0))
					continue;
				int nodeX = x+i;
				int nodeY = y+j;
				if(nodeX < 0 || nodeX >= nodes[0].length || nodeY < 0 || nodeY >= nodes.length)
					continue;
				adjacentNodes.add(nodes[nodeX][nodeY]);
			}
		}
		return adjacentNodes;
	}

	private static Node getNode(float x, float y, Node[][] nodes) {
		try {
			return nodes[(int) Math.floor(x / GameConstants.TILE_SIZE)][(int) Math
			                                            				.floor(y / GameConstants.TILE_SIZE)];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
}
