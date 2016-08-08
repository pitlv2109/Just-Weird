package com.pitlv.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	//store score
	public static Preferences prefs;
	
	//images
	public static Texture mrFront, mrRight, mrLeft, mrBehind;
	public static TextureRegion RmrFront, RmrRight, RmrLeft, RmrBehind;
	public static Texture bg, bg2, board, wood, duck, instruct, title, pitlv;

	public static Animation mushRoom;
	
	//sounds
	public static Sound jump, hit, coin;
	public static Sound ohman, ohman2, ohno, ohoh, saddie, yousuck;
	public static Sound[] sound;
	
	//font
	public static BitmapFont font, shadow;
	
	public static void load() {
		
		//stores score
		prefs = Gdx.app.getPreferences("Just Weird");
		
		//Provides default high score of 0
		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
		
		//images
		mrFront = new Texture(Gdx.files.internal("images/mrfront.png"));
		mrBehind = new Texture(Gdx.files.internal("images/mrbehind.png"));
		mrLeft = new Texture(Gdx.files.internal("images/mrleft.png"));
		mrRight = new Texture(Gdx.files.internal("images/mrright.png"));

		RmrFront = new TextureRegion(mrFront);
		RmrRight = new TextureRegion(mrRight);
		RmrLeft = new TextureRegion(mrLeft);
		RmrBehind = new TextureRegion(mrBehind);

		bg = new Texture(Gdx.files.internal("images/bg.png"));
		bg2 = new Texture(Gdx.files.internal("images/bg2.png"));
		board = new Texture(Gdx.files.internal("images/board.png"));
		wood = new Texture(Gdx.files.internal("images/wood.png"));
		duck = new Texture(Gdx.files.internal("images/duck.png"));
		instruct = new Texture(Gdx.files.internal("images/instruct.png"));
		title = new Texture(Gdx.files.internal("images/title.png"));
		pitlv = new Texture(Gdx.files.internal("images/pitlv.png"));
		
		//animations
		TextureRegion[] mr = {RmrRight, RmrBehind, RmrLeft, RmrFront};
		mushRoom = new Animation(0.06f, mr);
		mushRoom.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		//sounds
		jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));
		hit = Gdx.audio.newSound(Gdx.files.internal("sounds/hit.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
		
		sound = new Sound[6];
		
		ohman = Gdx.audio.newSound(Gdx.files.internal("sounds/ohman.mp3"));
		ohman2 = Gdx.audio.newSound(Gdx.files.internal("sounds/ohman2.mp3"));
		ohno = Gdx.audio.newSound(Gdx.files.internal("sounds/ohno.mp3"));
		ohoh = Gdx.audio.newSound(Gdx.files.internal("sounds/ohoh.mp3"));
		saddie = Gdx.audio.newSound(Gdx.files.internal("sounds/saddie.mp3"));
		yousuck = Gdx.audio.newSound(Gdx.files.internal("sounds/yousucks.mp3"));
		
		sound[0] = ohman;
		sound[1] = ohman2;
		sound[2] = ohno;
		sound[3] = ohoh;
		sound[4] = saddie;
		sound[5] = yousuck;
	
		//font
		font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
		font.setScale(Gdx.graphics.getHeight()/768f, Gdx.graphics.getHeight()/768f);
		shadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
		shadow.setScale(Gdx.graphics.getHeight()/768f, Gdx.graphics.getHeight()/768f);
	}
	
	public static void dispose() {
		bg.dispose();
		font.dispose();
		shadow.dispose();
		jump.dispose();
	}
	
	public static void setHighScore (int score) {
		prefs.putInteger("highScore", score);
		prefs.flush();
	}
	
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}


}
