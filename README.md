mtg-sdk-java
===========

[![Build Status](https://travis-ci.org/MagicTheGathering/mtg-sdk-java.svg?branch=master)](https://travis-ci.org/MagicTheGathering/mtg-sdk-java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.magicthegathering/javasdk/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.magicthegathering/javasdk)

Java SDK for using the magicthegathering.io APIs

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
