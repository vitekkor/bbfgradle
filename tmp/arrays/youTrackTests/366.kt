// Original bug: KT-12789

class Markov(prefixLength: Int = 2, trainingSet:String){
    val input: List<String>
    init{
        input = trainingSet.split(Regex("""\b"""))
    }//<-- Breakpoint here to see value of "input" after execution of last line.
}
