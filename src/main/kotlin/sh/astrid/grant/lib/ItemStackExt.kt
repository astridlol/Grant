package sh.astrid.grant.lib

import org.bukkit.inventory.ItemStack

fun ItemStack.setName(name: String): ItemStack {
    val meta = this.itemMeta
    name.let { meta.displayName(it.mm()) }
    this.itemMeta = meta
    return this
}
