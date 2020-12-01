package it.unipd.tos.model;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest{

    @Test
    public void User_GetterMethods_Test(){
        User user = new User(12, "Federico Ballarin", "via Polli", LocalDate.of(1996,06,06));

        assertEquals(12, user.getId());
        assertEquals("Federico Ballarin", user.getName());
        assertEquals("via Polli", user.getAddress());
        assertEquals(LocalDate.of(1996,06,06), user.getBirthDate());
    } 
    
    @Test
    public void User_IsUnder18_Test(){
        User adultUser = new User(12, "Federico Ballarin", "via Polli", LocalDate.of(1996,06,06));
        User childUser = new User(12, "Luca Abbiate", "via Marzi", LocalDate.of(2012,11,27));

        assertFalse(adultUser.isUnder18());
        assertTrue(childUser.isUnder18());
    } 
}