import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//Added scanner to get user input
        Scanner in = new Scanner(System.in);
        boolean canRun = true;
//Boolean to check whether program can run or not
        while (canRun) {
            int memNum = 0;
            int skipCount = 0;
            boolean isInteger = false;
            Queue<Integer> q = new LinkedList<>();
            while (!isInteger) {
//Get user input of group member count
                System.out.println("How many members if the group?");
                String numStr = in .nextLine();
//Check user input is integer if not then repeat
//process till get integer from user
                if (tryParseInt(numStr)) { memNum = Integer.parseInt(numStr);
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println("Please enter an Integer");
                }
            }
            isInteger = false;
//Check user input is integer if not then repeat
//process till get integer from user
            while (!isInteger) {
//Get user input for skip count
                System.out.println("What is the skip count?");
                String skpCount = in .nextLine();
                if (tryParseInt(skpCount)) {
                    skipCount = Integer.parseInt(skpCount);
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println("Please enter an Integer");
                }
            }
// Adds elements e.g; {0, 1, 2, 3, 4, ...} to queue
            for (int i = 1; i <= memNum; i++)
            {
                q.add(i);
            }
            int sizeOfQueue = q.size();
            Integer numPlaceToRemove = 1;
            int position = 0;
//Apply the sequence algorithm to get the final result
            while (sizeOfQueue > 1)
            {
                for (int i = 0; i < sizeOfQueue; i++) {
                    if (numPlaceToRemove == skipCount) {
                        q.remove(get(q, i));
                        sizeOfQueue = sizeOfQueue - 1;
                        numPlaceToRemove = 1;
                    }
                    numPlaceToRemove = numPlaceToRemove + 1;
                }
            }
//Get the position number from queue
            System.out.println("You want to be in position number #" + q.peek());
//Ask user whether to continue program or not
            System.out.println("Again [Y/N]?");
            String userInput = in.nextLine();
//If Y then do the whole logic again
            if (userInput == "Y") {
                canRun = true;
            } else {
                canRun = false;
                System.out.println("Goodbye and GoodLuck");
            }
        }
    }
    //method which checks the user input is integer or not
    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //method which used to get the value from queue
    public static <T> T get(Queue<T> queue, int index) {
        synchronized (queue) {
            if (queue == null) {
                return null;
            }
            int size = queue.size();
            if (index < 0 || size < index + 1) {
                return null;
            }
            T element = null;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    element = queue.remove();
                } else {
                    queue.add(queue.remove());
                }
            }
            return element;
        }
    }
}