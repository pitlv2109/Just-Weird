package com.pitlv.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.pitlv.helpers.AssetLoader;
import com.pitlv.objects.Board;
import com.pitlv.objects.Duck;
import com.pitlv.objects.StickMan;

public class GameWorld {
	
	public enum GameState {
		READY, RUNNING, GAMEOVER
	}
	
	private GameState currentState;
	
	//objects
	private StickMan stickMan;
	private Array<Board> board;
	private Array<Duck> duck;
	
	//screen
	private float width;
	private float height;
	
	private int score;
	private float random;
	private static float pace = (float) (Gdx.graphics.getWidth()/190.0);
	
	public GameWorld(float width, float height) {
		currentState = GameState.READY;
		
		
		random = MathUtils.random();
		this.width = width;
		this.height = height;
		
		score = 0;
		
		board = new Array<Board>();
		duck = new Array<Duck>();
		
		//adding board
		board.add(new Board(width, height/2, width/10, height/30));
		
		for (int i = 1; i < 5; i++)
			board.add(new Board(width + i*width/5, MathUtils.random(height/3, 2*height/3), width/10, height/30));
		
		stickMan = new StickMan (width/2, width/2 + board.get(0).getPosY() + board.get(0).getHeight(), width/10, height/7);
		

		
		//adding ducks
		for (int i = 0; i < 2; i++) {
			duck.add(new Duck (board.get(i*2 + 1).getPosX() + board.get(i*2 + 1).getWidth()/4,
					board.get(i*2 + 1).getPosY() + board.get(i*2 + 1).getHeight(),
					width/15, height/12));

			
		}
	}
	
	public void updateRunning(float delta, boolean onTop, float yOnTop, boolean isAlive) {

		// Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
		if (delta > .15f) {
            delta = .15f;
        }
		
		if (!isAlive) {
			if (stickMan.getY() + stickMan.getHeight() > 0) {
				if (stickMan.getY() + stickMan.getHeight() >= height && !stickMan.getJumToFar()) {
					stickMan.setY(height);
					stickMan.setJumpToFar(true);
				}
				stickMan.setY(stickMan.getY() - height/40);
			}
			else {
				currentState = GameState.GAMEOVER;
			}
			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
			}
		}
		
		//still alive
		else { 
			for (int i = 0; i < board.size; i++)
				board.get(i).update(delta);
		
			for (int i = 0; i < duck.size; i++)
				duck.get(i).update(board);
		
			stickMan.update(delta, onTop, yOnTop);
		}
	}
	
	public void update (float delta, boolean onTop, float yOnTop, boolean dead) {
		
		switch (currentState) {
		
		case READY:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta, onTop, yOnTop, dead);
			break;
		case GAMEOVER:
			stickMan.update(delta, onTop, yOnTop);
			break;
		default:
			break;
		}
	}
	
	private void updateReady (float delta) {
		//DO NOTHING FOR NOW
	}
	
	public StickMan getStickMan() {
		return stickMan;
	}
	
	public Array<Board> getBoard() {
		return board;
	}
	
	public Array<Duck> getDuck() {
		return duck;
	}
	
	public int getScore() {
		return score;
	}

	public void addScore() {
		score++;
	}
	
	public boolean isReady() {
		return currentState == GameState.READY;
	}
	
	public void start() {
		currentState = GameState.RUNNING;
	}
	
	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}
	
	public float getRandom() {
		return random;
	}
	
	public static float getPace() {
		return pace;
	}
	

	public void restart() {
		currentState = GameState.RUNNING;
		score = 0;
		stickMan.setIsAlive(true);
		stickMan.setFirstTime(true);
		stickMan.setFirstJump(true);
		stickMan.setJumpToFar(false);
		stickMan.setJump(false);
		stickMan.setFirstDie(false);
		stickMan.setPressTime(0);
		stickMan.setGravity(0);
		stickMan.setVelocity(0);
		stickMan.setCount(0);
		random = MathUtils.random();
		
		//boards' update
		board.clear();
		//adding board 1
		//adding board
		board.add(new Board(width, height/2, width/10, height/30));
				
		for (int i = 1; i < 5; i++)
			board.add(new Board(width + i*width/5, MathUtils.random(height/3, 2*height/3), width/10, height/30));

		//stick man's update
		stickMan.setX(width/2);
		stickMan.setY(width/2 + board.get(0).getPosY() + board.get(0).getHeight());
		
		//ducks' update
		duck.clear();
		for (int i = 0; i < 2; i++) {
			duck.add(new Duck (board.get(i*2 + 1).getPosX() + board.get(i*2 + 1).getWidth()/4,
					board.get(i*2 + 1).getPosY() + board.get(i*2 + 1).getHeight(),
					width/15, height/12));
		}
	}
}
