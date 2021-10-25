// Original bug: KT-29965

annotation class Ann

@Ann
fun foo(s: String = "123") {}

//// access flags 0x19
 // public final static foo(Ljava/lang/String;)V
//  @Ltest/Ann;()


// access flags 0x1009
// public static synthetic foo$default(Ljava/lang/String;ILjava/lang/Object;)V
// @Ltest/Ann;() - !!!!!!!!!!!!!! this annotation  should be absent
  
