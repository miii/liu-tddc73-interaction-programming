package com.example.jacob.tddc73lab2;

import java.util.ArrayList;
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

    public PathNode getChild(String title) {
        for (PathNode child : children)
            if (child.getTitle().equals(title))
                return child;

        return null;
    }

    public boolean hasChild(String title) {
        for (PathNode child : children)
            if (child.getTitle().equals(title))
                return true;

        return false;
    }

    public boolean hasChildStartingWith(String title) {
        for (PathNode child : children)
            if (child.getTitle().startsWith(title))
                return true;

        return false;
    }

    public PathNode addChild(String title) {
        PathNode node = new PathNode(title);
        node.parent = this;

        children.add(node);
        return node;
    }
}
