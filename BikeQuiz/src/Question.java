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

    //Questions.txt failis on igal real küsimus,õige vastusevariandi indeks ja vastusevariandid
    //rea sisu on eraldatud semikooloniga
    //meetod getData loeb tekstifaili sisu rida-realt programmi
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

    //muutuja questionIndex on tekstifaili reanumber data maatriksis ehk küsimusenr
    public String getQuestionText(int questionIndex) {
        return data.get(questionIndex).get(0);
    }

    //i on 2, sest vastusevariandid algavad tekstifailis 3. positsioonilt
    public ArrayList getAnswerChoices(int questionIndex) {
        ArrayList list = new ArrayList();
        for (int i = 2; i < data.get(questionIndex).size(); i++) {
            list.add(data.get(questionIndex).get(i));
        }
        return list;
    }
    // kontrollib, kas vastaja valik ühtib tekstifailis oleva õige vastuse indeksiga
    public boolean checkAnswer(int questionIndex) {
        if (data.get(questionIndex).get(1).equals(Quiz.USER_CHOICE)) {
            return true;
        }
        return false;
    }

}
