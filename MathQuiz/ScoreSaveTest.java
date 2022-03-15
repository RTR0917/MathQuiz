import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.io.*;
public class ScoreSaveTest {
    public void main(String[] args) throws Exception{
        //System.out.println(readData("Rina 11 12 13 14").showResult());
        //System.out.println(readDataNew("Rina 110 12 13 14").showResult());

        // read scores from saved data
        BufferedReader br = new BufferedReader(new FileReader("MathQuiz/scores.dat"));
        String st;
        ArrayList<MathQuizPlayer> players = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            players.add(readData(st));
        }
        br.close();
        // print the list of scores
        for(int i=0; i<players.size(); i++){
            players.get(i).printScores();
        }
        System.out.println("\n");

        // change some scores of the players
        players.get(0).updateScore(99999999);
        players.get(1).updateScore(-100);

        // print the list of scores
        for(int i=0; i<players.size(); i++){
            players.get(i).printScores();
        }

        // write scores into a new file "scores_new.dat"
        BufferedWriter bw = new BufferedWriter(new FileWriter("MathQuiz/scores_new.dat"));
        for (int i = 0; i < players.size(); i++) {
            bw.write(players.get(i).getScores());
        }
        bw.close();
    }

    public MathQuizPlayer readDataNew(String data) {

        String [] fileInput = data.split(" ");
        String name = fileInput[0];
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 1; i < fileInput.length; i++) {
            scores.add(i-1, parseInt(fileInput[i]));
        }

        return new MathQuizPlayer(name, scores);
    }

    // Rina's implementation of reading data by a space delimited input
    public MathQuizPlayer readData(String data){
        String name = "";
        int score = 0;
        ArrayList<Integer> scores = new ArrayList<>();

        int i=0;
        boolean isSpace=false;
        while(!isSpace) {
            if (data.charAt(i) == ' ') {
                isSpace = true;
            }else{
                name+= data.charAt(i);
                i++;
            }
        }
        i++;
        isSpace = false;
        String scoreString = "";
        while(i< data.length()){
            while(!isSpace&&i< data.length()){
                if (data.charAt(i) == ' ') {
                    isSpace = true;
                }else {
                    scoreString += data.charAt(i);
                    i++;
                }
            }
            scores.add(parseInt(scoreString));
            scoreString="";
            i++;
            isSpace = false;
        }
        return new MathQuizPlayer(name, scores);
    }
}
