package com.pitlv.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.pitlv.helpers.AssetLoader;
import com.pitlv.objects.Board;
import com.pitlv.objects.Duck;
import com.pitlv.objects.StickMan;

public class GameRenderer {

	private GameWorld myWorld;
	
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;

	
	float width;
	float height;
	float bgScroll;
	
	//Game objects
	private StickMan stickMan;
	private Array<Board> board;
	private Array<Duck> duck;
	
	//Game Assets
	private Texture bg, bg2, imgBoard, imgWood, imgDuck, instruct, title, pitlv;
	private TextureRegion RmrRight;
	private Animation mushRoom;
	
	public GameRenderer (GameWorld world, float width, float height) {
		myWorld = world;
		gameObjects();
		gameAssets();
		
		this.width = width;
		this.height = height;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		
		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}
	
	public void render(float runTime) {
		
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        //Begin batcher
        batcher.begin();
       
        drawBackground();

        // TEMPORARY CODE! We will fix this section later
		
        if (myWorld.isReady()) {
        
        	drawTitle();
            
        	AssetLoader.font.setColor(Color.WHITE);
            AssetLoader.shadow.draw(batcher, "Touch to play", width/4, height/3);
            AssetLoader.font.draw(batcher, "Touch to play", width/4 - 1, height/3);
            
            
        } 
        
        else {
        	if (stickMan.getFirstJump() && stickMan.isAlive())
        		drawInstruct();

        	if (myWorld.getStickMan().isAlive()) {
        		drawBoard();
        
    			drawDuck();
    			
    			//Score
    	        // Draw shadow score first
    	        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), width/2, height - height/15);
    	        // Draw text score
    	        AssetLoader.font.draw(batcher, "" + myWorld.getScore(), width/2 - 1, height - height/15 - 1);
    	       
    	        
        	}
       
    		drawStickMan(runTime);
    		
        	if (myWorld.isGameOver()) {
        		
        		//show latest score
        		AssetLoader.font.setColor(Color.CYAN);
                AssetLoader.shadow.draw(batcher, "Your score: " + myWorld.getScore(), width/4, height - height/6);
                AssetLoader.font.draw(batcher, "Your score: " + myWorld.getScore(), width/4 - 1, height - height/6);
                
                AssetLoader.font.setColor(Color.RED);
                //show highest score
                AssetLoader.shadow.draw(batcher, "HIGHEST SCORE: " + AssetLoader.getHighScore(), width/4, height - height/3);
                AssetLoader.font.draw(batcher, "HIGHEST SCORE: " + AssetLoader.getHighScore(), width/4 - 1, height - height/3);

                //touch to play
        		AssetLoader.font.setColor(Color.WHITE);
                AssetLoader.shadow.draw(batcher, "Touch to play", width/4, height/3);
                AssetLoader.font.draw(batcher, "Touch to play", width/4 - 1, height/3);
        		
            } 
        }
        
        batcher.end();
	}
	
	private void gameObjects() {
		stickMan = myWorld.getStickMan();
		board = myWorld.getBoard();
		duck = myWorld.getDuck();
	}
	
	private void gameAssets() {
		bg = AssetLoader.bg;
		bg2 = AssetLoader.bg2;
		imgBoard = AssetLoader.board;
		imgWood = AssetLoader.wood;
		imgDuck = AssetLoader.duck;
		instruct = AssetLoader.instruct;
		RmrRight = AssetLoader.RmrRight;
		mushRoom = AssetLoader.mushRoom;
		title = AssetLoader.title;
		pitlv = AssetLoader.pitlv;
	}
	
	private void drawBackground() {
		
		if (0 - bgScroll < -1*width)
        	bgScroll = 0;
		if (myWorld.getRandom() > 0.5) {
			batcher.draw(bg, 0 - bgScroll, 0, width, height);
			batcher.draw(bg, 0 - bgScroll + width, 0, width, height);
		}
		else {
			batcher.draw(bg2, 0 - bgScroll, 0, width, height);
			batcher.draw(bg2, 0 - bgScroll + width, 0, width, height);
		}
        bgScroll += 0.2;
	}
	
	private void drawStickMan(float runTime) {
		//Draw stick man
        batcher.draw(RmrRight, stickMan.getX(), stickMan.getY(), stickMan.getWidth(), stickMan.getHeight());
        if (!stickMan.isJump()) {
     	   batcher.enableBlending();
     	   batcher.draw(mushRoom.getKeyFrame(runTime),stickMan.getX(), stickMan.getY(), stickMan.getWidth(), stickMan.getHeight());
        }
	}
	
	private void drawBoard() {
		if ((int)(myWorld.getRandom() * 10) % 2.0 == 0)
			for (int i = 0; i < board.size; i++)
	        	batcher.draw(imgBoard, board.get(i).getPosX(), board.get(i).getPosY(), board.get(i).getWidth(), board.get(i).getHeight() );
	
		else 
			for (int i = 0; i < board.size; i++)
        	batcher.draw(imgWood, board.get(i).getPosX(), board.get(i).getPosY(), board.get(i).getWidth(), board.get(i).getHeight() );
		
	}
	
	private void drawDuck() {
		for (int i = 0; i < duck.size; i++) {
			batcher.draw(imgDuck, duck.get(i).getPosX(), duck.get(i).getPosY(), duck.get(i).getWidth(), duck.get(i).getHeight());
		}
	}
	
	private void drawTitle() {
		batcher.draw(title, width/2 - width/3 , height - height/2 + height/10, width - width/3, height/3);
		batcher.draw(pitlv, width/2, height - height/2, width/7, height/10);
	}
	
	private void drawInstruct() {
		batcher.draw(instruct, stickMan.getX() + stickMan.getWidth(), stickMan.getY() + stickMan.getHeight() - stickMan.getHeight()/10, width/4, height/3);
	}
}
