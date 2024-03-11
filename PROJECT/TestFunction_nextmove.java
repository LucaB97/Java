import java.util.List;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFunction_neighbours {
    
    public static class quentin {

        private List<Integer> board = new ArrayList<>();
        private int line_size;
        
        quentin(final int size) {
            board.addAll(Collections.nCopies(size*size, -1));
            line_size = size;
        }
        
        public TreeSet<Integer> neighbours(final int idx, final ArrayList<Integer> exclude) {        
            TreeSet<Integer> mylist = new TreeSet<>();
            if ((idx+line_size < board.size()) && (!exclude.contains(idx+line_size)))
                mylist.add(idx+line_size);
            if ((idx+1 < board.size()) && (idx/line_size == (idx+1)/line_size) && (!exclude.contains(idx+1)))
                mylist.add(idx+1);
            if ((idx-line_size >= 0) && (!exclude.contains(idx-line_size)))
                mylist.add(idx-line_size);
            if ((idx-1 >= 0) && (idx/line_size == (idx-1)/line_size) && (!exclude.contains(idx-1)))
                mylist.add(idx-1);        
            return mylist;            
        }
    }

    @Test
    public void neighbours_test_topleft() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(1,5)), myboard.neighbours(0, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_topright() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(3,9)), myboard.neighbours(4, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_bottomleft() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(15,21)), myboard.neighbours(20, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_bottomright() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(19,23)), myboard.neighbours(24, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_upperedge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(2,4,8)), myboard.neighbours(3, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_loweredge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(17,21,23)), myboard.neighbours(22, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_leftedge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(5,11,15)), myboard.neighbours(10, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_rightedge() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(4,8,14)), myboard.neighbours(9, new ArrayList<>()));
    }

    @Test
    public void neighbours_test_center() {
        quentin myboard = new quentin(5);
        assertEquals(new TreeSet<>(Arrays.asList(6,10,12,16)), myboard.neighbours(11, new ArrayList<>()));
    }

}
