# Testable Inline Specifications for your Kotlin Functions

*funspec* allows you to write inline specifications for your functions which serve as both documention and tests. It provides two infix functions, `returns` and `throws`, for defining specs, and a `test` function to test all the specs in a class.

Example:

    fun String_wrap_spec() {
        "12\n345".wrap(0) throws IndexOutOfBoundsException::class
        "12345".wrap(1) returns "1\n2\n3\n4\n5"
        "12345".wrap(2) returns "12\n34\n5"
        "12345".wrap(5) returns "12345"
        "12345".wrap(6) returns "12345"
        "1\n2345".wrap(2) returns "1\n23\n45"
        "12\n345".wrap(2) returns "12\n34\n5"
        "12\n345".wrap(3) returns "12\n345"
    }
    fun String.wrap(width: Int): String { 
        // your implementation goes here... 
    }

    // test the spec by running with ./app test
    fun main(args: Array<String>) {
        if (args[0] == "test") test()
    }

*funspec* is designed for single-file apps launched with a [hashbang](https://github.com/rogerkeays/hashbang). Because `kotlin.test` is not included in the standard library, *funspec* offers a simple way to create an app with no dependencies. To use it, just copy the functions from [funspec.kt](https://github.com/rogerkeays/funspec/blob/main/funspec.kt) to the bottom of your script.

*funspec* is fail-fast. That is, it stops with an error when one of the specs fail.

## Related Resources

  * [Vimjournal](https://github.com/rogerkeays/vimjournal): Example of a single-file Kotlin app using embedded specs for testing.
  * [hashbang](https://github.com/rogerkeays/hashbang): #! lines to start singe-file apps, with examples for Kotlin.
  * [kotlin.test](https://kotlinlang.org/api/latest/kotlin.test/): Kotlin's standard test library.
  * [More solutions looking for a problem](https://rogerkeays.com).

