package wolf.playingwithfire3.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import wolf.playingwithfire3.Game;
import wolf.playingwithfire3.entities.BombsManager;
import wolf.playingwithfire3.entities.LocalPlayer;
import wolf.playingwithfire3.entities.OnlinePlayer;
import wolf.playingwithfire3.entities.Player;
import wolf.playingwithfire3.online.Client;
import wolf.playingwithfire3.worlds.World;

public class QueueState extends State{

	private Client client;
	private Game game;
	private BombsManager bombsManager;
	private Player[] players;
	private World world;
	
	
	
	public QueueState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		client = new Client(this);
		client.sendPlayerInfos();
		
		this.game = game;
		this.bombsManager = bombsManager;
		this.world = new World("world2.txt");
	}

	public void joinPlayer(int x,int y,int health,String playerID, String skinName,int spielerIndex) {
		if(players[spielerIndex-1]==null) {
			if(playerID==client.getPlayerId()) {
				players[spielerIndex-1] = new LocalPlayer(world, game, bombsManager, 1100000000,spielerIndex,3, skinName);
			}else {
				players[spielerIndex-1] = new OnlinePlayer(spielerIndex, spielerIndex, 45, 45);
			}
		}
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.fillRect(0, 0, game.width, game.height);
		graphics.setFont(new Font(graphics.getFont().getFontName(), Font.PLAIN, 25));
		graphics.setColor(Color.YELLOW);
		graphics.drawString("Queue...", 350, 500);
	}

}