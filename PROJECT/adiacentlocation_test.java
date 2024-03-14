import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class adiacentlocation_test {
    
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

        public List<Integer> find_locations(boolean filled) {
            List<Integer> locations;
            if (filled) {
                locations = IntStream.range(0, board.size())
                    .filter(i -> board.get(i) != -1)
                    .boxed()
                    .collect(Collectors.toList());
            } else {
                locations = IntStream.range(0, board.size())
                    .filter(i -> board.get(i) == -1)
                    .boxed()
                    .collect(Collectors.toList());
            }
            return locations;
        }
        
        public int nextMove(final boolean black, final String user_input) throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
            List<Integer> filled_locations = find_locations(true);

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

        public boolean adiacent_location(List<Integer> region, int idx) {
            Set<Integer> region_neighbours = new TreeSet<>();
            for (int curr : region) {
                region_neighbours.addAll(neighbours(curr));
                region_neighbours.removeIf(index -> board.get(index) != -1);
            }
            Set<Integer> extended_idx_neighbours = new TreeSet<>(neighbours(idx));
            extended_idx_neighbours.removeIf(index -> board.get(index) != -1);
            extended_idx_neighbours.add(idx);
            Set<Integer> tempSet = new TreeSet<>(extended_idx_neighbours);
            tempSet.retainAll(region_neighbours);
            if (!tempSet.isEmpty())
                return true;

            while (true) {
                for (int x : extended_idx_neighbours) {
                    tempSet.addAll(neighbours(x));
                    tempSet.removeIf(index -> board.get(index) != -1);
                }
                if (extended_idx_neighbours.equals(tempSet))
                    return false;
                extended_idx_neighbours.clear();
                extended_idx_neighbours.addAll(tempSet);
                tempSet.retainAll(region_neighbours);
                if (!tempSet.isEmpty())
                    return true;
            }
        }
    }

    //TEST REGIONS WHICH ARE NOT TERRITORIES
    @Test
    public void adiacentlocation_test_false() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        List<Integer> myregion = new ArrayList<>(Arrays.asList(2,3,4));
        myboard.nextMove(true, "a0");
        myboard.nextMove(true, "a1");
        myboard.nextMove(true, "b1");
        myboard.nextMove(true, "c1");
        myboard.nextMove(true, "c0");
        assertEquals(false, myboard.adiacent_location(myregion, 5));
    }

    @Test
    public void adiacentlocation_test_true() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        List<Integer> myregion = new ArrayList<>(Arrays.asList(2,3,4));
        myboard.nextMove(true, "a0");
        myboard.nextMove(true, "a1");
        myboard.nextMove(true, "b1");
        myboard.nextMove(true, "c1");
        assertEquals(true, myboard.adiacent_location(myregion, 5));
    }

}