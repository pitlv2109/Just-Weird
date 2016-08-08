package com.pitlv.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pitlv.gameworld.GameRenderer;
import com.pitlv.gameworld.GameWorld;
import com.pitlv.helpers.Collision;
import com.pitlv.helpers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private Collision collision;
	
	private float runTime = 0;
	
	float width;
	float height;
	
    public GameScreen() {	
        Gdx.app.log("GameScreen", "Attached");
        
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

    	world = new GameWorld(width, height);
    	renderer = new GameRenderer(world, width, height);
    	collision = new Collision(world);
    	
    	Gdx.input.setInputProcessor(new InputHandler(world));
    }
    
    @Override
    public void render(float delta) {
    	
    	runTime += delta;

    	collision.onTop();
    	//stickMan renderer
    	renderer.render(runTime);
    	world.update(delta, collision.getOnTop(), collision.yOnTop(), world.getStickMan().isAlive());
    	collision.eat();
    	collision.dead();
    	
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");     
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");        
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");       
    }

    @Override
    public void dispose() {
        // Leave blank
    }

}
