// Original bug: KT-9583

public open class A {
    protected interface B
    public open class C {
        protected open class D {
            protected interface E : B // An error is expected here...
        }
    }
}

public open class F : A.C() {
    protected open class G : A.C.D() {
        protected interface H : A.C.D.E // ...because E is visible here, but its superinterface B isn't
    }
}
