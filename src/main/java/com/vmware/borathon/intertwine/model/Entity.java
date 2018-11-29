package com.vmware.borathon.intertwine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private String id;
    @NotNull
    private Type type;
    @NotNull
    private String vendor;
    @NotNull
    private String name;
    @NotNull
    private String version;
    private List<Type> deps;
    private List<String> upgradeFrom;
    
    public String getId()
    {
    	StringBuilder idHash = new StringBuilder();
    	return idHash.append(type).append(vendor).append(name).append(version).toString();
    }
}
