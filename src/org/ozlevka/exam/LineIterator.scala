package org.ozlevka.exam

import java.io.{File, FileReader, FileWriter}
import java.nio.CharBuffer
import java.nio.file.{Files, Path}

import scala.collection.immutable.HashMap
import scala.io.Source

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

  // Can't be huge amount of words. Even in Shakespeare English exists 800K words then it can be number of millions if we take
  // all existing words.
  def mergeMap(calculatedMap: HashMap[String, Int]): Unit = {
     this.wordsCount = this.wordsCount ++ calculatedMap.map {
       case (word, count) => word -> (count + this.wordsCount.getOrElse(word, 1))
     }
  }

  def getTop5 = {
    val sorted = this.wordsCount.toSeq sortBy(_._2)
    sorted.take(5) toMap
  }


  //Because files might be huge amount for reverse we need to rewrite destination file each time
  def append(line: String) = {
    val tmpFile = new File(dest.getAbsolutePath + "tmp")
    val output = new FileWriter(tmpFile)
    if(!dest.exists) {
       output.write(line)
    } else {
       output.write(line)
       Source.fromFile(dest) getLines() foreach(l => {
          output.write(l + "\n")
       })
       Files.delete(dest.toPath)
    }
    output.close()
    Files.move(tmpFile.toPath, dest.toPath)
  }
}
