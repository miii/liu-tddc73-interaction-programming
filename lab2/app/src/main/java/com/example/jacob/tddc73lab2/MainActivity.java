package com.example.jacob.tddc73lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jacob.tddc73lab2.elements.PathTextBox;
import com.example.jacob.tddc73lab2.elements.PathTree;
import com.example.jacob.tddc73lab2.mediator.PathMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Laboration 2");

        final PathNode rootNode = createTree();
        PathMediator mediator = new PathMediator(rootNode);

        PathTextBox textBox = new PathTextBox(mediator, findViewById(R.id.filePath));
        PathTree tree = new PathTree(mediator, findViewById(R.id.fileTreeList), MainActivity.this);

        mediator.add(textBox);
        mediator.add(tree);

        mediator.updatePath("/", null);
    }

    private PathNode createTree() {
        PathNode root = new PathNode("/");

        PathNode f1 = root.addChild("light");
            f1.addChild("white");
            f1.addChild("yellow");

        PathNode f2 = root.addChild("medium");
            f2.addChild("green");
            f2.addChild("yellow");
            f2.addChild("red");
            f2.addChild("blue");

        PathNode f3 = root.addChild("dark");
            f3.addChild("blue");
            f3.addChild("black");

        return root;
    }
}
