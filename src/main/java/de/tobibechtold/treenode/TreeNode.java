package de.tobibechtold.treenode;

import java.util.stream.Stream;

public class TreeNode {

    private TreeNode parent;
    private PathComponent lastPathComponent = new PathComponent();

    public TreeNode(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParentPath() {
        return parent;
    }

    public PathComponent getLastPathComponent() {
        return lastPathComponent;
    }

    public void setLastPathComponent(PathComponent lastPathComponent) {
        this.lastPathComponent = lastPathComponent;
    }

    public Stream<TreeNode> stream()
    {
        return Stream.concat(Stream.of(this), Stream.of(getParentPath()).flatMap(TreeNode::stream));
    }

}
