package com.ants.map;

public class Map {

	private int[][] mapLoaded;
	
	public Map(int[][] map){
		this.mapLoaded = map;
	}
	
	public int[][] getMap(){
		return this.mapLoaded;
	}
	
	
}
