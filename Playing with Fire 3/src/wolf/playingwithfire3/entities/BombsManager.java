package wolf.playingwithfire3.entities;

// Armin

import java.awt.Graphics;

import wolf.playingwithfire3.states.SettingState;
import wolf.playingwithfire3.tile.Tile;
import wolf.playingwithfire3.worlds.World;

public class BombsManager {
	
	private int width, height;
	private float bombDuration;
	private float explosionDuration;
	private World world;
	private Bomb[][] bombs;
	private boolean[][] explosions;
	private boolean[][] futureExplosions;

	private int fps;
	
	public BombsManager(int width, int height, float bombDuration, float explosionDuration,int fps, World world) {
		this.width = width;
		this.height = height;
		this.bombDuration = bombDuration;
		this.explosionDuration=explosionDuration;
		this.fps = fps;
		this.bombs = new Bomb[width][height];
		this.explosions = new boolean[width][height];
		this.futureExplosions = new boolean[width][height];
		this.world = world;


	}
	
	public boolean addBomb(int x, int y, Player owner,int range, boolean isOnline) {
		System.out.println(x);
		if(bombs[x][y]==null) {
			bombs[x][y]=new Bomb(x*Tile.TILEWIDTH+SettingState.xOffset,y*Tile.TILEHEIGHT+SettingState.yOffset,Tile.TILEWIDTH,Tile.TILEHEIGHT,bombDuration,explosionDuration,range,fps,this,world, owner,isOnline);
			return true;
		}
		return false;
	}
	
	public void endExplosion(int x, int y, boolean isOnlinePlayer) {
		int[] toDestroy = bombs[x][y].getToDestroy();
		if(!isOnlinePlayer) {
			if(toDestroy[0]!=0)
				world.destroyBox(toDestroy[0], y);
			if(toDestroy[1]!=0)
				world.destroyBox(toDestroy[1], y);
			if(toDestroy[2]!=0)
				world.destroyBox(x, toDestroy[2]);
			if(toDestroy[3]!=0)
				world.destroyBox(x, toDestroy[3]);
		}else {
			if(toDestroy[0]!=0)
				world.setTile(toDestroy[0], y,0);
			if(toDestroy[1]!=0)
				world.setTile(toDestroy[1], y,0);
			if(toDestroy[2]!=0)
				world.setTile(x, toDestroy[2],0);
			if(toDestroy[3]!=0)
				world.setTile(x, toDestroy[3],0);
		}
		bombs[x][y]=null;
	}
	public void sendOn(int x, int y) {
		if(bombs[x][y]!=null&&!bombs[x][y].isExploded()) {
			bombs[x][y].explode();
		}
			
	}
	public void setExplosions(int x, int y, boolean value) {
		explosions[x][y] = value;
	}
	
	public void setExplosionsFuture(int x, int y, boolean value) {
		futureExplosions[x][y] = value;
	}
	
	public boolean getExplosionFuture(int x, int y) {
		return futureExplosions[x][y];
	}
	
	public boolean getExplosions(int x, int y) {
		return explosions[x][y];
	}
	
	public boolean isSolid(int x, int y) {
		return bombs[x][y] != null && !bombs[x][y].isExploded();
	}
	public void tick() {
		for(int y = 0;y<height;y++) {
			for(int x = 0;x<width;x++) {
			if(bombs[x][y]!=null)
				bombs[x][y].tick();
			}			
		}
	}
	
	public void render(Graphics graphics) {
		this.explosions = new boolean[width][height];
		this.futureExplosions = new boolean[width][height];
		for(int y = 0;y<height;y++) {
			for(int x = 0;x<width;x++) {
			if(bombs[x][y]!=null) 
				bombs[x][y].render(graphics);
			}
		}
	}
	
}
