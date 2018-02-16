package de.tobibechtold.treenode;

public class PathComponent {

    private final Model model;

    public PathComponent() {
        this(new Model() {
        });
    }

    public PathComponent(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

}
