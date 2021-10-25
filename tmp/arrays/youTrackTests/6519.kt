// Original bug: KT-17111

fun test2(xs: List<String>) = 
        xs.map { it.length }
//  L3  ALOAD 2         | $receiver$iv$iv Ljava/lang/Iterable;
//      INVOKEINTERFACE java/lang/Iterable.iterator ()Ljava/util/Iterator;
//      ASTORE 4
//  L4  ALOAD 4
//      INVOKEINTERFACE java/util/Iterator.hasNext ()Z
//      IFEQ L5
//      ALOAD 4
//      INVOKEINTERFACE java/util/Iterator.next ()Ljava/lang/Object;
//      ASTORE 5        | item$iv$iv Ljava/lang/Object;
// --- _destination_.add( ... )
//  L6  ALOAD 3         | destination$iv$iv Ljava/util/Collection;
// --- Inline lambda argument
//      ALOAD 5         | item$iv$iv Ljava/lang/Object;
//      CHECKCAST java/lang/String
//      ASTORE 6        | it Ljava/lang/String;
// --- Spill stack before inline lambda
//      ASTORE 10       | *3
// --- Inline lambda 
//  L7  ALOAD 6         | it Ljava/lang/String;
//      INVOKEVIRTUAL java/lang/String.length ()I
//  L8  INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
//      ASTORE 11       | inline lambda return value
// --- Restore stack after inline lambda
//      ALOAD 10        | *3
// --- ... back in 'mapTo' ...
//      ALOAD 11        | inline lambda return value
//      INVOKEINTERFACE java/util/Collection.add (Ljava/lang/Object;)Z
//      POP
//  L10 GOTO L4
