import java.util.List;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFunction_diagonals {
    
    public static class quentin {

        private List<Integer> board = new ArrayList<>();
        private int line_size;
        
        quentin(final int size) {
            board.addAll(Collections.nCopies(size*size, -1));
            line_size = size;
        }
        
        public TreeSet<Integer> diagonals(final int idx) {        
            TreeSet<Integer> mylist = new TreeSet<>();
            if (idx+line_size+1 < board.size() && (idx+1)/line_size == idx/line_size)
                mylist.add(idx+line_size+1);
            if (idx+line_size-1 < board.size() && (idx-1)/line_size == idx/line_size && idx != 0)
                mylist.add(idx+line_size-1);
            if (idx-line_size-1 >= 0 && (idx-1)/line_size == idx/line_size)
                mylist.add(idx-line_size-1);
            if (idx-line_size+1 >= 0 && (idx+1)/line_size == idx/line_size)
                mylist.add(idx-line_size+1);           
            return mylist;  
        }
    }

    @Test
    public void diagonals_test_topleft() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(6)), myboard.diagonals(0));
    }

    @Test
    public void diagonals_test_topright() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(8)), myboard.diagonals(4));
    }

    @Test
    public void diagonals_test_bottomleft() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(16)), myboard.diagonals(20));
    }

    @Test
    public void diagonals_test_bottomright() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(18)), myboard.diagonals(24));
    }

    @Test
    public void diagonals_test_upperedge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(7,9)), myboard.diagonals(3));
    }

    @Test
    public void diagonals_test_loweredge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(16,18)), myboard.diagonals(22));
    }

    @Test
    public void diagonals_test_leftedge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(6,16)), myboard.diagonals(10));
    }

    @Test
    public void diagonals_test_rightedge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(3,13)), myboard.diagonals(9));
    }

    @Test
    public void diagonals_test_center() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(5,7,15,17)), myboard.diagonals(11));
    }

}
