
public class CyclistTest {

    public static void main(String[] args) {
        Question firstQuestion = new Question();
        firstQuestion.printQuestion(0);
        AnswerChoice forFirstQuestion = new AnswerChoice();
        forFirstQuestion.twoChioces();
        forFirstQuestion.userInput();
        firstQuestion.printQuestion(1);
        forFirstQuestion.userInput();


    }
}
