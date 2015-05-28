package com.ants.managers;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {

	
	public final static HashMap<Integer, Texture> textures = new HashMap<Integer, Texture>();
	
	public static void Load(){
		textures.put(0, new Texture("tiles/dirt.png")); //ID : 0
		textures.put(1, new Texture("tiles/dirt2.png")); //ID : 1
		textures.put(2, new Texture("tiles/grass.png")); //ID : 2
		textures.put(3, new Texture("tiles/grass2.png")); //ID : 3
		textures.put(4, new Texture("tiles/gravel.png")); //ID : 4
		textures.put(5, new Texture("tiles/gravel2.png")); //ID : 5
		textures.put(6, new Texture("tiles/sand.png")); //ID : 6
		textures.put(7, new Texture("tiles/sand2.png")); //ID : 7
		textures.put(8, new Texture("tiles/water.png")); //ID : 8
		textures.put(9, new Texture("tiles/water2.png")); //ID : 9
		
		textures.put(10, new Texture("tiles/water_up_left.png")); //ID : 9
		textures.put(11, new Texture("tiles/water_up_center.png")); //ID : 9
		textures.put(12, new Texture("tiles/water_up_right.png")); //ID : 9
		textures.put(13, new Texture("tiles/water_center_left.png")); //ID : 9
		textures.put(14, new Texture("tiles/water_center_right.png")); //ID : 9
		textures.put(15, new Texture("tiles/water_bottom_left.png")); //ID : 9
		textures.put(16, new Texture("tiles/water_bottom_center.png")); //ID : 9
		textures.put(17, new Texture("tiles/water_bottom_right.png")); //ID : 9
		
		
		
		textures.put(400, new Texture("tileObjects/tree.png")); //ID : 400
		textures.put(401, new Texture("tileObjects/tree2.png")); //ID : 401
		textures.put(402, new Texture("tileObjects/tree3.png")); //ID : 402
		textures.put(403, new Texture("tileObjects/bush.png")); //ID : 403
		textures.put(404, new Texture("tileObjects/bush2.png")); //ID : 404
		textures.put(405, new Texture("tileObjects/bush3.png")); //ID : 405
		textures.put(406, new Texture("tileObjects/cactus.png")); //ID : 406
		textures.put(407, new Texture("tileObjects/cactus2.png")); //ID : 407
		textures.put(408, new Texture("tileObjects/cactus3.png")); //ID : 408
		textures.put(409, new Texture("tileObjects/cactus4.png")); //ID : 409
		textures.put(410, new Texture("tileObjects/cactus5.png")); //ID : 410
		textures.put(411, new Texture("tileObjects/deadtree.png")); //ID : 411
		textures.put(412, new Texture("tileObjects/fir.png")); //ID : 412
		textures.put(413, new Texture("tileObjects/fir2.png")); //ID : 413
		textures.put(414, new Texture("tileObjects/fir3.png")); //ID : 414
		textures.put(415, new Texture("tileObjects/flowerblue.png")); //ID : 415
		textures.put(416, new Texture("tileObjects/flowerorange.png")); //ID : 416
		textures.put(417, new Texture("tileObjects/flowerpurple.png")); //ID : 417
		textures.put(418, new Texture("tileObjects/flowerwhite.png")); //ID : 418
		textures.put(419, new Texture("tileObjects/grass.png")); //ID : 419
		textures.put(420, new Texture("tileObjects/grass2.png")); //ID : 420
		
	}

	public static Texture getTextureById(int id){
		return textures.get(id);
	}

}
