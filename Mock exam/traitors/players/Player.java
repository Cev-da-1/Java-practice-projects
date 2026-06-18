package traitors.players;

import java.util.*;
import traitors.Game;
import traitors.players.Faithful;

public class Player {

    protected Game game;
    protected String name;

    public Player(Game game, String name) {
        this.game = game;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
	public Player getVote(ArrayList<Player> players) {
		Random random = game.getRandom();
		int index = random.nextInt(players.size());
		while (players.get(index).toString().equals(name)) {
			index = random.nextInt(players.size());
		}
		return players.get(index);
	}
}
