package by.kazlova.entity;

public enum RoleType {
    ADMIN(1, "path.page.admin.profile"), //?
    REGISTERED(2, "path.page.registered.profile"); //?

    private int value;
    private String page;

    RoleType(int value, String page) { // modif
        this.value = value;
        this.page = page;
    }

    public int getValue() {
        return value;
    }

    public String getPage() {
        return page;
    }
}
