package org.ozlevka.exam

import scala.collection.immutable.HashMap

/**
  * Created by ozlevka on 3/2/17.
  */
class WordCounter(line:String, delimeter:String) {
    val words = line.split(delimeter)
    val countedWords = words.foldLeft(HashMap.empty[String, Int]) {
        (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))
    }
}
