package org.ozlevka.exam

import java.io.{File, RandomAccessFile}

import scala.collection.immutable.HashMap

/**
  * Created by ozlevka on 3/2/17.
  */
class LineIterator(it: Iterator[String], dest: File) {
  var wordsCount = collection.immutable.HashMap[String, Int]()

  def iterate(): Unit = {
    it foreach(line => {
      val counted = new WordCounter(line reverse, ",")
      this.mergeMap(counted.countedWords)
      this.append(line)
    })
  }

  def mergeMap(calculatedMap: HashMap[String, Int]): Unit = {
     this.wordsCount = this.wordsCount ++ calculatedMap.map {
       case (word, count) => word -> (count + this.wordsCount.getOrElse(word, 1))
     }
  }

  def getTop5 = {
    val sorted = this.wordsCount.toSeq sortBy(_._2)
    sorted.take(5) toMap
  }

  def append(line: String) = {

  }
}
