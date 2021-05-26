package codechef;

import java.util.*;
import java.util.logging.*;


/**
 * "Elements of Programming Interviews" 16.2 p280
 */
public final class PeacefulQueens {
    private static final Logger log = Logger.getLogger(PeacefulQueens.class.getName());

    public static final class Position {
        public final int row;
        public final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return this.row * 31 + this.col;
        }

        @Override
        public String toString() {
            return "(" + this.row + "," + this.col + ")";
        }

        @Override
        public boolean equals(Object other) {
            if (null != other && other instanceof Position) {
                final Position b = (Position) other;
                return this.row == b.row && this.col == b.col;
            }
            return false;
        }

        public boolean canAttack(int row, int col) {
            return row == this.row || col == this.col 
                || this.row - this.col == row - col
                || this.row + this.col == row + col;
        }

        public boolean canAttack(Position other) {
            return this.canAttack(other.row, other.col);
        }
    }

    public static final class Board {
        public final Set<Position> queens = new HashSet<>();
        public final Set<Position> available = new HashSet<>();
        public final int dimension;

        public Board(int dimension) {
            this.dimension = dimension;

            for (int row=0; row < dimension; ++row) {
                for (int col=0; col < dimension; ++col) {
                    this.available.add(new Position(row, col));
                }
            }
        }

        public Board(Board other) {
            this.dimension = other.dimension;
            this.queens.addAll(other.queens);
            this.available.addAll(other.available);
        }

        public void addQueen(Position queen) {
            queens.add(queen);
            final List<Position> remove = new ArrayList<>();
            for (Position it : available) {
                if (queen.canAttack(it)) {
                    remove.add(it);
                }
            }
            available.removeAll(remove);
        }

        @Override
        public String toString() {
            final var sb = new StringBuilder();
            sb.append("[");
            for (var it: this.queens) {
                sb.append(it.toString()).append(",");
            }
            sb.append("]");
            return sb.toString();
        }

        @Override
        public int hashCode() {
            return this.queens.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (null != other && other instanceof Board) {
                final Board b = (Board) other;
                return this.queens.equals(b.queens);
            }
            return false;
        }
    
    }

    public static Set<Board> placeQueen(Board board) {
        final Set<Board>   result = new HashSet<>();

        for (Position queen: board.available) {
            final Board newBoard = new Board(board);
            newBoard.addQueen(queen);
            result.add(newBoard);
        }
        return result;
    }

    /**
     * Find the max number of non-attacking placements
     * of n queens on an n by n board
     * 
     * @param coinList
     * @return score of first player
     */
    public static Set<Board> placeQueens(int n) {
        Set<Board> boards = new HashSet<>();

        boards.add(new Board(n));

        for (int i=0; i < n; ++i) {
            final Set<Board> newBoards = new HashSet<>();
            for (Board it: boards) {
                newBoards.addAll(placeQueen(it));
            }
            boards = newBoards;
            log.log(Level.INFO, "Got " + boards.size() + " boards with " + (i+1) + " queens");
        }
        return boards;        
    }

}
