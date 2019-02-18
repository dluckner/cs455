package Assignment1;

import java.util.ArrayList;

//public class Agent {
//	private boolean db = false;
//	private int boardSize = 6;
//	private int color;
//	private ArrayList<int[]> availibleMoves;
//	private int[] nextMove;
//	public Agent(int color) {
//		this.color=color;
//		availibleMoves = new ArrayList<int[]>();
//	}
//	public void inspectBoard(ArrayList<int[]> history) {
//		if(db) {
//			System.out.println("Color: <"+this.color+"> is inspecting moves...");
//		}
//		for(int j=0;j<this.boardSize;j++) {
//			for(int i=0;i<j;i++) {
//				if(!historyContains(history,j,i)) {
//					int newMove[] = {j,i};
//					availibleMoves.add(newMove);
//				}
//			}
//		}
//	}
//	private boolean historyContains(ArrayList<int[]> history, int node1, int node2) {
//		if(history == null) {return false;}
//		if(history.size()<1) {return false;}
//		for(int i=0; i<history.size();i++) {
//			int move[] = history.get(i);
//			if(move[0]==node1&&move[1]==node2) {
//				return true;
//			}
//		}
//		return false;
//	}	
////	public void inspectBoard(Graph board) {
////		if(db) {
////			System.out.println("Color: <"+this.color+"> is inspecting moves...");
////		}
////		availibleMoves.clear();
////		int size = board.getGraphSize();
////		for(int j=0;j<size;j++) {
////			for(int i=0;i<=j;i++) {
////				if(!board.areNodesConnected(j,i)) {
////					int[] newMove = {j,i};
////					availibleMoves.add(newMove);
////				}
////			}
////		}
////		if(db) {this.printMoves();}
////	}
//	public boolean canMove() {
//		return availibleMoves.size()>0;
//	}
//	public ArrayList<int[]> getAvailibleMoves(){return this.availibleMoves;}
//	public int[] addMove() {
//		if (availibleMoves.size()>0) {
//			this.nextMove = availibleMoves.get(0);
//			if(db) {System.out.println(this.color+": Making move["+nextMove[0]+","+nextMove[1]+"]");}
//			return availibleMoves.get(0);
//		}
//		return null;
//	}
//	private void printMoves() {
//		String output = this.color+"'s Moves:\n{ ";
//		for(int i=0;i<this.availibleMoves.size();i++) {
//			output += "["+this.availibleMoves.get(i)[0]+","+this.availibleMoves.get(i)[1]+"] ";
//		}
//		output+="}";
//		System.out.println(output);
//	}
//}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class Agent {
	private boolean db = false;
	private int color;
	private ArrayList<int[]> availibleMoves;
	private int[] nextMove;
	public Agent(int color) {
		this.color=color;
		availibleMoves = new ArrayList<int[]>();
	}
	public void inspectBoard(Graph board) {
		if(db) {
			System.out.println("Color: <"+this.color+"> is inspecting moves...");
		}
		availibleMoves.clear();
		int size = board.getGraphSize();
		for(int j=0;j<size;j++) {
			for(int i=0;i<=j;i++) {
				if(!board.areNodesConnected(j,i)) {
					int[] newMove = {j,i};
					availibleMoves.add(newMove);
				}
			}
		}
		if(db) {this.printMoves();}
	}
	public boolean canMove() {
		return availibleMoves.size()>0;
	}
	public ArrayList<int[]> getAvailibleMoves(){return this.availibleMoves;}
	public int[] addMove() {
		if (availibleMoves.size()>0) {
			this.nextMove = availibleMoves.get(0);
			if(db) {System.out.println(this.color+": Making move["+nextMove[0]+","+nextMove[1]+"]");}
			return availibleMoves.get(0);
		}
		return null;
	}
	private void printMoves() {
		String output = this.color+"'s Moves:\n{ ";
		for(int i=0;i<this.availibleMoves.size();i++) {
			output += "["+this.availibleMoves.get(i)[0]+","+this.availibleMoves.get(i)[1]+"] ";
		}
		output+="}";
		System.out.println(output);
	}
}
