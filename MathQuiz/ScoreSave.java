import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.io.*;
public class ScoreSave {
    public void writeFiles()throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter("MathQuiz/scores_new.dat"));
        for (int i = 0; i < MathQuiz.players.size(); i++) {
            bw.write(MathQuiz.players.get(i).getScores());
        }
        bw.close();
    }
    public void readFiles()throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("MathQuiz/scores_new.dat"));
        String st;
        while ((st = br.readLine()) != null) {
            MathQuiz.players.add(readData(st));
        }
        br.close();
    }
    public MathQuizPlayer readData(String data) {

        String [] fileInput = data.split(" ");
        String name = fileInput[0];
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 1; i < fileInput.length; i++) {
            scores.add(i-1, parseInt(fileInput[i]));
        }

        return new MathQuizPlayer(name, scores);
    }
}
