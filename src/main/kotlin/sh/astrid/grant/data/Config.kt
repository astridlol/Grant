package sh.astrid.grant.data

data class Rank(
    val displayName: String,
    val displayItem: String = "minecraft:green_wool",
    val durations: List<String>
)

data class Config(
    val ranks: Map<String, Rank>
)