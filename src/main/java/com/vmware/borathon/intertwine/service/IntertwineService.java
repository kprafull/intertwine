package com.vmware.borathon.intertwine.service;

import com.vmware.borathon.intertwine.model.Entity;
import com.vmware.borathon.intertwine.model.WorkingSet;

import java.util.Collection;
import java.util.List;

public interface IntertwineService {

    public Collection<Entity> getAllEntities() throws Exception;
    public Entity getEntity(String id) throws Exception;
    public WorkingSet getWorkingSet(String id) throws Exception;
    public Collection<WorkingSet> getAllWorkingSets() throws Exception;
    public void addEntity(Entity entity);
    public void addMultipleEntities(Collection<Entity> entities);
    public void addWorkingSet(WorkingSet workingSet);
    public Entity removeEntity(String id);
    public WorkingSet removeWorkingSet(String id);
	public List<Entity> findApplicableUpgrade(WorkingSet start, WorkingSet destination) throws Exception;
}
