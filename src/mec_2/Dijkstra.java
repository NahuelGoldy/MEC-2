package mec_2;

import mec_2.model.Graph;
import mec_2.model.Node;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<Node> calculateShortestPath(String origin, final String destination) {

        // to notice: this variant actually calculates the shortest path from "origin" to ALL other nodes in the graph

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        ArrayList<Node> path = new ArrayList<>();
        Node node_origin = this.graph.getNode(origin);
        // set up cost zero for initial node
        node_origin.setCostFromSource(0);

        unsettledNodes.add(node_origin);

        while (unsettledNodes.size() != 0) {
            // from the list of unsettled nodes, select the one that holds the lowest aggregate cost from source
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            Map<Node, Integer> adjacents = currentNode.getAdjacents();

            // check each one of its adjacent nodes
            for (Node adj : adjacents.keySet()) {
                // if the adjacent node has not been already settled
                //  and the current path has a lower cost than the one it already holds, update it
                if (!settledNodes.contains(adj) && (currentNode.getCostFromSource() + adjacents.get(adj) < adj.getCostFromSource())) {
                    adj.setCostFromSource(currentNode.getCostFromSource() + adjacents.get(adj));
                    adj.setPath(currentNode.getPath());
                    adj.addToPath(currentNode);
                    unsettledNodes.add(adj);
                }
            }
            settledNodes.add(currentNode);
        }

        // finalize construction of the result list
        settledNodes.forEach((Node n) -> {
            if(n.getName().equals(destination)) {
                path.addAll(n.getPath());
                path.add(n);
                return;
            }
        });
        return path;
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getCostFromSource();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
