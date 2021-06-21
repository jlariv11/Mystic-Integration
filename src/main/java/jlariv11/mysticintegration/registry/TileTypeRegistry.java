package jlariv11.mysticintegration.registry;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.blocks.tiles.GeneratorTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileTypeRegistry {

    public static final DeferredRegister<TileEntityType<?>> TILE_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MysticIntegration.MODID);

    public static final RegistryObject<TileEntityType<GeneratorTile>> GENERATOR_TILE = TILE_TYPES.register(
            "generator_tile", () -> TileEntityType.Builder.of(GeneratorTile::new,
                    BlockRegistry.LIGHT_GENERATOR.get(), BlockRegistry.DARK_GENERATOR.get()).build(null));

}
