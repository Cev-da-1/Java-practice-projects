package traitors.players;
import traitors.Game;
import traitors.players.Faithful;
import traitors.players.Player;
import java.util.*;

public class Traitor extends Player{

    private Traitor(Game game, String name) {
        super(game, name);
    }
	
	public static Traitor recruit(Faithful faithful) {
		Traitor traitor = new Traitor(faithful.game, faithful.toString());
		ArrayList<Player> players = faithful.game.getPlayers();
		players.set(players.indexOf(faithful), traitor);
		faithful.game.setPlayers(players);
		return traitor;
	}
}
