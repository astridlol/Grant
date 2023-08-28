# Grant

A simple plugin to easily grant ranks to players.

## Setup

Grant is relatively simple to set up, and can work right out of the box depending on your LuckPerms setup. But, let's go into detail about how to set it up further.

### Adding a rank

In order to add a rank, open `Grant/config.toml`. You should see some example ranks, like the following:

```toml
[ranks.vip]
displayName = "VIP"
displayItem = "minecraft:green_wool"
durations = ["1h", "6h", "12h", "24h", "forever"]
```

The name after the dot (ranks.**vip**) is called a key, and this should be identical to the groups name within LuckPerms. Below you will find more information on the particular options:

- Display Item | The display item is the item it will display in the grant GUI, I suggest making them unique so you can easily differentiate ranks.
- Durations | A list of durations you would like this rank to have, a maximum of six is allowed. Supports hours (h), days (d), weeks (w) and months (m), as well as `forever`.

After you've made your changes, you can reload the current configurations using `/grant reload`.

## Downloading

In order to download Grant, you can go to the Polymart page [here](https://polymart.org/resource/grant.4717) and purchase it for $2.50! This way you get updates immediately, and you support my work :) But, if you feel like being a meanie, you can always compile the source yourself.