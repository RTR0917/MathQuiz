import java.util.*;
import java.lang.*;
public class MathQuizPlayer {
    private String playerName;
    private int currentScore = 0;
    private ArrayList<Integer> scores = new ArrayList<>();

    MathQuizPlayer(String name) {
        playerName = name;
    }

    MathQuizPlayer(String name, ArrayList<Integer> list) {
        playerName = name;
        scores = list;
    }

    public void setPlayerName(String newName) {
        playerName = newName;
    }

    public void updateScore(int newScore) {
        scores.add(newScore);
        currentScore = newScore;
    }

    public void printScores() {
        String sc = playerName + " ";
        for (int i = 0; i < scores.size(); i++) {
            sc += scores.get(i) + " ";
        }
        System.out.println(sc);
    }

    public String getScores() {
        String sc = playerName + " ";
        for (int i = 0; i < scores.size(); i++) {
            sc += scores.get(i) + " ";
        }
        sc += "\n";
        return sc;
    }

    public void sortScores() {
        if (scores.size() > 1) {
            for (int i = 0; i < scores.size(); i++) {
                for (int j = i; j < scores.size(); j++) {
                    if (scores.get(i) < scores.get(j)) {
                        int temp = scores.get(i);
                        scores.set(i, scores.get(j));
                        scores.set(j, temp);
                    }
                }
            }
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public String getName() {
        return playerName;
    }

    public int getBestScore() {
        sortScores();
        if (scores.size() >= 1) {
            return scores.get(0);
        }
        return 0;
    }

    public String showResult() {
        return playerName + ": " + currentScore + "\nBest score : " + getBestScore();
    }

    public int numScores() {
        return scores.size();
    }
}
