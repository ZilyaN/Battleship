package ru.zilya.view

import java.awt.Color
import javax.swing.*


class GameView(private val model: GameModel) : JFrame() {

    //private var GameController: controller
    private var mntmNewGame: JMenuItem? = null
    private var mntmExit: JMenuItem? = null
    private var mntmAbout: JMenuItem? = null
    private var panelPlayerA: PanelFieldA? = null
    private var panelPlayerB: PanelFieldB? = null
    private var panelScore: ScoreField? = null

    /**
     * обновляем данные на форме
     */
    fun update() {
        panelPlayerA.repaint()
        panelPlayerB.repaint()
        panelScore.repaint()
        println("view update")
    }
    /**
     * добавление слушателей формы и направляем их на контроллер
     */
//        fun attachController() {
//            mntmAbout.addActionListener((ActionListener) controller);
//            mntmNewGame.addActionListener((ActionListener) controller);
//            mntmExit.addActionListener((ActionListener) controller);
//            panelPlayerB.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent arg0) {
//                   // controller.mousePressed(arg0);
//                }
//            });
//        }
    /**
     * построение интерфейса пользователя
     */
    private fun buildUI() {
        title = "SeaBattle"
        this.isResizable = false
        this.setBounds(400, 300, 483, 228)
        defaultCloseOperation = EXIT_ON_CLOSE
        this.contentPane.layout = null
        panelPlayerA = PanelFieldA(model)
        panelPlayerA.setBounds(20, 31, 151, 151)
        this.contentPane.add(panelPlayerA)
        panelPlayerB = PanelFieldB(model)
        panelPlayerB.setBounds(190, 31, 151, 151)
        this.contentPane.add(panelPlayerB)
        panelScore = ScoreField(model)
        panelScore.setBounds(370, 31, 90, 151)
        panelScore.setBackground(Color(225, 225, 255))
        this.contentPane.add(panelScore)
        val menuBar = JMenuBar()
        menuBar.setBounds(0, 0, 477, 21)
        this.contentPane.add(menuBar)
        val mnGame = JMenu("Game")
        menuBar.add(mnGame)
        mntmNewGame = JMenuItem("New game")
        mnGame.add(mntmNewGame)
        val mntmProperties = JMenuItem("Properties")
        mnGame.add(mntmProperties)
        mntmExit = JMenuItem("Exit")
        mnGame.add(mntmExit)
        val mnHelp = JMenu("Help")
        menuBar.add(mnHelp)
        mntmAbout = JMenuItem("About")
        mnHelp.add(mntmAbout)
    }

    companion object {
        private const val serialVersionUID = 1L
    }

    /**
     * Создание представления главной формы
     * @param model - модель главной формы
     */
    init {
        buildUI()
        model.register(panelPlayerA)
        model.register(panelPlayerB)
        model.register(panelScore)
        //this.controller=new GameController(this, model);
        //attachController();
    }
}


fun main() {
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel")
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    val model = GameModel()
    val view = GameView(model)
    view.isVisible = true
}
