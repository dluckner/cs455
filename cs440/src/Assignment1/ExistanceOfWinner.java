public class ExistanceOfWinner {
	private static int history_node1[] = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	private static int history_node2[] = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	private static int history_moves[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	private static boolean availible_nodes[] = new boolean[100];
	private static int movesMade=0;
	private static int maxMoves=15;
	private static int numPlayers;
	private static int winner = -1;
	private static int winnerCount[] = {0,0,0,0};
	private static int gamesPlayed = 0;
	public static void main(String args[]) {
		if(args.length!=1){
			System.out.println("java existanceOfWinner #players");
		}
		numPlayers=args[0].charAt(0)-48;
		if(numPlayers!=2&&numPlayers!=3) {System.out.println("Supports 2 or 3 Players only!");return;}
		System.out.println("Checking existanceOfWinner for "+numPlayers+" players...");
		existanceOfWinner();
		printResults();
	}
	public static void existanceOfWinner() {
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
	public static void printResults() {
		System.out.println("Total Permutations Checked           : "+gamesPlayed);
		System.out.println("Total Permutations Ending in a win   : "+winnerCount[0]);
	}
	public static void printHistory() {
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
	public static void setPlayerMove(int player) {
		history_moves[movesMade] = player;
	}
	public static void makeMove() {
		movesMade++;
	}
	public static void takeBackMove() {
		history_moves[movesMade-1] = -1;
		movesMade--;
		winner=-1;
	}
	public static int map(int node1, int node2) {
		return (node1+1)*10+(node2+1);
	}
	public static int unmap1(int hash) {
		return (hash/10)-1;
	}
	public static int unmap2(int hash) {
		return (hash%10)-1;
	}
	public static void printAvailibleArray() {
		for(int i=0;i<100;i++) {
			System.out.print(i+": "+availible_nodes[i]+" ");
		}
		System.out.println();
	}
	public static int[] availibleMoves() {
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
				if(isMoveAvailible(j,i)) {
					moves[index]=map(j,i);
					index++;
				}
			}
		}
		return moves;
	}
	public static boolean isMoveAvailible(int node1, int node2) {
		return availible_nodes[map(node1,node2)];
	}
	public static int getWinner() {
		return winner;
	}
	public static boolean isGameOver() {
		if(isGameWon()) {return true;}
		if(isNoMoreMoves()) {return true;}
		return false;
	}
	public static boolean isNoMoreMoves() {
		return movesMade>=maxMoves;
	}
	public static boolean isGameWon() {
		for(int i=0;i<numPlayers;i++) {
			if(hasPlayerWon(i)) {return true;}
		}
		return false;
	}
	public static boolean hasPlayerWon(int player) {
		for(int k=0;k<6;k++) {
			for(int j=0;j<k;j++) {
				for(int i=0;i<j;i++) {
					if(
							(doesPlayerOwnEdge(player, j, i)||doesPlayerOwnEdge(player, i, j))&&
							(doesPlayerOwnEdge(player, k, i)||doesPlayerOwnEdge(player, i, k))&&
							(doesPlayerOwnEdge(player, j, k)||doesPlayerOwnEdge(player, k, j))
					) {
						winner = player;
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean doesPlayerOwnEdge(int player, int node1, int node2) {
		for(int i=0;i<maxMoves;i++) {
			if(history_moves[i]==player) {
				if(history_node1[i]==node1&&history_node2[i]==node2) {
					return true;
				}
			}
		}
		return false;
	}
}

