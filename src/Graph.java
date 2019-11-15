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
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMap = new HashMap<>();
        Vertex smallestVertex;
        // initialize arrays
        for (Vertex v : Vertices) {
            DistanceMap.put(v, 1000);
            PredecessorMap.put(v, null);
        }
        DistanceMap.put(source, 0);
        for (Vertex v : Vertices) {
            while (!DistanceMap.isEmpty()) {
                smallestVertex = getmin(DistanceMap);
                if(smallestVertex==null) {
                    return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(zink), PredecessorMap));
                }
                //System.out.println("This is the smallest Vertex: " + smallestVertex.Name);
                ArrayList<Edge> edges = smallestVertex.getOutEdges();
                for (Edge e : edges) {
                    if (DistanceMap.containsKey(e.tovertex)) {
                        Integer alt = DistanceMap.get(smallestVertex) + e.distance;
                //        System.out.println("Information on e: " + e.fromvertex.Name + ", " + e.tovertex.Name);
                //        System.out.println("This is the distance going out from that Vertex: " + alt + ", with previous distance: " + DistanceMap.get(e.tovertex));
                        if (alt < DistanceMap.get(e.tovertex)) {
                            DistanceMap.put(e.tovertex, alt);
                            PredecessorMap.put(e.tovertex, smallestVertex);
                //            System.out.println("In the PredecessorMap it now looks like: " + e.tovertex.Name + ", " + smallestVertex.Name);
                        }
                    }
                }
                DistanceMap.remove(smallestVertex);
            }
        }
        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(zink), PredecessorMap));
    }

    public Vertex getmin(Map<Vertex, Integer> qmap) {
        Vertex vertex = null;
        int value = 1000;

        for (Map.Entry<Vertex, Integer> entry : qmap.entrySet()) {
            Vertex vertex1 = entry.getKey();
            int val = entry.getValue();
            //System.out.println("This is val: " + val + "for Vertex: " + entry.getKey().Name);
            if (val < value) {
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