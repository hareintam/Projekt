import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Quiz extends Application {

    public static String USER_CHOICE = "0";
    private int QUESTIONS_ANSWERED = 1;
    private int GAME_SCORE = 0;
    private ArrayList<Integer> WRONG_ANSWERS = new ArrayList<>();
    private boolean IS_RADIOBUTTON_SELECTED = false;



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
        Button continueButton = new Button("Edasi");
        window.getChildren().add(continueButton);

        addQuestionToLayout(0, layout, question, continueButton);
        setContinueButtonHandler(0, continueButton, layout, question, window);

    }

    public void addQuestionToLayout(int questionIndex, VBox layout, Question question, Button continueButton) {

        continueButton.setDisable(true);
        Label questionText = new Label(questionIndex + 1 + "/" + question.data.size() + ". küsimus: " + question.getQuestionText(questionIndex));
        layout.getChildren().add(questionText);

        ToggleGroup answerChoices = new ToggleGroup();
        for (int i = 0; i < question.data.get(questionIndex).size() - 2; i++) {
            RadioButton answer = new RadioButton(question.getAnswerChoices(questionIndex).get(i).toString());
            answer.setToggleGroup(answerChoices);
            answer.setUserData(i);
            layout.getChildren().add(answer);
        }
        getSelectedAnswer(answerChoices, continueButton);
    }

    public void getSelectedAnswer(ToggleGroup answerChoices, Button continueButton) {
        answerChoices.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                USER_CHOICE = answerChoices.getSelectedToggle().getUserData().toString();
                IS_RADIOBUTTON_SELECTED = true;

                if (IS_RADIOBUTTON_SELECTED) {
                    continueButton.setDisable(false);
                }
            }
        });
    }

    public void setContinueButtonHandler(int questionIndex, Button continueButton, VBox layout, Question question, StackPane window) {

        continueButton.setOnAction(event -> {
            countCorrectAnswersAndSaveIncorrectAnswers(questionIndex, question);
            System.out.println("Mängu skoor on " + GAME_SCORE);
            layout.getChildren().clear();

            if (question.data.size() == QUESTIONS_ANSWERED) {
                removeContinueButton(continueButton, window);
                quizSummary(layout, question);
                // show summary page
            } else {
                addQuestionToLayout(QUESTIONS_ANSWERED, layout, question, continueButton);
                System.out.println("Vastatud on " + QUESTIONS_ANSWERED + " küsimusele");
                QUESTIONS_ANSWERED++;
            }
        });
    }

    public void countCorrectAnswersAndSaveIncorrectAnswers(int questionIndex, Question question) {
        if (question.checkAnswer(questionIndex)) {
            GAME_SCORE++;
        } else {
            WRONG_ANSWERS.add(QUESTIONS_ANSWERED-1);
            System.out.println(WRONG_ANSWERS.toString());
        }
    }

    public void removeContinueButton(Button continueButton, StackPane window){
        window.getChildren().remove(continueButton);
    }

    public void quizSummary(VBox layout, Question question) {
        if (WRONG_ANSWERS.isEmpty()) {
            Label quizScore = new Label("Palju õnne! Vastasite õigesti kõigile küsimustele. \nKokku saite " + GAME_SCORE + " punkti.");
            layout.getChildren().add(quizScore);
        } else {
            Label quizScore = new Label("Tubli üritus! Vastasite õigesti " + GAME_SCORE + " küsimusele " + question.data.size() +"st!");
            Label wrongAnswers = new Label("Valesti vastasite järgmistele küsimustele: ");
            layout.getChildren().addAll(quizScore, wrongAnswers);
            for (int i = 0; i < WRONG_ANSWERS.size() ; i++) {
                Label wrongs = new Label(question.getQuestionText(WRONG_ANSWERS.get(i)));
                layout.getChildren().add(wrongs);
            }
        }
    }



}

