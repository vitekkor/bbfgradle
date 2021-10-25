// Original bug: KT-6620

fun test() {
  val list = """first
                  - some
                  - some   
                second
                  - item
                  - item"""

  val list1 = """first
                   - some"""

  val list2 = """first
                 second"""
  
  val list3 = """
      first
        - some
      second
        - item"""

  val list4 = """
              first
                - some
              second
                - item"""

  val text = """
                -- Title --
      Here goes some text. This text is
      Long and and very interesting.""" 
}
