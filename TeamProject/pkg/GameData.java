package pkg;

import java.io.Serializable;

public class GameData implements Serializable {
	// Data fields for pit selected
	private int pit;
	
	public void setPit(int pit) {
		this.pit = pit;
	}
	public int getPit() {
		return pit;
	}
	public GameData(int pit) {
		setPit(pit);
	}
}
