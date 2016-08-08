package com.pitlv.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.pitlv.helpers.AssetLoader;
import com.pitlv.screens.GameScreen;

public class startingPoint extends Game {

	@Override
	public void create() {
		Gdx.app.log("ZBGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		AssetLoader.dispose();
	}
}
