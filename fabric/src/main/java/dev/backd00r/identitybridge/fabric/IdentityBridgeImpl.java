package dev.backd00r.identitybridge.fabric;

import dev.backd00r.identitybridge.fabric.api.IdentityBridgeApi;
import draylar.identity.ability.AbilityRegistry;
import draylar.identity.ability.IdentityAbility;
import draylar.identity.api.PlayerIdentity;
import draylar.identity.api.variant.IdentityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public final class IdentityBridgeImpl implements IdentityBridgeApi {

    @Override
    public <T extends LivingEntity> void registerAbility(@NotNull EntityType<T> entityType, @NotNull IdentityAbility<T> ability) {
        AbilityRegistry.register(entityType, ability);
    }

    @Override
    public boolean transformInto(@NotNull ServerPlayerEntity player, @NotNull EntityType<? extends LivingEntity> entityType) {
        LivingEntity newIdentity = entityType.create(player.getWorld());
        if (newIdentity != null) {
            IdentityType<LivingEntity> type = new IdentityType<>(newIdentity);
            return PlayerIdentity.updateIdentity(player, type, newIdentity);
        }
        return false;
    }
}