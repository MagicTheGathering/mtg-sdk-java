mtg-sdk-java - Magic: The Gathering Java SDK 
===========

[![Build Status](https://travis-ci.org/MagicTheGathering/mtg-sdk-java.svg?branch=master)](https://travis-ci.org/MagicTheGathering/mtg-sdk-java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.magicthegathering/javasdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.magicthegathering/javasdk)

Java SDK for using the [magicthegathering.io](http://magicthegathering.io) APIs.
Note that API use is free and does not require authentication or registration, but some rate limits apply. Read official API website for more information.

Add the dependency to your project and you're good to go! 

Integration
-------

#### Maven
```xml
<dependency>
    <groupId>io.magicthegathering</groupId>
    <artifactId>javasdk</artifactId>
    <version>0.0.1</version>
</dependency>
```
#### Gradle
```gradle
compile 'io.magicthegathering:javasdk:0.0.1'
```

#### Ivy
```xml
<dependency org="io.magicthegathering" name="javasdk" rev="0.0.1"/>
```

Usage examples
-------

#### Get a Card
```java
int multiverseId = 1;
Card card = CardAPI.getCard(multiverseId);
```

#### Get all Cards
```java
List<Card> cards = CardAPI.getAllCards();
```

#### Get a Set
```java
String setCode = "KLD";
MtgSet set = SetAPI.getSet(setCode);
```

#### Get all Sets
```java
List<MtgSet> sets = SetAPI.getAllSets();
```

License
-------
This project is licensed under [MIT license](http://opensource.org/licenses/MIT).
