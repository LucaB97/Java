import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuentinGame {



    public static class quentin {

        private List<Integer> board = new ArrayList<>();

        public void random_filling(int dim) {
            Random random = new Random();
            for (int i = 0; i < dim*dim; i++) {
                board.add(random.nextInt(2)); 
            }
        }
    
        @Override
        public String toString() {
            int line_size = (int)Math.sqrt(board.size());
            String printed = "|";
            for (int i = 0 ; i < board.size() ; i++) {
                printed = printed + board.get(i);
                if (i%line_size < line_size-1)
                    printed = printed + "\t";
                else
                    printed = printed + "|\n|";
            }
            return printed;
        }

        public void gameover() {
            boolean black_won = false;
            int line_size = (int)Math.sqrt(board.size());
            int i = 0;
            while (!black_won && i<line_size) {
                black_won = check_neighbours(i, true, new ArrayList<Integer>());
                i++;
            }
        }

        public boolean check_neighbours(int idx, boolean black, ArrayList<Integer> exclude) {
            
            ArrayList<Integer> good_neighbours = new ArrayList<>();
            for (Integer n : neighbours(idx, black, exclude)) {
                if (board.get(n).equals(board.get(idx)))
                    good_neighbours.add(n);
            }

            int start_last_row = board.size()-(int)Math.sqrt(board.size());
            boolean last_row_reached = idx >= start_last_row;
            
            if (last_row_reached) {
                return true;
            } 
            else {
                exclude.add(idx);
                for (Integer g : good_neighbours) {
                    if (check_neighbours(g, black, exclude)) {
                        System.out.println(g + "\t" + idx);
                        return true;
                    }
                }
            }
            return false;
        }

        public ArrayList<Integer> neighbours(int idx, boolean black, ArrayList<Integer> exclude) {
            int line_size = (int)Math.sqrt(board.size());
            ArrayList<Integer> mylist = new ArrayList<>();
            if (black) {
                if ((idx+line_size < board.size()) && (!exclude.contains(idx+line_size)))
                    mylist.add(idx+line_size);
                if (idx > line_size-1) {
                    if ((idx-1 > 0) && (idx/line_size == (idx-1)/line_size) && (!exclude.contains(idx-1)))
                        mylist.add(idx-1);
                    if ((idx+1 < board.size()) && (idx/line_size == (idx+1)/line_size) && (!exclude.contains(idx+1)))
                        mylist.add(idx+1);
                }
            } else {
                if ((idx+1 < board.size()) && (idx/line_size == (idx+1)/line_size) && (!exclude.contains(idx+1)))
                    mylist.add(idx+1);
                if (idx % line_size != 0) {
                    if ((idx-line_size > 0) && (!exclude.contains(idx-line_size)))
                        mylist.add(idx-line_size);
                    if ((idx+line_size < board.size()) && (!exclude.contains(idx+line_size)))
                        mylist.add(idx+line_size);
                }
            }
            return mylist;
        }
    }

    public static void main(String[] args) {
        
        quentin myboard = new quentin();
        myboard.random_filling(5);
        System.out.println(myboard);
        //System.out.println(myboard.check_neighbours(3,true,new ArrayList<Integer>()));
        myboard.gameover();
    }
}