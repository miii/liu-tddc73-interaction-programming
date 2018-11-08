package com.example.jacob.tddc73lab2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

public class FileTreeListAdapter extends BaseExpandableListAdapter {

    private ExpandableListView listView;
    private Context context;
    private List<PathNode> nodes;
    private PathNode activeNode;

    public FileTreeListAdapter(ExpandableListView v, Context ctx, PathNode rootNode) {
        listView = v;
        context = ctx;
        nodes = rootNode.getChildren();
    }

    public void setActiveNode(PathNode path) {
        activeNode = path;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return nodes.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return nodes.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup p) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group, null);
        }

        PathNode node = (PathNode) getGroup(groupPosition);
        TextView group = view.findViewById(R.id.group);
        group.setText(node.getTitle());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return nodes.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return nodes.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child, null);
        }

        PathNode node = (PathNode) getChild(groupPosition, childPosition);
        TextView item = view.findViewById(R.id.item);
        item.setText(node.getTitle());

        if (activeNode == node)
            // Highlight node
            view.setBackgroundColor(Color.LTGRAY);
        else
            // Reset background
            view.setBackgroundColor(Color.TRANSPARENT);

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
