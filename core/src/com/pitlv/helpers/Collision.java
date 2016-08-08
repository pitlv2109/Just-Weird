package com.pitlv.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.pitlv.gameworld.GameWorld;
import com.pitlv.objects.Board;
import com.pitlv.objects.Duck;
import com.pitlv.objects.StickMan;

public class Collision {
	
	private GameWorld gameWorld;
	
	private StickMan stickMan;
	private Array<Board> board;
	private Array<Duck> duck;
	
	private boolean onTop;
	private float yOnTop;
	
	private float boardHeight;
	private float boardWidth;
	private float smWidth;
	
	public Collision(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		
		stickMan = gameWorld.getStickMan();
		board = gameWorld.getBoard();
		duck = gameWorld.getDuck();
	
		
		boardHeight = gameWorld.getBoard().get(0).getHeight();
		boardWidth = gameWorld.getBoard().get(0).getWidth();
		smWidth = gameWorld.getStickMan().getWidth();
		
		yOnTop = 0;
	}
	
	public void onTop() {
		
		if (stickMan.isAlive() && stickMan.getY() != yOnTop) {
			
			for (int i = 0; i < board.size; i++) {
					if (   ((stickMan.getX() + smWidth/3 >= board.get(i).getPosX()
						 && stickMan.getX() + smWidth/3 <= board.get(i).getPosX() + boardWidth)
						 
						 || (stickMan.getX() + smWidth - smWidth/3 >= board.get(i).getPosX()
						 && stickMan.getX() + smWidth - smWidth/3 <= board.get(i).getPosX() + boardWidth))
						 
						 && stickMan.getY() <= board.get(i).getPosY() + boardHeight 
						 && stickMan.getY() >= board.get(i).getPosY() ) {
						
					yOnTop = board.get(i).getPosY() + boardHeight;
					stickMan.setY(yOnTop);
					onTop = true;	
					return;
				}
					else onTop = false;
			}
		}	
	}
	
	public void dead() {
		if ( stickMan.getX() <= 0 
				|| stickMan.getX() +  stickMan.getWidth() >= (float)Gdx.graphics.getWidth()
				|| stickMan.getY() <= 0 ) {
			
			if (stickMan.isAlive()) {
				AssetLoader.hit.play();
				int x = MathUtils.random(0, 5);
				AssetLoader.sound[x].play();
			}
			
			stickMan.setIsAlive(false);
		}
	}
	
	public void eat() {
		if (stickMan.isAlive()) {
			for (int i = 0; i < duck.size; i++) {
		     	if ( getOnTop() && (((stickMan.getX() + stickMan.getWidth()/4 >= duck.get(i).getPosX() && stickMan.getX() + stickMan.getWidth()/4 <= duck.get(i).getPosX() + duck.get(i).getWidth() ) 
					|| (stickMan.getX() + smWidth - stickMan.getWidth()/4 >= duck.get(i).getPosX() && stickMan.getX() + smWidth - stickMan.getWidth()/4 <= duck.get(i).getPosX() + duck.get(i).getWidth() )) 
					&& ((stickMan.getY() >= duck.get(i).getPosY() && stickMan.getY() <= duck.get(i).getPosY() + duck.get(i).getHeight())
					|| 	(stickMan.getY() + stickMan.getHeight() >= duck.get(i).getPosY() && stickMan.getY() + stickMan.getHeight() <= duck.get(i).getPosY() + duck.get(i).getHeight() ))
				)) {
					duck.removeIndex(i);
					AssetLoader.coin.play();
					gameWorld.addScore();
					duck.add(new Duck(0 - Gdx.graphics.getHeight()/10, 0 - Gdx.graphics.getHeight()/12, Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/12));
					duck.get(duck.size-1).update(board);
					break;
			}
		  }
		}
	}
	
	public boolean getOnTop() {
		return onTop;
	}
	
	public float yOnTop() {
		return yOnTop;
	}
}
