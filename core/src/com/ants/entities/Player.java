package com.ants.entities;

import java.awt.Dimension;

import com.ants.crafty.Settings;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	private Vector2 position;
	private Texture texture;
	private String name;
	private int ID = -1;
	private int life = 100;
	
	public Player(int ID, Vector2 vector2, Texture texture, String name){
		this.position = vector2;
		this.texture = texture;
		this.name = name;
		this.ID = ID;
	}

	
	public void render(SpriteBatch batch){
		batch.draw(texture, position.x, position.y);
	}
	
	public void translate(Vector2 dir, Dimension size){
		
		position.x += dir.x;
		position.y += dir.y;
		
		if(position.x < 0)
			position.x = 0;
		
		if(position.x > size.getWidth() * Settings.TileSize - texture.getWidth())
			position.x = (float)size.getWidth() * Settings.TileSize - texture.getWidth();
		
		if(position.y < 0)
			position.y = 0;
		
		if(position.y > size.getHeight() * Settings.TileSize - texture.getHeight())
			position.y = (float)size.getHeight() * Settings.TileSize - texture.getHeight();
	}
	
	public void translate(float x, float y, Dimension size){
		translate(new Vector2(x, y), size);
	} 
	
	public String toString(){
		return ID + " " + name + " x : " + position.x + " y : " + position.y;
	}
	
	
	//Setters & Getters
	
	public int getID(){
		return ID;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public void setPosition(Vector2 newPos){
		this.position = newPos;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public Texture setTexture(){
		return texture;
	}
	
	public String getName(){
		return name;
	}
	
	public void removeLife(int amount){
		life = (life - amount >= 0)?life-amount:0;
	}
	
	public void addLife(int amount){
		life = (life + amount >= 100)?life+amount:100;
	}
	

}
