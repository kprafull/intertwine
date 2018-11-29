package com.vmware.borathon.intertwine.controller;

import com.vmware.borathon.intertwine.model.Entity;
import com.vmware.borathon.intertwine.model.Type;
import com.vmware.borathon.intertwine.model.WorkingSet;
import com.vmware.borathon.intertwine.service.IntertwineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/intertwine")
public class IntertwineController {
	
	@Autowired
	IntertwineService intertwine;

    @RequestMapping(value = "/working-sets", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllWorkingSets() {
		// TODO: call service
		try {
			return new ResponseEntity<>(intertwine.getAllWorkingSets(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/working-sets/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getWorkingSet(@PathVariable("id") String id) {
    	try {
			return new ResponseEntity<>(intertwine.getWorkingSet(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value = "/working-sets/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> postWorkingSet(@RequestBody String id) {
        //TODO: call service
        WorkingSet ws = new WorkingSet();
        Entity e = new Entity();
        e.setType(Type.HORIZON);
        e.setVersion("1.0");
        List<Entity> entities = new ArrayList<>();
        entities.add(e);
        //ws.setEntities(entities);

        return new ResponseEntity<>("123", HttpStatus.CREATED);
    }
}
