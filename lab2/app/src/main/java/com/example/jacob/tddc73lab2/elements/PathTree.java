package com.example.jacob.tddc73lab2.elements;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.jacob.tddc73lab2.PathNode;
import com.example.jacob.tddc73lab2.mediator.PathChanger;
import com.example.jacob.tddc73lab2.mediator.PathMediator;

import java.util.List;

public class PathTree extends PathChanger {

    private ExpandableListView view;
    private FileTreeListAdapter adapter;

    public PathTree(PathMediator m, View v, Context activityCtx) {
        super(m);

        // Set list adapter
        view = (ExpandableListView) v;
        adapter = new FileTreeListAdapter(view, activityCtx, mediator.getRootNode());
        final PathTree ctx = this;
        view.setAdapter(adapter);

        // Update path on click
        view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                PathNode group = mediator.getRootNode().getChild(groupPosition);
                PathNode child = group.getChild(childPosition);

                mediator.updatePath("/" + group.getTitle() + "/" + child.getTitle(), ctx);
                return true;
            }
        });
    }

    @Override
    public void receive(String path) {
        PathNode root = mediator.getRootNode();
        PathNode node = root.getTree().get(path);

        // Check if path was found
        if (node != null) {
            adapter.setActiveNode(node);
            List<PathNode> groups = root.getChildren();

            for (int i = 0; i < groups.size(); i++)
                if (groups.get(i).hasChild(node))
                    view.expandGroup(i);
                else
                    view.collapseGroup(i);
        }
    }
}
