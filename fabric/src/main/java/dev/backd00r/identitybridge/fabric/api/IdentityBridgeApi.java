package dev.backd00r.identitybridge.fabric.api;

import draylar.identity.ability.IdentityAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

/**
 * api de "puente" para el identity
 * se puede registrar habilidades y transformar a personas
 */
public interface IdentityBridgeApi {

    /**
     * registra una habilidad para una entidad
     *
     * @param entityType tipo de entidad
     * @param ability instancia de la entidad (clase)
     * @param <T> tipo de entidad
     */
    <T extends LivingEntity> void registerAbility(@NotNull EntityType<T> entityType, @NotNull IdentityAbility<T> ability);

    /**
     * no necesito explicar esto xd
     *
     * @param player
     * @param entityType
     */
    boolean transformInto(@NotNull ServerPlayerEntity player, @NotNull EntityType<? extends LivingEntity> entityType);
}