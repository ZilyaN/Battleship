package ru.zilya.logic

import java.util.*


/**
 * Исскуственный интелект
 */
class Robot(var field: FieldShip) {
    /**
     * Получить координуту X
     * @return
     */
    var x = 0

    /**
     * Получить координуту Y
     * @return
     */
    var y = 0
    var rand: Random

    /**
     * Попытка сделать выстрел
     * @return
     */
    fun tryShot(): Boolean {
        val list = ArrayList<Element>()
        for (j in 0..9) {
            for (i in 0..9) {
                val e = field.elements[i][j]!!
                if (!e.shuted) {
                    list.add(e)
                }
            }
        }
        val e = list[rand.nextInt(list.size)]
        return field.doShot(e.x, e.y)
    }

    /**
     * сделать ход
     */
    fun move(): Boolean {
        var finded = false
        // ищём раненный элемент корабля
        first@ for (j in 0..9) {
            for (i in 0..9) {
                val element = field.elements[i][j]!!
                if (element.state === ElementStates.enInjured) {
                    // нашли раненный элемент корабля
                    finded = true
                    x = i
                    y = j
                    break@first
                }
            }
        }
        /*
                if (finded) {
                        //TODO добавить здесь анализатор
                        System.out.printf("finded> %s,%s\n", x, y);

                        ArrayList<Element> list = new ArrayList<Element>();

                        // пробуем стрелять вокруг раненого корабля
                        // выбрал случайную позицию
                        for(int j = 0; j < 2; j++) {
                                int a = x;
                                int b = y + j*2-1;
                                if ( (b < 0) || (b>9) ) {
                                        continue;
                                }
                                if (!field.elements[a][b].shuted) {
                                        list.add(field.elements[a][b]);
                                        System.out.printf("> %s,%s\n", a, b);
                                }
                        }
                        for(int i = 0; i < 2; i++) {
                                int a = x + i*2-1;
                                int b = y;
                                if ( (a < 0) || (a>9) ) {
                                        continue;
                                }
                                if (!field.elements[a][b].shuted) {
                                        list.add(field.elements[a][b]);
                                        System.out.printf("> %s,%s\n", a, b);
                                }
                        }
                        Element e = list.get(rand.nextInt(list.size()));
                        return field.doShot(e.x, e.y);
                }
*/return tryShot()
    }

    /**
     * Создание робота для выполнения хода
     * создаётся для определённого поля
     */
    init {
        rand = Random()
    }
}