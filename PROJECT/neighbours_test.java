import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class neighbours_test {
    
    public static class quentin {

        private List<Integer> board = new ArrayList<>();
        private int line_size;
        
        quentin(final int size) {
            board.addAll(Collections.nCopies(size*size, -1));
            line_size = size;
        }
        
        public List<Integer> neighbours(final int idx) {
        
            List<Integer> mylist = new ArrayList<>();            
            if (idx-line_size >= 0)
                mylist.add(idx-line_size);
            if ((idx-1 >= 0) && (idx/line_size == (idx-1)/line_size))
                mylist.add(idx-1); 
            if ((idx+1 < board.size()) && (idx/line_size == (idx+1)/line_size))
                mylist.add(idx+1);      
            if (idx+line_size < board.size())
                mylist.add(idx+line_size);                 
            return mylist;
            
        }
    }

    @Test
    public void neighbours_test_topleft() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(1,5)), myboard.neighbours(0));
    }

    @Test
    public void neighbours_test_topright() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(3,9)), myboard.neighbours(4));
    }

    @Test
    public void neighbours_test_bottomleft() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(15,21)), myboard.neighbours(20));
    }

    @Test
    public void neighbours_test_bottomright() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(19,23)), myboard.neighbours(24));
    }

    @Test
    public void neighbours_test_upperedge() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(2,4,8)), myboard.neighbours(3));
    }

    @Test
    public void neighbours_test_loweredge() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(17,21,23)), myboard.neighbours(22));
    }

    @Test
    public void neighbours_test_leftedge() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(5,11,15)), myboard.neighbours(10));
    }

    @Test
    public void neighbours_test_rightedge() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(4,8,14)), myboard.neighbours(9));
    }

    @Test
    public void neighbours_test_center() {
        quentin myboard = new quentin(5);
        assertEquals(new ArrayList<>(Arrays.asList(6,10,12,16)), myboard.neighbours(11));
    }

}
