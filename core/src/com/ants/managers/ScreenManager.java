package com.ants.managers;

import com.ants.screens.IScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {

	IScreen actualScreen;
	
	public ScreenManager(IScreen screen){
		setScreen(screen);
	}
	
	public void update(){
		actualScreen.update();
	}
	
	public void render(SpriteBatch batch){
		actualScreen.render(batch);
	}
	
	public void setScreen(IScreen screen){
		actualScreen = screen;
		actualScreen.init();
	}
	
	public IScreen getScreen(){
		return actualScreen;
	}
	
}
