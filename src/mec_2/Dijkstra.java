package mec_2;

import mec_2.model.Graph;
import mec_2.model.Node;

import java.util.ArrayList;
import java.util.Map;

public class Dijkstra {

    private Graph graph;
    private ArrayList<Node> visited;

    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.visited = new ArrayList<>();
    }

    public ArrayList<Node> calculateShortestPath(String origin, String destination) {

        ArrayList<Node> path = new ArrayList<>();
        Node node_origin = this.graph.getNode(origin);
        Map<Node, Integer> adjacents = node_origin.getAdjacents();
        Node closest = null;
        int minWeight = Integer.MAX_VALUE;

        this.visited.add(node_origin);
        path.add(node_origin);

        for(Node adj : adjacents.keySet()) {
            if(adjacents.get(adj) < minWeight) {
                minWeight = adjacents.get(adj);
                closest = adj;
            }

        }

        // calculate path
        if(closest.getName().equals(destination)) {
            path.add(closest);
        }
        else {
            path.addAll(calculateShortestPath(closest.getName(), destination));
        }
        return path;

    }

}
