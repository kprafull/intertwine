package com.vmware.borathon.intertwine.dal;

import java.util.ArrayList;

import com.vmware.borathon.intertwine.model.Entity;

public interface DalService {

	public ArrayList<Entity> getEntities() throws Exception;
	
	public ArrayList<Entity> getMatchingEntities(String entityHash) throws Exception;

	public void updateEntity(Entity entity) throws Exception;

	public void deleteEntity(Entity entity) throws Exception;

	public void addEntity(Entity entity) throws Exception;
}
