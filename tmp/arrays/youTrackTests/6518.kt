// Original bug: KT-17111

fun test1(xs: List<String>): List<Int> {
    val result = arrayListOf<Int>()
    for (x in xs) {
        result.add(x.length)
    }
    return result
}
//  L5  ALOAD 0
//      INVOKEINTERFACE java/util/List.iterator ()Ljava/util/Iterator;
//      ASTORE 3
//  L6  ALOAD 3
//      INVOKEINTERFACE java/util/Iterator.hasNext ()Z
//      IFEQ L7
//      ALOAD 3
//      INVOKEINTERFACE java/util/Iterator.next ()Ljava/lang/Object;
//      CHECKCAST java/lang/String
//      ASTORE 2
//  L8  ALOAD 1
//      ALOAD 2
//      INVOKEVIRTUAL java/lang/String.length ()I
//      INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
//      INVOKEVIRTUAL java/util/ArrayList.add (Ljava/lang/Object;)Z
//      POP
//  L9  GOTO L6
