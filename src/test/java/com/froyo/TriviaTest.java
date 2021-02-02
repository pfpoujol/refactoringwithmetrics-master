/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froyo;

import com.google.common.util.concurrent.ExecutionError;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TriviaTest {
    private Trivia trivia;
    ArrayList players = new ArrayList();



    public TriviaTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws ExecutionError{
        trivia = new Trivia();
        trivia.add("Ludovic le bg");
        trivia.add("Lewis le grand troll");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createRockQuestion method, of class Trivia.
     */
    @Test
    public void testCreateRockQuestion() {
        assertEquals("Rock Question 1", trivia.createRockQuestion(1));
    }


    /**
     * Test of howManyPlayers method, of class Trivia.
     */
    @Test
    public void testHowManyPlayers() {
        assertNotEquals(trivia.howManyPlayers(), 0);
        //assertEquals(trivia.howManyPlayers(), trivia.howManyPlayers());
    }

    @Test
    public void testIsPlayable() {
        assertEquals(true, trivia.isPlayable());
    }

    @Test
    public void testAdd() {
        assertEquals(true, trivia.add("Thomas"));
    }

    @Test
    public void testwasCorrectlyAnswered() {
        assertEquals(true, trivia.wasCorrectlyAnswered());
    }

    @Test
    public void testwrongAnswer() {
        assertEquals(true, trivia.wrongAnswer());
    }

}