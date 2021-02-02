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
    public void setUp() throws ExecutionError {
        trivia = new Trivia();
        trivia.add("Ludovic");

        trivia.popQuestions.add("a");
        trivia.popQuestions.add("b");

        trivia.scienceQuestions.add("a");
        trivia.scienceQuestions.add("b");

        trivia.sportsQuestions.add("a");
        trivia.sportsQuestions.add("b");

        trivia.rockQuestions.add("a");
        trivia.rockQuestions.add("b");
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
        assertEquals(false, trivia.isPlayable());
        trivia.add("Lewis");
        assertEquals(true, trivia.isPlayable());
    }

    @Test
    public void testAdd() {
        assertEquals(true, trivia.add("Thomas"));
    }

    @Test
    public void testwasCorrectlyAnswered() {
        trivia.add("Lewis");
        // not correctly answered, one gold won
        assertEquals(true, trivia.wasCorrectlyAnswered());
        // not correctly answered
        trivia.inPenaltyBox[trivia.currentPlayer] = true;
        assertEquals(true, trivia.wasCorrectlyAnswered());
        trivia.isGettingOutOfPenaltyBox = false;
        assertEquals(true, trivia.wasCorrectlyAnswered());
//        trivia.inPenaltyBox[trivia.currentPlayer] = false;
//        System.out.println(trivia.inPenaltyBox[trivia.currentPlayer]);
//        assertEquals(trivia.players.get(trivia.currentPlayer), trivia.wasCorrectlyAnswered());

    }

    @Test
    public void testwrongAnswer() {
        assertEquals(true, trivia.wrongAnswer());
    }

    @Test
    public void testaskQuestion() {
        trivia.add("Lewis");
        trivia.askQuestion();
        if (trivia.popQuestions.get(0) != "Pop Question 0") {
            assertEquals(trivia.popQuestions.get(0), "Pop Question 1");
        }
        if (trivia.scienceQuestions.get(0) != "Science Question 1") {
            assertEquals(trivia.scienceQuestions.get(0), "Science Question 0");
        }
        if (trivia.sportsQuestions.get(0)!= "Sports Question 1") {
            assertEquals(trivia.sportsQuestions.get(0), "Sports Question 0");
        }
        if (trivia.rockQuestions.get(0) != "Rock Question 1") {
            assertEquals(trivia.rockQuestions.get(0), "Rock Question 0");
        }

    }

}