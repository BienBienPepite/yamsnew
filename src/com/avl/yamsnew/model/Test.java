package com.avl.yamsnew.model;

public class Test {

	public static void main(String[] args) {
		
		Hand hand = new Hand();
		int[] diceIndex = new int[2];
		
		diceIndex[0] = 2;
		diceIndex[1] = 4;
		
		for (int i= 0 ; i < 5 ; i++) {
			System.out.print(hand.getDices()[i].getDiceValue() + " ");
		}
		
		System.out.println();
		
		System.out.println("Full : " + hand.isFullHouse());
		System.out.println("Carré : " + hand.isFourOfAKind());
		System.out.println("Suite : " + hand.isStraight());
		System.out.println("Yams : " + hand.isYahtzee());
		System.out.println("Un : " + hand.amountOf(DiceValue.ACE));
		System.out.println("Somme : " + hand.sum());
		
		System.out.println();
		
		
		hand.rollAll();
		
		for (int i= 0 ; i < 5 ; i++) {
			System.out.print(hand.getDices()[i].getDiceValue()  + " ");
		}
		
		System.out.println();
		
		System.out.println("Full : " + hand.isFullHouse());
		System.out.println("Carré : " + hand.isFourOfAKind());
		System.out.println("Suite : " + hand.isStraight());
		System.out.println("Yams : " + hand.isYahtzee());
		System.out.println("Un : " + hand.amountOf(DiceValue.ACE));
		System.out.println("Somme : " + hand.sum());

		System.out.println();
		
		
		
		hand.roll(diceIndex);
		
		for (int i= 0 ; i < 5 ; i++) {
			System.out.print(hand.getDices()[i].getDiceValue()  + " ");
		}
		
		System.out.println();
		
		System.out.println("Full : " + hand.isFullHouse());
		System.out.println("Carré : " + hand.isFourOfAKind());
		System.out.println("Suite : " + hand.isStraight());
		System.out.println("Yams : " + hand.isYahtzee());
		System.out.println("Un : " + hand.amountOf(DiceValue.ACE));
		System.out.println("Somme : " + hand.sum());

		System.out.println();
		

	}

}
