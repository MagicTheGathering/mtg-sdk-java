package io.magicthegathering.javasdk.resource;

import java.io.Serializable;

/**
 * This file is part of mtgsdk.
 * https://github.com/MagicTheGathering/mtg-sdk-java
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT-license
 *
 * Created by thechucklingatom on 2/16/2016.
 *
 * Card class that is created from the JSON set representation.
 *
 * @author thechucklingatom
 */
@SuppressWarnings("serial")
public class Card implements Serializable {
	private String id;
	private String layout;
	private String name;
	private String[] names;
	private String manaCost;
	private double cmc;
	private String[] colors;
	private String[] colorIdentity;
	private String type;
	private String[] supertypes;
	private String[] types;
	private String[] subtypes;
	private String rarity;
	private String text;
	private String flavor;
	private String artist;
	private String number;
	private String power;
	private String toughness;
	private int loyalty;
	private int multiverseid = -1;
	private int[] variations;
	private String imageName;
	private String watermark;
	private String border;
	private boolean timeshifted;
	private int hand;
	private int life;
	private boolean reserved;
	private String releaseDate;
	private boolean starter;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public double getCmc() {
		return cmc;
	}

	public void setCmc(double cmc) {
		this.cmc = cmc;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public String[] getColorIdentity() {
		return colorIdentity;
	}

	public void setColorIdentity(String[] colorIdentity) {
		this.colorIdentity = colorIdentity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getSupertypes() {
		return supertypes;
	}

	public void setSupertypes(String[] supertypes) {
		this.supertypes = supertypes;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String[] getSubtypes() {
		return subtypes;
	}

	public void setSubtypes(String[] subtypes) {
		this.subtypes = subtypes;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getToughness() {
		return toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public int getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public int getMultiverseid() {
		return multiverseid;
	}

	public void setMultiverseid(int multiverseid) {
		this.multiverseid = multiverseid;
	}

	public int[] getVariations() {
		return variations;
	}

	public void setVariations(int[] variations) {
		this.variations = variations;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public boolean isTimeshifted() {
		return timeshifted;
	}

	public void setTimeshifted(boolean timeshifted) {
		this.timeshifted = timeshifted;
	}

	public int getHand() {
		return hand;
	}

	public void setHand(int hand) {
		this.hand = hand;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isStarter() {
		return starter;
	}

	public void setStarter(boolean starter) {
		this.starter = starter;
	}

	/**
	 * dirty compare to in order to start testing. Just comparing the MultiverseId
	 * which should be unique.
	 * @param toCompare A {@link Card} object hopefully
	 * @return true if the same set, false if different.
	 */
	@Override
	public boolean equals(Object toCompare){
		if(toCompare instanceof Card){
			Card cardCompare = (Card)toCompare;
			return getMultiverseid() == cardCompare.getMultiverseid();
		}else{
			return false;
		}
	}

	/**
	 * Prints the Card name and multiverseId which should give enough info for debug testing.
	 * @return The cards name and Id
	 */
	@Override
	public String toString(){
		return "Card Name: " + getName() +
				"\nMultiverseId: " + getMultiverseid();
	}
}
