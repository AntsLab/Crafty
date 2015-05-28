package com.ants.map;

import com.ants.crafty.Settings;
import com.ants.managers.ResourceManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Tile {
	
	private Texture texture;
	private boolean walkable;
	private int ID;
	
	public Tile(int ID){
		this.ID = ID;
		setID(ID);	
	}
	
	public void render(SpriteBatch batch, int x, int y){
		batch.draw(texture, Settings.TileSize * x, Settings.TileSize * y, Settings.TileSize, Settings.TileSize);
	}
	
	public void setID(int ID)
	{
		this.walkable = true;
		this.ID = ID;
		texture = ResourceManager.getTextureById(ID);
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public boolean isWalkable()
	{
		return this.walkable;
	}
	
	public void setWalkable(boolean isWalkable){
		this.walkable = isWalkable;
	}
	
	public Texture getTexture(){
		return texture;
	}

	public void setTexture(Texture texture){
		this.texture = texture;
	}
}
