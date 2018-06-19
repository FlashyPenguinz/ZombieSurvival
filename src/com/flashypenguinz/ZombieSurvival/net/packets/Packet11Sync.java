package com.flashypenguinz.ZombieSurvival.net.packets;

import java.util.ArrayList;
import java.util.List;

import com.flashypenguinz.ZombieSurvival.net.entities.SyncEntity;

public class Packet11Sync extends Packet {

	private List<SyncEntity> entities;
	
	public Packet11Sync(byte[] data) {
		super(11);
		List<SyncEntity> entities = new ArrayList<SyncEntity>();
		String[] array = readData(data).split("|");
		if(array.length == 0) {
			entities = null;
			return;
		}
		for(String entity: array) {
			String[] info = entity.split(",");
			if(info.length != 4) {
				entities = null;
				return;
			}
			entities.add(new SyncEntity(Integer.valueOf(info[0]), Float.valueOf(info[1]), Float.valueOf(info[2]), Float.valueOf(info[3])));
		}
		this.entities = entities;
	}

	public Packet11Sync(List<SyncEntity> entities) {
		super(11);
		this.entities = entities;
	}

	@Override
	public byte[] getData() {
		String prod = "";
		for (SyncEntity entity: entities) {
			prod += entity.getId()+",";
			prod += entity.getX()+",";
			prod += entity.getY()+",";
			prod += entity.getRotation()+"|";
		}
		return ("11"+prod).getBytes();
	}
	
	public List<SyncEntity> getEntities() {
		return entities;
	}
	
}
