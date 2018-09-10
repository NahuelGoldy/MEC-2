package mec_2;

import mec_2.model.Graph;
import mec_2.model.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DijkstraTest {

    public static Graph testSetup() {

        // simpler mock graph for unit testing the algorithm

        //  A  --1-- D --2-- E
        //    \     /        |
        //     8   3         1
        //      \ /          |
        //       B  ---2---  c

        // initialize Nodes
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        // define adjacencies
        nodeA.addAdjacent(nodeD, 1);
        nodeA.addAdjacent(nodeB, 8);

        nodeB.addAdjacent(nodeA, 8);
        nodeB.addAdjacent(nodeC, 2);
        nodeB.addAdjacent(nodeD, 3);

        nodeC.addAdjacent(nodeB, 2);
        nodeC.addAdjacent(nodeE, 1);

        nodeD.addAdjacent(nodeA, 1);
        nodeD.addAdjacent(nodeB, 3);
        nodeD.addAdjacent(nodeE, 2);

        nodeE.addAdjacent(nodeD, 2);
        nodeE.addAdjacent(nodeC, 1);

        // initialize Graph
        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);

        return graph;
    }

    @Test
    public void shortestPathTest_AtoC() {
        String origin = "A";
        String dest = "C";
        Graph graph = testSetup();
        Dijkstra dijkstra = new Dijkstra(graph);
        ArrayList<Node> path = dijkstra.calculateShortestPath(origin, dest);

        String pathString = "";
        int pathCost = 0;
        for(Node n : path) {
            pathString += (n.getName() + " -> ");
            if(n.getName() == dest) pathCost = n.getCostFromSource();
        }
        pathString = pathString.substring(0, pathString.length()-4);

        Assert.assertEquals(4, path.size());
        Assert.assertEquals("A -> D -> E -> C", pathString);
        Assert.assertEquals(4, pathCost);
    }

    @Test
    public void shortestPathTest_AtoB() {
        String origin = "A";
        String dest = "B";
        Graph graph = testSetup();
        Dijkstra dijkstra = new Dijkstra(graph);
        ArrayList<Node> path = dijkstra.calculateShortestPath(origin, dest);

        String pathString = "";
        int pathCost = 0;
        for(Node n : path) {
            pathString += (n.getName() + " -> ");
            if(n.getName() == dest) pathCost = n.getCostFromSource();
        }
        pathString = pathString.substring(0, pathString.length()-4);

        Assert.assertEquals(3, path.size());
        Assert.assertEquals("A -> D -> B", pathString);
        Assert.assertEquals(4, pathCost);
    }

    @Test
    public void shortestPathTest_BtoE() {
        String origin = "B";
        String dest = "E";
        Graph graph = testSetup();
        Dijkstra dijkstra = new Dijkstra(graph);
        ArrayList<Node> path = dijkstra.calculateShortestPath(origin, dest);

        String pathString = "";
        int pathCost = 0;
        for(Node n : path) {
            pathString += (n.getName() + " -> ");
            if(n.getName() == dest) pathCost = n.getCostFromSource();
        }
        pathString = pathString.substring(0, pathString.length()-4);

        Assert.assertEquals(3, path.size());
        Assert.assertEquals("B -> C -> E", pathString);
        Assert.assertEquals(3, pathCost);
    }
}
