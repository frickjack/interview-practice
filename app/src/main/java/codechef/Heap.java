package codechef;

import java.util.*;

/**
 * Java heap built on top of an ArrayList.
 * Min floats to the top.
 * java.util.PriorityQueue is the way to go - 
 * just exercising here
 */
public final class Heap<T> {
    private final List<T> tree = new ArrayList<T>();
    private final Comparator<T> cmpr;

    public Heap(Comparator<T> cmpr) {
        this.cmpr = cmpr;
    }

    private int leftChild(int node) {
        return node * 2 + 1;
    }

    private int rightChild(int node) {
        return node * 2 + 2;
    }

    private int parent(int node) {
        return Math.floorDiv(node - 1, 2);
    }

    private int minChild(int node) {
        final int right = rightChild(node);
        final int left = leftChild(node);

        if (right < tree.size()) { 
            if (0 >= this.cmpr.compare(tree.get(left), tree.get(right))) {
                return left;
            }
            return right;
        } else if (left < tree.size()) {
            return left;
        }
        return -1;
    }

    public void swap(int a, int b) {
        final T tempA = tree.get(a);
        tree.set(a, tree.get(b));
        tree.set(b, tempA);
    }

    /**
     * Assuming everything under node in the tree is
     * already a heap, then heapify node
     * 
     * @param node
     */
    private void heapify(final int start) {
        if (start >= tree.size()) {
            return;
        }

        for(int minChild = minChild(start), node = start;
            minChild > 0 && 0 >= this.cmpr.compare(tree.get(minChild), tree.get(node));
            node = minChild, minChild = minChild(node)
            ) {
            // child is smaller than node - swap and recurse
            swap(minChild, node);
        }
    }

    public boolean isEmpty() {
        return this.tree.isEmpty();
    }

    public T remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        final int last = tree.size() - 1;
        if (last > 0) {
            swap(0, last);
        }
        final T result = tree.remove(last);
        if (! tree.isEmpty()) {
            heapify(0);
        }
        return result;
    }

    public void add(T value) {
        tree.add(value);
        // now bubble up
        for (int it = tree.size() - 1, parent = parent(it);
            it > 0 && 0 > this.cmpr.compare(tree.get(it), tree.get(parent));
            it = parent, parent = parent(it)
        ) {
            swap(parent, it);
        }
    }
}
