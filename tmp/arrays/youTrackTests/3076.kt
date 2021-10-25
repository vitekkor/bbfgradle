// Original bug: KT-17831

fun main(args: Array<String>) {

    val t = "test"

    val indicies = intArrayOf(1, -1, 10)

    fun checkMethod(sample: String, f: (sample: String, index: Int) -> Unit) {
        for (i in indicies) {
            try {
                f(sample, i)
            } catch (e: Throwable) {
                //print("\t$e") 
                // Actually there is different exceptions. 
                // I replaced all of them by #excp# only for readable output
                print("\t#excp#")
            }
        }
    }

    print("indicies:")
    for (i in indicies) {
        print("\t$i")
    }

    print("\nsample[index]")
    checkMethod(t)  { sample, index -> print("\t${sample[index]}") }
    print("\nsample.elementAt(index)")
    checkMethod(t)  { sample, index -> print("\t${sample.elementAt(index)}") }
    print("\nsample.dropLast(index)")
    checkMethod(t)  { sample, index -> print("\t${sample.dropLast(index)}") }
    print("\nsample.padEnd(index)")
    checkMethod(t)  { sample, index -> print("\t${sample.padEnd(index)}") }
    print("\nsample.padStart(index)")
    checkMethod(t)  { sample, index -> print("\t${sample.padStart(index)}") }
    print("\nsample.take(index)")
    checkMethod(t)  { sample, index -> print("\t${sample.take(index)}") }
    print("\nsample.takeLast(index)")
    checkMethod(t)  { sample, index -> print("\t${sample.takeLast(index)}") }

    print("\nsample.removeRange(index..2)")
    checkMethod(t)  { sample, index -> print("\t${sample.removeRange(index..2)}") }
    print("\nsample.removeRange(2..index)")
    checkMethod(t)  { sample, index -> print("\t${sample.removeRange(2..index)}") }

    print("\nsample.replaceRange(index..2, \"\")")
    checkMethod(t)  { sample, index -> print("\t${sample.replaceRange(index..2, "")}") }
    print("\nsample.replaceRange(2..index, \"\")")
    checkMethod(t)  { sample, index -> print("\t${sample.replaceRange(2..index, "")}") }

    print("\nsample.subSequence(index, 2)")
    checkMethod(t)  { sample, index -> print("\t${sample.subSequence(index, 2)}") }
    print("\nsample.subSequence(2, index)")
    checkMethod(t)  { sample, index -> print("\t${sample.subSequence(2, index)}") }

    print("\nsample.slice(index..2)")
    checkMethod(t)  { sample, index -> print("\t${sample.slice(index..2)}") }
    print("\nsample.slice(2..index)")
    checkMethod(t)  { sample, index -> print("\t${sample.slice(2..index)}") }

    print("\nsample.substring(index..2)")
    checkMethod(t)  { sample, index -> print("\t${sample.substring(index..2)}") }
    print("\nsample.substring(2..index)")
    checkMethod(t)  { sample, index -> print("\t${sample.substring(2..index)}") }

    print("\nsample.substring(index,2)")
    checkMethod(t)  { sample, index -> print("\t${sample.substring(index,2)}") }
    print("\nsample.substring(2,index)")
    checkMethod(t)  { sample, index -> print("\t${sample.substring(2,index)}") }

    print("\nsample.substring(index)")
    checkMethod(t)  { sample, index -> print("\t${sample.substring(index)}") }

    println()

}
