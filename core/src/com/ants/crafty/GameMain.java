package com.ants.crafty;

import com.ants.managers.ResourceManager;
import com.ants.managers.ScreenManager;
import com.ants.screens.ScreenGame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends ApplicationAdapter {
	
	private SpriteBatch batch;
	
	private ScreenManager screenManager;
	
	@Override
	public void create () {		
		batch = new SpriteBatch();
		ResourceManager.Load();
		screenManager = new ScreenManager(new ScreenGame());
	}
	
	public void update(){
		screenManager.update();
	}


	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screenManager.render(batch);
	}
}
