package com.rasenyer.notestwo.utils

object ColorPicker {

    private val colors = arrayOf("#3EB9DF", "#3685BC", "#D36280", "#f498ad", "#db5a6b", "#68a0b0",
        "#00d2ff", "#6638e2", "#a29088", "#a6ab66", "#4FB66C", "#E3AD17", "#627991", "#FA8056")

    private var colorIndex = 1

    fun getColor():String { return colors[colorIndex++ % colors.size] }

}