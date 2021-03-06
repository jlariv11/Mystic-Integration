package jlariv11.mysticintegration.registry;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.blocks.tiles.CableTile;
import jlariv11.mysticintegration.blocks.tiles.ChargerTile;
import jlariv11.mysticintegration.blocks.tiles.GeneratorTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileTypeRegistry {

    public static final DeferredRegister<TileEntityType<?>> TILE_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MysticIntegration.MODID);

    public static final RegistryObject<TileEntityType<GeneratorTile>> GENERATOR_TILE = TILE_TYPES.register(
            "generator_tile", () -> TileEntityType.Builder.of(GeneratorTile::new,
                    BlockRegistry.MAGIC_GENERATOR.get()).build(null));
    public static final RegistryObject<TileEntityType<ChargerTile>> CHARGER_TILE = TILE_TYPES.register(
            "charger_tile", () -> TileEntityType.Builder.of(ChargerTile::new, BlockRegistry.CHARGER.get())
            .build(null));
    public static final RegistryObject<TileEntityType<CableTile>> CABLE_TILE = TILE_TYPES.register(
            "cable_tile", () -> TileEntityType.Builder.of(CableTile::new, BlockRegistry.TEMP_CABLE.get())
                    .build(null));
//    public static final RegistryObject<TileEntityType<DecayingLightTile>> DECAYING_LIGHT = TILE_TYPES.register(
//            "decaying_light_tile", () -> TileEntityType.Builder.of(DecayingLightTile::new,
//                    BlockRegistry.DECAYING_LIGHT_BLOCK.get()).build(null));

}
