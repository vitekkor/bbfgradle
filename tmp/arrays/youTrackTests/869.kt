// Original bug: KT-43679

interface ReproStepState<StateT>

class ReproStep<StateT>

// Comment out StateT's upper bound and the problem goes away.
fun <StateT : ReproStepState<StateT>> create(
  stepList: List<ReproStep<StateT>>
) {
}

class State : ReproStepState<State>

fun repro() = create<State>(
  // The duration of the IDE freeze correlates to how many items are
  // passed to listOf here. Up to 5 are OK on my machine. If I try to
  // duplicate one of the items to make 6, the IDE starts to freeze.
  // If your machine differs, keep adding items here until it starts
  // to freeze.
  stepList = listOf(
    ReproStep(),
    ReproStep(),
    ReproStep(),
    ReproStep(),
    ReproStep(),
    ReproStep(),
  )
)
