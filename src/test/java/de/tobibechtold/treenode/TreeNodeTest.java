package de.tobibechtold.treenode;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

public class TreeNodeTest
{
    @Test
    public void treeNodeGetParent()
    {
        TreeNode parent = new TreeNode(null);
        PathComponent lastPathComponent = new PathComponent(new FooBar());
        parent.setLastPathComponent(lastPathComponent);
        TreeNode layer1 = new TreeNode(parent);
        TreeNode layer2 = new TreeNode(layer1);
        TreeNode layer3 = new TreeNode(layer2);

        assertNotNull(findFooBar(layer3));
    }

    @Test
    public void treeNodeGetParentJava8()
    {
        TreeNode parent = new TreeNode(null);
        PathComponent lastPathComponent = new PathComponent(new FooBar());
        parent.setLastPathComponent(lastPathComponent);
        TreeNode layer1 = new TreeNode(parent);
        TreeNode layer2 = new TreeNode(layer1);
        TreeNode layer3 = new TreeNode(layer2);

        Optional<Model> result = findFooBar2(layer3);
        assertThat(result.isPresent(), is(true));

    }

    public Optional<Model> findFooBar2(TreeNode selection)
    {
        return selection.stream().map(node -> extractLastpath(node)).filter(lastPath -> lastPath instanceof FooBar).findFirst();
    }

    public FooBar findFooBar(TreeNode selection) {
        for (TreeNode current = selection; current != null; current = current.getParentPath()) {
            Model lastPath = extractLastpath(current);
            if (lastPath instanceof FooBar) {
                return (FooBar) lastPath;
            }
        }
        return null;
    }

    private static Model extractLastpath(TreeNode parentPath) {
        return parentPath.getLastPathComponent().getModel();
    }
}
