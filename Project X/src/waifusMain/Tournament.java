package waifusMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tournament {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		BufferedReader reader = null;
		Path file = null;
		Group group = null;
		Competitor waifu = null;
		while(true) {
			String input;
			String fileName;
			String nextRound;
			int numGroups;
			int waifuCount;
			int winners;
			int fileIndex;
			System.out.println("Hello and welcome to a new Round!");
			System.out.println("Specify a name for the new file:");
			nextRound = userInput.nextLine();
			file = Paths.get(nextRound);
			System.out.println("Please specify the file:");
			fileName = userInput.nextLine();
			try {
				reader = new BufferedReader(new FileReader(fileName));
				System.out.println("Please input the number of groups:");
				numGroups = userInput.nextInt();
				System.out.println("Please input the number of waifus per group:");
				waifuCount = userInput.nextInt();
				System.out.println("Please input the number of winners per group:");
				winners = userInput.nextInt();
				List<String> lines = new ArrayList<String>();
				
				for (int i = 0; i<numGroups; i++) {
					group = new Group(waifuCount,winners);
					
					for (int j = 0; j<group.waifus.length; j++) {
						System.out.println("Please enter a contestant number:");
						fileIndex = userInput.nextInt();
						String line = null;
						reader.mark(1);
						for (int k = 0; k<fileIndex; k++) {
							line = reader.readLine();
						}
						reader.reset();
						waifu = new Competitor(line, "FEH");
						group.addWaifu(waifu);
					}
					group.reveal();
					int loosers = group.waifus.length - group.winners;
					for (int j = 0; j<loosers; j++) {
						System.out.println("Please elimate a waifu:");
						int index;
						index = userInput.nextInt();
						group.eliminate(index);
					}
					lines.addAll(group.listWinners());
				}
				Files.write(file, lines, Charset.forName("UTF-8"));
				System.out.println("Round over! Awaiting new command:");
				input = userInput.nextLine();
				if (input == "exit") {
					userInput.close();
					reader.close();
					break;
				}
			} catch(IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

}
