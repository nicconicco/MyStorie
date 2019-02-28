package com.mystorie.cgalves.mystorie.firsttests;

import junit.framework.Assert;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by scopus on 03/08/18.
 */

public class EmailValidatorTest {

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertFalse(!EmailValidator.isValidEmail("name@email.com"));
        assertTrue(EmailValidator.isValidEmail("name@email.com"));
        assertThat(EmailValidator.isValidEmail("name@email.com"), isA(Boolean.class));
        Assert.assertEquals(EmailValidator.isValidEmail("name@email.com"), true);
        Assert.assertNotNull(EmailValidator.isValidEmail("name@email.com"));
        Assert.assertNotSame(EmailValidator.isValidEmail("name@email.com"), "nameNotSame@email.com");
    }

    public static class EmailValidator {
        public static boolean isValidEmail(String email) {
            return email.contains("@");
        }
    }
}
