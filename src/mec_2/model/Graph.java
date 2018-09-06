package mec_2.model;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes;

    public Graph() {
        this.nodes = new HashSet<>();
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Node getNode(String name) {
        Node result = null;
        for(Node n : this.nodes) {
            if(n.getName() == name) {
                result = n;
            }
        }
        return result;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
