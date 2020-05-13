package ru.zilya.logic

import Ship

abstract class PlaceShip(ship: Ship) {
    protected var field: Field

    abstract fun setShip(x: Int, y: Int): Boolean
    abstract fun setBorder(x: Int, y: Int): Boolean

    init {
        this.field = ship.getField()
    }
}