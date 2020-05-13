package ru.zilya.logic

import java.util.*


class FieldShip {
    var elements: Array<Array<Element?>>
    var ships: ArrayList<Ship>? = null

    /**
     * Заполняем поле водой и расставляем корабли
     */
    fun PutShip() {

        // заполняем поле водой
        for (j in 0..9) {
            for (i in 0..9) {
                val element = elements[i][j]
                element!!.state = ElementStates.enWater
                element.shuted = false
            }
        }

        // заполняем поле короблями
        ships = ArrayList()
        for (i in 4 downTo 1) {
            for (j in 5 - i downTo 1) {
                val ship = Ship(this, i)
                ships!!.add(ship)
            }
        }

        // удаляем окружение коробля
        for (j in 0..9) {
            for (i in 0..9) {
                val element = elements[i][j]
                if (element!!.state === ElementStates.enBorder) {
                    element!!.state = ElementStates.enWater
                }
            }
        }
    }

    /**
     * Сделать выстрел
     * @param x
     * @param y
     * @return
     * Возвращает результат выстрела
     */
    fun doShot(x: Int, y: Int): Boolean {
        var shot = false
        val state = GetElement(x, y)
        elements[x][y]!!.shuted = true
        if (state === ElementStates.enWell) {
            shot = true
            val ship = elements[x][y]!!.ship
            if (ship!!.health !== 0) {
                ship!!.health--
                if (ship.health === 0) {
                    ship.state = ShipStates.enKilled
                    for (e in ship.elements) {
                        e.state = ElementStates.enKilled
                    }
                } else {
                    ship.state = ShipStates.enInjured
                    elements[x][y]!!.state = ElementStates.enInjured
                }
            }
        } else {
            if (state === ElementStates.enBorder ||
                state === ElementStates.enWater
            ) {
                SetElement(x, y, ElementStates.enMissed)
            }
        }
        return shot
    }

    /**
     * Проверка координат в пределах поля
     * @param x
     * @param y
     * @return - boolean
     */
    fun isBound(x: Int, y: Int): Boolean {
        return !(x < 0 || x > 9 || y < 0 || y > 9)
    }

    /**
     * Получить по координатам тип элемента
     *
     * @param x
     * @param y
     * @return
     */
    fun GetElement(x: Int, y: Int): ElementStates {
        return if (isBound(x, y)) {
            elements[x][y]!!.state
        } else {
            ElementStates.enEmpty
        }
    }

    /**
     * Установить по координатам тип элемента
     * @param x
     * @param y
     * @param state
     */
    fun SetElement(x: Int, y: Int, state: ElementStates?): Boolean {
        if (isBound(x, y)) {
            elements[x][y]!!.state = state!!
        }
        return true
    }

    /**
     * отрисовка поля
     */
    fun Draw() {
        for (j in 0..9) {
            for (i in 0..9) {
                System.out.print(elements[i][j].toString())
            }
            println()
        }
    }

    /**
     * создание поля с кораблями
     */
    init {
        // заполняем поле элементами воды
        elements = Array(10) { arrayOfNulls(10) }
        for (j in 0..9) {
            for (i in 0..9) {
                elements[i][j] = Element(i, j)
            }
        }
        PutShip()
    }
}