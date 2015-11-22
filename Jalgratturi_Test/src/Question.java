
public class Question {

    public Question() {

    }

    String q1 = "Kas rattaga tohib sõita käed lahtiselt?";
    String q2 = "Millisesse suunda ei tohi rattur keerata?";
    String[] a = {q1, q2};



    public void printQuestion(int i) {
        String x = a[i];
        System.out.println(x);

    }
}

