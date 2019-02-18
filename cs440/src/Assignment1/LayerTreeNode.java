package Assignment1;

import java.util.ArrayList;

public class LayerTreeNode {
	private boolean db = false;
	private int playerCount;
	private Position myPosition;
	private ArrayList<LayerTreeNode> children;
	private int[] previousMove;
	public LayerTreeNode(Position myPosition, int playerCount) {
		this.children = new ArrayList<LayerTreeNode>();
		this.myPosition = myPosition;
		this.playerCount = playerCount;
		this.previousMove = myPosition.getPreviousMove();
	}
	public void addChild(LayerTreeNode childNode) {
		this.children.add(childNode);
	}
	
	public boolean isDeadEnd() {return myPosition.gameOver();}
	public int positionWinner() {return myPosition.getWinner();}
	public int numberOfChildren() {return children.size();}
	public LayerTreeNode getChild(int index) {return children.get(index);}
	public ArrayList<int[]> getHistory() {return this.myPosition.copyHistory();}
	public int[] getPreviousMove() {return this.previousMove;}
	public void deleteChild(int index) {children.remove(index);}
	
	public void makeChildren() {
		if(db) {System.out.println("Making Children");}
		ArrayList<int[]> childrenMoves = myPosition.getAvailibleMoves();
		for(int i=0;i<childrenMoves.size();i++) {
			Position childPosition = new Position(myPosition.copyHistory(),childrenMoves.get(i),this.playerCount);
			this.children.add(new LayerTreeNode(childPosition, this.playerCount));
		}
	}
}
