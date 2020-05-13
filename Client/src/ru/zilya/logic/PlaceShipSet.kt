package ru.zilya.logic

import Ship
import ru.zilya.logic.Cell.Companion.CELL_BORDER
import ru.zilya.logic.Cell.Companion.CELL_WELL

class PlaceShipSet(private val ship: Ship) : PlaceShip(ship) {
    override fun setShip(x: Int, y: Int): Boolean {
        field.getCell(x, y)!!.setState(CELL_WELL)
        ship.getListCells().add(field.getCell(x, y)!!)
        field.getCell(x, y)!!.setShip(ship)
        return true
    }

    override fun setBorder(x: Int, y: Int): Boolean {
        if (field.isBound(x, y)) {
            field.getCell(x, y)!!.setState(CELL_BORDER)
            ship.getListBorders().add(field.getCell(x, y)!!)
        }
        return true
    }

}