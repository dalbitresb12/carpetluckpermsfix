package com.dalbitresb.carpetlpfix;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.dalbitresb.carpetlpfix.platform.IPlatformHelper;

import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {

    private boolean bothModsLoaded;

    @Override
    public void onLoad(String mixinPackage) {
        IPlatformHelper platform = ServiceLoader.load(IPlatformHelper.class, MixinPlugin.class.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Failed to load IPlatformHelper service implementation"));

        boolean hasCarpet = platform.isModLoaded("carpet");
        boolean hasLuckPerms = platform.isModLoaded("luckperms");
        this.bothModsLoaded = hasCarpet && hasLuckPerms;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return this.bothModsLoaded;
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
