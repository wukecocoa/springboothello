
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.util.Assert;
import springboot.example.entity.Department;
import springboot.example.entity.Role;
import springboot.example.entity.User;
import springboot.example.repository.DepartmentRepository;
import springboot.example.repository.RoleRepository;
import springboot.example.repository.UserRepository;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MySqlTest {

    private static Logger logger = LoggerFactory.getLogger(MySqlTest.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void initData() {
        userRepository.deleteAll();
        departmentRepository.deleteAll();
        roleRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.notNull(department.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId());


        User user = new User();
        user.setName("user");
        user.setCreateDate(new Date());
        user.setDepartment(department);
        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles);
        user.setRoles(roles);
        userRepository.save(user);

        Assert.notNull(user);

    }

    @Test
    public void findPage(){
        Pageable pageable=new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        Page<User> page=userRepository.findAll(pageable);
        Assert.notNull(page);

        for (User user:page.getContent()){
            logger.info("user information:"+user);
        }
    }

}
