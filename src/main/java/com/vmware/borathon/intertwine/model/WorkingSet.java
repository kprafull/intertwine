package com.vmware.borathon.intertwine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingSet {
    private String id;
    @NotNull
    private List<String> entityIds;
    
    public String getId()
    {
    	StringBuilder idHash = new StringBuilder();
    	for (String entity : entityIds) {
    		idHash.append(entity).toString();
		}
    	
    	return idHash.toString();
    }
}
