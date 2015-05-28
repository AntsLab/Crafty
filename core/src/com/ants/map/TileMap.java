package com.ants.map;

import com.ants.crafty.Settings;
import com.ants.managers.ResourceManager;
import com.ants.screens.ScreenGame;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TileMap {

	private Tile[][] map;
	
	private Vector2 spawnLocation;
	
	
	public TileMap(Map map)	{
		
		spawnLocation = new Vector2(map.getMap()[0].length * Settings.TileSize, map.getMap().length * Settings.TileSize);

		this.map = new Tile[map.getMap()[0].length][map.getMap().length];

		for(int y = 0; y < map.getMap().length; y++)
			for(int x = 0; x < map.getMap()[0].length; x++)
			{
				this.map[x][y] = new Tile(map.getMap()[y][x]);
			}
	}
		
	public void render(SpriteBatch batch)
	{
		int minX = (int)ScreenGame.cam.position.x / Settings.TileSize - 21;
		int maxX = (int)ScreenGame.cam.position.x / Settings.TileSize + 21;
		int minY = (int)ScreenGame.cam.position.y / Settings.TileSize - 13;
		int maxY = (int)ScreenGame.cam.position.y / Settings.TileSize + 13;
		
		
		if(maxX > this.map.length)
			maxX = this.map.length;
		if(minX < 0)
			minX = 0;
		if(maxY > this.map[0].length)
			maxY = this.map[0].length;
		if(minY < 0)
			minY = 0;
		
		
		for(int y = minY; y < maxY; y++){
			for(int x = minX; x < maxX; x++){
				this.map[x][y].render(batch, x, y);
			}
		}
	}
	
	public int getID(Point pos){
		return this.map[pos.x][pos.y].getID();
	}
	
	public void setID(Point pos, int ID){
		this.map[pos.x][pos.y].setID(ID);
	}
	
	public void setSpawnLocation(Vector2 spawn){
		spawnLocation = spawn;
	}
	
	public Vector2 getSpawnLocation(){
		return spawnLocation;
	}
	
	public Dimension getSize(){
		return new Dimension(map.length, map[0].length);
	}
	
	public static int[][] fullMap(int ID, int width, int height){
		int[][] result = new int[width][height];
		for(int x = 0; x < width; x++){
			for(int y = 0; y < width; y++){
				result[x][y] = ID;
			}
		}
		return result;
	}
	
	/*
	public static Map LoadFromFile(String path) throws IOException{
		
		Map map;
		
		int[][] mapLoaded;
		int[][] objLoaded;
		
		File file = new File(path);
		List<String> lines = Files.readAllLines(file.toPath());

		List<String> mapList = lines.subList(0, lines.size() / 2);
		List<String> objList = lines.subList(lines.size() / 2, lines.size());

		mapLoaded = new int[mapList.size()][mapList.get(0).split(";").length];
		objLoaded = new int[objList.size()][objList.get(0).split(";").length];
		
		for(int x = 0; x < mapList.size(); x++){
			String[] columns = mapList.get(x).split(";");
			for(int y = 0; y < columns.length; y++){
				mapLoaded[x][y] = Integer.parseInt(columns[y]);
			}
		}
		
		for(int x = 0; x < objList.size(); x++){
			String[] columns = objList.get(x).split(";");
			for(int y = 0; y < columns.length; y++){
				objLoaded[x][y] = Integer.parseInt(columns[y]);
			}
		}
		
		map = new Map(mapLoaded);
		return map;
	}*/
	
}
