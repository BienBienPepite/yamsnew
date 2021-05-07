package com.avl.yamsnew.gamemodel;

public enum Figure {

	ACE         (Section.UPPER,   0),
	TWO         (Section.UPPER,   1),
	THREE       (Section.UPPER,   2),
	FOUR        (Section.UPPER,   3),
	FIVE        (Section.UPPER,   4),
	SIX         (Section.UPPER,   5),
	MIN         (Section.MEDIUM, -1),
	MAX         (Section.MEDIUM, -1),
	FULLHOUSE   (Section.LOWER,  -1),
	FOUROFAKIND (Section.LOWER,  -1),
	STRAIGHT    (Section.LOWER,  -1),
	YAHTZEE     (Section.LOWER,  -1);
	
	private Section section;
	private int order;
	
	Figure(Section section, int order){
		this.section = section;
		this.order = order;
	}
	
	public Section getSection() {
		return this.section;
	}
	
	public int getOrder() {
		return this.order;
	}
}
