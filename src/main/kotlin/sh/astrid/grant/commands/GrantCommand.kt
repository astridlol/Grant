@file:Command(
    "grant",
    permission = "grant.use"
)

package sh.astrid .grant.commands

import me.honkling.commando.annotations.Command
import me.honkling.pocket.GUI
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import sh.astrid.grant.Grant
import sh.astrid.grant.lib.*

@Suppress("unused")
fun grant(executor: Player, target: Player) {
    val template = """
        xxxxxxxxx
        xxxxxxxxx
        xxxxxxxxx
    """.trimIndent()

    var gui = GUI(
        Grant.instance,
        template,
        "Granting a rank"
    )

    gui.put('x', ItemStack(Material.GRAY_STAINED_GLASS_PANE).setName("<red>"))

    var int = 0

    Grant.configData.ranks.forEach {
        val rank = it.value

        val rankMaterial = rank.displayItem.parseMaterial() ?: Material.GREEN_WOOL

        val item = ItemStack(rankMaterial).setName(rank.displayName)

        val meta = item.itemMeta

        meta.lore(Grant.messageData.listRankLore.map { s ->
            s.mm(arrayOf(
                    Placeholder.parsed("player", target.name),
                    Placeholder.parsed("rank", rank.displayName))
            )
        })

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        item.itemMeta = meta

        gui.put(int, item) { ev ->
            if(ev.whoClicked is Player) {
                ev.whoClicked.closeInventory()
                (ev.whoClicked as Player).performCommand("grant ${target.name} ${it.key}")
            }
        }

        int++
    }

    gui.open(executor)
}

@Suppress("unused")
fun grant(executor: Player, target: Player, rank: String) {
    val rankData = Grant.configData.ranks[rank]

    if (rankData === null) {
        executor.sendMessage("<error>Invalid rank!".mm())
        return
    }

    val template = """
        xxxxxxxxx
        xxxxxxxxx
        bxxxxxxxx
    """.trimIndent()

    val middleIndex = 12
    val numDurations = rankData.durations.size
    val startIndex = middleIndex - (numDurations - 1) / 2

    var gui = GUI(
        Grant.instance,
        template,
        "Granting ${rankData.displayName}"
    )

    gui.put('x', ItemStack(Material.GRAY_STAINED_GLASS_PANE).setName("<red>"))

    var index = startIndex

    if(numDurations == 1) index = startIndex + 1

    rankData.durations.forEach { d ->
        val time = prettyTime(d)
        val item = ItemStack(Material.GREEN_WOOL)
            .setName("<p>$time")

        val meta = item.itemMeta

        meta.lore(Grant.messageData.durationLore.map { s ->
            s.mm(arrayOf(
                Placeholder.parsed("player", target.name),
                Placeholder.parsed("rank", rankData.displayName),
                Placeholder.parsed("duration", time.lowercase()))
            )
        })

        item.itemMeta = meta

        gui.put(index, item) {
            val server = Grant.instance.server

            if(d == "forever") {
                server.dispatchCommand(server.consoleSender, "lp user ${target.name} parent set $rank");
            } else {
                server.dispatchCommand(server.consoleSender, "lp user ${target.name} parent addtemp $rank $d");
            }

            it.whoClicked.closeInventory()
            it.whoClicked.sendMessage("<success>Successfully granted ${rankData.displayName} to ${target.name}.".mm())
        }
        index++
    }

    val goBack = ItemStack(Material.ARROW).setName("<#ff8c8c><i>← Go back")
    gui.put('b', goBack) {
        if(it.whoClicked is Player) {
            it.whoClicked.closeInventory()
            (it.whoClicked as Player).performCommand("grant ${target.name}")
        }
    }

    gui.open(executor)
}

@Suppress("unused")
fun reload(executor: Player) {
    if(!executor.hasPermission("grant.reload")) {
        executor.sendMessage("<error>You must have the grant.reload permission to execute this!")
        return
    }
    Grant.instance.reloadConfigs()
    executor.sendMessage("<success>Reloaded grant config!".mm())
}