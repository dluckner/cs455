package Assignment1;

import java.util.ArrayList;
import java.util.Arrays;

public class LayerTree {
	private boolean db = false;
	private int numPlayers;
	private int maximumOutcomes;
	private int[] winnerCount;
	private Position root;
	public LayerTree(int numPlayers) {
		this.root = new Position(null, null, this.numPlayers);
		this.winnerCount = new int[numPlayers];
	}
	
	public void existanceOfWinner() {
		System.out.println("~~~Running Existance of Winner~~~");
		ArrayList<int[]> h = new ArrayList<int[]>();
		Position endPos = new Position(h,null,2);
		LayerTreeNode newNode = new LayerTreeNode(endPos, 2);
		this.existanceOfWinnerRun(newNode);
		System.out.println("Max Possible Games: "+maximumOutcomes);
		System.out.println("Blue Wins: "+winnerCount[0]);
		System.out.println("Red Wins: "+winnerCount[1]);
		System.out.println("Total Wins: "+(winnerCount[0]+winnerCount[1]));
		System.out.println(Arrays.toString(winnerCount));
	}
	
	public int[] getWinnerCount() {return this.winnerCount;}
	
	public void existanceOfWinnerRun(LayerTreeNode currentNode) {
		if(db) {System.out.println("Evaluating Node: "+Arrays.toString(currentNode.getPreviousMove()));}
		if(currentNode==null) {return;}
		if(currentNode.isDeadEnd()) {
			if(db) {System.out.println("Number of children: 0");}
			this.maximumOutcomes++;
			int winner = currentNode.positionWinner();
			//if(db) {System.out.println("WINNER: "+winner);}
			if(winner>=0) {this.winnerCount[winner]++;}
			return;
		}
		currentNode.makeChildren();
		if(db) {System.out.println("Number of children: "+currentNode.numberOfChildren());}
		int num = currentNode.numberOfChildren();
		for(int i=0;i<num;i++) {
			existanceOfWinnerRun(currentNode.getChild(0));
			currentNode.deleteChild(0);
		}
	}
	
	public void winningStrategy(int winningPlayer) {
		System.out.println("~~~Running Winning Strategy~~~");
		ArrayList<int[]> h = new ArrayList<int[]>();
		Position endPos = new Position(h,null,2);
		LayerTreeNode newNode = new LayerTreeNode(endPos, 2);
		this.winningStrategyRun(newNode, winningPlayer);
		System.out.println("Max Possible Games: "+maximumOutcomes);
		System.out.println("Red Wins: "+winnerCount[0]);
		System.out.println("Blue Wins: "+winnerCount[1]);
		System.out.println("Total Wins: "+(winnerCount[0]+winnerCount[1]));
		System.out.println(Arrays.toString(winnerCount));
	}
	
	private void winningStrategyRun(LayerTreeNode currentNode, int winningPlayer) {
		if(db) {System.out.println("Evaluating Node: "+Arrays.toString(currentNode.getPreviousMove()));}
		if(currentNode==null) {return;}
		if(currentNode.isDeadEnd()) {
			if(db) {System.out.println("Number of children: 0");}
			this.maximumOutcomes++;
			int winner = currentNode.positionWinner();
			//if(db) {System.out.println("WINNER: "+winner);}
			if(winner>=0) {this.winnerCount[winner]++;}
			return;
		}
		currentNode.makeChildren();
		if(db) {System.out.println("Number of children: "+currentNode.numberOfChildren());}
		int num = currentNode.numberOfChildren();
		for(int i=0;i<num;i++) {
			existanceOfWinnerRun(currentNode.getChild(0));
			currentNode.deleteChild(0);
		}
	}
	
