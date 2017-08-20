package com.splat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shambala on 21.08.17.
 */
public class Folder {
    private static int idCounter = 0;
    private String name;
    private List<Folder> children;
    private final int id;

    public Folder(String name) {
        this.id = idCounter++;
        this.name = name;
        this.children = new ArrayList<>();
    }

    public Folder(String name, int id) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Folder folder) {
        children.add(folder);
    }

    public boolean removeChild(Folder folder) {
        return children.remove(folder);
    }

    public Folder[] getChildren() {
        return children.toArray(new Folder[children.size()]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
