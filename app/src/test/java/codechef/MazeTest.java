package codechef;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;


public class MazeTest {
    @Test public void testMaze() {
        final Maze maze = new Maze(10, 10);
        maze.addVerticalPath(0, 0, 10);
        maze.addVerticalPath(9, 0, 10);
        maze.addHorizontalPath(2, 0, 5);
        maze.addHorizontalPath(6, 0, 10);
        final var start = new Maze.Coord(0, 0);
        final var end = new Maze.Coord(9, 9);
        final var result = maze.traverse(start, end);
        final var expected = Arrays.asList(
            new Maze.Coord(0, 0),
            new Maze.Coord(1, 0),
            new Maze.Coord(2, 0),
            new Maze.Coord(3, 0),
            new Maze.Coord(4, 0),
            new Maze.Coord(5, 0),
            new Maze.Coord(6, 0),
            new Maze.Coord(6, 1),
            new Maze.Coord(6, 2),
            new Maze.Coord(6, 3),
            new Maze.Coord(6, 4),
            new Maze.Coord(6, 5),
            new Maze.Coord(6, 6),
            new Maze.Coord(6, 7),
            new Maze.Coord(6, 8),
            new Maze.Coord(6, 9),
            new Maze.Coord(7, 9),
            new Maze.Coord(8, 9),
            new Maze.Coord(9, 9)
        );
        assertEquals("maze found path", expected, result);
    }
}
