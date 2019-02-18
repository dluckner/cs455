package Assignment1;

import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
//    	System.out.println("~~~Starting new game with 2 players~~~");
//        Game preliminaries = new Game(2);
//        preliminaries.run();
    	InvertWinningStrategy myGame = new InvertWinningStrategy(2);
//    	myGame.makeMove(0,1); //1
//    	myGame.makeMove(1,2); //2
//    	myGame.makeMove(0,2); //3
//    	myGame.makeMove(3,4); //4
//    	myGame.makeMove(2,3); //5
//    	myGame.makeMove(2,4); //6
//    	myGame.makeMove(4,5); //7
//    	myGame.makeMove(2,5); //8
//    	myGame.makeMove(0,4); //9
//    	myGame.makeMove(0,5); //10
//    	myGame.makeMove(1,5); //11
//    	myGame.makeMove(3,5); //12
////    	myGame.makeMove(1,3); //13
////    	myGame.makeMove(1,4); //14
//    	System.out.println(Arrays.toString(myGame.availibleMoves()));
//    	myGame.applyStrategy(myGame.availibleMoves());
//    	System.out.println(Arrays.toString(myGame.availibleMoves()));
//    	myGame.printHistory();
//    	System.out.println(myGame.isGameOver());
    	myGame.winningStrategy(myGame.availibleMoves());
    	myGame.printResults();
    	
    	
    	
    	
    	
//    	myGame.makeMove(); //1
//    	myGame.makeMove(); //2
//    	myGame.makeMove(); //3
//    	myGame.makeMove(); //4
//    	myGame.makeMove(); //5
//    	myGame.makeMove(); //6
//    	myGame.makeMove(); //7
//    	myGame.makeMove(); //8
//    	myGame.makeMove(); //9
//    	myGame.makeMove(); //10
//    	myGame.makeMove(); //11
//    	myGame.makeMove(); //12
//    	myGame.makeMove(); //13
//    	myGame.makeMove(); //14
//    	myGame.existanceOfWinner();
//    	myGame.printResults();
//    	myGame.makeMove(0,1); //1
//    	myGame.makeMove(1,2); //2
//    	myGame.makeMove(1,3); //3
//    	myGame.makeMove(0,3); //4
//    	myGame.makeMove(3,4); //5
//    	myGame.makeMove(1,4); //6
//    	myGame.makeMove(4,5); //7
//    	myGame.makeMove(3,5); //8
//    	myGame.makeMove(0,5); //9
//    	myGame.makeMove(0,4); //10
//    	myGame.makeMove(1,5); //11
//    	myGame.makeMove(3,5); //12
//    	myGame.makeMove(1,3); //13
//    	myGame.makeMove(1,4); //14
//    	System.out.println(myGame.isGameOver());
//    	myGame.printHistory();
//    	System.out.println(Arrays.toString(myGame.applyStrategy(myGame.availibleMoves())));
//    	myGame.printHistory();
//    	myGame.winningStrategy(myGame.availibleMoves());
//    	myGame.printResults();
    	
    	
    	
    	
//    	myGame.makeMove(3,0); //EndsinTies-12
//    	myGame.makeMove(5,3); //EndsinTies
//    	myGame.makeMove(3,1); //EndsinTies
//    	myGame.makeMove(4,1); //EndsinTies

//    	myGame.printHistory();
//    	System.out.println(myGame.isGameOver());
//    	System.out.println(myGame.getWinner());
//    	System.out.println(Arrays.toString(myGame.availibleMoves()));
//    	myGame.existanceOfWinner2(myGame.availibleMoves());
//    	myGame.printResults();
    }
}
