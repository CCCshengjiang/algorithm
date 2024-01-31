package cn.cwblue.graph;

import java.util.*;

/**
 * 图
 * 由点的集合和边的集合构成
 * 虽然存在有向图和无向图的概念，但是实际上都可以用有向图来表达
 * 边上可能带有权值
 *
 * @author wen
 */
public class GraphGenerator {
    // 根据邻接表来表达图的结构
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] integers : matrix) {
            Integer from = integers[0];
            Integer to = integers[1];
            Integer weight = integers[2];
            if (graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(fromNode, toNode, weight);
            fromNode.edges.add(newEdge);
            fromNode.outNum++;
            toNode.inNum++;
            fromNode.nexts.add(toNode);
            graph.edges.add(newEdge);
        }
        return graph;

    }

    // 边的定义
    public static class Edge {
        public Node from;
        public Node to;
        public int weight; // 权重

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge edge = (Edge) o;
            return weight == edge.weight && Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }

    // 点的定义
    public static class Node {
        public int value;
        public int inNum; // 入度
        public int outNum; // 出度
        public List<Node> nexts; // 出度点
        public List<Edge> edges; // 出度边

        public Node(int value) {
            this.value = value;
            this.inNum = 0;
            this.outNum = 0;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return value == node.value && inNum == node.inNum && outNum == node.outNum && Objects.equals(nexts, node.nexts) && Objects.equals(edges, node.edges);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, inNum, outNum, nexts, edges);
        }
    }

    // 图的定义
    public static class Graph {
        public Map<Integer, Node> nodes; // 每个编号对应的节点
        public Set<Edge> edges; // 边

        public Graph() {
            this.nodes = new HashMap<>();
            this.edges = new HashSet<>();
        }
    }
}
