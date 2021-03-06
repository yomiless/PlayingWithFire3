package wolf.playingwithfire3.entities;

// Armin

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import wolf.playingwithfire3.gfx.Assets;
import wolf.playingwithfire3.sound.AudioPlayer;
import wolf.playingwithfire3.states.SettingState;
import wolf.playingwithfire3.tile.Tile;
import wolf.playingwithfire3.worlds.World;

public class Bomb extends Entity{

	private float bombDuration;
	private float explosionDuration;
	private int fps;
	private int tickCounter;
	private BombsManager bombsManager;
	private boolean explosion;
	private int range;
	private World world;
	private int[] toDestroy = new int[4];
	private Player owner;
	private BufferedImage bombPicture;
	private boolean isOnline;
	
	
	public Bomb(float x, float y, int width, int height, float bombDuration, float explosionDuration,int range,int fps, BombsManager bombsManager, World world, Player owner, boolean isOnline) {
		super(x, y, width, height);
		this.bombDuration = bombDuration;
		this.fps = fps;
		this.tickCounter = 0;
		this.bombsManager = bombsManager;
		this.range = range;
		this.world = world;
		this.explosionDuration = explosionDuration;
		this.owner = owner;
		this.isOnline = isOnline;
		chooseBombPicture(owner.getPlayerNumber());
	}
	
	public void chooseBombPicture(int playerNumber) {
		switch(playerNumber) {
		case 1:
			bombPicture = Assets.blueBomb;
			break;
		case 2:
			bombPicture = Assets.redBomb;
			break;
		case 3:
			bombPicture = Assets.purpleBomb;
			break;
		case 4:
			bombPicture = Assets.greenBomb;
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(!explosion) {
			tickCounter++;
			if(tickCounter/fps>=bombDuration) {
				System.out.println("booom!");
				tickCounter = 0;
				explode();
			}
		}else {
			tickCounter++;
			if(tickCounter/fps>=explosionDuration) {
				bombsManager.endExplosion((int)(x-SettingState.xOffset)/Tile.TILEWIDTH, (int) (y-SettingState.yOffset)/Tile.TILEHEIGHT, isOnline);
			}
		}
	}
	
	public void explode() {
		explosion = true; 
		tickCounter = 0;
		for(int i = 1;i<range;i++) {
			if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isSolid()) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isDestructible())
					toDestroy[0]=(int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i;
				break;	
			}	
			bombsManager.sendOn((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT);
		}
		for(int i = 1;i<range;i++) {
			if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isSolid()) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isDestructible())
					toDestroy[1]=(int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i;
				break;
			}
			bombsManager.sendOn((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT);
		}
		for(int i = 1;i<range;i++) {
			if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i).isSolid()) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i).isDestructible())
					toDestroy[2]=(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i;
				break;
			}
			bombsManager.sendOn((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i);
		}
		for(int i = 1;i<range;i++) {
			if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i).isSolid()) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i).isDestructible())
					toDestroy[3] = (int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i;
				break;
			}
			bombsManager.sendOn((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i);
		}
		
		owner.setBombAmount(owner.getBombAmount()+1);
		AudioPlayer.playExplosionSound();
		
	}
	
	public int[] getToDestroy() {
		return toDestroy;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public boolean isExploded() {
		return explosion;
	}
	
	@Override
	public void render(Graphics graphics) {
		if(!explosion) {
			graphics.drawImage(bombPicture,(int) x,(int) y, null);
			bombsManager.setExplosionsFuture((int) (x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT, true);
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isSolid()) {
					break;	
				}
				bombsManager.setExplosionsFuture((int) (x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT, true);
				if((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i==toDestroy[0])
					break;
			}
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isSolid()) {
					break;
				}
				bombsManager.setExplosionsFuture((int) (x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT, true);
				if((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i==toDestroy[1])
					break;
			}
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i).isSolid()) {
					break;
				}
				bombsManager.setExplosionsFuture((int) (x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i, true);
				if((int)(y-SettingState.yOffset)/Tile.TILEHEIGHT+i==toDestroy[2])
					break;
			}
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i).isSolid()) {
					break;
				}
				bombsManager.setExplosionsFuture((int) (x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i, true);
				if((int)(y-SettingState.yOffset)/Tile.TILEHEIGHT-i==toDestroy[3])
					break;
			}
		}else {
			graphics.drawImage(Assets.bombExplosion,(int) x,(int) y, null);
			bombsManager.setExplosions((int) (x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT, true);
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isSolid()) {
					if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isDestructible()) {
						graphics.drawImage(Assets.bombExplosion,(int) x+i*Tile.TILEWIDTH,(int) y, null);;
					}
					break;	
				}
				graphics.drawImage(Assets.bombExplosion,(int) x+i*Tile.TILEWIDTH,(int) y, null);
				bombsManager.setExplosions((int) (x-SettingState.xOffset)/Tile.TILEWIDTH+i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT, true);
				if((int)(x-SettingState.xOffset)/Tile.TILEWIDTH+i==toDestroy[0])
					break;
			}
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isSolid()) {
					if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT).isDestructible()) {
						graphics.drawImage(Assets.bombExplosion,(int) x-i*Tile.TILEWIDTH,(int) y, null);
					}
					break;
				}
				graphics.drawImage(Assets.bombExplosion,(int) x-i*Tile.TILEWIDTH,(int) y, null);
				bombsManager.setExplosions((int) (x-SettingState.xOffset)/Tile.TILEWIDTH-i,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT, true);
				if((int)(x-SettingState.xOffset)/Tile.TILEWIDTH-i==toDestroy[1])
					break;
			}
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i).isSolid()) {
					if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i).isDestructible()) {
						graphics.drawImage(Assets.bombExplosion,(int) x,(int) y+i*Tile.TILEHEIGHT, null);
					}
					break;
				}
				graphics.drawImage(Assets.bombExplosion,(int) x,(int) y+i*Tile.TILEHEIGHT, null);
				bombsManager.setExplosions((int) (x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT+i, true);
				if((int)(y-SettingState.yOffset)/Tile.TILEHEIGHT+i==toDestroy[2])
					break;
			}
			for(int i = 1;i<range;i++) {
				if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i).isSolid()) {
					if(world.getTile((int)(x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i).isDestructible()) {
						graphics.drawImage(Assets.bombExplosion,(int) x,(int) y-i*Tile.TILEHEIGHT, null);
					}
					break;
				}
				graphics.drawImage(Assets.bombExplosion,(int) x,(int) y-i*Tile.TILEHEIGHT, null);
				bombsManager.setExplosions((int) (x-SettingState.xOffset)/Tile.TILEWIDTH,(int) (y-SettingState.yOffset)/Tile.TILEHEIGHT-i, true);
				if((int)(y-SettingState.yOffset)/Tile.TILEHEIGHT-i==toDestroy[3])
					break;
			}
		}
	}
	
}
