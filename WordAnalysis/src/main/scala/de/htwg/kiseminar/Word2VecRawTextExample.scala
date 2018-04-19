package de.htwg.textanalysis

import java.io.InputStream
import java.util

import org.deeplearning4j.models.word2vec.Word2Vec
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory


object Word2VecRawTextExample {

  def main(args: Array[String]): Unit = {

    val stream: InputStream = getClass.getResourceAsStream("/raw_sentences.txt")

    val iter = new BasicLineIterator(stream)
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
    // Prints out the closest 10 words to "day". An example on what to do with these Word Vectors.

    val lst: util.Collection[String] = vec.wordsNearestSum("mann", 10)
    println("10 Words closest to 'mann': {}", lst)

  }
}
