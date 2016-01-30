/*
* ValidatorTest.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The Class ValidatorTest.
 *
 * @author ffischer
 */
public class ValidatorTest {
    
    /**
     * Instantiates a new validator test.
     */
    public ValidatorTest() {
    }
    
    /**
     * Sets the up class.
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     * Tear down class.
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
    }
    
    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test for validate docker image name default.
     */
    @Test
    public void testValidateDockerImageNameDefault() {
        System.out.println("testValidateDockerImageNameDefault");
        String name = "abc/def";
        assertTrue( "Unexpected result",Validator.validateDockerImageName(name));
    }
    
    /**
     * Test for validate docker image name short.
     */
    @Test
    public void testValidateDockerImageNameShort() {
        System.out.println("testValidateDockerImageNameDefault");
        String name = "abc/";
        assertTrue( "Unexpected result",Validator.validateDockerImageName(name));
    }
    
    /**
     * Test for validate docker image name empty.
     */
    @Test
    public void testValidateDockerImageNameEmpty() {
        System.out.println("testValidateDockerImageNameEmpty");
        String name = "";
        assertFalse( "Unexpected result",Validator.validateDockerImageName(name));
    } 
    
    /**
     * Test for validate docker image name is null.
     */
    @Test
    public void testValidateDockerImageNameIsNull() {
        System.out.println("testValidateDockerImageNameIsNull");
        String name = null;
        assertFalse( "Unexpected result",Validator.validateDockerImageName(name));
    }
    
    /**
     * Test for validate docker image name segment too long.
     */
    @Test
    public void testValidateDockerImageNameSegmentTooLong() {
        System.out.println("testValidateDockerImageNameSegmentTooLong");
        String name = "abc/a1234567890123456789012345678901234567890";
        assertFalse( "Unexpected result: Segments with a length > 39 are invalid",Validator.validateDockerImageName(name));
    }
    
    /**
     * Test for validate docker image name too long.
     */
    @Test
    public void testValidateDockerImageNameTooLong() {
        System.out.println("testValidateDockerImageNameTooLong");
        StringBuilder name = new StringBuilder("a");
        for (int i = 0; i < 40; i++) {
            name.append("1234567890");
        }
        assertFalse( "Unexpected result: String with length > 255 is invalid",Validator.validateDockerImageName(name.toString()));
    }
}
