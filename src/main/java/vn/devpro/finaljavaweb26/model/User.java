package vn.devpro.finaljavaweb26.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tbl_user")
public class User extends BaseModel implements UserDetails {
    @Column(name = "username", length = 120, nullable = false)
    private String username;
    @Column(name = "password", length = 120, nullable = false)
    private String password;
    @Column(name = "name", length = 120, nullable = false)
    private String name;
    @Column(name = "email", length = 200, nullable = true)
    private String email;
    @Column(name = "mobile", length = 60, nullable = true)
    private String mobile;
    @Column(name = "address", length = 300, nullable = true)
    private String address;
    @Column(name = "description", length = 500, nullable = true)
    private String description;

//    --------------------Mapping many-to-many
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
    private List<Role> roles = new ArrayList<Role>();
    //add and remove elements out of relational user-role list
    public void addRelationalUserRole(Role role){
        role.getUsers().add(this);
        roles.add(role);
    }
    public void removeRelationalUserRole(Role role){
        role.getUsers().remove(this);
        roles.remove(role);
    }
//    -------------------------------------------------------------------------

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<SaleOrder> saleOrders = new HashSet<SaleOrder>();
    public void addRelationalSaleOrder (SaleOrder saleOrder){
        saleOrders.add(saleOrder);
        saleOrder.setUser(this);
    }
    public void removeRelationalSaleOrder (SaleOrder saleOrder){
        saleOrders.remove(saleOrder);
        saleOrder.setUser(null);
    }
//    ----------------Mapping many to one
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "create_by")
    private User userCreateUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "update_by")
    private User userUpdateUser;
//    -----------------------Mapping one to many user to user
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateUser")
    private Set<User> userCreateUsers = new HashSet<User>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateUser")
    private Set<User> userUpdateUsers = new HashSet<User>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateCategory")
    private Set<Category> userCreateCategories = new HashSet<Category>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateCategory")
    private Set<Category> userUpdateCategories = new HashSet<Category>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateProduct")
    private Set<Product> userCreateProducts = new HashSet<Product>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateProduct")
    private Set<Product> userUpdateProducts = new HashSet<Product>();
//    -------------------------------------------------------------------------
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User getUserCreateUser() {
        return userCreateUser;
    }

    public void setUserCreateUser(User userCreateUser) {
        this.userCreateUser = userCreateUser;
    }

    public User getUserUpdateUser() {
        return userUpdateUser;
    }

    public void setUserUpdateUser(User userUpdateUser) {
        this.userUpdateUser = userUpdateUser;
    }

    public Set<User> getUserCreateUsers() {
        return userCreateUsers;
    }

    public void setUserCreateUsers(Set<User> userCreateUsers) {
        this.userCreateUsers = userCreateUsers;
    }

    public Set<User> getUserUpdateUsers() {
        return userUpdateUsers;
    }

    public void setUserUpdateUsers(Set<User> userUpdateUsers) {
        this.userUpdateUsers = userUpdateUsers;
    }

    public Set<Category> getUserCreateCategories() {
        return userCreateCategories;
    }

    public void setUserCreateCategories(Set<Category> userCreateCategories) {
        this.userCreateCategories = userCreateCategories;
    }

    public Set<Category> getUserUpdateCategories() {
        return userUpdateCategories;
    }

    public void setUserUpdateCategories(Set<Category> userUpdateCategories) {
        this.userUpdateCategories = userUpdateCategories;
    }
    //    --------------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
