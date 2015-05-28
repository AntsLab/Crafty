package com.ants.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreen {

	public void init();
	
	public void update();
	
	public void render(SpriteBatch batch);

}
