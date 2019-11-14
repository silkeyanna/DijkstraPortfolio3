import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {
        // Create graph
        GraphTests TestGraph= new GraphTests();
        Graph g = TestGraph.MakeSmallGraph();
        Graph.Vertex source = g.getvertex("A");
        Graph.Vertex zink = g.getvertex("F");
        Pair<Integer, Map<Graph.Vertex, Graph.Vertex>> results=g.ShortestDistance(source, zink);
        Graph.Vertex current =zink;
        ArrayList<Graph.Vertex> Path= new ArrayList<>();
        Path.add(zink);
        while ((current != source) && (results.getValue().get(current)!=null))
        {
            current=results.getValue().get(current);
            Path.add(0,current);
        }
        for(Graph.Vertex v : Path)
        {
            System.out.print( v.Name);
            if (v!=zink)
                System.out.print("->");
        }

        Graph n = TestGraph.MakeSmallGraph1();
        Graph.Vertex source1 = n.getvertex("10");
        Graph.Vertex zink1 = n.getvertex("6");
        Pair<Integer, Map<Graph.Vertex, Graph.Vertex>> results1=n.ShortestDistance(source1, zink1);
        Graph.Vertex current1 =zink1;
        ArrayList<Graph.Vertex> Path1= new ArrayList<>();
        Path1.add(zink1);
        while ((current1 != source1) && (results1.getValue().get(current1)!=null))
        {
            current1=results1.getValue().get(current1);
            Path1.add(0,current1);
        }
        for(Graph.Vertex v : Path1)
        {
            System.out.print( v.Name);
            if (v!=zink1)
                System.out.print("->");
        }
    }
    public Graph MakeSmallGraph()
    {
        Graph mygraph= new Graph();
        final Graph.Vertex A=mygraph.addvertex("A");
        final Graph.Vertex B= mygraph.addvertex("B");
        final Graph.Vertex C =mygraph.addvertex("C");
        final Graph.Vertex D = mygraph.addvertex("D");
        final Graph.Vertex E = mygraph.addvertex("E");
        final Graph.Vertex F = mygraph.addvertex("F");

        mygraph.newedge(A,B,1,2);
        mygraph.newedge(A,C, 5,1);
        mygraph.newedge(A,D, 4,6);
        mygraph.newedge(B,C, 3,2);
        mygraph.newedge(B,D, 2,3);
        mygraph.newedge(B,E, 2,4);
        mygraph.newedge(C,F, 1,8);
        mygraph.newedge(C,E, 2,2);
        mygraph.newedge(D,F, 2,7);
        mygraph.newedge(E,F, 3,6);


        return mygraph;

    }
    public Graph MakeSmallGraph1()
    {
        Graph mygraph= new Graph();
        final Graph.Vertex A=mygraph.addvertex("1");
        final Graph.Vertex B= mygraph.addvertex("2");
        final Graph.Vertex C =mygraph.addvertex("3");
        final Graph.Vertex D = mygraph.addvertex("4");
        final Graph.Vertex E = mygraph.addvertex("5");
        final Graph.Vertex F = mygraph.addvertex("6");
        final Graph.Vertex G=mygraph.addvertex("7");
        final Graph.Vertex H= mygraph.addvertex("8");
        final Graph.Vertex I =mygraph.addvertex("9");
        final Graph.Vertex J = mygraph.addvertex("10");

        //new graph
        mygraph.newedge(A,B,10,1);
        mygraph.newedge(A,D, 4,1);
        mygraph.newedge(A,E, 20,1);
        mygraph.newedge(A,F, 5,1);
        mygraph.newedge(A,G, 15,1);
        mygraph.newedge(B,C, 5,1);
        mygraph.newedge(B,D, 10,1);
        mygraph.newedge(C,B, 15,1);
        mygraph.newedge(C,D, 5,1);
        mygraph.newedge(D,E, 10,1);
        mygraph.newedge(E,F,5,1);
        mygraph.newedge(G,F, 10,1);
        mygraph.newedge(H,A, 5,1);
        mygraph.newedge(H,B, 20,1);
        mygraph.newedge(H,G, 5,1);
        mygraph.newedge(I,B, 15,1);
        mygraph.newedge(I,H, 20,1);
        mygraph.newedge(I,J, 10,1);
        mygraph.newedge(J,B, 5,1);
        mygraph.newedge(J,C, 15,1);

        return mygraph;

    }
}