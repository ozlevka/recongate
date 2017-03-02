package org.ozlevka.exam

/**
  * Created by ozlevka on 3/2/17.
  */

import java.io.File

import scala.io.Source

//Application Entry Point
object Main {

  def main(args: Array[String])  {
    val file = new File("/home/ozlevka/newdata/Vipers_OK.csv")
    val reverseFile = new File("/home/ozlevka/newdata/Vipers_OK.reverse")
    val it = Source.fromFile(file, "UTF-8").getLines

    val my_iterator = new LineIterator(it, reverseFile)
    my_iterator.iterate()
    val top = my_iterator.getTop5

    top foreach(pair => {
       println("key: " + pair._1 + " value: " + pair._2)
    })
  }

}
