package io.magicthegathering.javasdk.resource;

import java.util.List;

/**
 * This file is part of mtgsdk.
 * https://github.com/MagicTheGathering/mtg-sdk-java
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT-license
 *
 * Created by thechucklingatom on 2/16/2016.
 *
 * Set class that is created from the JSON set representation.
 *
 * @author thechucklingatom
 */
public class MtgSet {
	private String name;
	private String code;
	private String gathererCode;
	private String oldCode;
	private String magicCardsInfoCode;
	private String releaseDate;
	private String border;
	private String type;
	private String block;
	private boolean onlineOnly;
	private List<Object> booster;
	private List<Card> cards;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGatherercode() {
		return gathererCode;
	}

	public void setGatherercode(String gatherercode) {
		this.gathererCode = gatherercode;
	}

	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	public String getMagicCardsInfoCode() {
		return magicCardsInfoCode;
	}

	public void setMagicCardsInfoCode(String magicCardsInfoCode) {
		this.magicCardsInfoCode = magicCardsInfoCode;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public boolean getOnlineOnly() {
		return onlineOnly;
	}

	public void setOnlineOnly(boolean onlineOnly) {
		this.onlineOnly = onlineOnly;
	}

	public List<Object> getBooster() {
		return booster;
	}

	public void setBooster(List<Object> booster) {
		this.booster = booster;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * dirty compare to in order to start testing. Just comparing the setGathererCode
	 * which should be unique. May change to just the code.
	 * @param toCompare A {@link MtgSet} object hopefully
	 * @return true if the same set, false if different.
	 */
	@Override
	public boolean equals(Object toCompare){
		if(toCompare instanceof MtgSet){
			MtgSet cardCompare = (MtgSet)toCompare;
			return getGatherercode().equalsIgnoreCase(cardCompare.getGatherercode());
		}else{
			return false;
		}
	}
}
