package vi_generics

import util.TODO
import java.util.*

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun <T, C: MutableCollection<T>> Collection<T>.partitionTo(collection1: C, collection2: C, predicate: (T) -> Boolean) : Pair<C, C> {
    for (element in this) {
        if (predicate(element)) {
            collection1.add(element)
        }
        else {
            collection2.add(element)
        }
    }

    return Pair(collection1, collection2)
}

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}