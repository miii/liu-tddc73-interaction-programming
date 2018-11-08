package com.example.jacob.tddc73lab2.elements;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.jacob.tddc73lab2.FileTreeListAdapter;
import com.example.jacob.tddc73lab2.PathNode;
import com.example.jacob.tddc73lab2.mediator.PathChanger;
import com.example.jacob.tddc73lab2.mediator.PathMediator;

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
        // Remove slash prefix/suffix and split into array
        String[] segments = path.replaceAll("^/(.*)/?$", "$1").split("/");

        // Traverse through all groups
        PathNode root = mediator.getRootNode();
        for (int i = 0; i < root.getChildren().size(); i++) {
            // Check if group exists
            if (root.getChild(i).getTitle().equals(segments[0])) {
                PathNode group = root.getChild(i);

                // Check if child exists
                if (segments.length != 2 || !group.hasChild(segments[1]))
                    break;

                // Set active node in adapter
                PathNode child = group.getChild(segments[1]);
                adapter.setActiveNode(child);

                // Expand the specific group, collapse the others
                for (int j = 0; j < root.getChildren().size(); j++)
                    if (j == i)
                        view.expandGroup(j);
                    else
                        view.collapseGroup(j);

                break;
            }
        }
    }
}
