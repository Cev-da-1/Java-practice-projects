package traitors;
import java.util.*;
import traitors.players.Player;
import traitors.players.Faithful;
import traitors.players.Traitor;

public class Game {
    private ArrayList<Player> players;
	private Random random;
    private ArrayList<Player> banished;
	private RoundTable roundTable;
	
	public Game(Random random) {
		this.random = random;
		this.players = new ArrayList<>();
		this.banished = new ArrayList<>();
	}
	
	public Random getRandom(){
		return random;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public void setPlayers(ArrayList<Player> players){
		this.players = players;
	}
	
	public void initialisePlayers(String[] names) {
		for (String name: names) {
			Faithful newPlayer = new Faithful(this, name);
			players.add(newPlayer);
		}
		
		ArrayList<Integer> traitorIndexes = new ArrayList<>();
		int currentIndex;
		while (traitorIndexes.size() < 3) {
			currentIndex = random.nextInt(players.size());
			if (!traitorIndexes.contains(currentIndex)) traitorIndexes.add(currentIndex);
		}	
		
		for (int index: traitorIndexes) {
			Traitor.recruit((Faithful) players.get(index));
		}
	}
	
	public int runRoundTable() {
		roundTable = new RoundTable(players);
		boolean tied = !roundTable.collectVotes();
		int cycles = 1;
		while (tied) {
			tied = !roundTable.resolveTie();
			cycles ++;
		}
		
		Player mostVoted = roundTable.getBanishedPlayer();
		players.remove(mostVoted);
		banished.add(mostVoted);
		
		
		return cycles;
	}
}
	