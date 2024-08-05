package vn.devpro.finaljavaweb26.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_role")
public class Role extends BaseModel implements GrantedAuthority {
    @Column(name = "name", length = 300, nullable = false)
    private String name;
    @Column(name = "description", length = 500, nullable = true)
    private String description;
//    ------------------------------------------------------------------------------
    // First_side: owner (user has some roles)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<User>();
//    -------------------------------------------------------------------------------

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //    ----------------------------------------------------------------------------------

    @Override
    public String getAuthority() {
        return this.name;
    }
}
