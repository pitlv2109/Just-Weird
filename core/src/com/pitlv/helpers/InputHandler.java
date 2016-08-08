package com.pitlv.helpers;

import com.badlogic.gdx.InputProcessor;
import com.pitlv.gameworld.GameWorld;
import com.pitlv.objects.StickMan;

public class InputHandler implements InputProcessor {

	private GameWorld myWorld;
	private StickMan stickMan;
	
	public InputHandler(GameWorld world) {
		myWorld = world;
		stickMan = myWorld.getStickMan();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		stickMan.setPressed(true);
	
		myWorld.start();
		
		if (!stickMan.isAlive())
			myWorld.restart();
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		stickMan.setPressed(false);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
