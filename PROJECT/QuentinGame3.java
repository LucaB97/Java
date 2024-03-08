import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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

        public boolean gameover() {
            
            boolean black_won = false;
            int i = 0;
            while (!black_won && i<line_size) {
                if (board.get(i) == 0)
                    black_won = winning_path_lookup(i, new ArrayList<Integer>());
                i++;
            }

            boolean white_won = false;
            i = 0;
            while (!white_won && i<line_size) {
                if (board.get(i*line_size) == 1)
                    white_won = winning_path_lookup(i*line_size, new ArrayList<Integer>());
                i++;
            }
            return black_won||white_won;
        }

        public boolean winning_path_lookup(int idx, ArrayList<Integer> exclude) {
            
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
                    if (winning_path_lookup(g, exclude)) {
                        System.out.println(g + "\t" + idx);
                        return true;
                    }
                }
            }
            return false;
        }

        public ArrayList<Integer> neighbours(int idx, ArrayList<Integer> exclude) {
        
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
    
        // public List<Integer> empty_neighbours(int idx) {
        //     ArrayList<Integer> all = neighbours(idx, false, new ArrayList<>(), false);
        //     List<Integer> empty = IntStream.range(0, all.size())
        //                         .filter(i -> all.get(i) == -1)
        //                         .boxed()
        //                         .collect(Collectors.toList());
        //     return empty;
        // }
        
        public int nextMove(final boolean black) {
            List<Integer> filled_locations = IntStream.range(0, board.size())
                                            .filter(i -> board.get(i) != -1)
                                            .boxed()
                                            .collect(Collectors.toList());
            
            if (black) {
                System.out.println("BLACK MOVES");
            }
            else {
                System.out.println("WHITE MOVES");
            }
            boolean valid_move = false; 
            String pattern = "[a-zA-Z][0-9]";
            int location = -1;
            while (!valid_move) {
                System.out.println("Enter a position in the board (e.g.: a0): ");               
                Scanner scanner = new Scanner(System.in);
                String user_input = scanner.nextLine();
                if (!(user_input.matches(pattern))) {
                    System.out.println("Incorrect format. Try again!");
                    continue;
                }
                int letter = user_input.toLowerCase().charAt(0) - 'a';
                int number = user_input.charAt(1) - '0';
                location = letter*line_size + number;
                valid_move = !filled_locations.contains(location) && location>=0 && location<board.size() &&number<line_size;
                if (valid_move)
                    board.set(location, black ? 0 : 1); 
                else
                    System.out.println("Invalid location. Try again!");
            }
            return location;   
        }

        public void update_board() {
            List<Integer> empty_locations = IntStream.range(0, board.size())
                                            .filter(i -> board.get(i) == -1)
                                            .boxed()
                                            .collect(Collectors.toList());
            
            ArrayList<Integer> group = new ArrayList<>();
            ArrayList<Integer> exclude = new ArrayList<>();

            while (!exclude.equals(empty_locations)) {
            
                for (int loc : empty_locations) {
                    if (exclude.contains(loc))
                        continue;
                
                    if (group.isEmpty() || adiacent_location(group, loc))
                        group.add(loc);
                }

                System.out.println(group);
                if (candidate_for_territory(group))
                    System.out.println("New territory found:" + group);
                exclude.addAll(group);
                Collections.sort(exclude);
                group.clear();

            }
        }

        public boolean adiacent_location(ArrayList<Integer> group, int idx) {

            for (int curr : group) {
                if (neighbours(curr, new ArrayList<>()).contains(idx))
                    return true;
            }
            return false;
        }

        public boolean candidate_for_territory(ArrayList<Integer> group) {            
            int cnt = 0;

            for (int curr : group) {
                System.out.println("i am " + curr + ",\t my neighbours are: " + neighbours(curr, new ArrayList<>()));
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
    }

    public static void main(String[] args) {
        
        quentin myboard = new quentin(5);
        System.out.println(myboard + "\n\n");

        do {
            myboard.nextMove(true);
            myboard.update_board();
            System.out.println(myboard + "\n");
            // myboard.update_board();
            // System.out.println("\n\n");
            if (!myboard.gameover()) {
                myboard.nextMove(false);
                myboard.update_board();
                System.out.println(myboard + "\n");
                // myboard.update_board();
                // System.out.println("\n\n");
            }
        } while (!myboard.gameover());

    }
}