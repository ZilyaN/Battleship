package ru.zilya.logic

import Ship
import ru.zilya.logic.Cell.Companion.CELL_WATER

class PlaceShipCheck(ship: Ship) : PlaceShip(ship) {
    override fun setShip(x: Int, y: Int): Boolean {
        return (if (field.isBound(x, y)) {
            field.getCell(x, y)?.getState() == Cell.CELL_WATER
        } else {
            false
        })
    }

    override fun setBorder(x: Int, y: Int): Boolean {
        return true
    }
}