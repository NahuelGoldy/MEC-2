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

    public ArrayList<Node> calculateShortestPath(String origin, String destination) {

        Node node_origin = this.graph.getNode(origin);
        node_origin.setCostFromSource(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        ArrayList<Node> path = new ArrayList<>();

        unsettledNodes.add(node_origin);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            Map<Node, Integer> adjacents = currentNode.getAdjacents();

            for (Node adj : adjacents.keySet()) {
                if (!settledNodes.contains(adj)) {
                    CalculateMinimumDistance(adj, adjacents.get(adj), currentNode);
                    unsettledNodes.add(adj);
                }
            }
            settledNodes.add(currentNode);
        }

        for(Node n : settledNodes) {
            if(n.getName().equals(destination)) {
                path = (n.getPath());
                path.add(n);
            }
        }
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

    private void CalculateMinimumDistance(Node evaluationNode, Integer edgeCost, Node sourceNode) {
        if (sourceNode.getCostFromSource() + edgeCost < evaluationNode.getCostFromSource()) {
            evaluationNode.setCostFromSource(sourceNode.getCostFromSource() + edgeCost);
            evaluationNode.setPath(sourceNode.getPath());
            evaluationNode.addToPath(sourceNode);
        }
    }

}
