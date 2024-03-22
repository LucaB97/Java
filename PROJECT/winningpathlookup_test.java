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

public class winningpathlookup_test {
    
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
    
        public boolean winning_path_lookup(final int idx, List<Integer> exclude , Set<Integer> winning_path) {
            
            //find the neighbours of the starting location (idx) which might be useful to be explored to detect
            //a winning path (i.e., they share the same color as the starting point and are not included in the
            //"exclude" list of already considered points, which are the direct and indirect ancestors of idx)
            List<Integer> good_neighbours = new ArrayList<>();
            for (Integer n : neighbours(idx)) {
                if (board.get(n).equals(board.get(idx)) && !exclude.contains(n))
                    good_neighbours.add(n);
            }

            //define the locations eligible for the end of a winning path: last row for black, last column for white
            List<Integer> arrival = new ArrayList<>();
            for (int i = 0; i < line_size; i++) {
                if (board.get(idx) == 0)
                    arrival.add(board.size() - line_size + i);
                else
                    arrival.add(i*line_size + line_size - 1);
            }
            
            //if idx is present in the array of arrival locations, a winning path is found -> return true
            if (arrival.contains(idx)) {
                return true;
            } 
            //otherwise,
            else {
                //insert idx in the list of locations to be excluded by further research
                exclude.add(idx);
                //recursively, repeat the procedure on all the useful neighbours of idx
                for (Integer g : good_neighbours) {
                    if (winning_path_lookup(g, exclude, winning_path)) {
                        winning_path.add(g);
                        winning_path.add(idx);
                        return true;
                    }
                }
            }
            return false;
        }
    
    }


    @Test
    public void winningpathlookup_test_black_false_1() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a0");
        myboard.nextMove(true, "a1");
        myboard.nextMove(true, "b1");
        myboard.nextMove(true, "c1");
        myboard.nextMove(true, "c2");
        myboard.nextMove(true, "c3");
        myboard.nextMove(true, "d3");

        //      _________________________________
        //     |                                 |
        //    a|    B ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... B ... B ... B ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|      ...   ...   ... B ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        assertEquals(false, myboard.winning_path_lookup(0, new ArrayList<Integer>(), new TreeSet<Integer>()));
    }

    @Test
    public void winningpathlookup_test_black_false_2() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a0");
        myboard.nextMove(true, "a1");
        myboard.nextMove(true, "b1");
        myboard.nextMove(true, "c1");
        myboard.nextMove(true, "c2");
        myboard.nextMove(true, "c3");
        myboard.nextMove(true, "d3");
        myboard.nextMove(true, "d4");

        //      _________________________________
        //     |                                 |
        //    a|    B ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... B ... B ... B ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|      ...   ...   ... B ... B    |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        assertEquals(false, myboard.winning_path_lookup(0, new ArrayList<Integer>(), new TreeSet<Integer>()));
    }

    @Test
    public void winningpathlookup_test_black_true() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a0");
        myboard.nextMove(true, "a1");
        myboard.nextMove(true, "b1");
        myboard.nextMove(true, "c1");
        myboard.nextMove(true, "c2");
        myboard.nextMove(true, "c3");
        myboard.nextMove(true, "d3");
        myboard.nextMove(true, "e3");

        //      _________________________________
        //     |                                 |
        //    a|    B ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... B ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... B ... B ... B ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|      ...   ...   ... B ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ... B ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        assertEquals(true, myboard.winning_path_lookup(0, new ArrayList<Integer>(), new TreeSet<Integer>()));
    }

    @Test
    public void winningpathlookup_test_white_false_1() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(false, "a0");
        myboard.nextMove(false, "a1");
        myboard.nextMove(false, "b1");
        myboard.nextMove(false, "c1");
        myboard.nextMove(false, "c2");
        myboard.nextMove(false, "c3");
        myboard.nextMove(false, "d3");

        //      _________________________________
        //     |                                 |
        //    a|    W ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... W ... W ... W ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|      ...   ...   ... W ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        assertEquals(false, myboard.winning_path_lookup(0, new ArrayList<Integer>(), new TreeSet<Integer>()));
    }

    @Test
    public void winningpathlookup_test_white_false_2() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(false, "a0");
        myboard.nextMove(false, "a1");
        myboard.nextMove(false, "b1");
        myboard.nextMove(false, "c1");
        myboard.nextMove(false, "c2");
        myboard.nextMove(false, "c3");
        myboard.nextMove(false, "d3");
        myboard.nextMove(false, "e3");

        //      _________________________________
        //     |                                 |
        //    a|    W ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... W ... W ... W ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|      ...   ...   ... W ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ... W ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        assertEquals(false, myboard.winning_path_lookup(0, new ArrayList<Integer>(), new TreeSet<Integer>()));
    }

    @Test
    public void winningpathlookup_test_white_true() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(false, "a0");
        myboard.nextMove(false, "a1");
        myboard.nextMove(false, "b1");
        myboard.nextMove(false, "c1");
        myboard.nextMove(false, "c2");
        myboard.nextMove(false, "c3");
        myboard.nextMove(false, "d3");
        myboard.nextMove(false, "d4");

        //      _________________________________
        //     |                                 |
        //    a|    W ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    b|      ... W ...   ...   ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    c|      ... W ... W ... W ...      |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    d|      ...   ...   ... W ... W    |
        //     |    :     :     :     :     :    |
        //     |    :     :     :     :     :    |
        //    e|      ...   ...   ...   ...      |
        //     |_________________________________|
        //          0     1     2     3     4

        assertEquals(true, myboard.winning_path_lookup(0, new ArrayList<Integer>(), new TreeSet<Integer>()));
    }

}