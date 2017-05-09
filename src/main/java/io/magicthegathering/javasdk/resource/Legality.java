package io.magicthegathering.javasdk.resource;

import java.io.Serializable;

/**
 * This file is part of mtgsdk.
 * https://github.com/MagicTheGathering/mtg-sdk-java
 * <p>
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT-license
 * <p>
 * Created by thechucklingatom on 5/8/2017.
 * <p>
 * The Legality sub object in the card json object.
 *
 * @author thechucklingatom
 *
 */
public class Legality implements Serializable{
	private String format;
	private String legality;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLegality() {
		return legality;
	}

	public void setLegality(String legality) {
		this.legality = legality;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Legality){
			return ((Legality) o).getFormat().equals(format)
					&& ((Legality) o).getLegality().equals(legality);
		}else{
			return false;
		}
	}
}
