# Grant

A simple plugin to easily grant ranks to players.

## Setup

Grant is relatively simple to set up, and can right out of the box depending on your LuckPerms setup. But, let's go into detail about how to use it.

### Adding a rank

In order to add a rank, open `Grant/config.toml`. You should see some example ranks, like the following:

```toml
[ranks.vip]
displayName = "VIP"
displayItem = "minecraft:green_wool"
durations = ["1h", "6h", "12h", "24h", "forever"]
```

The name after the dot (ranks.**vip**) is called a key, and this should be identical to the groups name within LuckPerms. Below you will find more information on the particular options:

- Display Item | The display item is the item it will display in the grant GUI, I suggest making them unique so you can easily differenciate ranks.
- Durations | A list of durations you would like this rank to have, a maximum of six is allowed. Supports hours (h), days (d), weeks (w) and months (m), as well as `forever`.