package com.ants.screens;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.ants.crafty.Settings;
import com.ants.entities.Player;
import com.ants.map.Map;
import com.ants.map.TileMap;
import com.ants.mapGenerator.MapGenerator;
import com.ants.packets.ClientConnectedPacket;
import com.ants.packets.ClientDisconnectedPacket;
import com.ants.packets.PositionPacket;
import com.ants.packets.SeedPacket;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ScreenGame implements IScreen {

	private TileMap tileMap;
	public static OrthographicCamera cam;
	private Player player;
	private ArrayList<Player> players;
	
	private Viewport viewport;
	
	private Client client;
	
	public void init(){
		
		String host = "127.0.0.1";
		int portTCP = 43594;
		int portUDP = 43595;
		
		client = new Client();
		
		client.getKryo().register(ClientConnectedPacket.class);
		client.getKryo().register(ClientDisconnectedPacket.class);
		client.getKryo().register(PositionPacket.class);
		client.getKryo().register(SeedPacket.class);
		client.getKryo().register(Vector2.class);
		
		
		try{
			host = JOptionPane.showInputDialog("Enter server's ip:", InetAddress.getLocalHost().getHostAddress());			
		}catch (Exception ex){
		}
		
		if(host == null)
			Gdx.app.exit();
		
		/*
		try {
			tileMap = new TileMap(TileMap.LoadFromFile("C:/Ants/Crafty/core/assets/maps/map01.cmap"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		players = new ArrayList<Player>();

		tileMap = new TileMap(new Map(TileMap.fullMap(2, 100, 100)));
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false);
		cam.position.set(Settings.gameWidth / 2,Settings.gameHeight / 2,0);
		viewport = new FitViewport(Settings.gameWidth, Settings.gameHeight, cam);
		viewport.apply();
		cam.update();

		client.start();
		try {
			client.connect(2000, host, portTCP, portUDP);
		} catch (IOException e) {
			Gdx.app.exit();
		}
		
		client.addListener(new Listener(){
			
			public void received (Connection connection, Object object) {
				/*
				 * Position packet received
				 */
				if(object instanceof PositionPacket){
					PositionPacket posPacket = (PositionPacket) object;
					for(Player p : players){
						if(p.getID() == posPacket.ID){
							p.setPosition(posPacket.position);
							break;
						}
					}
					
					
				}
				/*
				 * New client connected packet
				 */
				else if(object instanceof ClientConnectedPacket){
					ClientConnectedPacket ccPacket = (ClientConnectedPacket) object;
					players.add(new Player(ccPacket.ID, ccPacket.position, player.getTexture(), ccPacket.username));
				}
				/*
				 * Client disconnected packet
				 */
				else if(object instanceof ClientDisconnectedPacket){
					ClientDisconnectedPacket cdPacket = (ClientDisconnectedPacket) object;
					for(Player p : players){
						if(p.getID() == cdPacket.ID){
							players.remove(p);
							break;
						}
					}
				}
				/*
				 * Seed packet received
				 */
				else if(object instanceof SeedPacket){
					SeedPacket seedPacket = (SeedPacket) object;
					Log(seedPacket.seed + "");
					tileMap = new TileMap(new Map(new MapGenerator(seedPacket.seed, 100, 100).Generate()));
				}
		    }
			
		});

		player = new Player(client.getID(), new Vector2(100, 100), new Texture("entities/alienGreen.png"), "CraftyGuy");
		
		ClientConnectedPacket ccPacket = new ClientConnectedPacket();
		ccPacket.username = player.getName();
		ccPacket.position = player.getPosition();
		ccPacket.ID = player.getID();
		client.sendUDP(ccPacket);
	}
	
	public void handleInputs(){
		
	
		float prevX = player.getX();
		float prevY = player.getY();
		float deltaX = 0;
		float deltaY = 0;
		
		if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.Z)){
			deltaY += Settings.movingSpeed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)){
			deltaY -= Settings.movingSpeed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.Q)){
			deltaX -= Settings.movingSpeed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)){
			deltaX += Settings.movingSpeed;
		}
		
		
		float deltaT = Gdx.graphics.getDeltaTime();
		
		float newSpeed = (deltaX == 0 || deltaY == 0)?1:Settings.COSPIOVER4;
		player.translate(deltaT * newSpeed * 50 * deltaX, deltaT * newSpeed * 50 * deltaY, tileMap.getSize());
		
		//The Camera follows the player
		cam.position.set(player.getPosition(), 0);
		
		if(prevX != player.getX() || prevY != player.getY()){
			PositionPacket posP = new PositionPacket();
			posP.ID = player.getID();
			posP.position = player.getPosition();
			client.sendUDP(posP);
		}
		
	}
	
	public void update(){

		handleInputs();
		
		if(cam.position.x < Settings.gameWidth/2)
			cam.position.set(Settings.gameWidth/2f, cam.position.y, 0);
		if(cam.position.x > tileMap.getSize().getWidth() * Settings.TileSize - Settings.gameWidth/2)
			cam.position.set((float)tileMap.getSize().getWidth() * Settings.TileSize - Settings.gameWidth/2, cam.position.y, 0);
		if(cam.position.y < Settings.gameHeight/2)
			cam.position.set(cam.position.x, Settings.gameHeight/2f, 0);
		if(cam.position.y > tileMap.getSize().getHeight() * Settings.TileSize - Settings.gameHeight/2)
			cam.position.set(cam.position.x, (float)tileMap.getSize().getHeight() * Settings.TileSize - Settings.gameHeight/2, 0);
		
		cam.update();
		
		
		
	}

	public void render(SpriteBatch batch){
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		tileMap.render(batch);
		
		try{
			for(Player p : players){
				p.render(batch);
			}
		}catch(Exception ex){
			
		}
		
		player.render(batch);
		batch.end();

	}
	
	private static void Log(String message, MType type){
		System.out.println("[" + type + "] "  + message);
	}
	
	private static void Log(String message){
		System.out.println(message);
	}
	
	private enum MType{
		error,
		info,
		unknown
	}
	
	
}
