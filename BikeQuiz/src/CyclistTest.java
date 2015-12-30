import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class CyclistTest extends Application {

    public static String USER_CHOICE;



    public static void main(String[] args) {
        launch(args);


        //System.out.println(question.showAnswerChoices(0).toString());
        /*for (int i = 0; i < question.data.size(); i++) {
            question.showQuestion(i);
        }*/

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //StackPane layout = new StackPane();
        primaryStage.setTitle("Küsimustik");
        VBox layout = new VBox();
        Scene questionScene = new Scene(layout, 400, 400);
        primaryStage.setScene(questionScene);
        primaryStage.show();

        Question question = new Question();
        Button nextQuestion = new Button("Edasi");

        addContentToLayout(0, layout, question, nextQuestion);

/*        question.getData();
        Label questionText = new Label(question.showQuestionText(0));
        Button nextQuestion = new Button("Edasi");

        ToggleGroup answerChoices = new ToggleGroup();
        RadioButton answer1 = new RadioButton(question.showAnswerChoices(0).get(0).toString());
        RadioButton answer2 = new RadioButton(question.showAnswerChoices(0).get(1).toString());
        RadioButton answer3 = new RadioButton(question.showAnswerChoices(0).get(2).toString());
        RadioButton answer4 = new RadioButton(question.showAnswerChoices(0).get(3).toString());
        answer1.setToggleGroup(answerChoices);
        answer1.setSelected(true);
        answer2.setToggleGroup(answerChoices);
        answer3.setToggleGroup(answerChoices);
        answer4.setToggleGroup(answerChoices);

        answer1.setUserData(0);
        answer2.setUserData(1);
        answer3.setUserData(2);
        answer4.setUserData(3);

        answerChoices.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == null) {
                    answer1.setSelected(true);
                } else {
                    System.out.println(answerChoices.getSelectedToggle().getUserData().toString());
                    USER_CHOICE = answerChoices.getSelectedToggle().getUserData().toString();
                }
            }
        });

        layout.getChildren().addAll(questionText, answer1, answer2, answer3, answer4, nextQuestion);*/

        nextQuestion.setOnAction(event -> {
            System.out.println("järgmine küsimus");
            question.checkAnswer(0);
            layout.getChildren().clear();



        });


    }

    public void addContentToLayout(int questionIndex, VBox layout, Question question, Button nextQuestion) {
        question.getData();

        Label questionText = new Label(question.showQuestionText(questionIndex));

        ToggleGroup answerChoices = new ToggleGroup();
        RadioButton answer1 = new RadioButton(question.showAnswerChoices(questionIndex).get(0).toString());
        RadioButton answer2 = new RadioButton(question.showAnswerChoices(questionIndex).get(1).toString());
        RadioButton answer3 = new RadioButton(question.showAnswerChoices(questionIndex).get(2).toString());
        RadioButton answer4 = new RadioButton(question.showAnswerChoices(questionIndex).get(3).toString());

        answer1.setToggleGroup(answerChoices);
        answer1.setSelected(true);
        answer2.setToggleGroup(answerChoices);
        answer3.setToggleGroup(answerChoices);
        answer4.setToggleGroup(answerChoices);

        answer1.setUserData(0);
        answer2.setUserData(1);
        answer3.setUserData(2);
        answer4.setUserData(3);

        answerChoices.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == null) {
                    answer1.setSelected(true);
                } else {
                    System.out.println(answerChoices.getSelectedToggle().getUserData().toString());
                    USER_CHOICE = answerChoices.getSelectedToggle().getUserData().toString();
                }
            }
        });

        layout.getChildren().addAll(questionText, answer1, answer2, answer3, answer4, nextQuestion);

    }

}

