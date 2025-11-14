package HW_8;

import java.util.*;

public class B08_03 {

    public static class Graph<T> {

        private Map<T, Set<T>> adj = new HashMap<>();

        public void addVertex(T v) {
            adj.putIfAbsent(v, new HashSet<>());
        }

        public void removeVertex(T v) {
            if (!adj.containsKey(v)) return;
            for (T key : adj.keySet()) {
                adj.get(key).remove(v);
            }
            adj.remove(v);
        }

        public void addEdge(T v1, T v2) {
            addVertex(v1);
            addVertex(v2);
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        public void removeEdge(T v1, T v2) {
            if (adj.containsKey(v1)) adj.get(v1).remove(v2);
            if (adj.containsKey(v2)) adj.get(v2).remove(v1);
        }

        public void printGraph() {
            for (var e : adj.entrySet()) {
                System.out.println(e.getKey() + " -> " + e.getValue());
            }
        }
    }

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();

        g.addVertex(1);
        g.addVertex(2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);

        g.printGraph();

        g.removeEdge(1, 2);
        g.removeVertex(3);

        g.printGraph();
    }
}
