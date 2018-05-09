package com.lewisjmorgan.malzahar.riot.desktop

import com.lewisjmorgan.malzahar.riot.desktop.view.MainView
import javafx.stage.Stage
import mu.KLogging
import tornadofx.*

/**
 * Created by lewis on 4/20/18.
 */
class MalzaharDesktopApp : App(MainView::class) {
  companion object : KLogging()

  init {
    logger.info("Starting app.")
  }

  override fun start(stage: Stage) {
    //riot = Riot(parameters.named["apiKey"].toString())
    super.start(stage)
  }
}

//var riot = Riot("")
