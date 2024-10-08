import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class QuentinGame_v2 {

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

        public void random_filling(int dim) {
            Random random = new Random();
            for (int i = 0; i < dim*dim; i++) {
                board.add(random.nextInt(2)); 
            }
            line_size = dim;
        }
    
        @Override
        public String toString() {
            
            String rows = "abcdefghijklm";
            int rowidx = 0;
            String printed = "   _____" + "______".repeat(line_size-1) + "____\n";
            printed = printed + "  |     " + "      ".repeat(line_size-1) + "    |\n";
            String sep;            

            for (int i = 0 ; i < board.size() ; i++) {
                sep = i % line_size != 0 ? " ... " : " " + rows.charAt(rowidx++) + "|    ";
                if (board.get(i) == 0)
                    printed = printed + sep + "B";
                else if (board.get(i) == 1) 
                    printed = printed + sep + "W";
                else
                    printed = printed + sep + " ";

                if (i%line_size == line_size-1) {
                    if (i < board.size()-line_size)
                        printed = printed + "    |\n  |   " + " :    ".repeat(line_size) + "|\n  |   " + " :    ".repeat(line_size) + "|\n";
                    else
                    printed = printed + "    |\n";
                }
            }

            printed = printed + "  |_____" + "______".repeat(line_size-1) + "____|\n       ";
            for (int i = 0 ; i < line_size ; i++) 
                printed = printed + i + "     ";

            return printed;
        }

        public List<String> convert(final Set<Integer> values) {
            List<String> converted_values = new ArrayList<>();
            int col;
            int row;

            for (int v : values) {
                col = v % line_size;
                row = v / line_size + 1;
                converted_values.add((char)('a' + row - 1) + Integer.toString(col));
            }

            return converted_values;
        }
        
        public boolean gameover() {
            
            Set<Integer> winning_path = new TreeSet<>();
            boolean black_won = false;
            int i = 0;
            while (!black_won && i<line_size) {
                if (board.get(i) == 0)
                    black_won = winning_path_lookup(i, new ArrayList<Integer>(), winning_path);
                i++;
            }

            if (black_won) {
                System.out.println("BLACK WON!");
                List<String> winning_path_positions = convert(winning_path);
                System.out.println(winning_path_positions);
                System.out.println(winning_path);
                return true;
            }

            boolean white_won = false;
            i = 0;
            while (!white_won && i<line_size) {
                if (board.get(i*line_size) == 1)
                    white_won = winning_path_lookup(i*line_size, new ArrayList<Integer>(), winning_path);
                i++;
            }

            if (white_won) {
                System.out.println("WHITE WON!");
                List<String> winning_path_positions = convert(winning_path);
                System.out.println(winning_path_positions);
                System.out.println(winning_path);
                return true;
            }

            return false;
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

        public List<Integer> diagonals(final int idx) {
        
            List<Integer> mylist = new ArrayList<>();            
            if (idx-line_size-1 >= 0 && (idx-1)/line_size == idx/line_size)
                mylist.add(idx-line_size-1);
            if (idx-line_size+1 >= 0 && (idx+1)/line_size == idx/line_size)
                mylist.add(idx-line_size+1); 
            if (idx+line_size-1 < board.size() && (idx-1)/line_size == idx/line_size && idx != 0)
                mylist.add(idx+line_size-1);     
            if (idx+line_size+1 < board.size() && (idx+1)/line_size == idx/line_size)
                mylist.add(idx+line_size+1);     
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

        public int nextMove(final boolean black) throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
            List<Integer> filled_locations = find_locations(true);
        
            boolean valid_move = false;
            boolean free_location = false;
            String pattern = "[a-zA-Z]\\d+";
            int location = -1;
            while (!valid_move || !free_location) {
                try {
                    System.out.println("Enter a position in the board (e.g.: a0): ");
                    Scanner scanner = new Scanner(System.in);
                    String user_input = scanner.nextLine();
                    if (!(user_input.matches(pattern))) {
                        throw new IncorrectFormatException("Incorrect format. Try again!\n");
                    }
                    int letter = user_input.toLowerCase().charAt(0) - 'a';
                    int number = Integer.parseInt(user_input.substring(1));
                    location = letter * line_size + number;
                    valid_move = location >= 0 && location < board.size() && number < line_size;
                    free_location = !filled_locations.contains(location);
                    if (!valid_move) {
                        throw new InvalidLocationException("Invalid location. Try again!\n");                        
                    } else if (!free_location) {
                        throw new OccupiedLocationException("Location already filled. Try again!\n");
                    }
                    board.set(location, black ? 0 : 1);
                } catch (IncorrectFormatException | InvalidLocationException | OccupiedLocationException e) {
                    // Print the exception message and continue the loop
                    System.out.println(e.getMessage());
                }
            }
            return location;
        }
        
        public void update_board(final boolean black) throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
            
            List<Integer> empty_locations = new ArrayList<>();            
            List<Integer> region = new ArrayList<>();
            List<Integer> territories = new ArrayList<>();

            int last_move = nextMove(black);

            while (true) {
                empty_locations = find_locations(false);
                while (!empty_locations.isEmpty()) {
                    region = next_region(empty_locations);
                    //System.out.println("New region: " + region);

                    if (candidate_for_territory(region)) {
                        fill_territory(region, last_move);
                        territories.addAll(region);
                    }
        
                    empty_locations.removeAll(region);                    
                    //region.clear(); 
                }

                if (!legal_move(last_move)) {
                    board.set(last_move, -1);
                    territories.forEach(index -> board.set(index, -1));
                    territories.clear();
                    System.out.println("Illegal move. Try again!\n");
                    last_move = nextMove(black);
                }
                else
                    break;
            }
                  
        }

        public boolean legal_move(final int last_move) {

            //if the last move filled a location s.t. at least one neighbour has the same color, it is a legal move -> return true 
            for (int i : neighbours(last_move))
                if (board.get(i) == board.get(last_move))
                    return true;
            
            //if no neighbours have the same color as the last filled location, but a diagonal location does, the move is illegal -> return false
            for (int i : diagonals(last_move))
                if (board.get(i) == board.get(last_move))
                    return false;    
            
            return true;        
        }

        public List<Integer> next_region(final List<Integer> empty_locations) {
            List<Integer> region = new ArrayList<>();
            
            //evaluate, for each empty location, if it is adiacent to any point of the current region
            for (int loc : empty_locations) {                
                if (region.isEmpty() || adiacent_location(region, loc)) {
                    region.add(loc);
                }
            }
            return region;
        }
        
        public boolean adiacent_location(List<Integer> region, int idx) {
            
            //find all the empty neighbours of the points of the region
            Set<Integer> region_neighbours = new TreeSet<>();
            for (int curr : region) {
                region_neighbours.addAll(neighbours(curr));
            }
            region_neighbours.removeIf(index -> board.get(index) != -1);
            
            //add to the region all the empty neighbours found (to save calls to the function in the future)
            //...
            
            //store the neighbours of "idx"
            Set<Integer> extended_idx_neighbours = new TreeSet<>(neighbours(idx));
            extended_idx_neighbours.removeIf(index -> board.get(index) != -1);
            extended_idx_neighbours.add(idx);
            
            //if overlap between the neighbours of "idx" and those of the region's locations is found, return true
            Set<Integer> tempSet = new TreeSet<>(extended_idx_neighbours);
            tempSet.retainAll(region_neighbours);
            if (!tempSet.isEmpty())
                return true;

            while (true) {
                //update the set of neighbours of "idx", including the indirect ones (neighbours of neighbours)
                for (int x : extended_idx_neighbours) {
                    tempSet.addAll(neighbours(x));
                }
                tempSet.removeIf(index -> board.get(index) != -1);
                
                //if extending the research of adiacent empty locations is unsuccessful,
                //i.e., no more locations are found which are adiacent to "idx" or its current neighbours:
                //since neither "idx" nor its neighbours were adiacent to any location of the region before,
                //it will be the same now, then the function returns false   
                if (extended_idx_neighbours.equals(tempSet))
                    return false;
                
                //save the information which is stored in the temporary set used to test adiacency to the region 
                extended_idx_neighbours.clear();
                extended_idx_neighbours.addAll(tempSet);

                //if overlap between the extended neighbours of "idx" and those of the region's locations is found, return true
                tempSet.retainAll(region_neighbours);
                if (!tempSet.isEmpty())
                    return true;
            }
        }

        public boolean candidate_for_territory(final List<Integer> region) {            
            
            //for each location in the region, verify if it has at least two filled neighbours
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

        public void fill_territory(final List<Integer> territory, final int last_move) {
            
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
        }
    }

    public static void main(String[] args) throws IncorrectFormatException, InvalidLocationException, OccupiedLocationException {
        
        quentin myboard = new quentin(5);
        System.out.println(myboard + "\n");
        do {
            System.out.println("BLACK MOVES");
            myboard.update_board(true);
            System.out.println(myboard + "\n");
            if (!myboard.gameover()) {
                System.out.println("WHITE MOVES");
                myboard.update_board(false);
                System.out.println(myboard + "\n");
            }
        } while (!myboard.gameover());

    }
}