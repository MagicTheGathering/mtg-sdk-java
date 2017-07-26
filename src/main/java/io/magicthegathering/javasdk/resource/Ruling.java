package io.magicthegathering.javasdk.resource;

import java.io.Serializable;

/**
 * This file is part of mtgsdk.
 * https://github.com/MagicTheGathering/mtg-sdk-java
 * <p>
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT-license
 * <p>
 * Created by BeMacized on 7/23/2017.
 * <p>
 * The Ruling sub object in the card json object.
 *
 * @author BeMacized
 */
public class Ruling implements Serializable {

  private String date;
  private String text;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Ruling ruling = (Ruling) o;

    if (date != null ? !date.equals(ruling.date) : ruling.date != null) return false;
    return text != null ? text.equals(ruling.text) : ruling.text == null;
  }
}
