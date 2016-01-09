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
        layout.setPadding(new Insets(10));
        window.getChildren().add(layout);
        Scene questionScene = new Scene(window, 400, 300);
        primaryStage.setScene(questionScene);
        primaryStage.show();

        Button continueButton = new Button("Edasi");
        window.getChildren().add(continueButton);

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

        //Tsükkel loob vastusevariantidele Radio Buttonid, lisab nad ühte gruppi,
        //määrab vastusevariandi indeksi ja lisab layout'le kuvamiseks
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
                if (IS_RADIOBUTTON_SELECTED) {
                    continueButton.setDisable(false);
                }
            }
        });
    }

    //Edasi nupu vajutamisel: 1. kontrollitakse vastust:
    //kui on õige liidetakse skoorile 1; kui on vale, salvestatakse vale vastuse küsimuse nr
    //2. puhastatakse aken sisust, v.a Edasi nupp
    //Kui tegemist ei olnud viimase küsimusega, kuvatakse uus küsimus
    //Vastasel juhul kuvatakse kokkuvõte
    private void setContinueButtonHandler(int questionIndex, Button continueButton, VBox layout, Question question, StackPane window) {

        continueButton.setOnAction(event -> {
            countQuizScoreAndSaveIncorrectAnswers(question);
            layout.getChildren().clear();

            if (question.data.size() != QUESTIONS_ANSWERED) {
                addContentToLayout(QUESTIONS_ANSWERED, layout, question, continueButton);
                QUESTIONS_ANSWERED++;
            } else {
                removeContinueButton(continueButton, window);
                quizSummary(layout, question);
            }
        });
    }

    //kuigi meetodi nimetus läks pikaks, näis mõttekam skoori lugemine ja vale vastuse salvestamine kokku panna
    //valede vastuse listi lisatakse muutuja QUESTIONS_ANSWERED, sest see hakkab kasvama
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

    //Kokkuvõtte lehele lisandub küsimustiku sulgemise nupp
    //Vastavalt tulemusele kuvatakse 3 võimalust:
    //1. kui kõik vastati õigesti
    //2. kui kõik vastused läksid valesti
    //3. mõni õigesti/valesti, kuvab valesti vastatud küsimused
    private void quizSummary(VBox layout, Question question) {
        Button close = new Button("Sulge küsimustik");
        close.setAlignment(Pos.BOTTOM_CENTER);
        close.setOnAction(event -> ((Node)(event.getSource())).getScene().getWindow().hide());


        if (WRONG_ANSWERS.isEmpty()) {
            Label quizScore = new Label("Tubli töö! Vastasid õigesti kõigile küsimustele. \nKokku said " + QUIZ_SCORE + " punkti.");
            layout.getChildren().addAll(quizScore, close);
        } else if (QUIZ_SCORE == 0) {
            Label quizScore = new Label("Kahjuks vastasid kõigile küsimustele valesti. Pole hullu, proovi uuesti!");
            layout.getChildren().addAll(quizScore, close);
        } else {
            Label quizScore = new Label("Tubli üritus! Vastasid õigesti " + QUIZ_SCORE + "le" + " küsimusele " + question.data.size() +"st!");
            Label wrongAnswers = new Label("\nValesti vastasid järgmistele küsimustele: ");
            layout.getChildren().addAll(quizScore, wrongAnswers);
            for (int i = 0; i < WRONG_ANSWERS.size() ; i++) {
                Label wrongs = new Label(question.getQuestionText(WRONG_ANSWERS.get(i)));
                layout.getChildren().add(wrongs);
            }
            layout.getChildren().add(close);
        }
    }
}

