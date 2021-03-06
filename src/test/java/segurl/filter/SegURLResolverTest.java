/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurl.filter;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author LeeKiatHaw
 */
public class SegURLResolverTest {
    
    String[] excludes = {
        "index.xhtml"
    };
    
    String[][] programURLPair = {
        {
            "", //programName
            //All these URL should map to above programName
            null,
            "",
            "/a.bc",
            "ab.c/"
        },
        {
            "abc.", //programName
            //All these URL should map to above programName
            "/abc. ",
            "/abc./"
        },
        {
            "test", //programName
            //All these URL should map to above programName
            "pre/test",
            "test1/pre/test"
        }
    };

    String[] fileURLPool = {
        "/faces.file/something/something.f",
        "/faces.file/som/e.th/ing/something.file",
        "faces.file/som/e.th/ing/something.file/",
        "/javax.faces.resource/jsf.js"
    };
    
    /**
     * Looks like a file but not a file
     */
    String[] excludeFiles = {
        "/index.xhtml",
        "index.xhtml",
        "/program/test/index.xhtml",
        "program/test/index.xhtml",
        "////index.xhtml",
        "/index.xhtml/program/test",
        "/program/test/index.xhtml/program/test"
    };

    public SegURLResolverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resolveProgramName method, of class SegURLResolver.
     */
    @Test
    public void testResolveProgramName() {
        for(String[] mapping : programURLPair){
            String correctProgramName = mapping[0];
            //For each of the rest of the url, assert equals to correctProgramName
            for(int i=1; i<mapping.length; i++){
                Assert.assertEquals(correctProgramName, SegURLResolver.getResolver().resolveProgramName(mapping[i]));
            }
        }
    }

    @Test
    public void testResolveProgramNameOfFiles() {
        for (String url : fileURLPool) {
            Assert.assertEquals("", SegURLResolver.getResolver().resolveProgramName(url));
        }
    }

    @Test
    public void testContainsFile() {
        for (String url : fileURLPool) {
            Assert.assertTrue(SegURLResolver.getResolver().containsFile(url));
        }
    }
    
    @Test
    public void testContainsFileExcludes() {
        
        SegURLResolver resolver = SegURLResolver.getResolver();
        for(String exclude : excludes){
            resolver = resolver.addExclude(exclude);
        }
        
        for (String url : excludeFiles){
            Assert.assertFalse(resolver.containsFile(url));
        }
    }
}
