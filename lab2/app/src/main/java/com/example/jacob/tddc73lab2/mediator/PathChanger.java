package com.example.jacob.tddc73lab2.mediator;

public abstract class PathChanger {
    protected PathMediator mediator;

    public PathChanger(PathMediator m) {
        mediator = m;
    }

    protected void updatePath(String path) {
        mediator.updatePath(path, this);
    }

    public PathMediator getMediator() {
        return mediator;
    }

    public abstract void receive(String path);
}
