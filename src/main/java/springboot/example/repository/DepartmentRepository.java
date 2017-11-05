package springboot.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.example.entity.Department;
import springboot.example.entity.User;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
