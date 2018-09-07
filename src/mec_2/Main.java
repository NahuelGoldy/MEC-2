package mec_2;

import mec_2.model.Graph;
import mec_2.model.Node;
import mec_2.utils.Logger;

import java.util.ArrayList;

public class Main {

    private static Logger logger;

    public static void main(String[] args) {

        // set up variables
        String origin = "F";
        String dest = "I";
        logger = Logger.getLoggerInstance();
        Graph graph = buildGraph();

        // calculate path between 2 Nodes
        Dijkstra dijkstra = new Dijkstra(graph);
        ArrayList<Node> path = dijkstra.calculateShortestPath(origin, dest);

        // auxiliary variables and loop to improve legibility (log)
        String pathString = "";
        int pathCost = 0;
        for(Node n : path) {
            pathString += (n.getName() + " -> ");
            if(n.getName() == dest) pathCost = n.getCostFromSource();
        }
        pathString = pathString.substring(0, pathString.length()-3);
        logger.log(" SUCCESS!");
        logger.log(" Shortest path from " + origin + " to " + dest + " has been found!");
        logger.log(" Shortest path is: " + pathString + ", which has a cost of: " + pathCost);

        logger.saveLogToFile("path_from_" + origin + "_to_" + dest);
    }

    private static Graph buildGraph() {

        // initialize Nodes
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeJ = new Node("J");
        Node nodeK = new Node("K");
        Node nodeL = new Node("L");

        // define adjacencies
        nodeA.addAdjacent(nodeE, 10);

        nodeB.addAdjacent(nodeF, 1);
        nodeB.addAdjacent(nodeK, 4);

        nodeC.addAdjacent(nodeF, 11);
        nodeC.addAdjacent(nodeI, 13);
        nodeC.addAdjacent(nodeL, 6);

        nodeD.addAdjacent(nodeH, 5);
        nodeD.addAdjacent(nodeI, 8);
        nodeD.addAdjacent(nodeL, 15);

        nodeE.addAdjacent(nodeA, 10);
        nodeE.addAdjacent(nodeF, 4);
        nodeE.addAdjacent(nodeG, 9);
        nodeE.addAdjacent(nodeJ, 5);

        nodeF.addAdjacent(nodeB, 1);
        nodeF.addAdjacent(nodeC, 11);
        nodeF.addAdjacent(nodeE, 4);
        nodeF.addAdjacent(nodeG, 6);

        nodeG.addAdjacent(nodeE, 9);
        nodeG.addAdjacent(nodeF, 6);
        nodeG.addAdjacent(nodeH, 5);
        nodeG.addAdjacent(nodeI, 7);
        nodeG.addAdjacent(nodeJ, 8);

        nodeH.addAdjacent(nodeD, 5);
        nodeH.addAdjacent(nodeG, 5);
        nodeH.addAdjacent(nodeJ, 3);

        nodeI.addAdjacent(nodeC, 13);
        nodeI.addAdjacent(nodeD, 8);
        nodeI.addAdjacent(nodeG, 7);

        nodeJ.addAdjacent(nodeE, 5);
        nodeJ.addAdjacent(nodeG, 8);
        nodeJ.addAdjacent(nodeH, 3);

        nodeK.addAdjacent(nodeB, 4);
        nodeK.addAdjacent(nodeL, 8);

        nodeL.addAdjacent(nodeC, 6);
        nodeL.addAdjacent(nodeD, 15);
        nodeL.addAdjacent(nodeK, 8);

        // initialize Graph
        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        graph.addNode(nodeG);
        graph.addNode(nodeH);
        graph.addNode(nodeI);
        graph.addNode(nodeJ);
        graph.addNode(nodeK);
        graph.addNode(nodeL);

        return graph;
    }

}
