import java.time.*;
public class MathQuizFuncs {

    MathQuizFuncs(){

    }

    public int addition(int a, int b){
        return a + b;
    }
    public int substraction(int a, int b){
        return a-b;
    }
    public int multiplication(int a, int b){
        return a*b;
    }
    public int division(int a, int b){
        return a/b;
    }
    public void sortLeaderBoard(){
        if(MathQuiz.players.size()>1){
            for(int i=0; i<MathQuiz.players.size(); i++){
                for(int j=i; j<MathQuiz.players.size(); j++){
                    if(MathQuiz.players.get(i).getScore()>MathQuiz.players.get(j).getScore()){
                        MathQuizPlayer temp = MathQuiz.players.get(i);
                        MathQuiz.players.set(i,MathQuiz.players.get(j));
                        MathQuiz.players.set(j, temp);
                    }
                }
            }
        }
    }
    public String printLeaderboard(){
        String leaderboard = "";
        sortLeaderBoard();
        for(int i=0; i<MathQuiz.players.size(); i++){
            leaderboard += "\n" + (i+1) + ". " + MathQuiz.players.get(i).getName() + " : " + MathQuiz.players.get(i).getScore();
        }
        return leaderboard;
    }
}
