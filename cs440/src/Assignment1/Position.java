package Assignment1;

import java.util.ArrayList;
import java.util.Arrays;

//public class Position {
//	private boolean db = false;
//	private ArrayList<int[]> history;
//	private ArrayList<int[]> availibleMoves;
//	private int playerCount;
//	private int whosTurn;
//	private int winner;
//	private int[] lastMove;
//	private int graphSize = 6;
//	public Position(ArrayList<int[]> history, int[] newMove, int playerCount) {
//		this.history = new ArrayList<int[]>();
//		this.playerCount = playerCount;
//		this.whosTurn = this.copyHistory(history, playerCount);
//		this.lastMove = newMove;
//		this.makeMove(newMove, whosTurn);
//		Agent player = new Agent(whosTurn);
//		player.inspectBoard(history);
//		this.availibleMoves = player.getAvailibleMoves();
//	}
//	public ArrayList<int[]> copyHistory(){
//		ArrayList<int[]> historyCopy = new ArrayList<int[]>();
//		for(int i=0;i<this.history.size();i++) {
//			historyCopy.add(this.history.get(i));
//		}
//		return historyCopy;
//	}
//	
//	public int getWinner() {return this.winner;}
//	
//	public int[] getPreviousMove() {return this.lastMove;}
//	
//	public ArrayList<int[]> getAvailibleMoves() {return this.availibleMoves;}
//	
//	private int copyHistory(ArrayList<int[]> history, int playerCount) {
//		int whosTurn = 0;
//		if(history == null) {return 0;}
//		for(int i=0;i<history.size();i++) {
//			int[] nextMove = new int[3];
//			nextMove[0]=i%playerCount;
//			nextMove[1]=history.get(i)[0];
//			nextMove[2]=history.get(i)[0];
//			this.history.add(history.get(i));
//			whosTurn = (whosTurn+1)%playerCount;
//		}
//		return whosTurn;
//	}
//	private void makeMove(int[] move, int color) {
//		if(move==null) {return;}
//		//positionGraph.connect(move, color);
//		this.history.add(move);
//		this.whosTurn++;
//	}
//	public boolean gameOver() {
//		if(gameBoardFull()) {return true;}
//		for(int i=0;i<this.playerCount;i++) {
//			if(gameTriangleExists(i)) {
//				this.winner = i;
//				if(db) {System.out.println("~~~~~~~~~~WINNER: "+i+"~~~~~~~~~~");}
//				return true;
//			}
//		}
//		return false;
//	}
//	private boolean gameBoardFull() {
//		boolean unitdb = false;
//		boolean test = true;
//		for(int j=0;j<this.graphSize;j++) {
//			for(int i=0;i<=j;i++) {
//				if(unitdb) {System.out.println("Check: ["+j+","+i+"] --- "+(!nodesConnected(j, i)));}
//				if(!nodesConnected(j, i)) {
//					test = false;
//					break;
//				}
//			}
//			if(test == false) {break;}
//		}
//		if(unitdb) {System.out.println("Returning "+test);}
//		return test;
//	}
//	private boolean gameTriangleExists(int colr) {
//		int boardSize = this.graphSize;
//		for(int k=0;k<boardSize;k++) {
//			for(int j=0;j<k;j++) {
//				for(int i=0;i<j;i++) {
//					if(
//						historyContains(colr,i,j)&&
//						historyContains(colr,i,k)&&
//						historyContains(colr,j,k)
//					) {return true;} 
//				}
//			}
//		}
//		return false;
//	}
//	private boolean historyContains(int colr, int node1, int node2) {
//		if(this.history == null) {return false;}
//		if(this.history.size()<1) {return false;}
//		for(int i=0; i<this.history.size();i+=this.playerCount) {
//			int move[] = this.history.get(i);
//			if(move[0]==node1&&move[1]==node2) {
//				return true;
//			}
//		}
//		return false;
//	}
//	private boolean nodesConnected(int node1, int node2) {
//		if(this.history == null) {return false;}
//		if(this.history.size()<1) {return false;}
//		for(int i=0; i<this.history.size();i++) {
//			int move[] = this.history.get(i);
//			if(move[0]==node1&&move[1]==node2) {
//				return true;
//			}
//		}
//		return false;
//	}
//}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class Position {
	private boolean db = false;
	private ArrayList<int[]> history;
	private ArrayList<int[]> availibleMoves;
	private Graph positionGraph;
	private int playerCount;
	private int whosTurn;
	private int winner;
	private int[] lastMove;
	public Position(ArrayList<int[]> history, int[] newMove, int playerCount) {
		this.history = new ArrayList<int[]>();
		this.positionGraph = new Graph(6);
		this.playerCount = playerCount;
		this.whosTurn = this.copyHistory(history, playerCount);
		this.lastMove = newMove;
		this.buildPositionGraph();
		this.makeMove(newMove, whosTurn);
		Agent player = new Agent(whosTurn);
		player.inspectBoard(this.positionGraph);
		this.availibleMoves = player.getAvailibleMoves();
	}
	public ArrayList<int[]> copyHistory(){
		ArrayList<int[]> historyCopy = new ArrayList<int[]>();
		for(int i=0;i<this.history.size();i++) {
			historyCopy.add(this.history.get(i));
		}
		return historyCopy;
	}
	
	public int getWinner() {return this.winner;}
	
	public int[] getPreviousMove() {return this.lastMove;}
	
	public ArrayList<int[]> getAvailibleMoves() {return this.availibleMoves;}
	
	public void showConnections() {positionGraph.showConnections();}
	
	private ArrayList<int[]> buildPositionGraph() {
		if(db) {System.out.println("Building Position Graph");}
		for(int i=0;i<history.size();i++) {
			if(db) {System.out.println(Arrays.toString(history.get(i)));}
		}
		for(int i=0;i<this.history.size();i++) {
			int[] nextMove = history.get(i);
			int color = i%this.playerCount;
			positionGraph.connect(nextMove,color);
		}
		return null;
	}
	private int copyHistory(ArrayList<int[]> history, int playerCount) {
		int whosTurn = 0;
		if(history == null) {return 0;}
		for(int i=0;i<history.size();i++) {
			int[] nextMove = new int[3];
			nextMove[0]=i%playerCount;
			nextMove[1]=history.get(i)[0];
			nextMove[2]=history.get(i)[0];
			this.history.add(history.get(i));
			whosTurn = (whosTurn+1)%playerCount;
		}
		return whosTurn;
	}
	private void makeMove(int[] move, int color) {
		if(move==null) {return;}
		positionGraph.connect(move, color);
		this.history.add(move);
		this.whosTurn++;
	}
	public boolean gameOver() {
		if(gameBoardFull()) {return true;}
		for(int i=0;i<this.playerCount;i++) {
			if(gameTriangleExists(i)) {
				this.winner = i;
				if(db) {System.out.println("~~~~~~~~~~WINNER: "+i+"~~~~~~~~~~");}
				return true;
			}
		}
		return false;
	}
	private boolean gameBoardFull() {
		boolean unitdb = false;
		boolean test = true;
		for(int j=0;j<positionGraph.getGraphSize();j++) {
			for(int i=0;i<=j;i++) {
				if(unitdb) {System.out.println("Check: ["+j+","+i+"] --- "+(!positionGraph.areNodesConnected(j, i)));}
				if(!positionGraph.areNodesConnected(j, i)) {
					test = false;
					break;
				}
			}
			if(test == false) {break;}
		}
		if(unitdb) {System.out.println("Returning "+test);}
		return test;
	}
	private boolean gameTriangleExists(int colr) {
		int boardSize = positionGraph.getGraphSize();
		for(int k=0;k<boardSize;k++) {
			for(int j=0;j<k;j++) {
				for(int i=0;i<j;i++) {
					if(
						positionGraph.isNodeOccupying(colr,i,j)&&
						positionGraph.isNodeOccupying(colr,i,k)&&
						positionGraph.isNodeOccupying(colr,j,k)
					) {return true;} 
				}
			}
		}
		return false;
	}
}
