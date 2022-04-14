package pkg;

import java.util.ArrayList;

public class GameBoard {
	public class Pit {
		private int marbles;
		
		public Pit() {
			marbles = 0;
		}
		public int getMarbles() {
			return this.marbles;
		}
		public void addMarbles(int new_marbles) {
			this.marbles = this.marbles + new_marbles;
		}
		public void setMarbles(int new_marbles) {
			this.marbles = new_marbles;
		}
	}	
	
	// GAMEBOARD VARIABLES	
	private ArrayList<Pit> all_pits;
	private ArrayList<Pit> p1_pits;
	private ArrayList<Pit> p2_pits;
	private Pit p1_mancala;
	private Pit p2_mancala;
	
	
	public GameBoard() {
		//	INITIALIZE VARIABLES
		/*
		pits = new ArrayList<Pit>(14);
		for(Pit x : pits) {
			if (pits.indexOf(x) == 6) {
				p1_mancala = x; 			// Initialize p1_mancala to be 7th element of the pits array
			} else if (pits.indexOf(x) == 13) {
				p2_mancala = x;				// Initialize p2_mancala to be 14th element of the pits array
			} else {
				x.setMarbles(4);			// Set all other pits to have 4 marbles
			}
		}
		*/
		// Create all pits and assign them to players
		all_pits = new ArrayList<Pit>(14);
		for(int i = 0; i < all_pits.size(); i++) {
			if (i < 6) {
				p1_pits.add(all_pits.get(i));
			}
			if (i == 6) {
				p1_mancala = all_pits.get(i);
			}
			if (i > 6 && i < 13) {
				p2_pits.add(all_pits.get(i));
			}
			if (i == 13) {
				p2_mancala = all_pits.get(i);
			}
		}
		// Add starting marbles
		for(Pit p : p1_pits) {
			p.setMarbles(4);
		}
		for(Pit p : p2_pits) {
			p.setMarbles(4);
		}
	}
	
	public void makeMove(int player, int pit) { // I am assuming the pit number from the server is absolute starting from 0 at the beginning of player 1's pits.
		//Pit selected_pit = all_pits.get(pit);
		int count = all_pits.get(pit).getMarbles();
		for(int i = 0; i < count; i++) {
			all_pits.get((pit + 1 + i) % 14).addMarbles(1);
		}
		
		Pit last_pit = all_pits.get((all_pits.indexOf(pit) + count) % 14);
		
		if (last_pit.getMarbles() == 1) {
			if (player == 1) {
				if (p2_pits.contains(last_pit)) {
					if (p2_pits.indexOf(last_pit) == pit) {
						p1_mancala.addMarbles(last_pit.getMarbles() + all_pits.get(pit).getMarbles());
						last_pit.setMarbles(0);
						all_pits.get(pit).setMarbles(0);
					}
				}
			} else if (player == 2) {
				if (p1_pits.contains(last_pit)) {
					if (p1_pits.indexOf(last_pit) == p2_pits.indexOf(pit)) {
						p2_mancala.addMarbles(last_pit.getMarbles() + all_pits.get(pit).getMarbles());
						last_pit.setMarbles(0);
						all_pits.get(pit).setMarbles(0);
					}
				}
			} else {
				System.out.println("makeMove could not determine which player was moving.");
			}
		}
	}
	
	public int getScore(int player) {
		if (player == 1) {
			return this.p1_mancala.getMarbles();
		} else if (player == 2) {
			return this.p2_mancala.getMarbles();
		} else {
			System.out.println("getScore could not determine which player score to fetch.");
			return 0;
		}
	}

	
}
