package sh.astrid.grant

import cc.ekblad.toml.decode
import cc.ekblad.toml.tomlMapper
import me.honkling.commando.CommandManager
import org.bukkit.plugin.java.JavaPlugin
import sh.astrid.grant.data.Config
import sh.astrid.grant.data.Messages

class Grant : JavaPlugin() {
    companion object {
        lateinit var messageData: Messages
        lateinit var configData: Config
        lateinit var instance: Grant
    }

    fun reloadConfigs() {
        val mapper = tomlMapper {}
        dataFolder.mkdir()

        val fileConfigs = listOf(
            "config.toml",
            "messages.toml",
        )

        fileConfigs.forEach { configFile ->
            val configPath = dataFolder.resolve(configFile)
            saveResource(configFile, false)
            when (configFile) {
                "messages.toml" -> messageData = mapper.decode(configPath.toPath())
                "config.toml" -> configData = mapper.decode(configPath.toPath())
            }
        }
    }

    init {
        reloadConfigs()
    }

    override fun onEnable() {
        instance = this

        // Sets up the command handler
        val commandManager = CommandManager(instance)
        commandManager.registerCommands("sh.astrid.grant.commands")
    }
}