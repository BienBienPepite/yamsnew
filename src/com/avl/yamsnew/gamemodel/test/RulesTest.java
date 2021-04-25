package com.avl.yamsnew.gamemodel.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.avl.yamsnew.gamemodel.Game;

public class RulesTest {
	
	@Test
	public void checkNotRollingAllDicesOnFirstRoll() {
		
		Game game = new Game();
		int[] indexOfDicesToRoll = {1,2,3,4};
		
		
		String expectedError = "You must roll all the dices";
		
		
		String actualError = "";
		
		try {
			
			game.rollDices(indexOfDicesToRoll);
			
		} catch (Exception e) {
			actualError = e.getMessage();
		}
		
		assertEquals(expectedError, actualError);
		
	}
	
	@Test
	public void checkRollingAllDices() {
		
		Game game = new Game();
		int[] indexOfDicesToRoll = {0,1,2,3,4};
		
		String noError = "";
		String expectedError3 = "You can't play more than three times";
		
		String actualError0 = "";
		try {
			game.rollDices(indexOfDicesToRoll);
		} catch (Exception e) {
			actualError0 = e.getMessage();
		}
		
		String actualError1 = "";
		try {
			game.rollDices(indexOfDicesToRoll);
		} catch (Exception e) {
			actualError1 = e.getMessage();
		}
		
		String actualError2 = "";
		try {
			game.rollDices(indexOfDicesToRoll);
		} catch (Exception e) {
			actualError0 = e.getMessage();
		}
		
		String actualError3 = "";
		try {
			game.rollDices(indexOfDicesToRoll);
		} catch (Exception e) {
			actualError3 = e.getMessage();
		}
		
		assertEquals(noError, actualError0);
		assertEquals(noError, actualError1);
		assertEquals(noError, actualError2);
		assertEquals(expectedError3, actualError3);
	}
	

}
