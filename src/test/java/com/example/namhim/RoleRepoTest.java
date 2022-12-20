package com.example.namhim;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.namhim.models.Role;
import com.example.namhim.repos.RoleRepo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepoTest {

	@Autowired
	private RoleRepo roleRepo;
	 
	@Test
	public void testCreateRole() {
		Role admin = new Role("ROLE_ADMIN");
		Role editor = new Role("ROLE_EDITOR");
		Role customer = new Role("ROLE_CUSTOMER");
		
		roleRepo.saveAll(List.of(admin, editor, customer));

        long numberOfRoles = roleRepo.count();
        assertEquals(3, numberOfRoles);
		
	}
	
	@Test
	public void testListRoles() {
		List<Role> listRoles = roleRepo.findAll();
		assertThat(listRoles.size()).isGreaterThan(0);

		listRoles.forEach(System.out::println);
	}
}
