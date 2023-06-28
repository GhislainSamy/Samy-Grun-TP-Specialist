package com.samy_grun_test;

import com.samy_grun.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserEntityTest {

	@Test
	public void testToStringUser1() {
	    UserEntity user = new UserEntity();
	    user.setId(1L);
	    user.setName("ghislain");
	    user.setSurname("samy");
	    user.setEmail("ghislain.samy@specialist.itis");
	    user.setRole(" Utilisateur");
	    user.setPassword("1234");

	    String expected = "User {id: 1, name: ghislain, surname: samy, email: ghislain.samy@specialist.itis,password: ***********}";
	    String actual = user.toString();

	    Assertions.assertEquals(expected, actual);
	}

    @Test
    public void testGettersAndSettersUser1() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("ghislain");
        user.setSurname("samy");
        user.setEmail("ghislain.samy@specialist.itis");
        user.setRole("Utilisateur");
        user.setPassword("1234");

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("ghislain", user.getName());
        Assertions.assertEquals("samy", user.getSurname());
        Assertions.assertEquals("ghislain.samy@specialist.itis", user.getEmail());
        Assertions.assertEquals("Utilisateur", user.getRole());
        Assertions.assertEquals("1234", user.getPassword());
    }
    
    @Test
    public void testToStringUser2() {
        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setName("alexis");
        user.setSurname("grun");
        user.setEmail("alexis.grun@specialist.itis");
        user.setRole("Utilisateur");
        user.setPassword(" 5678");

        String expected = "User {id: 2, name: alexis, surname: grun, email: alexis.grun@specialist.itis,password: ***********}";
        String actual = user.toString();

        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGettersAndSettersUser2() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("alexis");
        user.setSurname("grun");
        user.setEmail("alexis.grun@specialist.itis");
        user.setRole("Utilisateur");
        user.setPassword("5678");

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("alexis", user.getName());
        Assertions.assertEquals("grun", user.getSurname());
        Assertions.assertEquals("alexis.grun@specialist.itis", user.getEmail());
        Assertions.assertEquals("Utilisateur", user.getRole());
        Assertions.assertEquals("5678", user.getPassword());
    }

}