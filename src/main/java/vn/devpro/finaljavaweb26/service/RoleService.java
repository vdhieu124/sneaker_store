package vn.devpro.finaljavaweb26.service;

import org.springframework.stereotype.Service;
import vn.devpro.finaljavaweb26.model.Role;

import java.util.List;

@Service
public class RoleService extends BaseService<Role> {
    @Override
    public Class<Role> clazz() {
        return Role.class;
    }

    public Role getRoleByName(String name) {

        String sql = "SELECT * FROM tbl_role WHERE name='" + name + "'";
        List<Role> roles = super.executeNativeSql(sql);

        if (roles.size() > 0) {
            return roles.get(0);
        }
        else {
            return new Role();
        }
    }
}
