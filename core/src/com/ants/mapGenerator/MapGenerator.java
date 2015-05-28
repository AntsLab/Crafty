package com.ants.mapGenerator;



public class MapGenerator {
	
	private int seed;
	private int width;
	private int height;

	public MapGenerator(int seed, int width, int height){
		this.seed = seed;
		this.width = width;
		this.height = height;

		
	}
	
	public int[][] Generate(){
		int[][] map = new int[width][height];
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				int ID = 0;
				
				float octave1 = PerlinSimplexNoise.noise((x * 6 + seed) * 0.0001f, (y * 6 + seed) * 0.0001f);
                float octave2 = PerlinSimplexNoise.noise((x * 6 + seed) * 0.0005f, (y * 6 + seed) * 0.0005f);
                float octave3 = PerlinSimplexNoise.noise((x * 6 + seed) * 0.005f, (y * 6 + seed) * 0.005f);
                float octave4 = PerlinSimplexNoise.noise((x * 6 + seed) * 0.005f, (y * 6 + seed) * 0.005f) * 20f;
                float octave5 = PerlinSimplexNoise.noise((x * 6 + seed) * 0.003f, (y * 6 + seed) * 0.003f) * 5f;
                float finalNumber = octave1 + octave2 + octave3 + octave4 + octave5 + octave1;

                if (finalNumber < 30 && finalNumber > 20)
                {
                    ID = 8;
                }
                else
                {
                	ID = 2;
                }
                map[x][y] = ID;
			}
		}
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				int ID = map[x][y];
				
				if(ID == 8){
					
					if(x - 1 >= 0){
						if(map[x - 1][y] == 0){
							
						
							
						}
					}
						
				}
				
			}
		}
		
		return map;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;	
	}
	
	public int getSeed(){
		return seed;
	}

}
