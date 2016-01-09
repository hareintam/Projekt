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

    //Meetod loeb kogu tekstifaili sisu rida-realt programmi
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

    //Muutuja questionIndex on küsimusenr data maatriksis
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
    //Kontrollib, kas vastaja valik ühtib tekstifailis oleva õige vastuse indeksiga
    public boolean checkAnswer(int questionIndex) {
        if (data.get(questionIndex).get(1).equals(Quiz.USER_CHOICE)) {
            return true;
        }
        return false;
    }

}
