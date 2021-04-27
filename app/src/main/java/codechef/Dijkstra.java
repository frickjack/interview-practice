package codechef;

import java.util.*;
import java.util.function.Consumer;


/**
 * "Elements of Programming Interviews" 19.9 variant p365
 */
public final class Dijkstra {
    // road db - bidirectional between 2 cities
    final Map<String, Map<String, Road>> roads = new HashMap<>();

    /**
     * Initialize maze with a set of cities
     */
    public Dijkstra(Set<String> cities) {
        for (String it : cities) {
            roads.put(it, new HashMap<>());
        }
    }

    public Dijkstra addRoad(String acity, String bcity, int distance) {
        this.roads.get(acity).put(bcity, new Road(acity, bcity, distance));
        this.roads.get(bcity).put(acity, new Road(bcity, acity, distance));
        return this;
    }

    public static final class Tree {
        public final Map<String, TreeNode> idToNode;
        public final TreeNode  root;

        public Tree(Map<String, TreeNode> idToNode, TreeNode root) {
            this.idToNode = idToNode;
            this.root = root;
        }
    }

    public static final class TreeNode {
        public final String id;
        // roads == edges
        public final Set<TreeNode> children = new HashSet<>();
        public final Optional<TreeNode> parent;
        public final int distanceFromParent;
        
        public TreeNode(String id, TreeNode parent, int distanceFromParent) {
            this.id = id;
            this.parent = Optional.ofNullable(parent);
            this.distanceFromParent = distanceFromParent;
        }

        public TreeNode(String id) {
            this.id = id;
            this.parent = Optional.empty();
            this.distanceFromParent = 0;
        }

        public void addChild(TreeNode child) {
            final var parent = child.parent.get();

            if (parent != this) {
                throw new IllegalArgumentException("invalid child");
            }
            this.children.add(child);
        }

        public boolean isRoot() { return this.parent.isEmpty(); }

        @Override
        public int hashCode() { return this.id.hashCode(); }

        @Override
        public boolean equals(Object other) {
            return (other instanceof TreeNode) &&
                ((TreeNode) other).id.equals(this.id);
        }
    }


    /**
     * Directed edge - goes from a to b
     */
    public static final class Road {
        public final String a;
        public final String b;
        public final int    distance;

        public Road(String a, String b, int distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object other) {
            if (null == other || ! (other instanceof Road)) {
                return false;
            }
            final Road road = (Road) other;
            return road.a == this.a && road.b == this.b && road.distance == this.distance;
        }

        @Override
        public String toString() {
            return new StringBuilder(
                ).append("{ \"a\": \""
                ).append(a
                ).append("\", \"b\":"
                ).append(b
                ).append("\", \"distance\":"
                ).append(distance
                ).append("\"}"
                ).toString();
        }
    }

    /**
     * Dijkstra's algorithm is actually a minimum
     * spanning tree algorithm, but it can stop
     * building the tree once it reaches an
     * optional end node
     * 
     * @param start
     * @param end
     * @return
     */
    public Tree buildSpanningTree(
        String start,
        Optional<String> end
    ) {
        // visited nodes
        final Map<String, TreeNode> idToNode = new HashMap<>();
        final Map<String, Road>  unvisited = new HashMap<>();

        // Little helper to update unvisited
        final Consumer<TreeNode> addNode = (TreeNode newNode) -> {
            // add the new node to the tree
            idToNode.put(newNode.id, newNode);
            
            // add new leaf nodes to the unvisited set,
            // or update the distance to leaf nodes
            final var newNodeRoads = roads.get(newNode.id).values();
            for (var it : newNodeRoads) {
                if (!idToNode.containsKey(it.b) && (
                    !unvisited.containsKey(it.b) 
                    || unvisited.get(it.b).distance > it.distance
                    )
                ) {
                    unvisited.put(it.b, it);
                }
            }
            // add a child to the parent node if any
            newNode.parent.ifPresent(
                (parentNode) -> {
                    parentNode.addChild(newNode);
                }
            );            
        };

        final TreeNode root = new TreeNode(start);
        addNode.accept(root);
 
        while (!unvisited.isEmpty() && (end.isEmpty() || !idToNode.containsKey(end.get()))) {
            // pick the node closest to the tree that is not
            // yet in the tree, and add it to the tree
            Road roadToClosest = null;
            for (var it : unvisited.values()) {
                if (null == roadToClosest || roadToClosest.distance > it.distance) {
                    roadToClosest = it;
                }
            }
            unvisited.remove(roadToClosest.b);
            final var parent = idToNode.get(roadToClosest.a);
            final var newNode = new TreeNode(roadToClosest.b, parent, roadToClosest.distance);
            addNode.accept(newNode);
        }

        return new Tree(idToNode, idToNode.get(start));
    }


    public List<Road> shortestPath(String start, String end) {
        final Tree tree = buildSpanningTree(start, Optional.of(end));
        final List<Road> result = new ArrayList<>();
        
        if (start.equals(end)) {
            result.add(new Road(start, end, 0));
            return result;
        }

        for (var it = tree.idToNode.get(end); null != it && it.parent.isPresent(); it = it.parent.get()) {
            final var parent = it.parent.get();
            result.add(roads.get(parent.id).get(it.id));
        }
        Collections.reverse(result);
        return result;
    }
}
