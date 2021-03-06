package wolf.playingwithfire3.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static int width = 45, height = 45;
	
	public static BufferedImage whiteDog, yellowDog, yellowDogFace, whiteDogFace;
	public static BufferedImage purpleProfile,blueProfile,greenProfile,redProfile;
	public static BufferedImage floor, box, wall;
	public static BufferedImage speedTile, bombAmount,bombRange;
	public static BufferedImage blueBomb, redBomb, purpleBomb, greenBomb, bombExplosion;
	public static BufferedImage logo, redheart,greenheart,purpleheart,blueheart;
	public static BufferedImage skull;
	public static BufferedImage cup;
	public static BufferedImage[] test_bluePlayer_down;
	public static BufferedImage[] test_bluePlayer_right;
	public static BufferedImage[] test_bluePlayer_left;
	public static BufferedImage[] test_bluePlayer_up;
	
	public static BufferedImage[] test_redPlayer_down;
	public static BufferedImage[] test_redPlayer_right;
	public static BufferedImage[] test_redPlayer_left;
	public static BufferedImage[] test_redPlayer_up;
	
	public static BufferedImage[] test_purplePlayer_down;
	public static BufferedImage[] test_purplePlayer_right;
	public static BufferedImage[] test_purplePlayer_left;
	public static BufferedImage[] test_purplePlayer_up;
	
	public static BufferedImage[] test_greenPlayer_down;
	public static BufferedImage[] test_greenPlayer_right;
	public static BufferedImage[] test_greenPlayer_left;
	public static BufferedImage[] test_greenPlayer_up;
	
	public static BufferedImage[] default_bluePlayer_down;
	public static BufferedImage[] default_bluePlayer_right;
	public static BufferedImage[] default_bluePlayer_left;
	public static BufferedImage[] default_bluePlayer_up;
	
	public static BufferedImage[] default_redPlayer_down;
	public static BufferedImage[] default_redPlayer_right;
	public static BufferedImage[] default_redPlayer_left;
	public static BufferedImage[] default_redPlayer_up;
	
	public static BufferedImage[] default_purplePlayer_down;
	public static BufferedImage[] default_purplePlayer_right;
	public static BufferedImage[] default_purplePlayer_left;
	public static BufferedImage[] default_purplePlayer_up;
	
	public static BufferedImage[] default_greenPlayer_down;
	public static BufferedImage[] default_greenPlayer_right;
	public static BufferedImage[] default_greenPlayer_left;
	public static BufferedImage[] default_greenPlayer_up;
	
	public static BufferedImage[] btn_start;
	
	public static BufferedImage background;
	public static BufferedImage playerAmount;
	public static BufferedImage opponentAmount;
	public static BufferedImage level;
	public static BufferedImage gameGUI;
	public boolean test;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/spritesheet.png"));
		SpriteSheet spritesheet = new SpriteSheet(ImageLoader.loadImage("res/textures/sheet.png"));
		SpriteSheet heartsheet = new SpriteSheet(ImageLoader.loadImage("res/textures/heartssheet.png"));
		SpriteSheet background_sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/gfll.png"));
		SpriteSheet profilesheet = new SpriteSheet(ImageLoader.loadImage("res/textures/profile.png"));
		SpriteSheet playerssheet = new SpriteSheet(ImageLoader.loadImage("res/textures/sheetplayers.png"));
		SpriteSheet gamesheet = new SpriteSheet(ImageLoader.loadImage("res/textures/gameGUI.png"));
		SpriteSheet playerAmountsheet = new SpriteSheet(ImageLoader.loadImage("res/textures/playersssss.png"));
		SpriteSheet opponentAmountsheet = new SpriteSheet(ImageLoader.loadImage("res/textures/opponnetn.png"));
		SpriteSheet levelsheet = new SpriteSheet(ImageLoader.loadImage("res/textures/levebells.png"));
		
		
		//test
		whiteDog = sheet.crop(0, 2*height, width, height);
		yellowDog = sheet.crop(0, height, width, height);
		whiteDogFace = sheet.crop(3*width, 0, width, height);
		yellowDogFace = sheet.crop(width, height, width, height);
		//default
		purpleProfile = profilesheet.crop(0, 0, 75, 74);
		blueProfile = profilesheet.crop(2*width, 0, 75, 74);
		greenProfile = profilesheet.crop(4*width, 0, 75, 74);
		redProfile = profilesheet.crop(6*width, 0, 75, 74);
		skull = sheet.crop(0, 4*width, width, height);
		
		floor = sheet.crop(0, 0, width, height);
		box = sheet.crop(width, 0, width, height);
		wall = sheet.crop(2*width, 0, width, height);
		
		speedTile = sheet.crop(3*width, 4*height, width, height);
		bombAmount = sheet.crop(2*width,4*height,width,height);
		bombRange = sheet.crop(width,4*height,width,height);
		
		cup = sheet.crop(3, 271, 157, 425-270);
		//button
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(180, 225, 270, 45);
		btn_start[1] = sheet.crop(180, 270, 270, 45);
		//background
		background = background_sheet.crop(0,0,820,800);
		playerAmount = playerAmountsheet.crop(0, 0, 820, 800);
		opponentAmount = opponentAmountsheet.crop(0, 0, 820, 800);
		level = levelsheet.crop(0, 0, 820, 800);
		gameGUI = gamesheet.crop(0,0,820,800);
		//bombs and explosions
		blueBomb = spritesheet.crop(0*width, 0*height, width, height);
		redBomb = spritesheet.crop(2*width, 0*height, width, height);
		purpleBomb = spritesheet.crop(3*width, 0*height, width, height);
		greenBomb = spritesheet.crop(1*width, 0*height, width, height);
		bombExplosion = sheet.crop(3*width, height, width, height);
		
		logo = sheet.crop(width, 2* height, 116, 87);
		//hearts
		redheart = heartsheet.crop(1,2, 26, 26);
		greenheart = heartsheet.crop(55, 2,26, 26);
		purpleheart = heartsheet.crop(27, 2,26, 26);
		blueheart = heartsheet.crop(82, 2, 26, 26);
		//players test
		test_bluePlayer_down = new BufferedImage[2];
		test_bluePlayer_up = new BufferedImage[2];
		test_bluePlayer_right = new BufferedImage[1];
		test_bluePlayer_left = new BufferedImage[1];
		
		test_bluePlayer_down[0] = sheet.crop(width * 4, height * 0, width, height);
		test_bluePlayer_down[1] = sheet.crop(width * 5, height * 0, width, height);
		test_bluePlayer_up[0] = sheet.crop(width * 6, height * 0, width, height);
		test_bluePlayer_up[1] = sheet.crop(width * 7, height * 0, width, height);
		test_bluePlayer_left[0] = sheet.crop(width * 8, height * 0, width, height);
		test_bluePlayer_right[0] = sheet.crop(width * 9, height * 0, width, height);
		
		test_redPlayer_down = new BufferedImage[2];
		test_redPlayer_up = new BufferedImage[2];
		test_redPlayer_right = new BufferedImage[1];
		test_redPlayer_left = new BufferedImage[1];
		
		test_redPlayer_down[0] = sheet.crop(width * 4, height * 1, width, height);
		test_redPlayer_down[1] = sheet.crop(width * 5, height * 1, width, height);
		test_redPlayer_up[0] = sheet.crop(width * 6, height * 1, width, height);
		test_redPlayer_up[1] = sheet.crop(width * 7, height * 1, width, height);
		test_redPlayer_left[0] = sheet.crop(width * 8, height * 1, width, height);
		test_redPlayer_right[0] = sheet.crop(width * 9, height * 1, width, height);

		test_purplePlayer_down = new BufferedImage[2];
		test_purplePlayer_up = new BufferedImage[2];
		test_purplePlayer_right = new BufferedImage[1];
		test_purplePlayer_left = new BufferedImage[1];
		
		test_purplePlayer_down[0] = sheet.crop(width * 4, height * 2, width, height);
		test_purplePlayer_down[1] = sheet.crop(width * 5, height * 2, width, height);
		test_purplePlayer_up[0] = sheet.crop(width * 6, height * 2, width, height);
		test_purplePlayer_up[1] = sheet.crop(width * 7, height * 2, width, height);
		test_purplePlayer_left[0] = sheet.crop(width * 8, height * 2, width, height);
		test_purplePlayer_right[0] = sheet.crop(width * 9, height * 2, width, height);
		
		test_greenPlayer_down = new BufferedImage[2];
		test_greenPlayer_up = new BufferedImage[2];
		test_greenPlayer_right = new BufferedImage[1];
		test_greenPlayer_left = new BufferedImage[1];
		
		test_greenPlayer_down[0] = sheet.crop(width * 4, height * 3, width, height);
		test_greenPlayer_down[1] = sheet.crop(width * 5, height * 3, width, height);
		test_greenPlayer_up[0] = sheet.crop(width * 6, height * 3, width, height);
		test_greenPlayer_up[1] = sheet.crop(width * 7, height * 3, width, height);
		test_greenPlayer_left[0] = sheet.crop(width * 8, height * 3, width, height);
		test_greenPlayer_right[0] = sheet.crop(width * 9, height * 3, width, height);
		
		
		default_bluePlayer_down = new BufferedImage[2];
		default_bluePlayer_up = new BufferedImage[2];
		default_bluePlayer_right = new BufferedImage[2];
		default_bluePlayer_left = new BufferedImage[2];
		
		default_bluePlayer_down[0] = playerssheet.crop(width * 5, height * 1, width, height);
		default_bluePlayer_down[1] = playerssheet.crop(width * 6, height * 1, width, height);
		default_bluePlayer_up[0] = playerssheet.crop(width * 7, height * 1, width, height);
		default_bluePlayer_up[1] = playerssheet.crop(width * 8, height * 1, width, height);
		default_bluePlayer_left[0] = playerssheet.crop(width * 3, height * 1, width, height);
		default_bluePlayer_left[1] = playerssheet.crop(width * 4, height * 1, width, height);
		default_bluePlayer_right[0] = playerssheet.crop(width * 1, height * 1, width, height);
		default_bluePlayer_right[1] = playerssheet.crop(width * 2, height * 1, width, height);
		
		default_redPlayer_down = new BufferedImage[2];
		default_redPlayer_up = new BufferedImage[2];
		default_redPlayer_right = new BufferedImage[2];
		default_redPlayer_left = new BufferedImage[2];
		
		default_redPlayer_down[0] = playerssheet.crop(width * 5, height * 3, width,height);
		default_redPlayer_down[1] = playerssheet.crop(width * 6, height * 3, width,height);
		default_redPlayer_up[0] = playerssheet.crop(width * 7, height * 3, width,height);
		default_redPlayer_up[1] = playerssheet.crop(width * 8, height * 3, width,height);
		default_redPlayer_left[0] = playerssheet.crop(width * 3, height * 3, width,height);
		default_redPlayer_left[1] = playerssheet.crop(width * 4, height * 3, width,height);
		default_redPlayer_right[0] = playerssheet.crop(width * 1, height * 3, width,height);
		default_redPlayer_right[1] = playerssheet.crop(width * 2, height * 3,width,height );


		default_purplePlayer_down = new BufferedImage[2];
		default_purplePlayer_up = new BufferedImage[2];
		default_purplePlayer_right = new BufferedImage[2];
		default_purplePlayer_left = new BufferedImage[2];
		
		default_purplePlayer_down[0] = playerssheet.crop(width * 5, 0, width,height);
		default_purplePlayer_down[1] = playerssheet.crop(width * 6, 0, width,height);
		default_purplePlayer_up[0] = playerssheet.crop(width * 7, 0, width,height);
		default_purplePlayer_up[1] = playerssheet.crop(width * 8, 0, width,height);
		default_purplePlayer_left[0] = playerssheet.crop(width * 3, 0, width,height);
		default_purplePlayer_left[1] = playerssheet.crop(width * 4, 0, width,height);
		default_purplePlayer_right[0] = playerssheet.crop(width * 1, 0, width,height);
		default_purplePlayer_right[1] = playerssheet.crop(width * 2, 0, width,height);
		
		default_greenPlayer_down = new BufferedImage[2];
		default_greenPlayer_up = new BufferedImage[2];
		default_greenPlayer_right = new BufferedImage[2];
		default_greenPlayer_left = new BufferedImage[2];
		
		default_greenPlayer_down[0] = playerssheet.crop(width * 5, height * 2, width,height);
		default_greenPlayer_down[1] = playerssheet.crop(width * 6, height * 2, width,height);
		default_greenPlayer_up[0] = playerssheet.crop(width * 7, height * 2, width,height);
		default_greenPlayer_up[1] = playerssheet.crop(width * 8, height * 2, width,height);
		default_greenPlayer_left[0] = playerssheet.crop(width * 3, height * 2, width,height);
		default_greenPlayer_left[1] = playerssheet.crop(width * 4, height * 2, width,height);
		default_greenPlayer_right[0] = playerssheet.crop(width * 1, height * 2, width,height);
		default_greenPlayer_right[1] = playerssheet.crop(width * 2, height * 2, width,height);
		
		
		
		//testend
	}
	
}
