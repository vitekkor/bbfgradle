// Original bug: KT-32525

    sealed class ProcessExecutable<E : ProcessExecutable<E>> {
        data class Command(val cmd: List<String>) : ProcessExecutable<Command>()

        data class KotlinScript(val id: String) : ProcessExecutable<KotlinScript>()

        sealed class ContainerExecutable : ProcessExecutable<ContainerExecutable>() {
            data class DefaultCommand(val args: List<String>) : ContainerExecutable()

            data class OverrideEntryPoint(val entryPoint: String, val args: List<String>) : ContainerExecutable()
        }

        sealed class VMExecutable : ProcessExecutable<VMExecutable>()
    }

    data class ProcessData<E : ProcessExecutable<E>>(
        val exec: ProcessExecutable<E>
    )

    sealed class Process<E : ProcessExecutable<E>> {
        abstract val data: ProcessData<E>

        data class Container(val image: String, override val data: ProcessData<ProcessExecutable.ContainerExecutable>) : Process<ProcessExecutable.ContainerExecutable>()
    }
