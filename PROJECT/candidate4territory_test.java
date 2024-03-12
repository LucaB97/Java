import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class candidate4territory_test {
    
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
    }

    //TEST REGIONS WHICH ARE NOT TERRITORIES
    @Test
    public void _candidate4territory_test_topleft() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a1");
        myboard.nextMove(true, "b1");
        myboard.nextMove(true, "b0");
        assertEquals(true, myboard.candidate_for_territory(Arrays.asList(0)));
    }


}