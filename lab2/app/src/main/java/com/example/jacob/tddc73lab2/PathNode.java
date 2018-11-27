package com.example.jacob.tddc73lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PathNode {
    private String title;
    private PathNode parent = null;
    private List<PathNode> children;

    public PathNode(String t) {
        title = t;
        children = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public PathNode getParent() {
        return parent;
    }

    public List<PathNode> getChildren() {
        return children;
    }

    public PathNode getChild(int i) {
        return children.get(i);
    }

    public boolean hasChild(String title) {
        for (PathNode child : children)
            if (child.getTitle().equals(title))
                return true;

        return false;
    }

    public boolean hasChild(PathNode node) {
        for (PathNode child : children)
            if (child == node)
                return true;

        return false;
    }

    public PathNode addChild(String title) {
        PathNode node = new PathNode(title);
        node.parent = this;

        children.add(node);
        return node;
    }

    private HashMap<String, PathNode> getTree(String path) {
        HashMap<String, PathNode> paths = new HashMap<>();

        // Make sure it's a leaf node
        if (children.size() > 0)
            for (PathNode child : children)
                // Traverse through all children
                paths.putAll(child.getTree(path + "/" + child.title));
        else
            // Add path to hashmap
            paths.put(path, this);

        return paths;
    }

    public HashMap<String, PathNode> getTree() {
        return getTree(title);
    }
}
