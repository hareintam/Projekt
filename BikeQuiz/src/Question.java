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

    public String getQuestionText(int questionIndex) {
        return data.get(questionIndex).get(0);
    }


    public ArrayList getAnswerChoices(int questionIndex) {
        ArrayList list = new ArrayList();
        for (int i = 2; i < data.get(questionIndex).size(); i++) {
            list.add(data.get(questionIndex).get(i));
        }
        return list;
    }

    public boolean checkAnswer(int questionIndex) {
        // kontrollib, kas vastaja valik ühtib Question.txt failis oleva õige vastuse indeksiga
        if (data.get(questionIndex).get(1).equals(Quiz.USER_CHOICE)) {
            System.out.println("Vastasite õigesti");
            return true;
        }
        System.out.println("Vastasite valesti");
        System.out.println("Õige valikvastus on " + data.get(questionIndex).get(5));
        System.out.println("Kasutaja valik salvestatakse kujul: " + Quiz.USER_CHOICE);
        return false;
    }

}

