// Original bug: KT-37616

@RequiresOptIn(level = RequiresOptIn.Level.ERROR) //[EXPERIMENTAL_IS_NOT_ENABLED] This class can only be used with the compiler argument '-Xopt-in=kotlin.RequiresOptIn'![](Screenshot 2020-03-20 at 00.41.03.png)
annotation class Marker
