package dev.backd00r.identitybridge.fabric;

import dev.backd00r.identitybridge.fabric.api.IdentityBridgeApi;
import net.fabricmc.api.ModInitializer;

import dev.backd00r.identitybridge.ExampleMod;
import org.jetbrains.annotations.Nullable;

public final class ExampleModFabric implements ModInitializer {
    @Nullable
    public static IdentityBridgeApi API;
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        API = new IdentityBridgeImpl();
        ExampleMod.init();
    }
}
