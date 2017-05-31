package springbook.user.domain;

/**
 * @author Kj Nam
 * @since 2017-05-11
 */
public class User {
    String id;
    String name;
    String password;

    Level level;
    int login;
    int recommend;

    public User() {
    }

    public User(String id, String name, String password, Level level, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Level getLevel() {
        return level;
    }

    public User setLevel(Level level) {
        this.level = level;
        return this;
    }

    public int getLogin() {
        return login;
    }

    public User setLogin(int login) {
        this.login = login;
        return this;
    }

    public int getRecommend() {
        return recommend;
    }

    public User setRecommend(int recommend) {
        this.recommend = recommend;
        return this;
    }
}
