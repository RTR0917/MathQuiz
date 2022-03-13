import org.junit.jupiter.api.Test;

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

    // Prints the number of seconds left before game starts staring with numSeconds
    public static void printCountDown(int numSeconds) {
        for (int i = numSeconds; i > 0; i--) {
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                // do nothing with the exception
            }
        }
    }

    public static void main(String args[]){
        int multiplyer = 10;
        int score = 0;
        boolean isStreak = true;
        boolean inGame = false;
        String[] signs = {" + ", " - " , " * " , " / "};
        Scanner sc = new Scanner(System.in);
        String userName = "";
        
        while(true){
            MathQuizPlayer player = new MathQuizPlayer(userName, score);
            if(userName.equals("")) {
                System.out.print("Enter Your name >");
                userName = sc.nextLine();
                players.add(player);
            }
                MathQuizFuncs game = new MathQuizFuncs();
            System.out.println("\nWelcome " + userName + ".");
            long time = System.currentTimeMillis();
            while((time+1000)>System.currentTimeMillis()){
                System.out.print("");
            }
            System.out.println(ANSI_CYAN + "\nMATH QUIZ" + ANSI_RESET);
            time = System.currentTimeMillis();
            boolean startGame = false;
            while(!startGame){
                System.out.println(ANSI_GREEN + "1.START GAME\n2.Rules\n3.Leaderboard\n4.My best score\n5.Change username\n6.Quit Game" + ANSI_RESET);
                int option = sc.nextInt();
                if(option==1){
                    inGame = true;
                    startGame = true;
                }else if(option==2){
                    System.out.println(ANSI_WHITE + "- You will have 60 seconds to answer as many questions as you can");
                    System.out.println("- All division problems will not have any remainder(The answer will always be in a whole number)");
                    System.out.println("- You will earn 10 points per question");
                    System.out.println("- The more you solve questions, the more harder they become");
                    System.out.println("- Mistakes will give 0 points" + ANSI_RESET);
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
                    boolean newUsername = false;
                    while(!newUsername){
                        System.out.println("What is your new username ?");
                        userName = sc.nextLine();
                        if(!game.usedUsername(userName)){
                            System.out.println("\nWelcome " + userName + ".");
                            newUsername = true;
                        }else{
                            System.out.println("\nThat username is already used.");
                            System.out.println("Would you like to continue with this used account? y/n");
                            String nameChoice = sc.nextLine();
                            boolean nameChoiceBoolean = false;
                            while(!nameChoiceBoolean){
                                if(nameChoice.equals("y")){
                                    System.out.println("Welcome back " + userName);
                                    newUsername = true;
                                }else if(nameChoice.equals("n")){
                                    nameChoiceBoolean = true;
                                }else{
                                    System.out.println("Please answer with y or n.");
                                }
                            }
                        }
                    }
                }else if(option==6){
                    System.out.println("Thank you for playing!");
                    time = System.currentTimeMillis();
                    while((time+1000)>System.currentTimeMillis()){
                        System.out.print("");
                    }
                    System.exit(-1);
                }else{
                    System.out.println("Please choose an option by entering a number from 1 to 6");
                }
                System.out.println("\n");
            }
            while(inGame){
                System.out.println(ANSI_RED + "YOU WILL HAVE 60 SECONDS TO COMPLETE" + ANSI_RESET);

                System.out.println("Starting in...");
                printCountDown(3);

                long startingTime = System.currentTimeMillis();
                while(isStreak && game.inTime(startingTime)){
                    double num1Rand = Math.random();
                    double num2Rand = Math.random();
                    int number1 = ((int)(num1Rand*multiplyer))+1;
                    int number2 = ((int)(num2Rand*multiplyer))+1;

                    while(number1%number2!=0 && number2%number1!=0){
                        num1Rand = Math.random();
                        num2Rand = Math.random();
                        number1 = ((int)(num1Rand*multiplyer))+1;
                        number2 = ((int)(num2Rand*multiplyer))+1;
                    }

                    int numerator = number1;
                    int denominator = number2;
                    if(number2%number1==0){
                        numerator = number2;
                        denominator = number1;
                    }
                    int numSign = (int)(Math.random()*4);
                    int answer = 0;

                    if(numSign==0){
                        answer = game.addition(number1, number2);
                    }else if(numSign==1){
                        answer = game.substraction(numerator, denominator);
                    }else if(numSign==2){
                        answer = game.multiplication(number1, number2);
                    }else if(numSign==3){
                        answer = game.division(numerator, denominator);
                    }
                    System.out.println("Time remaining :" + game.getRemainingTime());
                    System.out.println(ANSI_WHITE + "What is " + numerator + signs[numSign] + denominator + "?" + ANSI_RESET);
                    int userAnswer = sc.nextInt();

                    if(userAnswer==answer){
                        System.out.println("Correct!");
                        score+=10;
                        multiplyer += 10;
                    }else {
                        System.out.println("Incorrect!");
                        System.out.println("The answer is: " + answer);
                    }
                }
                if(isStreak){
                    System.out.println("Time up!");
                }
                player.updateScore(score);
                score = 0;
                multiplyer = 10;
                System.out.println(player.showResult());
                System.out.println("Continue? y/n >");
                System.out.println("Choose n to go back home");
                    sc.nextLine();
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
