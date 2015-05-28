package com.ants.crafty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Settings {

	public static final String Title = "Crafty", Version = "Alpha 0.2";
	
	//Math Consts
	public static final float COSPIOVER4 = 0.7071067812f; //(float) (Math.sqrt(2)/2)
	
	public static final int TileSize = 32;
	public static final float movingSpeed = 8f;
	
	public static final float gameWidth = 1280;
	public static final float gameHeight = 720;
	
	public static final Vector2 screenCenter = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			
}
