package codechef;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * "Elements of Programming Interviews" 19.1 p349
 */
public final class Maze {
    public static final class Coord {
        public final int row;
        public final int col;

        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object other) {
            if (null == other || !(other instanceof Coord)) {
                return false;
            }
            final Coord crd = (Coord) other;
            return crd.row == row && crd.col == col;
        }

        @Override
        public int hashCode() {
            return row * 31 * 31 + col * 31;
        }
    }

    //----------------

    private final List<List<Integer>> maze;

    public static final int WALL = 1;
    public static final int PATH = 0;

    //------------------

    /**
     * Initialize maze full of walls (1 indicates wall)
     * 
     * @param numRows
     * @param numCols
     */
    public Maze(int numRows, int numCols) {
        maze = new ArrayList<List<Integer>>(numRows);
        final List<Integer> wall = new ArrayList<>(numCols);
        for(int i=0; i < numCols; ++i) {
            wall.add(WALL);
        }
        for (int i=0; i < numRows; ++i) {
            final List<Integer> row = new ArrayList<>(wall);
            maze.add(row);
        }
    }

    public int get(int row, int col) {
        return maze.get(row).get(col);
    }

    public void addHorizontalPath(int row, int start, int end) {
        final var rowData = maze.get(row);
        for (int i=start; i < end; ++i) {
            rowData.set(i, PATH);
        }
    }

    public void addVerticalPath(int col, int start, int end) {
        for (int i=start; i < end; ++i) {
            maze.get(i).set(col, PATH);
        }
    }

    /**
     * Get the set of possible next steps
     * (neighbor cells that are also not walls)
     * 
     * @param start
     * @return list of coordinates
     */
    public List<Coord> nextSteps(
        Coord start
    ) {
        final Stream.Builder<Coord> stb = Stream.builder();
        return stb.add(new Coord(start.row -1, start.col)
        ).add(new Coord(start.row +1, start.col)
        ).add(new Coord(start.row, start.col-1)
        ).add(new Coord(start.row, start.col+1)
        ).build().filter(
            (Coord it) -> {
                return it.row >= 0 && it.col >=0 &&
                    it.row < maze.size() &&
                    it.col < maze.get(it.row).size() &&
                    PATH == maze.get(it.row).get(it.col);
            }
        ).collect(Collectors.toList());
    }

    /**
     * Depth first traverse
     * 
     * @param maze
     * @param start
     * @param end
     * @param already
     * @return
     */
    private List<Coord> dfTraverse(
        Coord start,
        Coord end,
        Set<Coord> already
    ) {
        final List<Coord> result = new ArrayList<Coord>();
        if (already.contains(start)) {
            return result;
        }
        result.add(start);
        already.add(start);
        if (start.equals(end)) {
            return result;
        }
        final List<Coord> nextSteps = nextSteps(start);
        for (Coord it : nextSteps) {
            if (it.equals(end)) {
                result.add(end);
                return result;
            }
        }
        for (Coord it : nextSteps) {
            if (! already.contains(it)) {
                final var path = dfTraverse(it, end, already);
                if (! path.isEmpty()) {
                    result.addAll(path);
                    return result;
                }
            }
        }
        return Collections.emptyList();
    }

    public List<Coord> traverse(
        Coord start,
        Coord end
    ) {
        return dfTraverse(start, end, new HashSet<Coord>());
    }
}
