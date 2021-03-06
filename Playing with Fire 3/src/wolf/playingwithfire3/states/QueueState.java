package wolf.playingwithfire3.states;

// Leon, Armin, Alex, Yakob

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import wolf.playingwithfire3.Game;
import wolf.playingwithfire3.entities.BombsManager;
import wolf.playingwithfire3.entities.LocalPlayer;
import wolf.playingwithfire3.entities.OnlinePlayer;
import wolf.playingwithfire3.entities.Player;
import wolf.playingwithfire3.online.Client;
import wolf.playingwithfire3.tile.Tile;
import wolf.playingwithfire3.worlds.World;

public class QueueState extends State{

	private Client client;
	private Game game;
	private BombsManager bombsManager;
	private Player[] players;
	private World world;
	private boolean lastSeconds;
	private StateManager stateManager;
	
	
	
	public QueueState(Game game, StateManager stateManager) {
		super(game);
		// TODO Auto-generated constructor stub
		
		this.game = game;
		this.world = new World("res/worlds/world2.txt");
		this.bombsManager = new BombsManager(world.getWidth(), world.getHeight(),1.5f,1.0f,game.getFps(),world);;
		this.players = new Player[4];
		
		client = new Client(this);
		world.setClient(client);
		client.sendPlayerInfos();
		this.stateManager = stateManager;
		for(int i = 1;i<5;i++) {
			System.out.println(world.getSpawnX(i)*Tile.TILEWIDTH+SettingState.xOffset);
			System.out.println(world.getSpawnY(i)*Tile.TILEHEIGHT+SettingState.yOffset);
		}
	}

	public void joinPlayer(int x,int y,int health,String playerID, String skinName,int spielerIndex) {
		if(players[spielerIndex-1]==null) {
			if(playerID.equals(client.getOwnPlayerId())) {
				players[spielerIndex-1] = new LocalPlayer(world, game, bombsManager, 1100000000,spielerIndex,3, skinName,true,client);
			}else {
				players[spielerIndex-1] = new OnlinePlayer(spielerIndex, spielerIndex, 45, 45,spielerIndex,skinName,world,client,bombsManager);
			}
		}
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setLastSeconds(boolean value) {
		lastSeconds = value;
	}
	
	public Player[] startGame() {
		stateManager.setState(new GameState(game, players, "world2", bombsManager, world,stateManager));
		System.out.println("----------------------");
		System.out.println(players[0]);
		System.out.println(players[1]);
		System.out.println(players[2]);
		System.out.println(players[3]);
		System.out.println("--------------------------");
		return players;
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
		if(lastSeconds) {
			graphics.drawString("Starting...", 350, 500);
		}else {
			graphics.drawString("Queue...", 350, 500);
		}
		
		
	}

}
