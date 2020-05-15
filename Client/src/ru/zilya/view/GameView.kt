package ru.zilya.view

import java.awt.Color
import java.awt.Toolkit
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.UIManager




class GameView(private val model: GameModel) : JFrame() {
    private val controller: GameController
    private var mntmNewGame: JMenuItem? = null
    private var mntmExit: JMenuItem? = null
    private var mntmAbout: JMenuItem? = null
    private var mntm5: JMenuItem? = null
    private var mntm10: JMenuItem? = null
    private var mntm15: JMenuItem? = null
    private var mntm20: JMenuItem? = null
    var panelPlayerPlayer: PanelFieldPlayer? = null
    var panelPlayerOpponent: PanelFieldOpponent? = null
    private var panelScore: ScoreField? = null

    init {
        buildUI()
        model.register(panelPlayerPlayer!!)
        model.register(panelPlayerOpponent!!)
        model.register(panelScore!!)
        controller = GameController(this, model)
        attachController()
    }

    /**
     * обновляем данные на форме
     */
    fun update() {
        panelPlayerPlayer?.repaint()
        panelPlayerOpponent?.repaint()
        panelScore!!.repaint()
        println("view update")
    }

    /**
     * добавление слушателей формы и направляем их на контроллер
     */
    fun attachController() {
        mntmAbout!!.addActionListener(controller)
        mntmNewGame!!.addActionListener(controller)
        mntmExit!!.addActionListener(controller)
        mntm5!!.addActionListener(controller)
        mntm10!!.addActionListener(controller)
        mntm15!!.addActionListener(controller)
        mntm20!!.addActionListener(controller)
        panelPlayerOpponent?.addMouseListener(object : MouseAdapter() {
            override fun mousePressed(arg0: MouseEvent) {
                controller.mousePressed(arg0)
            }
        })
    }

    /**
     * построение интерфейса пользователя
     */
    private fun buildUI() {
        title = "SeaBattle"
        this.isResizable = false
        this.setBounds(400, 300, 483, 228)
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        this.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2)
        defaultCloseOperation = EXIT_ON_CLOSE
        this.contentPane.layout = null
        panelPlayerPlayer = PanelFieldPlayer(model.playerFieldPlayer)
        panelPlayerPlayer?.setBounds(20, 31, 151, 151)
        this.contentPane.add(panelPlayerPlayer)
        panelPlayerOpponent = PanelFieldOpponent(model.playerFieldOpponent)
        panelPlayerOpponent?.setBounds(190, 31, 151, 151)
        this.contentPane.add(panelPlayerOpponent)
        panelScore = ScoreField(model)
        panelScore!!.setBounds(370, 31, 90, 151)
        panelScore!!.background = Color(225, 225, 255)
        this.contentPane.add(panelScore)
        val menuBar = JMenuBar()
        menuBar.setBounds(0, 0, 477, 21)
        this.contentPane.add(menuBar)
        val mnGame = JMenu("Game")
        menuBar.add(mnGame)
        mntmNewGame = JMenuItem("New game")
        mnGame.add(mntmNewGame)
        mntmExit = JMenuItem("Exit")
        mnGame.add(mntmExit)
        val mnProperties = JMenu("Properties")
        menuBar.add(mnProperties)
        mntm5 = JMenuItem("5 x 5")
        mnProperties.add(mntm5)
        mntm10 = JMenuItem("10 x 10")
        mnProperties.add(mntm10)
        mntm15 = JMenuItem("15 x 15")
        mnProperties.add(mntm15)
        mntm20 = JMenuItem("20 x 20")
        mnProperties.add(mntm20)
        val mnHelp = JMenu("Help")
        menuBar.add(mnHelp)
        mntmAbout = JMenuItem("About")
        mnHelp.add(mntmAbout)
    }

}


fun main(args: Array<String>) {
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel")
    } catch (e: Throwable) {
        e.printStackTrace()
    }

    val model = GameModel(15, 15, 6)
    val view = GameView(model)
    view.isVisible = true
}