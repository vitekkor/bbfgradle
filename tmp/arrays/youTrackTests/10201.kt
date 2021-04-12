// Original bug: KT-8396

import java.util.ArrayList

interface Presenter<V> {
    fun onBind(view: V)
    fun onUnbind(view: V)
}

open class Fragment<T : Fragment<T>> {

    private val presenters = ArrayList<Presenter<T>>()

    protected fun addPresenter(presenter: Presenter<T>) {
        presenters.add(presenter)
        presenter.onBind(this as T) // Warning: Unchecked cast: Fragment<T> to T
    }
}

class RegistrationPresenter : Presenter<RegistrationFragment> {
    override fun onBind(view: RegistrationFragment) {
        // do something
    }

    override fun onUnbind(view: RegistrationFragment) {
        // do something
    }
}

class RegistrationFragment : Fragment<RegistrationFragment>() {

    private val presenter: RegistrationPresenter = RegistrationPresenter()

    fun someFun() {
        addPresenter(presenter)
    }
}
