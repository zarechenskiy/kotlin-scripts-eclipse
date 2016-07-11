// invoke ctrl+1 on errors

open final class wrongModifiers

interface ImplementFun {
    fun toImplement()
}

class TestImplementAction : ImplementFun 

class MakeOverride : ImplementFun {
    fun toImplement() {
        throw UnsupportedOperationException()
    }
}

// ...