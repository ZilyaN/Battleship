import ru.zilya.logic.*
import java.util.*

open class Ship(field: Field, val size: Int) {
    var x = 0
    var y = 0
    var dx = 0
    var dy = 0
    private var health: Int
    var state: Int
        private set
    protected val f: Field
    private val listCells: ArrayList<Cell>
    private val listBorders: ArrayList<Cell>

    init {
        health = size
        this.f = field
        state = SHIP_WELL
        do {
            place
        } while (!checkPlace())
        listCells = ArrayList<Cell>()
        listBorders = ArrayList<Cell>()
        setShip()
        getField().setNumLiveShips(getField().getNumLiveShips() + 1)
    }

    companion object {
        const val SHIP_WELL = 1
        const val SHIP_INJURED = 2
        const val SHIP_KILLED = 3
    }

    open fun getField(): Field { return f }

    fun getListCells(): ArrayList<Cell> { return listCells }

    fun getListBorders(): ArrayList<Cell> { return listBorders }

    open fun getSize(): Int? { return health }

    open fun getState(): Int? { return state }

    private val place: Unit
        private get() {
            val rand = Random()
            x = rand.nextInt(getField().getWidth())
            y = rand.nextInt(getField().getHeight())
            dx = 0
            dy = 0
            if (rand.nextInt(2) == 1) {
                dx = 1
            } else {
                dy = 1
            }
        }

    /** Функция обхода корабля и его окружения**/
    private fun byPass(tp: PlaceShip): Boolean {
        var i: Int
        var m: Int
        var n: Int
        i = 0
        while (i < size) {

            // корабль
            m = y + i * dy
            n = x + i * dx
            if (!tp.setShip(m, n)) {
                return false
            }

            // площадка сверху и снизу корабля
            m = y + i * dy - dx
            n = x + i * dx - dy
            if (!tp.setBorder(m, n)) {
                return false
            }
            m = y + i * dy + dx
            n = x + i * dx + dy
            if (!tp.setBorder(m, n)) {
                return false
            }
            i++
        }
            //площадка слева и справа корабля
        i = -1
        while (i < 2) {
            m = y + i * dx - dy
            n = x + i * dy - dx
            if (!tp.setBorder(m, n)) {
                return false
            }
            m = y + i * dx + dy * size
            n = x + i * dy + dx * size
            if (!tp.setBorder(m, n)) {
                return false
            }
            i++
        }
        return true
    }

    private fun checkPlace(): Boolean {
        return byPass(PlaceShipCheck(this))
    }

    private fun setShip() {
        byPass(PlaceShipSet(this))
    }

    fun doShot(): Int {
        if (health != 0) {
            health--
            if (health == 0) {
                getField().setNumLiveShips(getField().getNumLiveShips() - 1)
                state = SHIP_KILLED
                for (e in listCells) {
                    e.setState(Cell.CELL_KILLED)
                }
                for (e in listBorders) {
                    e.setState(Cell.CELL_MISSED)
                    e.setMark(true)
                }
                return Field.SHUT_KILLED
            } else {
                state = SHIP_INJURED
            }
        }
        return Field.SHUT_INJURED
    }
}