# Java's besties: JVM Languages

---
# Introduction

Have you ever wondered what all the hype was around derivatives of Java, you know the host of other languages that compile
to Java Byte code but are each vastly different. 

However, for all of their differences they are able to work together seamlessly.

Let's take a look at some examples right away for some key points - let's start with a simple Word Counter written
in Groovy, Kotlin, and Java

---
#### Groovy, Kotlin, and Java Word Counter Program Example
```groovy
// Function to count words in a string
def countWords(text) {
    def words = text.split(/\s+/)        // Split the text by spaces or any whitespace
    def wordCounts = [:].withDefault { 0 }  // Create an empty map with default value of 0

    words.each { word ->
        if (word)
            wordCounts[word] += 1         // Count each word
    }
    return wordCounts
}

def text = """
    Bonjour, je m'appelle Lorem Ipsum. J'habite dans un village lointain
    où les mots dansent sur les lignes comme des étoiles dans le ciel.
    Le soleil brille, les oiseaux chantent, et tout le monde se parle
    avec des phrases élégantes et des pensées profondes.
    Chaque matin, les enfants vont à l'école pour apprendre à jongler
    avec les lettres et les sons. C'est un lieu magique où les rêves prennent vie.
"""


// Call the function and print the result
def wordCounts = countWords(text)

// Use .each {key, value} to iterate over the map
wordCounts.each { word, count ->
    println "$word: $count"
}
```

## Kotlin:

```kotlin
// Function to count words in a string
fun countWords(text: String): Map<String, Int> {
    return text.split("\\s+".toRegex())  // Split the text by spaces or any whitespace
        .filter { it.isNotEmpty() }      // Filter out any empty words
        .groupingBy { it }               // Group by the word
        .eachCount()                     // Count the occurrences of each word
}

// Sample text
val text ="""
    Bonjour, je m'appelle Lorem Ipsum. J'habite dans un village lointain
    où les mots dansent sur les lignes comme des étoiles dans le ciel.
    Le soleil brille, les oiseaux chantent, et tout le monde se parle
    avec des phrases élégantes et des pensées profondes.
    Chaque matin, les enfants vont à l'école pour apprendre à jongler
    avec les lettres et les sons. C'est un lieu magique où les rêves prennent vie.
"""


val wordCounts = countWords(text)
wordCounts.forEach { (word, count) ->
    println("$word: $count")
}
```

## Java
```java
import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    // Function to count words in a string
    public static Map<String, Integer> countWords(String text) {
        // Split the text by spaces or any whitespace
        String[] words = text.split("\\s+");

        // Create an empty map with word counts
        Map<String, Integer> wordCounts = new HashMap<>();

        // Iterate over the words
        for (String word : words) {
            if (!word.isEmpty()) { // Check if the word is not empty
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1); // Increment word count
            }
        }
        
        return wordCounts;
    }

    public static void main(String[] args) {
        // Sample text in French
        String text = """
            Bonjour, je m'appelle Lorem Ipsum. J'habite dans un village lointain
            où les mots dansent sur les lignes comme des étoiles dans le ciel.
            Le soleil brille, les oiseaux chantent, et tout le monde se parle
            avec des phrases élégantes et des pensées profondes.
            Chaque matin, les enfants vont à l'école pour apprendre à jongler
            avec les lettres et les sons. C'est un lieu magique où les rêves prennent vie.
        """;

        // Call the function and print the result
        Map<String, Integer> wordCounts = countWords(text);

        // Iterate over the map and print the word counts
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

You can quickly spot some major differences between the languages
1. Groovy had no demands for any static typing on return values or variables themselves
2. Both Groovy & Kotlin have no semicolons to terminate their statements
3. Kotlin does allow dynamic typing except for method parameters and return values
4. Both Groovy and Kotlin don't use the _new_ keyword when initializing an object.

# The Task Manager App
> written in Java, Kotlin, and Groovy!

###



