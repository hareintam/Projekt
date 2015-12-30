import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Question {

    public List<List<String>> data = new ArrayList<>();

    public Question() {
    }


    public void getData() {
        BufferedReader input = null;
        String line;
        String[] lineContent;

        try {
            input = new BufferedReader(new FileReader("Questions.txt"));
            while ((line = input.readLine()) != null) {
                lineContent = line.split(";");
                List<String> wordList = Arrays.asList(lineContent);
                data.add(wordList);
            }
            //System.out.println(data.get(0).get(2));
            //System.out.println(data.get(1));

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null)input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void showQuestion(int questionIndex) {
        showQuestionText(questionIndex);
        showAnswerChoices(questionIndex);
        checkAnswer(questionIndex);

    }

    public String showQuestionText(int questionIndex) {
        int questionNr = questionIndex + 1;
        //System.out.println("Küsimus number " + questionNr);
        //System.out.println(data.get(questionIndex).get(0));
        return data.get(questionIndex).get(0);
    }


    public ArrayList showAnswerChoices(int questionIndex) {
        ArrayList list = new ArrayList();
        for (int i = 1; i < 5; i++) {
            list.add(data.get(questionIndex).get(i));
        }
        return list;
    }

    public boolean checkAnswer(int questionIndex) {
        // kontrollib, kas kasutaja vastus ühtib tekstifailis olevaga
        if (data.get(questionIndex).get(5).equals(CyclistTest.USER_CHOICE)) {
            System.out.println("õige");
            return true;
        }
        System.out.println("vale");
        return false;
    }

}

