package com.pitlv.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.pitlv.gameworld.GameWorld;

public class Duck {
	private float posX;    //x-position
	private float posY;    //y-position

	private float width;   //duck's width
	private float height;   //duck's height
	
	
	public Duck (float posX, float posY, float width, float height) {
		
		
		this.posX = posX;
		this.posY = posY;
		
		this.width = width;
		this.height = height;
	}
	
	public void update(Array<Board> board) { 
		posX -= GameWorld.getPace();
		if (posX + width <= 0) {
			for (int i = 0; i < board.size; i++) {
				if (board.get(i).getPosX() >= Gdx.graphics.getWidth()) {
					posX = board.get(i).getPosX() + board.get(i).getWidth()/4;
					posY = board.get(i).getPosY() + board.get(i).getHeight();
				}
			}
		}
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int myWidth) {
		this.width = myWidth;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int myHeight) {
		this.height = myHeight;
	}

}

