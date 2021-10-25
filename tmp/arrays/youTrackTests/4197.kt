// Original bug: KT-8834

fun foo(a: String, b: Int = 5): String {
  return a + b
}

fun bar(body: (String) -> String): String {
  return body("something")
}

fun fails(): String {
  return bar(::foo) // Type mismatch
}
