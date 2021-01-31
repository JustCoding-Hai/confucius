package top.javahai.confucius.frame.base.service.model;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description:
 * @create 2021/1/9 - 17:48
 **/
public class LoginInfo {
    private String token;
    private String roles;
    private String name;
    private String avatar;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "token='" + token + '\'' +
                ", roles='" + roles + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
