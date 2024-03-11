import java.util.List;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFunction_nextMove {
    
    public static class quentin {

        private List<Integer> board = new ArrayList<>();
        private int line_size;
        
        quentin(final int size) {
            board.addAll(Collections.nCopies(size*size, -1));
            line_size = size;
        }
        
        public int nextMove(final boolean black, final String user_input) throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
            List<Integer> filled_locations = IntStream.range(0, board.size())
                                            .filter(i -> board.get(i) != -1)
                                            .boxed()
                                            .collect(Collectors.toList());

            String pattern = "[a-zA-Z]\\d+";

            if (!(user_input.matches(pattern))) {
                throw new IncorrectFormatException();
            }
            
            int letter = user_input.toLowerCase().charAt(0) - 'a';
            int number = Integer.parseInt(user_input.substring(1));
            int location = letter*line_size + number;
            
            boolean valid_move =  location>=0 && location<board.size() && number<line_size;
            boolean free_location = !filled_locations.contains(location);
            if (!valid_move) {
                throw new InvalidLocationException();
            }

            if (!free_location) {
                throw new OccupiedLocationException();
            }

            board.set(location, black ? 0 : 1);
            return location;   
        }

    }

    public static class IncorrectFormatException extends Exception {
        IncorrectFormatException() {
            super("Input has to be like: \"<letter><number>\"");
        }
    }

    public static class InvalidLocationException extends Exception {
        InvalidLocationException() {
            super("The location specified is invalid");
        }
    }

    public static class OccupiedLocationException extends Exception {
        OccupiedLocationException() {
            super("The location specified is already filled");
        }
    }


    //INCORRECT FORMAT EXCEPTION TESTS
    @Test
    public void nextmove_test_incorrectformatException_1() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        assertThrows(IncorrectFormatException.class, () -> {myboard.nextMove(true, "a-4");});
    }

    @Test
    public void nextmove_test_incorrectformatException_2() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        assertThrows(IncorrectFormatException.class, () -> {myboard.nextMove(true, "4a");});
    }

    @Test
    public void nextmove_test_incorrectformatException_3() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        assertThrows(IncorrectFormatException.class, () -> {myboard.nextMove(true, "4");});
    }

    //INVALID LOCATION EXCEPTION TESTS
    @Test
    public void nextmove_test_invalidlocationException_1() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        assertThrows(InvalidLocationException.class, () -> {myboard.nextMove(true, "f0");});
    }

    @Test
    public void nextmove_test_invalidlocationException_2() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        assertThrows(InvalidLocationException.class, () -> {myboard.nextMove(true, "a12");});
    }

    //OCCUPIED LOCATION EXCEPTION TESTS
    @Test
    public void nextmove_test_occupiedlocationException() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a0");
        assertThrows(OccupiedLocationException.class, () -> {myboard.nextMove(false, "a0");});
    }

    //REGULAR FUNCTIONING 
    @Test
    public void nextmove_test_correct_placing_black() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(true, "a2");
        assertEquals(0, myboard.board.get(2));
    }

    @Test
    public void nextmove_test_correct_placing_white() throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        quentin myboard = new quentin(5);
        myboard.nextMove(false, "a4");
        assertEquals(1, myboard.board.get(4));
    }

}