package com.lewisjmorgan.malzahar.riot.desktop.view

import javafx.scene.Node
import mu.KLogging
import tornadofx.*

/**
 * The view for which all views in the application extend from.
 *
 * Created by lewis on 4/20/18.
 */
abstract class BaseView @JvmOverloads constructor(title: String? = null, icon: Node? = null)
  : View(title, icon) {
  companion object : KLogging()
}