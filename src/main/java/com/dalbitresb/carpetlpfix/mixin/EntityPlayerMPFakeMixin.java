package com.dalbitresb.carpetlpfix.mixin;

import com.mojang.authlib.GameProfile;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "carpet.patches.EntityPlayerMPFake", remap = false)
public class EntityPlayerMPFakeMixin {

    @Unique
    private static final Logger LOGGER = LoggerFactory.getLogger("CarpetLPFix");

    @Inject(method = "<init>(Lnet/minecraft/server/level/ServerLevel;Lcom/mojang/authlib/GameProfile;Lnet/minecraft/server/level/ClientInformation;Z)V", at = @At("RETURN"), remap = false)
    private void onFakePlayerConstructed(
            ServerLevel worldIn,
            GameProfile profile,
            ClientInformation cli,
            boolean shadow,
            CallbackInfo ci) {
        try {
            LuckPermsProvider.get()
                    .getUserManager()
                    .loadUser(profile.getId(), profile.getName())
                    .join();
            LOGGER.info("Pre-loaded LuckPerms user for fake player {} ({})", profile.getName(), profile.getId());
        } catch (IllegalStateException ignored) {
            // LuckPerms API not loaded yet or not installed
        } catch (Exception e) {
            LOGGER.warn("Failed to pre-load LuckPerms user for fake player {}: {}", profile.getName(), e.getMessage());
        }
    }
}
