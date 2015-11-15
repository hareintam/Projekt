import java.util.Scanner;

public class AnswerChoice {

    public void twoChioces() {
        String first = "Õige";
        String second = "Vale";
        System.out.println("a. " + first + " \nb." + second);

    }
    public void userInput() {
        Scanner writeAnswer = new Scanner(System.in);
        System.out.println("Kirjuta siia õige vastus: ");
        String answerLetter = writeAnswer.next();

        if (answerLetter.equals("b") || answerLetter.toLowerCase().equals("vale") || answerLetter.toLowerCase().equals("paremale")) {
            System.out.println("Õige vastus");
        } else {
            System.out.println("Vale vastus");
        }
    }


}
