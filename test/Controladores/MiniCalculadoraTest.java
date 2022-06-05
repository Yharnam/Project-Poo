/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controladores;

import jframe.MiniCalculadora;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tutu5
 */
public class MiniCalculadoraTest {
    
    public MiniCalculadoraTest() {
    }

    /**
     * Test of sincero method, of class MiniCalculadora.
     */
    @Test
    public void testSincero() {
        System.out.println("sincero");
        double resultado = 0.0;
        MiniCalculadora instance = new MiniCalculadora();
        String expResult = "0";
        String result = instance.sincero(resultado);
        assertEquals(result,expResult);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of doublevalidat method, of class MiniCalculadora.
     */
    @Test
    public void testDoublevalidat() {
        System.out.println("doublevalidat");
        String number = "5.64";
        MiniCalculadora instance = new MiniCalculadora();
        double expResult = 0.0;
        double result = instance.doublevalidat(number);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class MiniCalculadora.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MiniCalculadora.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
