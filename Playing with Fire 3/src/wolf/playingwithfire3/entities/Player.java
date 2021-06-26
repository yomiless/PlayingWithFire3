package wolf.playingwithfire3.entities;

import java.awt.Color;
import java.awt.Graphics;

import wolf.playingwithfire3.Game;
import wolf.playingwithfire3.gfx.Assets;
import wolf.playingwithfire3.tile.Tile;
import wolf.playingwithfire3.worlds.World;

public class Player extends Entity{
	
	public static int DEFAULT_HEALTH = 10;
	public static float DEFAULT_SPEED = 3.0f;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	private Game game;
	private World world;
	private BombsManager bombsManager;
	private long lastTimeDamage = -1;
	private long damageCooldown;
	
	public Player(World world,Game game, BombsManager bombsManager,float x, float y, long damageCooldown) {
		super(x, y, 45, 45);
		this.bombsManager = bombsManager;
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		this.game = game;
		this.world = world;
		
		bounds.x = 9;
		bounds.y = 16;
		bounds.width = 18;
		bounds.height = 31;
		
		this.damageCooldown = damageCooldown;
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return world.getTile(x, y).isSolid();
	}
	
	public boolean isPixelInExplosion(int x, int y) {
		return bombsManager.getExplosions(x/Tile.TILEWIDTH, y/Tile.TILEHEIGHT);
	}
	
	public void move() {
		moveX();
		movey();
	}
	
	public void moveX() {
		if(xMove > 0) {
			int tx = (int) (x+ xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y+bounds.y)/Tile.TILEHEIGHT)&&!collisionWithTile(tx, (int) (y+bounds.y+bounds.height)/Tile.TILEHEIGHT)) {
				x +=xMove;
			}else {
				x = tx*Tile.TILEWIDTH-bounds.x-bounds.width-1;
				
			}
		}else if(xMove < 0) {
			int tx = (int) (x+ xMove + bounds.x ) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y+bounds.y)/Tile.TILEHEIGHT)&&!collisionWithTile(tx, (int) (y+bounds.y+bounds.height)/Tile.TILEHEIGHT)) {
				x +=xMove;
			}
		}
	}

	public void movey() {
		// TODO Auto-generated method stub
		if(yMove < 0) {
			int ty = (int) (y +yMove + bounds.y) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x+bounds.x)/Tile.TILEWIDTH ,ty)&&!collisionWithTile((int) (x+bounds.x+bounds.width)/Tile.TILEWIDTH ,ty))
				y+=yMove;
		}else if(yMove > 0){
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x+bounds.x)/Tile.TILEWIDTH ,ty)&&!collisionWithTile((int) (x+bounds.x+bounds.width)/Tile.TILEWIDTH ,ty))
				y+=yMove;
		}
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	


	public void tick() {
		getInput();
		move();
		explosionDamage();
	}
	
	public void explosionDamage() {
		if(System.nanoTime()-lastTimeDamage>=damageCooldown||lastTimeDamage == -1) {
			//check if collision box in explosion
			if(isPixelInExplosion((int) x+bounds.x, (int) y+bounds.y)||isPixelInExplosion((int) x+bounds.x+bounds.width, (int) y+bounds.y)||isPixelInExplosion((int) x+bounds.x, (int) y+bounds.y+bounds.height)||isPixelInExplosion((int) x+bounds.x+bounds.width, (int) y+bounds.y+bounds.height)) {
				System.out.println("Damage!");
				health--;
				lastTimeDamage = System.nanoTime();
			}
			
		}
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up)
			yMove = -speed;
		if(game.getKeyManager().down)
			yMove = speed;
		if(game.getKeyManager().left)
			xMove = -speed;
		if(game.getKeyManager().right)
			xMove = speed;
		if(game.getKeyManager().setBomb)
			setBomb();

	}
	
	public void setBomb() {
		bombsManager.addBomb(getTilePositionX(), getTilePositionY());
	}
	
	public int getTilePositionX() {
		return ((int)x + bounds.x+(bounds.width/2))/Tile.TILEWIDTH;
	}
	
	public int getTilePositionY() {
		return ((int)y + bounds.y+(bounds.height/2))/Tile.TILEHEIGHT;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	


	public void render(Graphics graphics) {
		//test

		graphics.drawImage(Assets.yellowDog,(int) x,(int) y, null);
		graphics.setColor(Color.red);
		//graphics.fillRect((int) (x +bounds.x), (int) (y +bounds.y), bounds.width, bounds.height);



	}
}
