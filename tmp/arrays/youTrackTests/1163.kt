// Original bug: KT-38262

class Bob {
  public fun then(): Int {
    println("Hi Bob");
    this.catch();
    return 42;
  }

  public fun catch(): Int {
    println("Hi Joe");
    this.then();
    return 24;
  }
}
