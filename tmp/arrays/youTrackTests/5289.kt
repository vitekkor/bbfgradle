// Original bug: KT-23868

  /**
   * Returns the name of the reference target element which does not depend on import statements
   * and other context (for example, the full-qualified name of the class if the reference targets
   * a Java class).
   *
   * @return the canonical text of the reference.
   */
