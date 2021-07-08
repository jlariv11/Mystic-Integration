package jlariv11.mysticintegration.data;

import com.google.gson.JsonElement;
import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.jline.utils.Log;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ItemModelGenerator extends ItemModelProvider {

    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MysticIntegration.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(RegistryObject<Item> i : ItemRegistry.ITEM_REGISTER.getEntries()){
            String name = i.getId().toString().split(":")[1];
            if(i.get() instanceof BlockItem) {
                this.withExistingParent(i.getId().toString(), new ResourceLocation(MysticIntegration.MODID, "block/" + name));
                continue;
            }
            this.withExistingParent(i.getId().toString(), "minecraft:item/generated").texture("layer0",
                    new ResourceLocation(MysticIntegration.MODID, "item/" + name));
        }
    }



}
