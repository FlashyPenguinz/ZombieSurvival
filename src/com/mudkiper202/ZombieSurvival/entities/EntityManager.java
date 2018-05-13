package com.mudkiper202.ZombieSurvival.entities;

import java.util.ArrayList;
import java.util.List;

import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.net.NetEntity;
import com.mudkiper202.ZombieSurvival.net.NetPlayer;

public class EntityManager {

	//private GameManager gm;
	
	private List<NetEntity> entities;
	
	public EntityManager(GameManager gm) {
		//this.gm = gm;
		this.entities = new ArrayList<NetEntity>();
	}
	
	public void addEntity(NetEntity entity) {
		entities.add(entity);
	}
	
	public NetEntity getEntityById(int id) {
		for (NetEntity entity : entities) {
			if (entity.getId() == id) {
				return entity;
			}
		}
		return null;
	}
	
	public void removeEntityById(int id) {
		NetEntity toBeRemoved = null;
		for (NetEntity entity : entities)
			if (entity.getId() == id) {
				toBeRemoved = entity;
				break;
			}
		if(toBeRemoved != null)
			entities.remove(toBeRemoved);
	}
	
	public NetPlayer getPlayerById(int id) {
		for (NetPlayer player : getPlayers())
				if(player.getId() == id)
					return player;
		return null;
	}
	
	public boolean isPlayer(int id) {
		return (getPlayerById(id) != null);
	}
	
	public List<NetEntity> getEntities() {
		return entities;
	}
	
	public List<NetPlayer> getPlayers() {
		List<NetPlayer> players = new ArrayList<NetPlayer>();
		for(NetEntity entity: entities)
			if(entity instanceof NetPlayer)
				players.add((NetPlayer) entity);
		return players;
	}
	
}
