/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurl.filter;

import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LeeKiatHaw
 */
public class SegURLResolverTest {
    
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
            "test/"
        }
    };

    String[] fileURLPool = {
        "/faces.file/something/something.f",
        "/faces.file/som/e.th/ing/something.file",
        "faces.file/som/e.th/ing/something.file/",
        "/javax.faces.resource/jsf.js"
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
                Assert.assertEquals(correctProgramName, SegURLResolver.resolveProgramName(mapping[i]));
            }
        }
    }

    @Test
    public void testResolveProgramNameOfFiles() {
        for (String url : fileURLPool) {
            Assert.assertEquals("", SegURLResolver.resolveProgramName(url));
        }
    }

    @Test
    public void testContainsFile() {
        for (String url : fileURLPool) {
            Assert.assertTrue(SegURLResolver.containsFile(url));
        }
    }
}
