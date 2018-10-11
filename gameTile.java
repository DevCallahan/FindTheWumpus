public class gameTile {

	private boolean playerHere = false;
	private boolean wumpusHere = false;
	private boolean explored = false;
	private boolean torchHere = false;
	private boolean weaponHere = false;
	private boolean compassHere = false;
	private int row;
	private int col;

	public gameTile() {
	}// gameTile default constructor

	public gameTile(boolean wumpusHere, boolean explored, boolean playerHere, boolean torchHere, boolean weaponHere, boolean compassHere, int row, int col) {
		this.wumpusHere = wumpusHere;
		this.explored = explored;
		this.playerHere = playerHere;
		this.torchHere = torchHere;
		this.weaponHere = weaponHere;
		this.compassHere = compassHere;
    this.row = row;
    this.col = col;
	}// gameTile constructor

	public boolean getWumpusHere() {
		return wumpusHere;
	}// getWumpusHere

	public void setWumpusHere(boolean newWumpusHere) {
		wumpusHere = newWumpusHere;
	}// setWumpusHere

	public boolean getExplored() {
		return explored;
	}// getExplored

	public void setExplored(boolean newExplored) {
		explored = newExplored;
	}// setExplored

	public boolean getPlayerHere() {
		return playerHere;
	}// getPlayerHere

	public void setPlayerHere(boolean newPlayerHere) {
		playerHere = newPlayerHere;
	}// setPlayerHere

	public boolean getTorchHere() {
		return torchHere;
	}//getTorchHere

	public void setTorchHere(boolean newTorchHere) {
		torchHere = newTorchHere;
	}//setTorchHere
	
	public boolean getWeaponHere() {
		return weaponHere;
	}//getWeaponHere
	
	public void setWeaponHere(boolean newWeaponHere) {
		weaponHere = newWeaponHere;
	}//setWeaponHere

	public boolean getCompassHere() {
		return compassHere;
	}//getCompassHere

	public void setCompassHere(boolean newCompassHere) {
		compassHere = newCompassHere;
	}//setCompassHere
  
	public int getRow() {
		return row;
	}//getRow
	
	public void setRow(int newRow) {
		row = newRow;
	}//setRow

	public int getCol() {
		return col;
	}//getCol

	public void setCol(int newCol) {
		col = newCol;
	}//setCol
}// END
