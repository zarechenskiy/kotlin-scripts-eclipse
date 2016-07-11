val map = hashMapOf<String, Int>().apply {
    put("one", 1)
    put("two", 2)
}

for ((key, value) in map) {
    println("key = $key, value = $value")
}

