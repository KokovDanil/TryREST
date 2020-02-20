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

    public boolean equalsUser(User anotherUser){
        if (!this.getName().equals(anotherUser.getName())) return false;
        if (this.getCompanyId() != anotherUser.getCompanyId()) return false;
        return true;
    }
}
