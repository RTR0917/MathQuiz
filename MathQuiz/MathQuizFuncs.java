public class MathQuizFuncs {
    private double end;
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

    public static void sortLeaderBoard(){
        if(MathQuiz.players.size()>1){
            for(int i=0; i<MathQuiz.players.size(); i++){
                for(int j=i; j<MathQuiz.players.size(); j++){
                    if(MathQuiz.players.get(i).getCurrentScore()>MathQuiz.players.get(j).getCurrentScore()){
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
            leaderboard += "\n" + (i+1) + ". " + MathQuiz.players.get(i).getName() + " : " + MathQuiz.players.get(i).getCurrentScore();
        }
        return leaderboard;
    }
    public boolean inTime(long startingTime){
        end = startingTime+60000;
        return (System.currentTimeMillis()<(end));
    }

    public double getRemainingTime(){
        return (end - System.currentTimeMillis())/1000;
    }

    public boolean usedUsername(String newUsername){
        for(int i=0; i<MathQuiz.players.size(); i++){
            if(newUsername.equals(MathQuiz.players.get(i))){
                return true;
            }
        }
        return false;
    }
}
