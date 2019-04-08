package io.magicthegathering.javasdk.resource;

import java.io.Serializable;

/**
 * This file is part of mtgsdk.
 * https://github.com/MagicTheGathering/mtg-sdk-java
 * <p>
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT-license
 * <p>
 * Created by thechucklingatom on 4/07/2019.
 * <p>
 * Foreign Data class that is created from the JSON set representation.
 *
 * @author thechucklingatom
 */
public class ForeignData implements Serializable {
    private String name;
    private String text;
    private String flavor;
    private String imageUrl;
    private String language;
    private int multiverseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMultiverseId() {
        return multiverseId;
    }

    public void setMultiverseId(int multiverseId) {
        this.multiverseId = multiverseId;
    }
}
