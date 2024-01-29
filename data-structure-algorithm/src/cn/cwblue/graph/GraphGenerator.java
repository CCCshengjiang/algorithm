package cn.cwblue.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
            fromNode.nodes.add(toNode);
            graph.edges.add(newEdge);
        }
        return graph;

    }

    // 边的定义
    private static class Edge {
        public Node from;
        public Node to;
        public int weight; // 权重

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // 点的定义
    private static class Node {
        public int value;
        public int inNum; // 入度
        public int outNum; // 出度
        public List<Node> nodes; // 出度点
        public List<Edge> edges; // 出度边

        public Node(int value) {
            this.value = value;
            this.inNum = 0;
            this.outNum = 0;
            this.nodes = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    // 图的定义
    private static class Graph {
        public HashMap<Integer, Node> nodes; // 每个编号对应的节点
        public HashSet<Edge> edges; // 边

        public Graph() {
            this.nodes = new HashMap<>();
            this.edges = new HashSet<>();
        }
    }
}
