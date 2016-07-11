fun sample(int:Int?,builder:StringBuilder):String {
    return builder.apply{
    int?.let{append(int)}
    }.toString()
}