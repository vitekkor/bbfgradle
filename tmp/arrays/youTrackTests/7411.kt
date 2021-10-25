// Original bug: KT-27360

package testlib

@Experimental
annotation class ExperimentalWithError

@Experimental(Experimental.Level.WARNING)
annotation class ExperimentalWithWarning

@ExperimentalWithError
class SeverelyExperimentalClass

@ExperimentalWithWarning
class SlightlyExperimentalClass
