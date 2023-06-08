import java.util.Random;
import java.util.Scanner;

public class BullsCows {
    protected int bullCount;
    protected int cowCount;

    public BullsCows() {
        this.bullCount = 0;
        this.cowCount = 0;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int length = Integer.parseInt(scanner.nextLine());
        System.out.println("Input the number of possible symbols in the code:");
        int numberOfSymbols = Integer.parseInt(scanner.nextLine());
        prepared(length, numberOfSymbols);

        // generate secret code and split it into array
        String secret = generateCode(length, numberOfSymbols);
        String[] secretCode = secret.split("");


        int turn = 0;
        while (true) {
            System.out.print("Turn " + ++turn + ":\n");
            String answer = scanner.nextLine();
            String[] answerCode = answer.split("");


            for (int i = 0; i < answerCode.length; i++) {
                if (answerCode[i].equals(secretCode[i])) {
                    bullCount++;
                }
                if (secret.contains(answerCode[i]) && !answerCode[i].equals(secretCode[i])) {
                    cowCount++;
                }
            }

            checkGrade(bullCount, cowCount);
            if (bullCount == length) {
                System.out.printf("Congratulations! You guessed the secret " +
                        "code %s.", secret);
                break;
            }
            bullCount = 0;
            cowCount = 0;
        }
    }
    // check grade and print it
    public void checkGrade(int bullCount, int cowCount) {

        if (bullCount > 0 && cowCount > 0) {
            if (bullCount > 1 && cowCount > 1) {
                System.out.printf("Grade: %d bulls and %d cows.\n", bullCount, cowCount);
            } else if (bullCount > 1) {
                System.out.printf("Grade: %d bulls and %d cow.\n", bullCount, cowCount);
            } else if (cowCount > 1) {
                System.out.printf("Grade: %d bull and %d cows.\n", bullCount, cowCount);
            } else {
                System.out.printf("Grade: %d bull and %d cow.\n", bullCount, cowCount);
            }
        } else if (bullCount > 0) {
            System.out.printf(bullCount == 4 ? "Grade: 4 bulls\n" : bullCount < 4 ? "Grade: %d bulls\n" : "Grade: %d bull\n", bullCount);
        } else if (cowCount > 0) {
            System.out.printf("Grade: %d cow(s).\n", cowCount);
        } else {
            System.out.println("Grade: None.\n");
        }

    }

    // generate unique 4-digit code and return the list
    public String generateCode(int length, int numberOfSymbols) {
        Random rnd = new Random();
        StringBuilder str = new StringBuilder();

        final int numbers = 10;
        int charNum = numberOfSymbols - numbers;


        if (numberOfSymbols > 10) {
            for (int i = str.length(); i < length;) {
                // generate random number OR letter
                boolean generateNumber = rnd.nextBoolean();
                if (generateNumber) {
                    // store random number to string
                    int number = rnd.nextInt(numbers);
                    // if str does not have the number/letter --> add to str
                    if (str.toString().contains(String.valueOf(number))) {
                        continue;
                    }
                    str.append(number);
                } else {
                    // random letter
                    char letter = (char) (rnd.nextInt(charNum) + 'a');
                    // if str already contains the random number -->
                    if (str.toString().contains(String.valueOf(letter))) {
                        continue;
                    }
                    str.append(letter);
                }
                i++;
            }
        } else {
            for (int i = 0; i < length;) {
                int number = rnd.nextInt(numbers);
                // if str does not have the number/letter --> add to str
                if (str.toString().contains(String.valueOf(number))) {
                    continue;
                }
                str.append(number);
                i++;
            }
        }

        return str.toString();
    }

    public static void prepared(int length, int numberOfSymbols) {
        String stars = stars(length);
        String startGame = "\nOkay, let's start a game!\n";

        if (numberOfSymbols == 11) {
            System.out.printf("The secret is prepared: %s " + "(0-9, a)" +
                    "%s", stars, startGame);
        } else if (numberOfSymbols > 11) {
            System.out.printf("The secret is prepared: %s " + "(0-9, " + "a" +
                    "-%c)" + "%s" ,stars, characters(numberOfSymbols), startGame);
        } else if (numberOfSymbols == 10) {
            System.out.printf("The secret is prepared: %s " + "(0-9)" + "%s",
                    stars, startGame);
        } else {
            System.out.printf("The secret is prepared: %s " + "(0-" + (numberOfSymbols) +
                    ")" + "%s", stars, startGame);
        }
    }

    public static String stars(int length) {
        StringBuilder star = new StringBuilder("*");
        int i = 1;
        while (i < length) {
            star.append("*");
            i++;
        }
        return star.toString();
    }

    public static char characters(int numberOfSymbols) {
        return (char) ('a' + numberOfSymbols - 11);
    }
}
