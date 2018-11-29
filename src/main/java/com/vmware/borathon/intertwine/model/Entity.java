package com.vmware.borathon.intertwine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private String id;
    private Type type;
    private String vendor;
    private String name;
    private String version;
    private List<Type> deps;
    private List<String> upgradeFrom;
}
