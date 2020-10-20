package com.github.diogosmendonca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.scpl.model.Node;
import br.scpl.view.Search;

/**
 * Structural and execution checking of elements of a singleton.
 */
public class SingletonTest
{

    private static String className = "./src/main/java/com/github/diogosmendonca/MySingleton.java";
    private static String patternsPath = "./src/test/resources/";

    @ParameterizedTest
    @ValueSource(strings = {"MissingInstanceSingleton", "DefaultConstructorSingleton", "MissingGetInstanceSingleton", 
    "NotStaticInstance", "NotPrivateInstance", "NotPrivateConstructor", "PrivateGetInstance", "NotStaticGetInstance", 
    "GetInstanceReturnsNull", "NotVerifyForNullBerforeInstantiate", "InstanceNullAssignment"}) 
    public void patternChecking(String pattern)
    {        
        List<Node> retorno = Search.searchOccurrences(className, patternsPath + pattern + ".java");
        if(retorno != null){
            for(Node n: retorno){
                assertFalse(n.isToReturn());;
            }
        }else{
            fail("Error during test execution.");
        }
    }

    @Test
    public void executionTest(){

        java.lang.reflect.Method method;
        try {
            method = MySingleton.class.getMethod("getInstance");
            assertNotNull(method.invoke(null));
            assertSame(method.invoke(null), method.invoke(null));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Execution of getInstance returned an exception");
        }
    }

}
