package xyz.najlek.elytraprotection.utils

import xyz.najlek.elytraprotection.Bootstrap

object ConfigManager {

    fun get(path: String): String {
        return Bootstrap.instance.config.getString(path)!!
    }
}