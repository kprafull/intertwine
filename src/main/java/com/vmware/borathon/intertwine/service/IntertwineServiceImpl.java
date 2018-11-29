package com.vmware.borathon.intertwine.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmware.borathon.intertwine.model.Entity;
import com.vmware.borathon.intertwine.model.WorkingSet;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

		List<String> destEntities = new ArrayList<String>(destination.getEntityIds());

		// find delta of entities between two sets
		destEntities.removeAll(start.getEntityIds());

		for (String entityId : destEntities) {

			if (!Collections.disjoint(start.getEntityIds(), getEntity(entityId).getUpgradeFrom())) {
				applicableEntityIds.add(entityId);
			} else {
				Set<String> orderedEntityUpgradeList = new HashSet<String>();
				orderedEntityUpgradeList.add(start.getEntityIds().get(1));

				createEnityUpgradeMap(start.getEntityIds().get(1), entityId, orderedEntityUpgradeList);
				
				applicableEntityIds.addAll(orderedEntityUpgradeList);

			}

		}
		return convertListEntityIDtoEntity(applicableEntityIds);
	}

	private List<Entity> convertListEntityIDtoEntity(List<String> entityIds) throws Exception {
		List<Entity> entities = new ArrayList<Entity>();

		for (String entityId : entityIds) {
			entities.add(getEntity(entityId));
		}
		return entities;
	}

	private void createEnityUpgradeMap(String current, String destination, Set<String> orderedEntityUpgradeList)
			throws Exception {

		// I1---- 1.(6.0.0-789,6.7.0-789), 2. upgradesApplicableForDestinationEnity=
		// VCVMwareVC6.5.0-123,VCVMwareVC6.5.0-234,VCVMwareVC6.5.0-567,VCVMwareVC6.5.0-678,VCVMwareVC6.5.0-789
		// I2---(6.0.0-789,6.5.0-789) 2. upgradesApplicableForDestinationEnity =
		// [VCVMwareVC6.5.0-123, VCVMwareVC6.5.0-234, VCVMwareVC6.5.0-567,
		// VCVMwareVC6.5.0-678, VCVMwareVC6.5.0-789]
		Entity upgradeToEntity = getEntity(destination);
		List<String> upgradesApplicableForDestinationEnity = null;
		if (upgradeToEntity != null)
			upgradesApplicableForDestinationEnity = upgradeToEntity.getUpgradeFrom();

		if (upgradeToEntity == null || upgradesApplicableForDestinationEnity == null)
			return;

		if (upgradesApplicableForDestinationEnity.contains(current)) {
			// I1 -- noop
			// I2 -- 6.0.0-789, (6.0.0-789,6.5.0-123,6.5.0-456)
			orderedEntityUpgradeList.add(current);
			orderedEntityUpgradeList.add(destination);
		} else {
			for (String upgradeTo : upgradesApplicableForDestinationEnity) {
				// I1 -- 6.0.0--789,6.5.0-789
				// I2 -- noop
				int initSize = orderedEntityUpgradeList.size();
				createEnityUpgradeMap(current, upgradeTo, orderedEntityUpgradeList);
				if(orderedEntityUpgradeList.size()>initSize)
					orderedEntityUpgradeList.add(upgradeTo);
			}
		}

	}

}
