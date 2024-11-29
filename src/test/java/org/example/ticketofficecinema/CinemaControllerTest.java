package org.example.ticketofficecinema;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CinemaControllerTest {

    @Test
    public void testValidEmail_Valid() {
        CinemaController controller = new CinemaController();
        // Проверка email
        assertTrue(controller.isValidEmail("test@example.com"));
        assertTrue(controller.isValidEmail("user.name@domain.co"));
        assertTrue(controller.isValidEmail("123user@sub.domain.org"));
    }

    @Test
    public void testValidEmail_Invalid() {
        CinemaController controller = new CinemaController();
        // Проверка неправильных адресов
        assertFalse(controller.isValidEmail("plainaddress"));
        assertFalse(controller.isValidEmail("missing@domain"));
        assertFalse(controller.isValidEmail("missing.domain@"));
    }

}
