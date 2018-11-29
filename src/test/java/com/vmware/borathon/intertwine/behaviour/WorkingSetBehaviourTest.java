package com.vmware.borathon.intertwine.behaviour;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmware.borathon.intertwine.model.Entity;
import com.vmware.borathon.intertwine.model.WorkingSet;
import com.vmware.borathon.intertwine.service.IntertwineService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkingSetBehaviourTest {

	@Autowired
	IntertwineService intertwine;
	
	//find available upgrade for a given environment
	//where entity is upgradable till given destination
	@Test
	public void findApplicableUpgradeDestination_test1() throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		WorkingSet start = mapper.readValue(new File("./src/test/resources/start_compatibility_test1.json"), WorkingSet.class);
		WorkingSet destination = mapper.readValue(new File("./src/test/resources/destination_compatibility_test1.json"), WorkingSet.class);

		List<Entity> entities = intertwine.findApplicableUpgrade(start, destination);
		assertTrue(entities.size()>0);
	}

	// find available upgrade for a given environment
	// where entity is not upgradable till given destination
	@Test
	public void findApplicableUpgradeDestination_test2() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		WorkingSet start = mapper.readValue(new File("./src/test/resources/start_compatibility_test2.json"),
				WorkingSet.class);
		WorkingSet destination = mapper.readValue(new File("./src/test/resources/destination_compatibility_test2.json"),
				WorkingSet.class);

		List<Entity> entities = intertwine.findApplicableUpgrade(start, destination);
		assertTrue(entities.size() == 0);
	}

}
