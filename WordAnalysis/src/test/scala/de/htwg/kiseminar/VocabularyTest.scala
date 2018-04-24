package de.htwg.kiseminar

import java.io.{File, FileInputStream}

import org.deeplearning4j.models.word2vec.Word2Vec
import org.deeplearning4j.models.word2vec.wordstore.VocabularyHolder
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory
import org.scalatest.{FlatSpec, Matchers}

class VocabularyTest extends FlatSpec with Matchers {

  it should "add words to vocabulary" in {
    val fileInputStream = new FileInputStream(new File("src/test/resources/sample_text.txt"))

    val iter = new BasicLineIterator(fileInputStream)
    // Split on white spaces in the line to get words
    val t = new DefaultTokenizerFactory
    /* CommonPreprocessor will apply the following regex to each token: [\d\.:,"'\(\)\[\]|/?!;]+
      So, effectively all numbers, punctuation symbols and some special symbols are stripped off.
      Additionally it forces lower case for all tokens.
     */
    t.setTokenPreProcessor(new CommonPreprocessor)
    val vec: Word2Vec = new Word2Vec.Builder()
      .minWordFrequency(1)
      .iterations(4)
      .layerSize(100)
      .seed(42)
      .windowSize(10)
      .epochs(5)
      .iterate(iter)
      .tokenizerFactory(t)
      .build
    vec.buildVocab()

    val vocab = vec.getVocab
    println(vocab.vocabWords())
  }

  it should "increment count for word 'Test' in vocabulary" in {

  }
}
