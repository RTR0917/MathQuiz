import java.time.*;
import java.util.*;
import java.lang.*;
public class MathQuiz {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static ArrayList<MathQuizPlayer> players = new ArrayList<>();
    public static void main(String args[]){
        int multiplyer = 8;
        int score = 0;
        boolean isStreak = true;
        boolean inGame = false;
        String[] signs = {" + ", " - " , " * " , " / "};
        Scanner sc = new Scanner(System.in);
        String userName;
        
        while(true){
            System.out.print("Enter Your name >");
            userName = sc.nextLine();
            MathQuizPlayer player = new MathQuizPlayer(userName, score);
            MathQuizFuncs game = new MathQuizFuncs();
            players.add(player);
            System.out.println("\nWelcome " + userName + ".");
            long time = System.currentTimeMillis();
            while((time+1000)>System.currentTimeMillis()){
                System.out.print("");
            }
            System.out.println(ANSI_CYAN + "\nMATH QUIZ" + ANSI_RESET);
            time = System.currentTimeMillis();
            boolean startGame = false;
            while(!startGame){
                System.out.println(ANSI_GREEN + "1.START GAME\n2.Rules\n3.Leaderboard\n4.My best score\n5.Quit Game" + ANSI_RESET);
                int option = sc.nextInt();
                if(option==1){
                    inGame = true;
                    /*time = System.currentTimeMillis();
                    int timeCount = 3;
                    if(System.currentTimeMillis()==(time+1000)&&){
                        System.out.println(timeCount);
                        timeCount--;
                    }
                     */
                    startGame = true;
                }else if(option==2){
                    System.out.println(ANSI_WHITE + "- You will have 20 seconds to answer each question");
                    System.out.println("- All division problems will not have any remainder(The answer will always be in a whole number)");
                    System.out.println("- You will earn 10 points per question");
                    System.out.println("- The game will be over if you make mistake" + ANSI_RESET);
                    time = System.currentTimeMillis();
                    while((time+5000)>System.currentTimeMillis()){
                        System.out.print("");
                    }
                }else if(option==3){
                    if(player.getBestScore()==0){
                        System.out.println("No Data");
                    }else{
                        System.out.println(game.printLeaderboard());
                    }
                    time = System.currentTimeMillis();
                    while((time+1000)>System.currentTimeMillis()){
                        System.out.print("");
                    }
                }else if(option==4){
                    if(player.getBestScore()==0){
                        System.out.println("No Data");
                    }else{
                        System.out.println("Your best score is :" + player.getBestScore());
                    }
                    time = System.currentTimeMillis();
                    while((time+1000)>System.currentTimeMillis()){
                        System.out.print("");
                    }
                }else if(option==5){
                    System.out.println("Thank you for playing!");
                    time = System.currentTimeMillis();
                    while((time+1000)>System.currentTimeMillis()){
                        System.out.print("");
                    }
                    System.exit(-1);
                }else{
                    System.out.println("Please choose an option by entering a number from 1 to 5");
                }
                System.out.println("\n");
            }
            while(inGame){
                while(isStreak){
                    double num1Rand = Math.random();
                    double num2Rand = Math.random();
                    System.out.println(num1Rand + " " + num2Rand);
                    int number1 = ((int)(num1Rand*multiplyer))+1;
                    int number2 = ((int)(num2Rand*multiplyer))+1;
                    System.out.println(number1 + " " + number2);

                    while(number1%number2!=0 || number2%number1!=0){
                        num1Rand = Math.random();
                        num2Rand = Math.random();
                        System.out.println("\n" + num1Rand + " " + num2Rand);
                        number1 = ((int)(num1Rand*multiplyer))+1;
                        number2 = ((int)(num2Rand*multiplyer))+1;
                        System.out.println(number1 + " " + number2 + "\n");
                    }

                    int denominator = number1;
                    int numerator = number2;
                    if(number1%number2==0){
                        denominator = number2;
                        numerator = number1;
                    }
                    int numSign = (int)(Math.random()*4);
                    int answer = 0;

                    if(numSign==0){
                        answer = game.addition(number1, number2);
                    }else if(numSign==1){
                        answer = game.substraction(number1, number2);
                    }else if(numSign==2){
                        answer = game.multiplication(number1, number2);
                    }else if(numSign==3){
                        answer = game.division(numerator, denominator);
                    }else{
                        System.out.println("Error: numSign = " + numSign);
                        System.exit(-1);
                    }
                    /*System.out.println(ANSI_RED + "20 SECONDS LIMIT" + ANSI_RESET);
                    System.out.println("Starting in...");
                    time = System.currentTimeMillis();
                    int timeCount = 3;
                    while(System.currentTimeMillis()<=time+3000){
                        System.out.println(ANSI_RED + timeCount + ANSI_RESET);
                        timeCount--;
                    }
                    boolean qAnswered = false;
                    */
                    System.out.println(ANSI_WHITE + "What is " + number1 + signs[numSign] + number2 + "?" + ANSI_RESET);
                    //!!!!!!!!!!!!!!!!!!!!WORK!!!!!!!!!!!!!!!!!!!!!!!
                    //20 seconds
                    //!!!!!!!!!!!!!!!!!!!!WORK!!!!!!!!!!!!!!!!!!!!!!!
                    int userAnswer = sc.nextInt();

                    if(userAnswer==answer){
                        System.out.println("Correct!");
                        score++;
                    }else{
                        System.out.println("Sorry! Game Over!");
                        isStreak = false;
                    }
                }
                player.updateScore(score);
                score = 0;
                multiplyer = 10;
                player.showResult();
                System.out.println("Continue? y/n >");
                    String userContinue = sc.nextLine();
                    if(userContinue.equals("n")){
                        inGame = false;
                    }else if(userContinue.equals("y")){
                        isStreak = true;
                    }else{
                        System.out.println("Please answer with y or n");
                    }
            }
        }

    }
}
