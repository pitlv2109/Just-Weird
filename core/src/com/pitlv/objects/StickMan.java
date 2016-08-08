package com.pitlv.objects;

import com.badlogic.gdx.Gdx;
import com.pitlv.gameworld.GameWorld;
import com.pitlv.helpers.AssetLoader;

public class StickMan {
	
	private float posX;    //x-position
	private float posY;    //y-position
	
	private float width;   //stickman's width
	private float height;   //stickman's height 
	
	private float screenWidth = Gdx.graphics.getWidth();
	
	private float pressTime;
	private float count;
	private float count2;
	private float velocity;
	private float gravity;
	private float pace;

	private boolean isPressed;
	private boolean jump;   //is able to jump (is on board)
	private boolean isAlive;
	private boolean firstTime = true;
	private boolean firstJump = true;   //for drawing instruction
	private boolean jumpToFar = false;
	private boolean firstDie = false;   //for troll
	
	public StickMan (float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		
		posX = x;
		posY = y;
		 
		count = 0;
		velocity = 0;
		count2 = 0;
		pace = GameWorld.getPace();
		
		isPressed = false;
		isAlive = true;
		jump = false;
		firstTime = true;
	}
	
	public void update (float delta, boolean onTop, float yOnTop) {
//	Gdx.app.log("First time ", "" + firstTime);
	if (!firstTime) {

		if (isAlive) {
			if (IsPressed() & jump) {
	    		pressTime += delta;
	    		velocity = pressTime*((float)(screenWidth/28.4));  //20
	    		gravity = velocity;
	    		firstJump = false;
	    		
	    		if (onTop) {
	    			setX(getX() - GameWorld.getPace());
	    			jump = true;
	    		}
			}

			else {	
				if (jump && !onTop) {
					AssetLoader.jump.play();
					jump = false;
				}
				
				if (count < pressTime) {
					setY(getY() + delta*((float)(screenWidth/8.114286)) + velocity);     //70
					setX(getX() + count*((float)(screenWidth/33.4117646)));		//17
					count += delta;
					count2 = count;
					velocity -= (float)(screenWidth/1136.0);		//0.5
				}
				
				//if count >= pressTime
				else {
					
					pressTime = 0;
					count = 0;
					
					if (onTop) {
						setX(getX() - pace);
						jump = true;
					}
						
					//no top
					//falling down
					else {
						if (pressTime < .15)
							setY(getY() - delta*((float)(screenWidth/1.5)));
						else
							setY(getY() - delta*((float)(screenWidth/11.36)) - gravity);	//50
						if (count2 > 0) {
							setX(getX() + delta*((float)(screenWidth/2.84) + count2));		//200
							count2 -= delta;
						}
						else 
						gravity += (float)(screenWidth/189333);			//0.0003
			      	}
		     	}
			 }
		}
	}
	
	//if first time
	else {
		setY(getY() - GameWorld.getPace());
		if (onTop) {
			firstTime = false;
			jump = true;
		}
	}
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setIsAlive (boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public void setPressed (boolean isPressed) {
		this.isPressed = isPressed;
	}
	
	public boolean IsPressed() {
		return isPressed;
	}
	
	public float getX() {
		return posX;
	}
	
	public float getY() {
		return posY;
	}
	
	public void setX (float x) {
		posX = x;
	}
	
	public void setY (float y) {
		posY = y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
//	public boolean getAtPeak() {
//		return atPeak;
//	}
	
	public boolean isJump() {
		return jump;
	}
	
	public void setJump (boolean bol) {
		jump = bol;
	}
	
	public void setFirstTime(boolean bol) {
		firstTime = bol;
	}
	
	public boolean getFirstTime() {
		return firstTime;
	}
	
	public void setFirstJump (boolean bol) {
		firstJump = bol;
	}
	
	public boolean getFirstJump() {
		return firstJump;
	}
	
	public void setJumpToFar (boolean bol) {
		jumpToFar = bol;
	}
	
	public boolean getJumToFar () {
		return jumpToFar;
	}
	
	public void setFirstDie (boolean bol) {
		firstDie = bol;
	}
	
	public boolean getFirstDie () {
		return firstDie;
	}
	
	public void setPressTime (float fl) {
		pressTime = fl;
	}
	
	public void setVelocity (float fl) {
		velocity = fl;
	}
	
	public void setGravity (float fl) {
		gravity = fl;
	}
	
	public void setCount (float fl) {
		count = fl;
	}
	
}
