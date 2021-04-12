// Original bug: KT-7394


class MapMemberWriter {
  public inline final fun map(f: MapMemberWriter.() -> Unit) {
    f()
  }
}

class TestClass {
  fun foo() {
    MapMemberWriter().map {
      describeDirectory()
    }
  }

  private fun MapMemberWriter.describeDirectory(fieldName: String = "children") {
    System.out.println("Hello! $fieldName")
  }
}
