package com.example.jacob.tddc73lab2.mediator;

import com.example.jacob.tddc73lab2.PathNode;

import java.util.ArrayList;

public class PathMediator {
    private ArrayList<PathChanger> pathChangers;
    private PathNode rootNode;

    public PathMediator(PathNode rn) {
        pathChangers = new ArrayList<>();
        rootNode = rn;
    }

    public void add(PathChanger changer) {
        pathChangers.add(changer);
    }

    public void updatePath(String path, PathChanger sender) {
        for (PathChanger changer : pathChangers)
            if (changer != sender)
                    changer.receive(path);
    }

    public PathNode getRootNode() {
        return rootNode;
    }
}
