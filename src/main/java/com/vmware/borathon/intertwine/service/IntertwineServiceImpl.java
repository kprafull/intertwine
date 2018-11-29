package com.vmware.borathon.intertwine.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmware.borathon.intertwine.model.Entity;
import com.vmware.borathon.intertwine.model.WorkingSet;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class IntertwineServiceImpl implements IntertwineService {
	@Override
	public Collection<Entity> getAllEntities() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Entity> entities = mapper.readValue(new File("./src/main/resources/sampleEntities.json"),
				new TypeReference<List<Entity>>() {
				});
		return entities;
	}

	@Override
	public Entity getEntity(String id) throws Exception {
		return getAllEntities().stream().filter(match -> id.equals(match.getId())).findAny().orElse(null);
	}

	@Override
	public WorkingSet getWorkingSet(String id) throws Exception {

		return getAllWorkingSets().stream().filter(match -> id.equals(match.getId())).findAny().orElse(null);

	}

	@Override
	public Collection<WorkingSet> getAllWorkingSets() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<WorkingSet> workingSets = mapper.readValue(new File("./src/main/resources/sampleWorkingSets.json"),
				new TypeReference<List<WorkingSet>>() {
				});
		return workingSets;
	}

	@Override
	public void addEntity(Entity entity) {

	}

	@Override
	public void addMultipleEntities(Collection<Entity> entities) {

	}

	@Override
	public void addWorkingSet(WorkingSet workingSet) {

	}

	@Override
	public Entity removeEntity(String id) {
		return null;
	}

	@Override
	public WorkingSet removeWorkingSet(String id) {
		return null;
	}

	// return list of Applicable entities for a given working set
	@Override
	public List<Entity> findApplicableUpgrade(WorkingSet start, WorkingSet destination) throws Exception {

		List<String> applicableEntityIds = new ArrayList<String>();
		// is working set compatible...?
		if (getWorkingSet(destination.getId()) == null)
			return new ArrayList<Entity>();

		List<String> startEntities = start.getEntityIds();
		List<String> destEntities = destination.getEntityIds();

		// find delta of entities between two sets
		destEntities.removeAll(startEntities);

		for (String entityId : destEntities) {
			
			if (!Collections.disjoint(start.getEntityIds(), getEntity(entityId).getUpgradeFrom())) {
				applicableEntityIds.add(entityId);
				}
			

		}

		// // for each entity find applicable upgrade
		// for (Entity entity : destEntities) {
		// // get entity
		// // is entity update applicable to start entity
		// // yes
		// // check working set applicable, add this entity to return entity
		// // no
		// // is this a stepping stone upgrade
		// // yes, recurse for stepping stone upgrade....
		//
		// if (!Collections.disjoint(entity.getUpgradeFrom(), startEntities)) {
		//
		// }
		// }
		return convertListEntityIDtoEntity(destEntities);
	}

	private List<Entity> convertListEntityIDtoEntity(List<String> entityIds) throws Exception {
		List<Entity> entities = new ArrayList<Entity>();

		for (String entityId : entityIds) {
			entities.add(getEntity(entityId));
		}
		return entities;
	}
}
