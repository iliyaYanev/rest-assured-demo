package dao;

public class Resources {
    private Link self;
    private Link edit;
    private Link avatar;

    public Resources() {
    }

    public Resources(Link self, Link edit, Link avatar) {
        this.self = self;
        this.edit = edit;
        this.avatar = avatar;
    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getEdit() {
        return edit;
    }

    public void setEdit(Link edit) {
        this.edit = edit;
    }

    public Link getAvatar() {
        return avatar;
    }

    public void setAvatar(Link avatar) {
        this.avatar = avatar;
    }
}
