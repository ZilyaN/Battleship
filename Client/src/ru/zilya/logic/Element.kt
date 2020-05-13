package ru.zilya.logic

class Element(x: Int, y: Int) {
    var state: ElementStates
    var ship: Ship? = null
    var shuted: Boolean
    var x: Int
    var y: Int

    init {
        state = ElementStates.enWater
        shuted = false
        this.x = x
        this.y = y
    }
}