// Original bug: KT-32183

interface AGraphExecutionEntity {
  val jobs: Sequence<AJobExecutionEntity>
}

interface AJobExecutionEntity {
  val meta: ProjectJob.Process<*, *>
}

sealed class ProjectJob { 
  sealed class Process<E : ProcessExecutable<E>, R : ProcessResources<R>> : ProjectJob()
  sealed class ProcessExecutable<E : ProcessExecutable<E>>
  sealed class ProcessResources<R : ProcessResources<R>>
}