	public void test2() {
		ArrayList<int[]> h = new ArrayList<int[]>();
//		int move1[] = {0,1}; // Red  01
//		h.add(move1);
//		int move2[] = {1,2}; // Blue 12
//		h.add(move2);
//		int move3[] = {0,2}; // Red  02
//		h.add(move3);
//		int move4[] = {3,4}; // Blue 34
//		h.add(move4);
//		int move5[] = {2,3}; // Red  23
//		h.add(move5);
//		int move6[] = {2,4}; // Blue 34
//		h.add(move6);
//		int move7[] = {4,5}; // Red  23
//		h.add(move7);
//		int move8[] = {2,5}; // Blue 34
//		h.add(move8);
//		int move9[] = {0,4}; // Red  23
//		h.add(move9);
//		int move10[] = {0,5}; // Blue 34
//		h.add(move10);
//		int move11[] = {1,5}; // Red  23
//		h.add(move11);
//		int move12[] = {3,5}; // Blue 34
//		h.add(move12);
//		int move13[] = {1,3}; // Red  23
//		h.add(move13);
		Position endPos = new Position(h,null,2);
		LayerTreeNode newNode = new LayerTreeNode(endPos, 2);
//		endPos.showConnections();
//		evaluateTreeNode(newNode);
//		this.printResults();
	}
	
	public void test() {
		ArrayList<int[]> h = new ArrayList<int[]>();
		int move1[] = {0,1}; // Red  01
		h.add(move1);
		int move2[] = {1,2}; // Blue 12 --- Takes 25 Minutes
		h.add(move2);
		int move3[] = {0,2}; // Red  02
		h.add(move3);
		int move4[] = {3,4}; // Blue 34
		h.add(move4);
		int move5[] = {2,3}; // Red  23
		h.add(move5);
		int move6[] = {2,4}; // Blue 34
		h.add(move6);
		int move7[] = {4,5}; // Red  23
		h.add(move7);
		int move8[] = {2,5}; // Blue 34
		h.add(move8);
		int move9[] = {0,4}; // Red  23
		h.add(move9);
		int move10[] = {0,5}; // Blue 34
		h.add(move10);
		int move11[] = {1,5}; // Red  23
		h.add(move11);
		int move12[] = {3,5}; // Blue 34
		h.add(move12);
		int move13[] = {1,3}; // Red  23
		h.add(move13);
//		int move14[] = {1,4}; // Blue 34
//		h.add(move14);
//		int move15[] = {0,3}; // Red  23 --- Red Wins
//		h.add(move15);
		Position endPos = new Position(h,null,2);
		LayerTreeNode newNode = new LayerTreeNode(endPos, 2);
		System.out.println(newNode.isDeadEnd());
		System.out.println(newNode.positionWinner());
//		this.printResults();
	}
	
	public void test3() {
		System.out.println("~~~Running Winning Strategy Test~~~");
		ArrayList<int[]> h = new ArrayList<int[]>();
		int move1[] = {0,1}; // Red  01
		h.add(move1);
		int move2[] = {1,2}; // Blue 12  --- Takes 25 Minutes
		h.add(move2);
		int move3[] = {0,2}; // Red  02
		h.add(move3);
		int move4[] = {3,4}; // Blue 34
		h.add(move4);
		int move5[] = {2,3}; // Red  23
		h.add(move5);
		int move6[] = {2,4}; // Blue 24
		h.add(move6);
		int move7[] = {4,5}; // Red  45
		h.add(move7);
		int move8[] = {2,5}; // Blue 25
		h.add(move8);
		int move9[] = {0,4}; // Red  04
		h.add(move9);
		int move10[] = {0,5}; // Blue 05
		h.add(move10);
		int move11[] = {1,5}; // Red  15
		h.add(move11);
		int move12[] = {3,5}; // Blue 35
		h.add(move12);
//		int move13[] = {1,3}; // Red  13
//		h.add(move13);
//		int move14[] = {1,4}; // Blue 14
//		h.add(move14);
		Position endPos = new Position(h,null,2);
		endPos.showConnections();
		LayerTreeNode newNode = new LayerTreeNode(endPos, 2);
		winningStrategyRun(newNode, 0);
		//existanceOfWinnerRun(newNode);
		System.out.println("Max Possible Games: "+maximumOutcomes);
		System.out.println("Red Wins: "+winnerCount[0]);
		System.out.println("Blue Wins: "+winnerCount[1]);
		System.out.println("Total Wins: "+(winnerCount[0]+winnerCount[1]));
		System.out.println(Arrays.toString(winnerCount));
	}
}
