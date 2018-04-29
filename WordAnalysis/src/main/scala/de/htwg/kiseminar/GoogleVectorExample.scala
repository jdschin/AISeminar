package de.htwg.kiseminar


object GoogleVectorExample {

  val modelPath = "src/main/resources/GoogleNews-vectors-negative300.bin"

  def main(args: Array[String]): Unit = {

    val googleModel = GoogleModel(modelPath)

    var continue = true
    while (continue) {
      printHelp()
      val command = readLine()
      continue = executeCommand(command, googleModel)
    }
  }

  def termCalculation(googleModel: GoogleModel) = {
    var continue = true
    while (continue) {
      println("Enter an operation:")
      val command = readLine()
      continue = isQuit(command)
      if (continue) {
        val results = googleModel.calculateTerm(command.replace(" ", ""))

        println("Result words:")
        results.foreach(println)
      }
    }
  }

  def nearestWords(googleModel: GoogleModel) = {
    var continue = true
    while (continue) {
      println("Enter a word:")
      val word = readLine()
      continue = isQuit(word)
      if (continue) {
        val results = googleModel.nearestWords(word.trim)

        println("Nearest words:")
        results.foreach(println)
      }
    }
  }

  def isQuit(command: String): Boolean = {
    command match {
      case "q" | "Q" => false
      case _ => true
    }
  }

  def executeCommand(input: String, googleModel: GoogleModel): Boolean = {
    val continue = isQuit(input)
    if (continue) {
      input match {
        case "v" | "V" => termCalculation(googleModel)
        case "s" | "S" => nearestWords(googleModel)
        case _ => println("Unknown Command")
      }
    }
    continue
  }

  private def printHelp(): Unit = {
    println("Help:")
    println("s  |  S => Enter a word and calculate the synonyms")
    println("v  |  V => Enter word operations like King - Man + Woman")
    println("q  |  Q => Quit the program")
    println
  }
}
