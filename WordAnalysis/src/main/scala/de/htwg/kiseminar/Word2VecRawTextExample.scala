package de.htwg.textanalysis

import java.io.{File, FileInputStream}
import java.util

import org.deeplearning4j.models.word2vec.Word2Vec
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory


object Word2VecRawTextExample {

  def main(args: Array[String]): Unit = {

    val fileInputStream = new FileInputStream(new File("src/main/resources/raw_sentences.txt"))

    val iter = new BasicLineIterator(fileInputStream)
    // Split on white spaces in the line to get words
    val t = new DefaultTokenizerFactory
    /*
        CommonPreprocessor will apply the following regex to each token: [\d\.:,"'\(\)\[\]|/?!;]+
        So, effectively all numbers, punctuation symbols and some special symbols are stripped off.
        Additionally it forces lower case for all tokens.
     */
    t.setTokenPreProcessor(new CommonPreprocessor)

    val vec: Word2Vec = new Word2Vec.Builder()
      .minWordFrequency(5)
      .iterations(4)
      .layerSize(100)
      .seed(42)
      .windowSize(20)
      .iterate(iter)
      .tokenizerFactory(t)
      .build

    vec.fit()

    val word = "day"
    val lst: util.Collection[String] = vec.wordsNearestSum(word, 10)
    println(s"10 Words closest to '$word': $lst")

  }
}
