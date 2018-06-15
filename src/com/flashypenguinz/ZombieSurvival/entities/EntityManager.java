package com.flashypenguinz.ZombieSurvival.entities;

import java.util.ArrayList;
import java.util.List;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.net.entities.NetEntity;
import com.flashypenguinz.ZombieSurvival.net.entities.NetPlayer;

public class EntityManager {

	//private GameManager gm;
	
	private List<NetEntity> entities;
	private List<NetEntity> toRemoveEntities;
	
	public EntityManager(GameManager gm) {
		//this.gm = gm;
		this.entities = new ArrayList<NetEntity>();
		this.toRemoveEntities = new ArrayList<NetEntity>();
	}
	
	public void addEntity(NetEntity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(NetEntity entity) {
		toRemoveEntities.add(entity);
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
			toRemoveEntities.add(toBeRemoved);
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
	
	public int getLatestEntityId() {
		int id = -1;
		for (NetEntity entity: entities) {
			if(id == -1)
				id = entity.getId();
			else if(entity.getId() > id)
				id = entity.getId();
		}
		return id;
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
	
	public void updateEntityLists() {
		for(NetEntity e: toRemoveEntities) {
			if(entities.contains(e)) {
				entities.remove(e);
			}
		}
		toRemoveEntities.clear();
	}
	
}
