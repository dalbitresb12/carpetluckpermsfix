# Carpet LuckPerms Fix

Fix for [Carpet](https://modrinth.com/mod/carpet) (and [Carpet: NeoForged](https://modrinth.com/mod/neoforge-carpet)) to pre-load [LuckPerms](https://modrinth.com/plugin/luckperms) user data when Carpet spawns a fake player.

This prevents issues where a fake player can't login or users are unable to run commands when other mods query LuckPerms when a player logins or interacts with the chat.

This incompatibility was discovered when running a server with [Solstice Essentials](https://modrinth.com/mod/solstice-essentials) installed.

It essentially injects a mixin into the `EntityPlayerMPFake` class from Carpet and ensures the fake player is registered with LuckPerms before the player is spawned into the world.

## Related issues

- [gnembon/fabric-carpet#1966](https://github.com/gnembon/fabric-carpet/issues/1966)
