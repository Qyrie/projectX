package waifusMain;

import java.util.ArrayList;
import java.util.List;

public class Group {
	int winners;
	int nextIndex = 0;
	Competitor[] waifus;
	
	public Group(int competitors, int winners) {
		this.waifus = new Competitor[competitors];
		this.winners = winners;
	}
	
	void addWaifu(Competitor waifu) {
		if (nextIndex != waifus.length) {
			waifus[nextIndex] = waifu;
			nextIndex++;
		} else {
			System.out.println("Too many waifus in this group.");
		}
	}
	
	void eliminate(int index) {
		waifus[index-1].defeat();
	}
	
	void reveal() {
		for (int i = 0; i<waifus.length; i++) {
			waifus[i].introduce();
		}
	}
	
	public String info(int index) {
		return waifus[index-1].name + "(" + waifus[index-1].affilation + ")";	
	}
	
	public List<String> listWinners() {
		List<String> lines = new ArrayList<String>();
		for (Competitor waifu : waifus) {
			if (waifu.active == true) {
				lines.add(waifu.name + "(" + waifu.affilation + ")");
			}
		}
		return lines;
	}
}
