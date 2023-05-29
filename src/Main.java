import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    protected static int bullCount;
    protected static int cowCount;
    protected static List<Integer> secretCode = generateCode();

    public static void main(String[] args) {
        // game log
        int turn = 0;
        bullCount = 0;
        cowCount = 0;

        System.out.println("The secret code is prepared: ****.\n");
        while (true) {
            turn++;
            System.out.println("Turn " + turn + ". Answer: ");

            // generate a different code each loop
            List<Integer> code = generateCode();
            // print that generated code
            System.out.println(print(code));


            // compare the two lists -- check if elements bull or cow
            for (int i = 0; i < code.size(); i++) {
                if (code.get(i).equals(secretCode.get(i))) {
                    bullCount++;
                }

                if (secretCode.contains(code.get(i)) && !(code.get(i).equals(secretCode.get(i)))) {
                    cowCount++;
                }
            }
            // check grade with passed bullCount and cowCount
            checkGrade(bullCount, cowCount);
            if (bullCount == 4) {
                break;
            }
            bullCount = 0;
            cowCount = 0;
        }
    }

    // check grade and print it
    public static void checkGrade(int bullCount, int cowCount) {

        String bulls = "bulls.";
        String cows = "cows.";

        // if there's NO bulls or cows
        if (bullCount == 0 && cowCount == 0) {
            System.out.println("\nNone.\n");
        }

        if (bullCount > 0 && cowCount > 0) {
            if (bullCount == 1 && cowCount == 1) {
                System.out.println("Grade: " + bullCount + " bull and " + cowCount + " cow.\n");
            } else if (bullCount > 1 && cowCount == 1) {
                System.out.println("Grade: " + bullCount + " bulls and " + cowCount + " cow.\n");
            } else {
                System.out.println("Grade: " + bullCount + " bull and " + cowCount + " cows.\n");
            }
        }

        // if only bull
        if (bullCount > 0 && cowCount == 0) {
            // if more than 1 bull
            if (bullCount == 1) {
                System.out.println("Grade: " + bullCount + " bull.\n" );
            } else if (bullCount < 4) {
                System.out.println("\nGrade: " + bullCount + " " + bulls +
                        "\n");
            }
            // if 4 bulls in total - win game --> end game
            if (bullCount == 4) {
                System.out.println("Grade: " + bullCount + " " + bulls +
                        "\nCongrats! The secret code is " + print(secretCode) +
                        "\n");
            }
        }

        // if only cow
        if (bullCount == 0 && cowCount > 0) {
            if (cowCount > 1) {
                System.out.println("Grade: " + cowCount + " " + cows +
                        "\n");
            } else {
                System.out.println("Grade: " + cowCount + " cow.\n");
            }
        }

    }

    // print elements in list
    public static String print(List<Integer> list) {
        StringBuilder num = new StringBuilder();
        for (int i : list) {
            num.append(i);
        }
        return num.toString();
    }

    // generate unique 4-digit code and return the list
    public static List<Integer> generateCode() {
        // generate unique 4-digit code
        Random random = new Random();
        // add each number to list
        List<Integer> list = new ArrayList<>();

        while (list.size() < 4) { // generate 4 numbers
            int rand = random.nextInt(9) + 1; // generate b/t 1 and 9
            if (!list.contains(rand)) {
                list.add(rand);
            }
        }
        return list;
    }
}