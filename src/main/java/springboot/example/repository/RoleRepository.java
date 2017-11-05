package springboot.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.example.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
