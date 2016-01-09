import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Quiz extends Application {

    public static String USER_CHOICE;
    private int QUESTIONS_ANSWERED = 1;
    private int QUIZ_SCORE = 0;
    private ArrayList<Integer> WRONG_ANSWERS = new ArrayList<>();
    private boolean IS_RADIOBUTTON_SELECTED = false;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Küsimustik");
        StackPane window = new StackPane();
        VBox layout = new VBox(5);
        window.setPadding(new Insets(20));
        window.getChildren().add(layout);
        Scene questionScene = new Scene(window, 400, 250);
        primaryStage.setScene(questionScene);
        primaryStage.show();

        Button continueButton = new Button("Edasi");
        window.getChildren().add(continueButton);
        StackPane.setAlignment(continueButton, Pos.BOTTOM_CENTER);

        Question question = new Question();
        question.getData();

        addContentToLayout(0, layout, question, continueButton);
        setContinueButtonHandler(0, continueButton, layout, question, window);
    }

    private void addContentToLayout(int questionIndex, VBox layout, Question question, Button continueButton) {

        //Edasi nuppu ei saa vajutada kuni kasutaja on vastusevariandi valinud
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

    private void getSelectedAnswer(ToggleGroup answerChoices, Button continueButton) {
        answerChoices.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                USER_CHOICE = answerChoices.getSelectedToggle().getUserData().toString();
                IS_RADIOBUTTON_SELECTED = true;
                //pärast kasutaja valikut tuleb Edasi nupp aktiivseks teha
                continueButton.setDisable(false);
            }
        });
    }

    //Edasi nupuga kontrollitakse vastust, kuvatakse järgmine küsimus. Kui küsimused otsas, kuvatakse kokkuvõte.
    private void setContinueButtonHandler(int questionIndex, Button continueButton, VBox layout, Question question, StackPane window) {

        continueButton.setOnAction(event -> {
            countQuizScoreAndSaveIncorrectAnswers(question);
            layout.getChildren().clear();

            if (question.data.size() != QUESTIONS_ANSWERED) {
                addContentToLayout(QUESTIONS_ANSWERED, layout, question, continueButton);
                QUESTIONS_ANSWERED++;
            } else {
                removeContinueButton(continueButton, window);
                quizSummary(layout, question, window);
            }
        });
    }

    //Kuigi meetodi nimetus läks pikaks, näis mõttekam skoori lugemine ja vale vastuse salvestamine kokku panna
    private void countQuizScoreAndSaveIncorrectAnswers(Question question) {
        if (question.checkAnswer(QUESTIONS_ANSWERED-1)) {
            QUIZ_SCORE++;
        } else {
            WRONG_ANSWERS.add(QUESTIONS_ANSWERED-1);
        }
    }

    private void removeContinueButton(Button continueButton, StackPane window){
        window.getChildren().remove(continueButton);
    }

    //Kuvab küsimustiku kokkuvõtte ja programmi sulgemise nupu
    private void quizSummary(VBox layout, Question question, StackPane window) {
        Button exitButton = new Button("Sulge küsimustik");
        layout.setAlignment(Pos.CENTER);
        exitButton.setOnAction(event -> ((Node) (event.getSource())).getScene().getWindow().hide());


        if (WRONG_ANSWERS.isEmpty()) {
            Label quizScore = new Label("Tubli töö! Vastasid õigesti kõigile küsimustele. \nKokku said " + QUIZ_SCORE + " punkti.");
            layout.getChildren().addAll(quizScore, exitButton);
        } else if (QUIZ_SCORE == 0) {
            Label quizScore = new Label("Kahjuks vastasid kõigile küsimustele valesti.");
            Label quizScore2 = new Label("Pole hullu, proovi uuesti!");
            layout.getChildren().addAll(quizScore, quizScore2, exitButton);
        } else {
            Label quizScore = new Label("Tubli üritus! Vastasid õigesti " + QUIZ_SCORE + "le" + " küsimusele " + question.data.size() +"st!");
            Label wrongAnswers = new Label("\nValesti vastasid järgmis(t)ele küsimus(t)ele: ");
            layout.getChildren().addAll(quizScore, wrongAnswers);
            for (int i = 0; i < WRONG_ANSWERS.size() ; i++) {
                Label wrongs = new Label(question.getQuestionText(WRONG_ANSWERS.get(i)));
                layout.getChildren().add(wrongs);
            }
            window.getChildren().add(exitButton);
            window.setAlignment(Pos.BOTTOM_CENTER);
        }
    }
}

