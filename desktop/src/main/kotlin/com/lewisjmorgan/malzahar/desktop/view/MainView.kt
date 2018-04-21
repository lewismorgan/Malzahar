package com.lewisjmorgan.malzahar.desktop.view

import javafx.geometry.Pos
import tornadofx.*

/**
 * Created by lewis on 4/20/18.
 */
class MainView : BaseView("Malzahar") {
  override val root = borderpane {
    center {
      padding = insets(5.0, 5.0, 5.0, 5.0)
      vbox {
        spacing = 5.0
        hbox {
          spacing = 5.0
          alignment = Pos.CENTER
          label("Summoner") {
            alignment = Pos.CENTER_LEFT
          }
          textfield("Summoner Name")
          button("Find")
        }
        hbox {
          spacing = 25.0
          alignment = Pos.CENTER
          vbox {
            spacing = 5.0
            label("SummonerName")
            label("SummonerLevel")
          }
          vbox {
            spacing = 5.0
            label("Last Played Champion")
            label("LastPlayedChampion")
          }
        }
      }
    }
  }
}