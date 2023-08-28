package sh.astrid.grant.lib

fun prettyTime(input: String): String {
    if(input.lowercase() == "forever") return "Forever"

    val regex = """(\d+)([hdwm])""".toRegex()
    val matchResult = regex.matchEntire(input)

    if (matchResult != null) {
        val (value, unit) = matchResult.destructured
        val numericValue = value.toInt()
        val unitName = when (unit) {
            "h" -> if (numericValue > 1) "Hours" else "Hour"
            "d" -> if (numericValue > 1) "Days" else "Day"
            "w" -> if (numericValue > 1) "Weeks" else "Week"
            "m" -> if (numericValue > 1) "Months" else "Month"
            else -> "Invalid"
        }

        return "$numericValue $unitName"
    }

    return "Invalid input"
}