package MockServer.models;

public class User {
    private final String name;
    private final int companyId;

    public User(String name, int companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public int getCompanyId() {
        return companyId;
    }
}
