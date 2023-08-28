package sh.astrid.grant.lib

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.Material

/**
 * A String extension function to convert a string into a deserialized MiniMessage component.
 *
 * @return A MiniMessage component
 */
fun String.mm(): Component {
    val mm = MiniMessage.miniMessage()
    val errorResolver = Placeholder.parsed("error", "<#ff6e6e>⚠ <#ff7f6e>")
    val successResolver = Placeholder.parsed("success", "<g>✔ ")

    return mm.deserialize(this, *getMMResolvers(), errorResolver, successResolver)
        .decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE)
}

/**
 * A String extension function to convert a string into a deserialized MiniMessage component.
 *
 * @param additional An array of additional tag resolvers.
 * @return A MiniMessage component
 */
fun String.mm(additional: Array<TagResolver>): Component {
    val mm = MiniMessage.miniMessage()
    val errorResolver = Placeholder.parsed("error", "<#ff6e6e>⚠ <#ff7f6e>")
    val successResolver = Placeholder.parsed("success", "<g>✔ ")

    return mm.deserialize(this, *getMMResolvers(), errorResolver, successResolver, *additional)
        .decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE)
}

fun String.parseMaterial(): Material? {
    return Material.matchMaterial(this)
}

fun getMMResolvers(): Array<TagResolver> {
    return listOf<TagResolver>(
        TagResolver.resolver("p", Tag.styling(TextColor.color(255, 212, 227))),
        TagResolver.resolver("s", Tag.styling(TextColor.color(255, 181, 207))),
        TagResolver.resolver("t", Tag.styling(TextColor.color(235, 155, 183))),
        TagResolver.resolver("g", Tag.styling(TextColor.color(191, 255, 198))),
        TagResolver.resolver("y", Tag.styling(TextColor.color(240, 245, 171))),
        TagResolver.resolver("l", Tag.styling(TextColor.color(244, 212, 255))),
    ).toTypedArray()
}
