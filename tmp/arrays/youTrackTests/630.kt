// Original bug: KT-43315

fun sayHello(message: String) {
  
  // Don't say anything if message is empty, because the new line in the output is ugly.
  if (message.isEmpty()) {
    return;
  }

  println(message)
}
