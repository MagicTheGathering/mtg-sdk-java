mtg-sdk-java
===========

[![Build Status](https://travis-ci.org/MagicTheGathering/mtg-sdk-java.svg?branch=master)](https://travis-ci.org/MagicTheGathering/mtg-sdk-java)

Java SDK for using the magicthegathering.io APIs

## Usage examples

### Get a Card
```java
int multiverseId = 1;
Card card = CardAPI.getCard(multiverseId);
```

### Get all Cards
```java
List<Card> cards = CardAPI.getAllCard();
```

### Get a Set
```java
String setCode = "KLD";
MtgSet set = SetAPI.getSet(setCode);
```

### Get all Sets
```java
List<MtgSet> sets = SetAPI.getAllSet();
```
