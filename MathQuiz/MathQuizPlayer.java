import java.util.*;
public class MathQuizPlayer {
    private String playerName;
    private int playerScore;
    private ArrayList<Integer> scores = new ArrayList<>();

    MathQuizPlayer(String name, int score){
        playerName = name;
        playerScore = score;
    }

    public void updateScore(int newScore){
        scores.add(newScore);
        playerScore = newScore;
    }

    public void sortScores(){
        if(scores.size()>1){
            for(int i=0; i<scores.size(); i++){
                for(int j=i; j<scores.size(); j++){
                    if(scores.get(i)<scores.get(j)){
                        int temp = scores.get(i);
                        scores.set(i,scores.get(j));
                        scores.set(j, temp);
                    }
                }
            }
        }
    }

    public int getScore(){
        return playerScore;
    }
    public String getName(){
        return playerName;
    }
    public int getBestScore(){
        sortScores();
        if(scores.size()>=1){
            return scores.get(0);
        }
        return 0;
    }

    public String showResult(){
        return playerName + ": " + playerScore + "\nBest score : " + getBestScore();
    }
    
}
