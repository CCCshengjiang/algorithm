package cn.cwblue.graph;

import cn.cwblue.graph.GraphGenerator.Node;
import java.util.*;

/**
 * 图的宽度优先遍历
 * 使用队列和set集合实现
 *
 * @author wen
 */
public class BFS {
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
