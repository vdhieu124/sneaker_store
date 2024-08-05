package vn.devpro.finaljavaweb26.service;

import org.springframework.stereotype.Service;
import vn.devpro.finaljavaweb26.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService extends BaseService<User>{
    @Override
    public Class<User> clazz() {
        return User.class;
    }
    public List<User> findAllActive(){
        return super.executeNativeSql("SELECT * from tbl_user WHERE status=1;");
    }
    @Transactional
    public void deleteUserById(int id){
        super.deleteById(id);
    }
}
