import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class fillterritory_test {
    
    public static class IncorrectFormatException extends Exception {
        IncorrectFormatException(String message) {
            super(message);
        }
    }

    public static class InvalidLocationException extends Exception {
        InvalidLocationException(String message) {
            super(message);
        }
    }

    public static class OccupiedLocationException extends Exception {
        OccupiedLocationException(String message) {
            super(message);
        }
    }

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

        public int nextMove(final boolean black, final String user_input) throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
            List<Integer> filled_locations = IntStream.range(0, board.size())
                                            .filter(i -> board.get(i) != -1)
                                            .boxed()
                                            .collect(Collectors.toList());

            String pattern = "[a-zA-Z]\\d+";

            if (!(user_input.matches(pattern))) {
                throw new IncorrectFormatException("Incorrect format. Try again!\n");
            }
            
            int letter = user_input.toLowerCase().charAt(0) - 'a';
            int number = Integer.parseInt(user_input.substring(1));
            int location = letter*line_size + number;
            
            boolean valid_move =  location>=0 && location<board.size() && number<line_size;
            boolean free_location = !filled_locations.contains(location);
            if (!valid_move) {
                throw new InvalidLocationException("Invalid location. Try again!\n");
            }

            if (!free_location) {
                throw new OccupiedLocationException("Location already filled. Try again!\n");
            }

            board.set(location, black ? 0 : 1);
            return location;   
        }

        public boolean candidate_for_territory(final List<Integer> region) {            
            int cnt = 0;

            for (int curr : region) {
                for (int i : neighbours(curr)) {
                    if (board.get(i) != -1)
                        cnt++;
                }
                if (cnt < 2)
                    return false;
                cnt = 0;
            }
            return true;
        }
    
        public List<Integer> fill_territory(final List<Integer> territory, final int last_move) {
            
            //find all the distinct neighbours of the points of a territory
            Set<Integer> neighbours_union = new TreeSet<>();
            for (int i : territory)
                neighbours_union.addAll(neighbours(i));
            neighbours_union.removeAll(territory);
            List<Integer> distinctNeighbours = new ArrayList<>(neighbours_union);

            //count how many neighbours are W and how many B
            int cnt_black = 0, cnt_white = 0;
            for (int i : distinctNeighbours) {
                if (board.get(i) == 0)
                    cnt_black++;
                else if (board.get(i) == 1)
                    cnt_white++;
            }

            //in case of tie, increase the count of the player who did not play the last move
            if (cnt_black == cnt_white) {
                if (board.get(last_move) == 1)
                    cnt_black++;
                else
                    cnt_white++;
            }

            //assign to all locations in the territory a value according to the counts
            final int replacement = (cnt_black<cnt_white)? 1 : 0;
            territory.forEach(index -> board.set(index, replacement));

            List<Integer> filled_territory = new ArrayList<>();
            for (int x : territory)
                filled_territory.add(board.get(x));
            
            return filled_territory;
        }
    
    }

    //TEST REGIONS WHICH ARE NOT TERRITORIES
    @Test
    public void fillterritory_test_black_majority() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a0");
        myboard.nextMove(false, "d0");
        myboard.nextMove(true, "c1");
        myboard.nextMove(false, "d1");
        myboard.nextMove(true, "b1");

        //      _________________________________
        //     |                                 |
        //    a|    B ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|    W ...   ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        //region {b0,c0} -> {5,10} should be filled by black (0)
        assertEquals(Arrays.asList(0,0), myboard.fill_territory(Arrays.asList(5,10),6));
    }

    @Test
    public void fillterritory_test_white_majority() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a0");
        myboard.nextMove(false, "d0");
        myboard.nextMove(true, "a1");
        myboard.nextMove(false, "b1");
        myboard.nextMove(true, "a2");
        myboard.nextMove(false, "c1");
        

        //      _________________________________
        //     |                                 |
        //    a|    B ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|    W ...   ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        //region {b0,c0} -> {5,10} should be filled by white (1)
        assertEquals(Arrays.asList(1,1), myboard.fill_territory(Arrays.asList(5,10),11));
    }

    @Test
    public void fillterritory_test_tie_blacklastmove() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(false, "d0");
        myboard.nextMove(true, "a0");
        myboard.nextMove(false, "d1");
        myboard.nextMove(true, "a1");
        myboard.nextMove(false, "c1");
        myboard.nextMove(true, "b1");

        //      _________________________________
        //     |                                 |
        //    a|    B ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|    W ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        //region {b0,c0} -> {5,10} should be filled by white (1)
        assertEquals(Arrays.asList(1,1), myboard.fill_territory(Arrays.asList(5,10),6));
    }

    @Test
    public void fillterritory_test_tie_whitelastmove() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a0");
        myboard.nextMove(false, "d0");
        myboard.nextMove(true, "a1");
        myboard.nextMove(false, "d1");
        myboard.nextMove(true, "b1");
        myboard.nextMove(false, "c1");
        

        //      _________________________________
        //     |                                 |
        //    a|    B ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|    W ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        //region {b0,c0} -> {5,10} should be filled by black (0)
        assertEquals(Arrays.asList(0,0), myboard.fill_territory(Arrays.asList(5,10),11));
    }
}