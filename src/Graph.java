import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private ArrayList<Vertex> Vertices = new ArrayList<>();

    public Vertex addvertex(String id) {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }

    public void addvertex(Vertex v) {
        Vertices.add(v);
    }

    public Vertex getvertex(String s) {
        for (Vertex v : Vertices) {
            if (v.Name == s)
                return v;
        }
        return null;
    }

    public void newedge(Vertex from, Vertex to, int dist, int tim) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = tim;
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex zink) {
        //method to apply the algorithm
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMap = new HashMap<>();
        Vertex a;
        // initialize arrays
        for (Vertex v : Vertices) {
            DistanceMap.put(v, 1000); //We initialize the array in infinite
            PredecessorMap.put(v, null);
        }
        DistanceMap.put(source, 0); //We put the source in 0
        for (Vertex v : Vertices) {
            while (!DistanceMap.isEmpty()) { //And we iterate until the distance map is empty
                a = getmin(DistanceMap); //we get the minimum vertex
                if(a==null) {
                    return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(zink), PredecessorMap));
                }

                ArrayList<Edge> edges = a.getOutEdges(); //we get de edges connected to this vertex
                for (Edge e : edges) { //we iterate getting the vertex of each edge
                    if (DistanceMap.containsKey(e.tovertex)) { //if the vertex is in the distance map:
                        Integer alt = DistanceMap.get(a) + e.distance; //we get it value and plus the distance of the edge

                        System.out.println("This is the distance going out from that Vertex: " + alt + ", with previous distance: " + DistanceMap.get(e.tovertex));
                        if (alt < DistanceMap.get(e.tovertex)) { //if it is less, we put it in the arrays
                            DistanceMap.put(e.tovertex, alt);
                            PredecessorMap.put(e.tovertex, a);

                        }
                    }
                }
                DistanceMap.remove(a); //and we remove it of the array
            }
        }
        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(zink), PredecessorMap));
    }

    public Vertex getmin(Map<Vertex, Integer> qmap) {
        Vertex vertex = null;
        int value = 1000;

        for (Map.Entry<Vertex, Integer> entry : qmap.entrySet()) { //we iterate in the vertex's
            Vertex vertex1 = entry.getKey();
            int val = entry.getValue(); //we get de vertex and the value of it

            if (val < value) { //And we return the less
                vertex = vertex1;
                value = val;
            }
        }

        // System.out.println("The minimum vertex: " + vertex.Name + ". and the value is: " + value);
        return vertex;

    }


    class Vertex {
        public String Name;
        public ArrayList<Edge> OutEdges = new ArrayList<>();

        public Vertex(String id) {
            Name = id;
        }

        public void addOutEdge(Edge outedge) {
            OutEdges.add(outedge);
        }

        public ArrayList<Edge> getOutEdges() {
            return OutEdges;
        }
    }

    class Edge {
        private Vertex fromvertex;
        private Vertex tovertex;
        public int distance = 0;
        public int time = 0;

        public Vertex getTovertex() {
            return tovertex;
        }

        public Edge(Vertex from, Vertex to) {
            fromvertex = from;
            tovertex = to;
            fromvertex.addOutEdge(this);
            //If not directional
            tovertex.addOutEdge(this);
        }
    }
}