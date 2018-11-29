package com.vmware.borathon.intertwine.service;

import com.vmware.borathon.intertwine.model.Entity;
import com.vmware.borathon.intertwine.model.WorkingSet;

import java.util.Collection;

public interface IntertwineService {

    public Collection<Entity> getAllEntities();
    public Entity getEntity(String id);
    public WorkingSet getWorkingSet(String id);
    public Collection<WorkingSet> getAllWorkingSets();
    public void addEntity(Entity entity);
    public void addMultipleEntities(Collection<Entity> entities);
    public void addWorkingSet(WorkingSet workingSet);
    public Entity removeEntity(String id);
    public WorkingSet removeWorkingSet(String id);
}
