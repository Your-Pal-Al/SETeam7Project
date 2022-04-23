package pkg;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GameData implements Serializable {	
	// GAMEBOARD VARIABLES
	private String state;
	private int[] all_pits;
	private int selected_pit;
	
	
	public GameData() {
		//	INITIALIZE VARIABLES
		newBoard();
	}
	
	public void newBoard() {
		// Create all pits and put marbles in
		all_pits = new int[14];
		for(int i = 0; i < all_pits.length; i++) { // Loop sets all_pits[0-5] to be player one's side with 4 marble in each
			if (i < 6 || (i > 6 && i < 13)) {	   // pit. Then it sets all_pits[6] to be player one's mancala with 0 marbles.
				all_pits[i] = 4;				   // It does the same for the other side with 7-12 set to 4 marbles and 13
			} else {							   // set to 0 for the player two mancala.
				all_pits[i] = 0;
			}
		}
		selected_pit = 0;
		state = "waitTurn";
	}
	
	public void makeMove(int pit) { // I am assuming the pit number is absolute starting from 0 at the beginning of player 1's pits.
		//Pit selected_pit = all_pits.get(pit);
		int marbles = all_pits[pit];
		// declare opposite and last pits
		int last_pit = 0;
		int opposite_pit = 0;
		int shift = 1;

		
		// Deposit marbles in pits 
		if (pit < 6) {
			for(int i = 0; i < marbles; i++) {
				all_pits[(pit + shift + i) % 13] = all_pits[(pit + shift + i) % 13] + 1;
				last_pit = (pit + shift + i) % 13;
			}
		} else {
			for(int i = 0; i < marbles; i++) {
				if (pit + marbles > 19) {
					if ((pit + shift + 1) % 14 == 6) {
						shift = 2;
					}
				}
				
				all_pits[(pit + shift + i) % 14] = all_pits[(pit + shift + i) % 14] + 1;
				last_pit = (pit + shift + i) % 14;
			}
		}
		all_pits[pit] = 0; // set pit to 0 after move
		
		// Find opposite pit
		if (last_pit == 6 || last_pit == 13) {
			opposite_pit = 0;
		} else {
			opposite_pit = 12 - last_pit;
		}
		
		//TODO: Delete - debug
		System.out.println("Make move is executing, pit = " + pit); // Debug
		System.out.println("last_pit is " + last_pit); // Debug
		System.out.println("opposite_pit is " + opposite_pit); // Debug

		if (pit < 6) { // if player == 1
			if (last_pit < 6 && all_pits[last_pit] == 1 && all_pits[opposite_pit] > 0) {
				all_pits[6] = all_pits[6] + all_pits[opposite_pit] + 1;
				all_pits[last_pit] = 0;
				all_pits[opposite_pit] = 0;
				setState("nextTurn");
			}
			else if (last_pit == 6) {
				setState("sameTurn");
			}
			else {
				System.out.println("makeMove evaluated to a normal turn.");
				setState("nextTurn");
				System.out.println(state);
			}
		}
		else if (pit > 6) {
			if (last_pit > 6 && last_pit < 13 && all_pits[last_pit] == 1 && all_pits[opposite_pit] > 0) {
				all_pits[13] = all_pits[13] + all_pits[opposite_pit] + 1;
				all_pits[last_pit] = 0;
				all_pits[opposite_pit] = 0;
				setState("nextTurn");
			}
			else if (last_pit == 13) {
				setState("sameTurn");
			}
			else {
				System.out.println("makeMove evaluated to a normal turn.");
				setState("nextTurn");
				System.out.println(state);
			}
		}
		/*
		if (last_pit < 6 && all_pits[last_pit] == 1 && all_pits[opposite_pit] > 0) { // Check if last bead lands on player 1 side and that pit was empty and the opposite side was not.
			all_pits[6] = all_pits[opposite_pit] + 1;
			all_pits[last_pit] = 0;
			all_pits[opposite_pit] = 0;
			setState("nextTurn");
		} else if (last_pit == 6) {
			System.out.println("makeMove evaluated to sameTurn");
			setState("sameTurn");
		} else {
			System.out.println("makeMove evaluated to a normal turn.");
			setState("nextTurn");
			System.out.println(state);
		}
		*/
	}
	
	public void checkWin() {
		if ((all_pits[0] + all_pits[1] + all_pits[2] + all_pits[3] + all_pits[4] + all_pits[5]) == 0 ||
				(all_pits[7] + all_pits[8] + all_pits[9] + all_pits[10] + all_pits[11] + all_pits[12]) == 0) {
			if (all_pits[6] > all_pits[13]) {
				state = "P1win";
			} else {
				state = "P2win";
			}
		} else if (all_pits[6] > 24) {
			state = "P1win";
		} else if (all_pits[13] > 24) {
			state = "P2win";
		}
	}
	
	public int getScore(int player) {
		if (player == 1) {
			return all_pits[6];
		} else if (player == 2) {
			return all_pits[13];
		} else {
			System.out.println("getScore could not determine which player score to fetch.");
			return 0;
		}
	}
	
	public void invert() {
		for (int i = all_pits.length; i > (all_pits.length / 2); i--) {
			int x, temp;
	        temp = all_pits[0];
	        for (x = 0; x < all_pits.length - 1; x++)
	            all_pits[x] = all_pits[x + 1];
	        all_pits[x] = temp;
		}
	}
	
	public String getPits() {
		String pits_string = "";
		
		for (int i = 0; i < all_pits.length; i++) {
			pits_string = pits_string + all_pits[i] + ",";
		}
		return pits_string;
	}
	public void setPits(int[] pits) {
		this.all_pits = pits;
	}
	public int getPit(int pit) {
		return all_pits[pit];
	}

	public int getSelectedPit() {
		return selected_pit;
	}
	public void setSelectedPit(int pit) {
		this.selected_pit = pit;
	}
	public String getState() {
		checkWin();
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}