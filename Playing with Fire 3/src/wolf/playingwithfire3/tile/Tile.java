package wolf.playingwithfire3.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile floorTile = new FloorTile(0);
	public static Tile boxTile = new BoxTile(1);
	public static Tile wallTile = new WallTile(2);
	public static Tile speedTile = new SpeedTile(3);
	public static Tile bombAmount = new BombAmount(4);
	public static Tile bombRange = new BombRange(5);
	
	
	public static int TILEWIDTH = 45, TILEHEIGHT = 45;
	
	protected BufferedImage texture;
	protected int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics graphics, int x, int y) {
		graphics.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT,null);
	}
	
	public boolean isSolid() {
		return false;
	}
	public boolean isDestructible() {
		return false;
	}
	
	public int getId() {
		return id;
	}
}
