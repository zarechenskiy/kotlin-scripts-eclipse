if (args.isEmpty()) {
    printBottles(99)
} else {
    try {
        printBottles(Integer.parseInt(args[0]))
    } catch (e: NumberFormatException) {
        System.err.println("You have passed '${args[0]}' as a number of bottles, " +
                "but it is not a valid integer number")
    }
}

fun printBottles(bottleCount: Int) {
    if (bottleCount <= 0) {
        println("No bottles - no song")
        return
    }

    println("The \"${bottlesOfBeer(bottleCount)}\" song\n")

    var bottles = bottleCount
    while (bottles > 0) {
        val bottlesOfBeer = bottlesOfBeer(bottles)
        print("$bottlesOfBeer on the wall, $bottlesOfBeer.\nTake one down, pass it around, ")
        bottles--
        println("${bottlesOfBeer(bottles)} on the wall.\n")
    }
    println("No more bottles of beer on the wall, no more bottles of beer.\n" +
            "Go to the store and buy some more, ${bottlesOfBeer(bottleCount)} on the wall.")
}

fun bottlesOfBeer(count: Int): String =
        when (count) {
            0 -> "no more bottles"
            1 -> "1 bottle"
            else -> "$count bottles"
        } + " of beer"