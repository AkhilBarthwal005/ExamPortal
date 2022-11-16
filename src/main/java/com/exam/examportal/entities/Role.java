package com.exam.examportal.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    private int roleId;
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<User> user;

    public Role() {
    }

    public Role(int roleId, String roleName, Set<User> user) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.user = user;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
