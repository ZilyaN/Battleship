package ru.zilya.logic

import java.util.*


class Ship(field: FieldShip, var size: Int) {
    var x = 0
    var y = 0
    private var dx = 0
    private var dy = 0
    var health: Int
    var state: ShipStates
    var field: FieldShip
    var elements: ArrayList<Element>

    /**
     * Генерирует случайное положение корабля и его направление
     */
    private fun GetPlace() {
        val rand = Random()
        x = rand.nextInt(10)
        y = rand.nextInt(10)
        dx = 0
        dy = 0
        if (rand.nextInt(2) == 1) {
            dx = 1
        } else {
            dy = 1
        }
    }

    /**
     * Функция обхода корабля и его окружения
     *
     * @return
     */
    private fun ByPass(tp: TrigerShip): Boolean {
        var i: Int
        var m: Int
        var n: Int

        // корабль
        i = 0
        while (i < size) {
            m = y + i * dy
            n = x + i * dx
            if (tp.Ship(m, n) === false) {
                return false
            }
            i++
        }
        // площадка сверху и снизу корабля
        i = 0
        while (i < size) {
            m = y + i * dy - dx
            n = x + i * dx - dy
            if (tp.Border(m, n) === false) {
                return false
            }
            m = y + i * dy + dx
            n = x + i * dx + dy
            if (tp.Border(m, n) === false) {
                return false
            }
            i++
        }
        // площадка слева и справа корабля
        i = -1
        while (i < 2) {
            m = y + i * dx - dy
            n = x + i * dy - dx
            if (tp.Border(m, n) === false) {
                return false
            }
            m = y + i * dx + dy * size
            n = x + i * dy + dx * size
            if (tp.Border(m, n) === false) {
                return false
            }
            i++
        }
        return true
    }

    /**
     * Проверка корректности нахождения корабля на поле
     * @return
     */
    private fun CheckPlace(): Boolean {
        return ByPass(TrigerShipCheck(this))
    }

    /**
     * Установка на поле корабля и его окружения
     */
    private fun SetShip() {
        ByPass(TrigerShipSet(this))
    }

    /**
     * создание коробля
     *
     * @param size - количество палуб
     */
    init {
        health = size
        this.field = field
        state = ShipStates.enWell
        do {
            GetPlace()
        } while (!CheckPlace())
        elements = ArrayList()
        SetShip()
    }
}