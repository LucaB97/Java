import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuentinGame {



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

        public ArrayList<String> convert(final TreeSet<Integer> values) {
            ArrayList<String> converted_values = new ArrayList<>();
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
            
            TreeSet<Integer> winning_path = new TreeSet<>();
            boolean black_won = false;
            int i = 0;
            while (!black_won && i<line_size) {
                if (board.get(i) == 0)
                    black_won = winning_path_lookup(i, new ArrayList<Integer>(), winning_path);
                i++;
            }

            if (black_won) {
                System.out.println("BLACK WON!");
                ArrayList<String> winning_path_positions = convert(winning_path);
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
                ArrayList<String> winning_path_positions = convert(winning_path);
                System.out.println(winning_path_positions);
                System.out.println(winning_path);
                return true;
            }

            return false;
        }

        public boolean winning_path_lookup(final int idx, ArrayList<Integer> exclude , TreeSet<Integer> winning_path) {
            
            ArrayList<Integer> good_neighbours = new ArrayList<>();
            for (Integer n : neighbours(idx, exclude)) {
                if (board.get(n).equals(board.get(idx)))
                    good_neighbours.add(n);
            }

            ArrayList<Integer> arrival = new ArrayList<>();
            for (int i = 0; i < line_size; i++) {
                if (board.get(idx) == 0)
                    arrival.add(board.size() - line_size + i);
                else
                    arrival.add(i*line_size + line_size - 1);
            }
            
            if (arrival.contains(idx)) {
                return true;
            } 
            else {
                exclude.add(idx);
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

        public ArrayList<Integer> neighbours(final int idx, final ArrayList<Integer> exclude) {
        
            ArrayList<Integer> mylist = new ArrayList<>();
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

        public ArrayList<Integer> diagonals(final int idx) {
        
            ArrayList<Integer> mylist = new ArrayList<>();
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
        
        public int nextMove(final boolean black) {
            List<Integer> filled_locations = IntStream.range(0, board.size())
                                            .filter(i -> board.get(i) != -1)
                                            .boxed()
                                            .collect(Collectors.toList());

            boolean valid_move = false; 
            String pattern = "[a-zA-Z]\\d+";
            int location = -1;
            while (!valid_move) {
                System.out.println("Enter a position in the board (e.g.: a0): ");               
                Scanner scanner = new Scanner(System.in);
                String user_input = scanner.nextLine();
                if (!(user_input.matches(pattern))) {
                    System.out.println("Incorrect format. Try again!\n");
                    continue;
                }
                int letter = user_input.toLowerCase().charAt(0) - 'a';
                int number = Integer.parseInt(user_input.substring(1));
                location = letter*line_size + number;
                valid_move = !filled_locations.contains(location) && location>=0 && location<board.size() &&number<line_size;
                if (valid_move)
                    board.set(location, black ? 0 : 1); 
                else
                    System.out.println("Invalid location. Try again!\n");
            }
            return location;   
        }

        public void update_board(final boolean black) {
            
            List<Integer> empty_locations = new ArrayList<>();            
            ArrayList<Integer> region = new ArrayList<>();
            ArrayList<Integer> territories = new ArrayList<>();

            int last_move = nextMove(black);

            while (true) {
                empty_locations = IntStream.range(0, board.size())
                                    .filter(i -> board.get(i) == -1)
                                    .boxed()
                                    .collect(Collectors.toList());
                while (!empty_locations.isEmpty()) {
                    for (int loc : empty_locations) {                
                        if (region.isEmpty() || adiacent_location(region, loc)) {
                            region.add(loc);
                        }
                    }
                    //System.out.println("New region: " + region);

                    if (candidate_for_territory(region)) {
                        fill_territory(region, last_move);
                        territories.addAll(region);
                    }
        
                    empty_locations.removeAll(region);                    
                    region.clear(); 
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

            for (int i : neighbours(last_move, new ArrayList<>()))
                if (board.get(i) == board.get(last_move))
                    return true;
            
            for (int i : diagonals(last_move))
                if (board.get(i) == board.get(last_move))
                    return false;    
            
            return true;        
        }

        public boolean adiacent_location(ArrayList<Integer> region, int idx) {
            Set<Integer> region_neighbours = new HashSet<>();
            for (int curr : region) {
                region_neighbours.addAll(neighbours(curr, new ArrayList<>()));
                region_neighbours.removeIf(index -> board.get(index) != -1);
            }
            Set<Integer> extended_idx_neighbours = new HashSet<>(neighbours(idx, new ArrayList<>()));
            extended_idx_neighbours.removeIf(index -> board.get(index) != -1);
            extended_idx_neighbours.add(idx);
            Set<Integer> tempSet = new HashSet<>(extended_idx_neighbours);
            tempSet.retainAll(region_neighbours);
            if (!tempSet.isEmpty())
                return true;

            while (true) {
                for (int x : extended_idx_neighbours) {
                    tempSet.addAll(neighbours(x, new ArrayList<>()));
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

        public boolean candidate_for_territory(final ArrayList<Integer> region) {            
            int cnt = 0;

            for (int curr : region) {
                for (int i : neighbours(curr, new ArrayList<>())) {
                    if (board.get(i) != -1)
                        cnt++;
                }
                if (cnt < 2)
                    return false;
                cnt = 0;
            }
            return true;
        }

        public void fill_territory(final ArrayList<Integer> region, final int last_move) {
            Set<Integer> neighbours_union = new HashSet<>();
            for (int i : region)
                neighbours_union.addAll(neighbours(i, new ArrayList<>()));
            neighbours_union.removeAll(region);

            ArrayList<Integer> distinctNeighbours = new ArrayList<>(neighbours_union);

            int cnt_black = 0, cnt_white = 0;
            for (int i : distinctNeighbours) {
                if (board.get(i) == 0)
                    cnt_black++;
                else if (board.get(i) == 1)
                    cnt_white++;
            }

            if (cnt_black == cnt_white) {
                if (board.get(last_move) == 1)
                    cnt_black++;
                else
                    cnt_white++;
            }

            final int replacement = (cnt_black<cnt_white)? 1 : 0;
            region.forEach(index -> board.set(index, replacement));
        }
    }

    public static void main(String[] args) {
        
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