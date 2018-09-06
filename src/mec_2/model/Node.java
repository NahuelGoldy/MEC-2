package mec_2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {

    private String name;
    private int costFromSource;
    private ArrayList<Node> path;
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
        this.costFromSource = Integer.MAX_VALUE;
        this.path = new ArrayList<>();
    }

    public void addAdjacent(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Map<Node, Integer> getAdjacents() {
        return this.adjacentNodes;
    }

    public String getName() {
        return name;
    }

    public int getCostFromSource() {
        return costFromSource;
    }

    public void setCostFromSource(int costFromSource) {
        this.costFromSource = costFromSource;
    }

    public ArrayList<Node> getPath() {
        return path;
    }

    public void setPath(ArrayList<Node> path) {
        this.path = new ArrayList<>();
        this.path.addAll(path);
    }

    public void addToPath(Node n) { this.path.add(n); }
}
