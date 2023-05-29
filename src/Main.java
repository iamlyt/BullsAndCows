import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String bulls = "bulls.";
        String cows = "cows.";

        // save generated numbers in list
        List<Integer> secretCode = generateCode();


        // game log
        System.out.println("The secret code is prepared: ****.\n");
        int turn = 0;

        int bullCount = 0;
        int cowCount = 0;

        while (turn < 3) {
            turn++;
            System.out.println("Turn " + turn + ". Answer: ");

            // generate a different code each loop
            List<Integer> code = generateCode();
            // print that generated code


            // compare the two lists --
            for (int i = 0; i < code.size(); i++) {
                if (code.get(i).equals(secretCode.get(i))) {
                    bullCount++;
                }            code.forEach(System.out::print);

                if (secretCode.contains(code.get(i)) && !(code.get(i).equals(secretCode.get(i)))) {
                    cowCount++;
                }
            }

            // if there's no bulls or cows
            if (bullCount == 0 && cowCount == 0) {
                System.out.println("\nNone.\n");
            }

            // if there's more than 0 bull and 0 cow
            if (bullCount > 0 && cowCount > 0) {
                if (bullCount == 1 && cowCount == 1) {
                    System.out.println("\nGrade: " + bullCount + " bull and " + cowCount + " cow.\n");
                } else if (bullCount > 1 && cowCount == 1) {
                    System.out.println("\nGrade: " + bullCount + " bulls and " + cowCount + " cow.\n");
                } else {
                    System.out.println("\nGrade: " + bullCount + " bull and " + cowCount + " cows.\n");
                }
            }

            // if there's only bull
            if (bullCount > 0 && cowCount == 0) {
                // if more than 1 bull
                System.out.println(bullCount == 1 ? "\nGrade: " + bullCount +
                        " bull.\n" : "\nGrade: " + bullCount + " " + bulls +
                        "\n");
//                if (bullCount > 1) {
//                    System.out.println("\nGrade: " + bullCount + " " + bulls +
//                            "\n");
//                } else {
//                    System.out.println("\nGrade: " + bullCount + " bull.\n");
//                }
                // if 4 bulls in total - win game --> end game
                if (bullCount == 4) {
                    System.out.println("\nGrade: " + bullCount + bulls +
                            "\nCongrats! The secret code is " + secretCode.toString() + "\n");
                    break;
                }
            }

            // if only cow
            if (bullCount == 0 && cowCount > 0) {
                if (cowCount > 1) {
                    System.out.println("\nGrade: " + cowCount + " " + cows +
                            "\n");
                } else {
                    System.out.println("\nGrade: " + cowCount + " cow.\n");
                }
            }



            System.out.println("The secret code is " + secretCode.toString() + "\n");
        }
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

//    }
    }
}
