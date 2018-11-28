package com.vmware.borathon.intertwine.model;

import java.util.List;

public class Entity {
    private Type type;
    private String version;
    private List<Type> deps;
    private List<String> upgradeFrom;
}
