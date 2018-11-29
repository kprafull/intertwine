package com.vmware.borathon.intertwine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingSet {
    private String id;
    private List<Entity> entities;
}
