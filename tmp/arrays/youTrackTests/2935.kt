// Original bug: KT-23324

inline suspend fun susp (x: suspend () -> Unit) {} // Warning: Redundant 'suspend' modifier: lambda parameters of suspend function type uses existing continuation
