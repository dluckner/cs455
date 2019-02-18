public class WinningStrategy {
	private static int history_node1[] = new int[15];
	private static int history_node2[] = new int[15];
	private static int history_moves[] = new int[15];
	private static boolean availible_nodes[] = new boolean[100];
	private static int movesMade=0;
	private static int maxMoves=15;
	private static int numPlayers;
	private static int winner = -1;
	private static int winnerCount[] = {0,0,0,0};
	private static int gamesPlayed = 0;
	private static int turnCount = 0;
	public static void main(String args[]) {
		if(args.length!=1){
			System.out.println("java WinningStrategy #players");
		}
		numPlayers=args[0].charAt(0)-48;
		if(numPlayers!=2&&numPlayers!=3) {System.out.println("Supports 2 or 3 Players only!");return;}
		System.out.println("Running Winning Strategy for "+numPlayers+" players...");
		populateMemory();
		winningStrategy(availibleMoves());
		printResults();
	}
	public static void winningStrategy(int[] possibleMoves) {
		turnCount = movesMade/numPlayers;
		if(movesMade>=15) {
			isGameOver();
			if(winner>-1) {
				winnerCount[0]++;
				winner = -1;
			}
			gamesPlayed++;
			return;
		}
		if(movesMade%numPlayers==0) { //Deploy Strategy
			int plannedMove[] = applyStrategy(possibleMoves);
			makeMove(unmap1(plannedMove[0]),unmap2(plannedMove[1]));
			winningStrategy(availibleMoves());
		}
		else { //Perform All Possible Moves
			for(int i=0;i<possibleMoves.length;i++) {
				makeMove(unmap1(possibleMoves[i]),unmap2(possibleMoves[i]));
				winningStrategy(availibleMoves());
				takeBackMove();
			}
		}
	}
	public static int[] applyStrategy(int[] possibleMoves) {
		turnCount = movesMade/numPlayers;
		if (turnCount==0) {
			int winningMove[] = {unmap1(possibleMoves[0]), unmap2(possibleMoves[0])};
			return winningMove;
		}
		if (turnCount==1) {
			int winningMove[] = build3Path(possibleMoves);
			return winningMove;
		}
		for(int i=0;i<possibleMoves.length;i++) {
			if(isGameWinningMove(unmap1(possibleMoves[i]), unmap2(possibleMoves[i]))) {
				int winningMove[] = {unmap1(possibleMoves[i]), unmap2(possibleMoves[i])};
				return winningMove;
			}
		}
		int winningMove[] = buildRing(possibleMoves);
		return winningMove;
	}
	public static int[] build3Path(int[] possibleMoves){
		int blue_node1 = history_node1[0];
		int blue_node2 = history_node2[0];
		int red_node1 = history_node1[1];
		int red_node2 = history_node2[1];
		boolean connected = false;
		if(
				blue_node1==red_node1||
				blue_node1==red_node2||
				blue_node2==red_node1||
				blue_node2==red_node2
			) {connected = true;}
		if (connected) {return buildRing(possibleMoves);}
		int winningMove[] = {-1,-1};
		if(isMoveAvailible(blue_node1,red_node1)) {winningMove[0]=blue_node1;winningMove[0]=red_node1;}
		if(isMoveAvailible(blue_node1,red_node2)) {winningMove[0]=blue_node1;winningMove[0]=red_node2;}
		if(isMoveAvailible(blue_node2,red_node1)) {winningMove[0]=blue_node2;winningMove[0]=red_node1;}
		if(isMoveAvailible(blue_node2,red_node2)) {winningMove[0]=blue_node2;winningMove[0]=red_node2;}
		return possibleMoves;
	}
	public static int[] buildRing(int[] possibleMoves) {
		int lastEdgeEnd = history_node2[movesMade-numPlayers];
		int nextEdgeEnd = -1;
		if(turnCount==4) {
			int nodeUsage[] = {2,2,2,2,2,2};
			printHistory();
			for(int i=0;i<15;i+=numPlayers) {
				if(history_node1[i]==-1) {break;}
				nodeUsage[history_node1[i]]--;
				nodeUsage[history_node2[i]]--;
			}
			int winningMove[] = {-1, -1};
			int spot = 0;
			for(int i=0;i<6;i++) {
				if(nodeUsage[i]==1) {
					winningMove[spot]=i;
					spot++;
				}
			}
			return winningMove;
		}
		for(int i=0;i<6;i++) {
			if(isMoveAvailible(lastEdgeEnd,i)) {
				boolean valid = true;
				for(int j=0;j<15;j++) {
					if(history_node1[j]==i||history_node2[j]==i) {
						valid = false;
					}
				}
				if(valid) {
					int winningMove[] = {lastEdgeEnd, i};
					return winningMove;
				}
			}
		}
		return null;
	}
	public static boolean isGameWinningMove(int node1, int node2) {
		boolean isWinningMove = false;
		makeMove(node1, node2);
		isWinningMove = isGameOver();
		takeBackMove();
		return isWinningMove;
	}
	public static void printResults() {
		System.out.println("Total Games Played            : "+gamesPlayed);
		System.out.println("Games Won By Player 0-'Blue  ': "+winnerCount[0]);
		System.out.println("Games Won By Player 1-'Red   ': "+winnerCount[1]);
		System.out.println("Games Won By Player 2-'Green ': "+winnerCount[2]);
		System.out.println("Games That Ended in a Tie     : "+winnerCount[3]);
	}
	private static void populateMemory() {
		for(int i=0;i<maxMoves;i++) {
			history_node1[i]=-1;
			history_node2[i]=-1;
			history_moves[i]=-1;
		}
		for(int i=0;i<100;i++) {
			availible_nodes[i]=true;
		}
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
	public static void makeMove(int node1, int node2) {
		history_node1[movesMade] = node1;
		history_node2[movesMade] = node2;
		history_moves[movesMade] = movesMade%numPlayers;
		movesMade++;
		availible_nodes[map(node1,node2)]=false;
		availible_nodes[map(node2,node1)]=false;
	}
	public static void takeBackMove() {
		int node1 = history_node1[movesMade-1];
		int node2 = history_node2[movesMade-1];
		availible_nodes[map(node1,node2)]=true;
		availible_nodes[map(node2,node1)]=true;
		history_node1[movesMade-1] = -1;
		history_node2[movesMade-1] = -1;
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
	public static boolean isMoveAvailible(int node1, int node2) {
		if(node1==node2) {return false;}
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
						//System.out.println("player has won with "+i+""+j+","+i+""+k+","+j+""+k+"...");
						winner = player;
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean doesPlayerOwnEdge(int player, int node1, int node2) {
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
