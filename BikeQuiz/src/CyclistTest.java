import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CyclistTest extends Application {

    public static String USER_CHOICE = "0";
    private int QUESTIONS_ANSWERED = 1;
    private int GAME_SCORE = 0;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Küsimustik");
        StackPane window = new StackPane();
        VBox layout = new VBox(15);
        window.getChildren().add(layout);
        Scene questionScene = new Scene(window, 400, 300);
        primaryStage.setScene(questionScene);
        primaryStage.show();

        Question question = new Question();
        question.getData();
        System.out.println("data size is: " + question.data.size());
        Button nextQuestion = new Button("Edasi");
        window.getChildren().add(nextQuestion);

        addQuestionToLayout(0, layout, question);
        setNextQuestionButtonHandler(0, nextQuestion, layout, question, window);

    }

    public void addQuestionToLayout(int questionIndex, VBox layout, Question question) {

        Label questionText = new Label(questionIndex+1 + "/" + question.data.size() + ". küsimus: " + question.getQuestionText(questionIndex));

        ToggleGroup answerChoices = new ToggleGroup();
        RadioButton answer1 = new RadioButton(question.getAnswerChoices(questionIndex).get(0).toString());
        RadioButton answer2 = new RadioButton(question.getAnswerChoices(questionIndex).get(1).toString());
        RadioButton answer3 = new RadioButton(question.getAnswerChoices(questionIndex).get(2).toString());
        RadioButton answer4 = new RadioButton(question.getAnswerChoices(questionIndex).get(3).toString());

        answer1.setSelected(true);
        answer1.setToggleGroup(answerChoices);
        answer2.setToggleGroup(answerChoices);
        answer3.setToggleGroup(answerChoices);
        answer4.setToggleGroup(answerChoices);

        answer1.setUserData(0);
        answer2.setUserData(1);
        answer3.setUserData(2);
        answer4.setUserData(3);

        layout.getChildren().add(questionText);
        layout.getChildren().add(answer1);
        layout.getChildren().add(answer2);
        layout.getChildren().add(answer3);
        layout.getChildren().add(answer4);

        getSelectedAnswer(answerChoices);

    }

    public void getSelectedAnswer(ToggleGroup answerChoices) {
        answerChoices.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                USER_CHOICE = answerChoices.getSelectedToggle().getUserData().toString();
            }
        });
    }

    public void setNextQuestionButtonHandler(int questionIndex, Button nextQuestion, VBox layout, Question question, StackPane window) {
        nextQuestion.setOnAction(event -> {
            countCorrectAnswers(questionIndex, question);
            System.out.println("Mängu skoor on " + GAME_SCORE);
            layout.getChildren().clear();

            if (question.data.size() == QUESTIONS_ANSWERED) {
                removeNextQuestionButton(nextQuestion, window);
                quizSummary(layout);
                // show summary page
            } else {
                addQuestionToLayout(QUESTIONS_ANSWERED, layout, question);
                System.out.println("Vastatud on " + QUESTIONS_ANSWERED + " küsimusele");
                QUESTIONS_ANSWERED++;
            }
        });
    }

    public void countCorrectAnswers(int questionIndex, Question question) {
        if (question.checkAnswer(questionIndex)) {
            GAME_SCORE++;
        }
    }

    public void removeNextQuestionButton(Button nextQuestion, StackPane window){
        window.getChildren().remove(nextQuestion);
    }

    public void quizSummary(VBox layout) {
        Label quizScore = new Label("Palju õnne! Vastasite õigesti " + GAME_SCORE + " küsimusele!");
        layout.getChildren().addAll(quizScore);
    }
}

