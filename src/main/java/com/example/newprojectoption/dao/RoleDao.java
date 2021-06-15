package com.example.newprojectoption.dao;
import com.example.newprojectoption.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RoleDao extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
