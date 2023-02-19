package ru.nsu.virtual_meetings.enity;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

//@Entity
//@Table(name = "role")
//public class Role implements GrantedAuthority {
//
//    public Role() {
//    }
//
//    public Role(Long roleId, String roleName) {
//        this.roleId = roleId;
//        this.roleName = roleName;
//    }
//
//    @Id
//    @Column(name = "roleId")
//    private Long roleId;
//
//    @Column(name = "roleName")
//    private String roleName;
//
//    @Transient
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//
//    public Long getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Long roleId) {
//        this.roleId = roleId;
//    }
//
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String getAuthority() {
//        return getRoleName();
//    }
//}

public enum Role {
    USER;
}