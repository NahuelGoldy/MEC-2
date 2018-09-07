package mec_2;

import mec_2.model.Graph;
import mec_2.model.Node;
import mec_2.utils.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    private Graph graph;
    private Logger logger;

    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.logger = Logger.getLoggerInstance();
    }

    public ArrayList<Node> calculateShortestPath(String origin, final String destination) {

        // to notice: this variant actually calculates the shortest path from "origin" to ALL other nodes in the graph
        this.logger.log("Started calculation: shortest path from node " + origin + " to node " + destination);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        ArrayList<Node> path = new ArrayList<>();
        Node node_origin = this.graph.getNode(origin);
        // set up cost zero for initial node
        node_origin.setCostFromSource(0);
        this.logger.log("Weight to origin node set to zero (algorithm initialization)");

        unsettledNodes.add(node_origin);
        this.logger.log("Node " + node_origin.getName() + " added to evaluation list");

        while (unsettledNodes.size() != 0) {
            int its = 1;
            this.logger.log("Iteration over evaluation list n°" + its + " started");
            // from the list of unsettled nodes, select the one that holds the lowest aggregate cost from source
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            this.logger.log("Optimal candidate node found: node" + currentNode.getName());
            unsettledNodes.remove(currentNode);
            this.logger.log("Node " + currentNode.getName() + " removed from evaluation list");
            Map<Node, Integer> adjacents = currentNode.getAdjacents();
            this.logger.log("Adjacent Nodes to node " + currentNode.getName() + " obtained (" + adjacents.keySet().size() + " in total)");

            this.logger.log("Evaluation of adjacent nodes to node " + currentNode.getName() + " started");
            // check each one of its adjacent nodes
            for (Node adj : adjacents.keySet()) {
                this.logger.log("Evaluation of adjacent node " + adj.getName() + " started");
                // if the adjacent node has not been already settled
                //   and the current calculated path has a lower cost than the one it already holds, update it
                this.logger.log("Settled nodes list contains node " + adj.getName() + "? -> " + settledNodes.contains(adj));
                this.logger.log("Current node (node " + currentNode.getName() + ") aggregate cost: " + currentNode.getCostFromSource());
                this.logger.log("Cost of moving from " + currentNode.getName() + " to " + adj.getName() + ": " + adj.getCostFromSource());
                if (!settledNodes.contains(adj) && (currentNode.getCostFromSource() + adjacents.get(adj) < adj.getCostFromSource())) {
                    this.logger.log("New lowest cost path found for node " + adj.getName());
                    this.logger.log("Previous cost: " + adj.getCostFromSource());
                    adj.setCostFromSource(currentNode.getCostFromSource() + adjacents.get(adj));
                    this.logger.log("New cost: " + adj.getCostFromSource());
                    adj.setPath(currentNode.getPath());
                    adj.addToPath(currentNode);
                    unsettledNodes.add(adj);
                    this.logger.log("Node " + adj.getName() + " added to evaluation list" );
                }
            }
            // as all of its adjacents have been evaluated, the current node can be marked as settled
            settledNodes.add(currentNode);
            this.logger.log("Shortest path found from node " + origin + " to " + currentNode.getName() + "; added to settled list");
            this.logger.log("Amount of settled nodes: " + settledNodes.size());
            this.logger.log("Amount of evaluation pending nodes: " + unsettledNodes.size());
            its++;
        }

        // finalize construction of the result list for a particular destination
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
        this.logger.log("Pending evaluation review with lowest cost selected: node " + lowestDistanceNode.getName() + ", with cost = " + lowestDistance);
        return lowestDistanceNode;
    }
}
