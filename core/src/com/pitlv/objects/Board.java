package com.pitlv.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.pitlv.gameworld.GameWorld;

public class Board {
	private float posX;    //x-position
	private float posY;    //y-position
	
	private float myWidth;   //board's width
	private float myHeight;   //board's height 
	
	//screen's
	private float width;
	private float height;   
	
	public Board (float x, float y, float width, float height) {
		posX = x;
		posY = y;
		
		myWidth = width;
		myHeight = height;
		
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
	}
	
	public void update (float delta) {
		posX -= GameWorld.getPace();
		if (posX + getWidth() <= 0) {
			posX = width;
			posY = MathUtils.random(height/3, 2*height/3);
		}
	}
	
	public void restart() {
		
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
		return myWidth;
	}

	public void setWidth(int width) {
		myWidth = width;
	}

	public float getHeight() {
		return myHeight;
	}

	public void setHeight(int height) {
		myHeight = height;
	}
	
	
}
