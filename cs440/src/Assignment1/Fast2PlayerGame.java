package Assignment1;

import java.util.ArrayList;

public class Fast2PlayerGame {
	private int history_node1[] = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	private int history_node2[] = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
//	private int history_node1[] = new int[15];
//	private int history_node2[] = new int[15];
	private int history_moves[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	private boolean availible_nodes[] = new boolean[100];
	private int movesMade=0;
	private int maxMoves=15;
	private int numPlayers;
	private int winner = -1;
	private int winnerCount[] = {0,0,0,0};
	private int gamesPlayed = 0;
	private int cntr = 0;
	public Fast2PlayerGame(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	public void existanceOfWinner() {
		if(movesMade>=15) {
			isGameOver();
			if(winner>-1) {
				winnerCount[0]++;
				winner = -1;
			}
			gamesPlayed++;
			return;
		}
		for(int i=0;i<numPlayers;i++) {
			setPlayerMove(i);
			makeMove();
			existanceOfWinner();
			takeBackMove();
		}
	}
	public void printResults() {
		System.out.println("Total Permutations Checked           : "+gamesPlayed);
		System.out.println("Total Permutations Ending in a win   : "+winnerCount[0]);
	}
	private void populateMemory() {
		for(int i=0;i<maxMoves;i++) {
			history_node1[i]=-1;
			history_node2[i]=-1;
			history_moves[i]=-1;
		}
		for(int i=0;i<100;i++) {
			availible_nodes[i]=true;
		}
	}
	public void printHistory() {
		System.out.println("Current Game: ");
		System.out.print("[");
		for(int i=0;i<maxMoves;i++) {
			System.out.print("\t"+history_moves[i]);
		}
		System.out.println("\t]");
		System.out.print("[");
		for(int i=0;i<maxMoves;i++) {
			System.out.print("\t"+history_node1[i]);
		}
		System.out.println("\t]");
		System.out.print("[");
		for(int i=0;i<maxMoves;i++) {
			System.out.print("\t"+history_node2[i]);
		}
		System.out.println("\t]");
	}
	public void setPlayerMove(int player) {
		//System.out.println("Setting Color of position "+movesMade+" to "+player);
		history_moves[movesMade] = player;
	}
	public void makeMove() {
		//System.out.println("Making new move: "+movesMade);
//		history_node1[movesMade] = node1;
//		history_node2[movesMade] = node2;
//		history_moves[movesMade] = movesMade%numPlayers;
		movesMade++;
//		availible_nodes[map(node1,node2)]=false;
//		availible_nodes[map(node2,node1)]=false;
	}
	public void takeBackMove() {
		//System.out.println("TakingBack{"+history_moves[movesMade-1]+""+history_node1[movesMade-1]+""+history_node2[movesMade-1]+"}");
//		int node1 = history_node1[movesMade-1];
//		int node2 = history_node2[movesMade-1];
//		availible_nodes[map(node1,node2)]=true;
//		availible_nodes[map(node2,node1)]=true;
//		history_node1[movesMade-1] = -1;
//		history_node2[movesMade-1] = -1;
		history_moves[movesMade-1] = -1;
		movesMade--;
		winner=-1;
	}
	public int map(int node1, int node2) {
		return (node1+1)*10+(node2+1);
	}
	public int unmap1(int hash) {
		return (hash/10)-1;
	}
	public int unmap2(int hash) {
		return (hash%10)-1;
	}
	public void printAvailibleArray() {
		for(int i=0;i<100;i++) {
			System.out.print(i+": "+availible_nodes[i]+" ");
		}
		System.out.println();
	}
	public int[] availibleMoves() {
		int size=0;
		for(int j=0;j<6;j++) {
			for(int i=0;i<j;i++) {
				if(isMoveAvailible(j,i)) {size++;}
			}
		}
		int moves[] = new int[size];
		int index=0;
		for(int j=0;j<6;j++) {
			for(int i=0;i<j;i++) {
				//System.out.println("Is ["+j+","+i+"] availible? "+isMoveAvailible(j,i));
				if(isMoveAvailible(j,i)) {
					//System.out.println("Mapping: ["+j+","+i+"] to "+map(j,i)+" at index "+index);
					moves[index]=map(j,i);
					index++;
				}
			}
		}
		return moves;
	}
	public boolean isMoveAvailible(int node1, int node2) {
		return availible_nodes[map(node1,node2)];
	}
	public int getWinner() {
		return winner;
	}
	public boolean isGameOver() {
		if(isGameWon()) {return true;}
		if(isNoMoreMoves()) {return true;}
		return false;
	}
	public boolean isNoMoreMoves() {
		return movesMade>=maxMoves;
	}
	public boolean isGameWon() {
		for(int i=0;i<numPlayers;i++) {
//			System.out.println("Has Player won: "+i);
			if(hasPlayerWon(i)) {return true;}
		}
		return false;
	}
	public boolean hasPlayerWon(int player) {
		for(int k=0;k<6;k++) {
			for(int j=0;j<k;j++) {
				for(int i=0;i<j;i++) {
					if(
							(doesPlayerOwnEdge(player, j, i)||doesPlayerOwnEdge(player, i, j))&&
							(doesPlayerOwnEdge(player, k, i)||doesPlayerOwnEdge(player, i, k))&&
							(doesPlayerOwnEdge(player, j, k)||doesPlayerOwnEdge(player, k, j))
					) {
						//System.out.println("player has won with "+i+""+j+","+i+""+k+","+j+""+k+"...");
						winner = player;
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean doesPlayerOwnEdge(int player, int node1, int node2) {
		//System.out.println("Does player "+player+" own ["+node1+","+node2+"]...?");
		for(int i=0;i<maxMoves;i++) {
			//System.out.println("\tChecking History{"+history_moves[i]+""+history_node1[i]+""+history_node2[i]+"}");
			if(history_moves[i]==player) {
				//System.out.println("\tChecking History{"+history_moves[i]+""+history_node1[i]+""+history_node2[i]+"}");
				if(history_node1[i]==node1&&history_node2[i]==node2) {
					//System.out.println("\t\tYES");
					return true;
				}
			}
		}
		return false;
	}
}
