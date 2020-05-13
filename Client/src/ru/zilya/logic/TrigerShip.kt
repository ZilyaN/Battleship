package ru.zilya.logic

abstract class TrigerShip(ship: Ship) {
    var field: FieldShip
    abstract fun Ship(m: Int, n: Int): Boolean
    abstract fun Border(m: Int, n: Int): Boolean

    init {
        field = ship.field
    }
}