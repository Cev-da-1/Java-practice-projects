package traitors;
import java.util.*;
import traitors.players.Player;
import java.lang.IllegalStateException;

public class RoundTable {
    private ArrayList<Player> players;
	private HashMap<Player, ArrayList<Player>> votes;
	
	public RoundTable (ArrayList<Player> playersIn) {
		players = playersIn;
		votes = new HashMap<>();
	}
	
	public boolean collectVotes() {
		for (Player player: players) {
			Player votedPlayer = player.getVote(players);
			ArrayList<Player> votedBy;
			
			if (votes.containsKey(votedPlayer)) votedBy = votes.get(votedPlayer);
			else votedBy = new ArrayList<>();
			
			votedBy.add(player);
			votes.put(votedPlayer, votedBy);
		}
		
		int max = votes.values().stream().map(item -> item.size()).max(Comparator.naturalOrder()).get();
		int maxNum = 0;
		
		for (Player vPlayer: votes.keySet()) {
			if (votes.get(vPlayer).size() == max) maxNum ++;
		}
		
		return maxNum == 1;
	}
	
	public boolean resolveTie() {
		int max = votes.values().stream().map(item -> item.size()).max(Comparator.naturalOrder()).get();
		ArrayList<Player> topPlayers = new ArrayList<>();
		
		for (Player player: players) {
			if (votes.get(player).size() == max) {
				topPlayers.add(player);
				votes.put(player, new ArrayList<>());
			}
		}
		
		for (Player player: players) {
			Player votedPlayer = player.getVote(topPlayers);
			ArrayList<Player> votedBy;
			
			if (votes.containsKey(votedPlayer)) votedBy = votes.get(votedPlayer);
			else votedBy = new ArrayList<>();
			
			votedBy.add(player);
			votes.put(votedPlayer, votedBy);
		}
		
		int newMax = votes.values().stream().map(item -> item.size()).max(Comparator.naturalOrder()).get();
		int maxNum = 0;
		
		for (Player vPlayer: votes.keySet()) {
			if (votes.get(vPlayer).size() == newMax) maxNum ++;
		}
		
		return maxNum == 1;
	}
	
	public Player getBanishedPlayer() {
		int max = votes.values().stream().map(item -> item.size()).max(Comparator.naturalOrder()).get();
		int maxNum = 0;
		
		for (Player player: votes.keySet()) {
			if (votes.get(player).size() == max) maxNum ++;
		}
		
		if (maxNum == 1) {
			for (Player player: votes.keySet()) {
				if (votes.get(player).size() == max) return player;
			}
		}
		
		throw new IllegalStateException();
	}
}