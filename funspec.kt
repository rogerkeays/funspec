//usr/bin/env [ $0 -nt $0.jar ] && kotlinc -d $0.jar $0; [ $0.jar -nt $0 ] && kotlin -cp $0.jar FunspecKt "$@"; exit 0

// example specs, run with ./funspec.kt
fun main_spec() {
    1 + 1 returns 2
    { throw Error() } throws Error::class
}
fun main() {
    test()
}

// test all specifications by running all functions in this class ending in _spec
fun test(klass: Class<*> = ::test.javaClass.enclosingClass, suffix: String = "_spec") {
    klass.declaredMethods.filter { it.name.endsWith(suffix) }.forEach { it(null) }
}
infix fun Any?.returns(result: Any?) { 
    if (this != result) throw AssertionError("Expected: $result, got $this") 
}
infix fun (() -> Any).throws(ex: kotlin.reflect.KClass<out Throwable>) { 
    try { 
        invoke() 
        throw AssertionError("Exception expected: $ex")
    } catch (e: Throwable) { 
        if (!ex.java.isAssignableFrom(e.javaClass)) throw AssertionError("Expected: $ex, got $e")
    } 
}

