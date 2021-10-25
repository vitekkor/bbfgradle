// Original bug: KT-30629

fun main(args: Array<String>) {
    DerivedFragment().onActivityCreated()
}

abstract class BaseFragment<T : BaseViewModel> {
    lateinit var viewModel: T

    open fun onActivityCreated() {
        viewModel = retrieveViewModel()
    }

    abstract fun retrieveViewModel(): T
}

class DerivedFragment : BaseFragment<DerivedViewModel>() {
    override fun onActivityCreated() {
        super.onActivityCreated()
        // Change to this for function refs to work:
        //        val vm = viewModel
        //        bind(vm::property)

        // Comment out to work:
        bind(viewModel::property)
    }

    override fun retrieveViewModel(): DerivedViewModel = DerivedViewModel()
    inline fun <T> bind(crossinline viewModelGet: () -> T?) {
        setOnFocusChangeListener { viewModelGet() }
    }

    fun setOnFocusChangeListener(l: () -> Unit) {
    }
}

abstract class BaseViewModel
class DerivedViewModel : BaseViewModel() {
    var property: String? = null
}
