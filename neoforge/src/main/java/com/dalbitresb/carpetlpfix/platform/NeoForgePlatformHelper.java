package com.dalbitresb.carpetlpfix.platform;

import net.neoforged.fml.loading.LoadingModList;

public class NeoForgePlatformHelper implements IPlatformHelper {
  @Override
  public boolean isModLoaded(String modId) {
    return LoadingModList.get().getModFileById(modId) != null;
  }
}
