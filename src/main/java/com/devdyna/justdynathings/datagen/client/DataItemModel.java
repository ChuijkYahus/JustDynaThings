package com.devdyna.justdynathings.datagen.client;

import static com.devdyna.justdynathings.Main.ID;
import static com.devdyna.justdynathings.compat.ae2.init.AE2_POWERED;
import static com.devdyna.justdynathings.compat.extendedae.init.EXTENDED_POWERED;
import static com.devdyna.justdynathings.compat.phasorite.init.PHASORITE_POWERED;

import com.devdyna.justdynathings.Main;
import com.devdyna.justdynathings.registry.types.zBlocks;
import com.devdyna.justdynathings.registry.types.zItems;
import com.devdyna.justdynathings.utils.DataGenUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;

public class DataItemModel extends ItemModelProvider {

        public DataItemModel(PackOutput o, ExistingFileHelper f) {
                super(o, Main.ID, f);
        }

        @Override
        protected void registerModels() {

                zItems.zItem.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this));

                zItems.zItemHanded.getEntries().forEach(item -> handheldItem(item.get()));

                zItems.zGooUpgraders.getEntries()
                                .forEach(item -> DataGenUtil.itemModel(item.get(), this));

                DataGenUtil.itemBlock(zBlocks.THERMOGEN.get(), this);

                DataGenUtil.itemBlock(zBlocks.BLACKHOLE.get(), this);

                AnvilBlockItem(zBlocks.FERRICORE_ANVIL.get());
                AnvilBlockItem(zBlocks.BLAZEGOLD_ANVIL.get());
                AnvilBlockItem(zBlocks.CELESTIGEM_ANVIL.get());
                AnvilBlockItem(zBlocks.ECLIPSEALLOY_ANVIL.get());

                SolarBlockItem(zBlocks.FERRICORE_SOLARGEN.get());
                SolarBlockItem(zBlocks.BLAZEGOLD_SOLARGEN.get());
                SolarBlockItem(zBlocks.CELESTIGEM_SOLARGEN.get());
                SolarBlockItem(zBlocks.ECLIPSEALLOY_SOLARGEN.get());

                DataGenUtil.itemBlockwithParent(zBlocks.FERRICORE_CLOCK.get(), this,
                                "block/cube_all", "all",
                                ID + ":block/" + DataGenUtil.getName(zBlocks.FERRICORE_CLOCK.get()) + "/on");

                DataGenUtil.itemBlockwithParent(zBlocks.PHASEBOX.get(), this,
                                ID + ":block/" + DataGenUtil.getName(zBlocks.PHASEBOX.get()) + "/true");

                DataGenUtil.itemBlockwithParent(zBlocks.REFORGER.get(), this,
                                ID + ":block/" + DataGenUtil.getName(zBlocks.REFORGER.get()) + "/off");

                DataGenUtil.itemBlockwithParent(zBlocks.STABILIZER.get(), this,
                                ID + ":block/" + DataGenUtil.getName(zBlocks.STABILIZER.get()) + "/x/off");

                DataGenUtil.itemBlockwithParent(zBlocks.PARADOX_MIXER.get(), this,
                                ID + ":block/" + DataGenUtil.getName(zBlocks.PARADOX_MIXER.get()) + "/off");

                GooItemModel(zBlocks.ENERGIZED_GOO.get());
                GooItemModel(zBlocks.CREATIVE_GOO.get());
                GooItemModel(zBlocks.T1_GOO.get());
                GooItemModel(zBlocks.T2_GOO.get());
                GooItemModel(zBlocks.T3_GOO.get());
                GooItemModel(zBlocks.T4_GOO.get());

                BuddingItemModel(zBlocks.ECHOING_BUDDING_AMETHYST.get());
                BuddingItemModel(zBlocks.ECHOING_BUDDING_TIME.get());
                BuddingItemModel(AE2_POWERED.get());
                BuddingItemModel(EXTENDED_POWERED.get());
                BuddingItemModel(PHASORITE_POWERED.get());

                zItems.zBucketItem.getEntries().forEach(b -> withExistingParent(b.getId().getPath(),
                                ResourceLocation.fromNamespaceAndPath(NeoForgeVersion.MOD_ID, "item/bucket"))
                                .customLoader(DynamicFluidContainerModelBuilder::begin)
                                .fluid(((BucketItem) b.get()).content));

        }

        private void BuddingItemModel(Block b) {
                DataGenUtil.itemBlockwithParent(b, this,
                                ID + ":block/echoing_budding/" + DataGenUtil.getName(b).replace("echoing_budding_", "")
                                                + "/dead");
        }

        private void GooItemModel(Block b) {
                DataGenUtil.itemBlockwithParent(b, this,
                                ID + ":block/goo/" + DataGenUtil.getName(b) + "/dead");
        }

        private void AnvilBlockItem(Block b) {
                DataGenUtil.itemBlockwithParent(b, this,
                                ID + ":block/anvils/" + DataGenUtil.getName(b).replace("_anvil", ""));
        }

        private void SolarBlockItem(Block b) {
                DataGenUtil.itemBlockwithParent(b, this,
                                ID + ":block/solar_panel/" + DataGenUtil.getName(b).replace("_solar_panel", ""));
        }

}
