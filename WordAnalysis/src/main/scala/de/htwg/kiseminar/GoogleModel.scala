package de.htwg.kiseminar

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer
import org.deeplearning4j.models.word2vec.Word2Vec

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

case class GoogleModel(modelPath: String) {

  val word2VecModel: Word2Vec = WordVectorSerializer
    .readWord2VecModel(modelPath, true)

  def calculateTerm(term: String): Iterable[String] = {
    var positive = new ListBuffer[String]()
    var negative = new ListBuffer[String]()

    term.split("\\+").foreach(t =>
      if (t.length.equals(1)) {
        positive += t
      } else {
        val s = t.split("\\-")
        positive += s(0)
        s.slice(1, s.size).foreach(
          negative += _
        )
      })
    val positiveColl = positive.toList.asJavaCollection
    val negativeColl = negative.toList.asJavaCollection
    word2VecModel.wordsNearest(positiveColl,
      negativeColl, 5).asScala
  }

  def nearestWords(word: String): Iterable[String] = {
    word2VecModel.wordsNearest(word, 5).asScala
  }
}
