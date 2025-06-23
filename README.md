

<h1 align="center">Puente Fabric-Identity</h1>
Un puente fabric-identity para proyectos que no tienen architectury, con esto puedes hacer habilidades custom para tus entidades custom, puedes transformarte mediante codigo, etc.
## Implementacion

#### 1- Instalar [bkd0ridentitybridge-fabric-1.x.x.jar](https://github.com/histerdev/IdentityFabricBridge/releases/download/1.20.1/bkd0ridentitybridge-fabric-1.0.0.jar).
#### 2- Instalar [Identity fix (Morph)](https://www.curseforge.com/minecraft/mc-mods/identity-fix-morph/files/all?page=1&pageSize=20).
#### 3- Instalar [omega-config-1.2.3-1.18.jar](https://www.curseforge.com/minecraft/mc-mods/omega-config/files/3612206)
(ES IMPORTANTE QUE SEA 1.18!)

Deberia quedar algo asi:
```groovy
dependencies {

    // dependencias
    modImplementation files('libs/identitybridge-fabric-1.0.0.jar')
    modImplementation files('libs/identity-2.8.5-1.20.1-fabric.jar')
    modImplementation files('libs/omega-config-1.2.3-1.18.jar')
}
```
## Ejemplo de habilidad
ejemplo de como implementar una habilidad a tu entidad
```java
public class Habilidad extends IdentityAbility<Entidadcustom> {

    @Override
    public void onUse(PlayerEntity player, Entidadcustom identity, World world) {
        if (!world.isClient) {
            // rayo
            HitResult hit = player.raycast(100, 1.0F, false);
            if (hit.getType() != HitResult.Type.MISS) {
                LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightningBolt.setPosition(hit.getPos());
                world.spawnEntity(lightningBolt);
            }

            // tnt
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.PLAYERS, 1.0F, 1.0F);
            for (int i = 0; i < 10; i++) {
                TntEntity tnt = new TntEntity(world, player.getX(), player.getEyeY(), player.getZ(), player);
                Vec3d lookVector = player.getRotationVector();
                tnt.setVelocity(
                        lookVector.x + world.random.nextGaussian() * 0.25,
                        lookVector.y + 0.3,
                        lookVector.z + world.random.nextGaussian() * 0.25
                );
                tnt.setFuse(80);
                world.spawnEntity(tnt);
            }
        }
    }

    @Override
    public Item getIcon() {
        return Items.TNT;
    }

    @Override
    public int getCooldown(Entidadcustom entity) {
        return 400;
    }
}
```

## Ejemplo de registro de habilidad

```java
public class Main implements ModInitializer {
    @Override
    public void onInitialize() {

        IdentityBridgeApi bridgeApi = ExampleModFabric.API; // ignora el nombre "ExampleModFabric" NFJDSNFJD

        if (bridgeApi != null) {
            // aqui se registra
            bridgeApi.registerAbility(ModEntities.Entidadcustom, new Habilidad());
        }
    }
}
```
## Ejemplo de uso (por ej para transformarse)

```java

CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
dispatcher.register(CommandManager.literal("transformarme").executes(context -> {
ServerPlayerEntity player = context.getSource().getPlayer();
IdentityBridgeApi bridgeApi = ExampleModFabric.API;

        if (bridgeApi != null && player != null) {
            bridgeApi.transformInto(player, ModEntities.MI_ENTIDAD_PERSONALIZADA);
            context.getSource().sendFeedback(() -> Text.literal("¡Transformado!"), false);
            return 1;
        }
        return 0;
    }));
});
```
