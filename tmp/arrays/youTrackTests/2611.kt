// Original bug: KT-41220

   // common
   public typealias size_t = kotlin.ULong // short-circuited and moved to common
   public typealias ssize_t = kotlin.ULong // short-circuited and moved to common

   // target 1:
   public typealias __ssize_t = kotlin.Long

   // target 2:
   public typealias __darwin_size_t = kotlin.ULong
   public typealias __darwin_ssize_t = kotlin.Long
   