package com.devdyna.justdynathings.datagen.client;

import static com.devdyna.justdynathings.Main.ID;

import java.util.List;

import com.devdyna.justdynathings.Constants;
import com.devdyna.justdynathings.Main;
import com.devdyna.justdynathings.registry.types.zBlocks;
import com.devdyna.justdynathings.registry.types.zItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, Main.ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                add(ID + ".invalid_block", "§cInvalid Block");
                add(ID + ".wand.blockstate", "§7BlockState : ");
                add(ID + ".wand.dimension", "§7Dimension : ");
                add(ID + ".wand.pos", "§7BlockPos : ");
                add(ID + ".disabled", "§cItem Disabled");
                add(Main.ID + "." + Constants.Blocks.Reforger + ".tip",
                                "§7Convert blocks using a catalyst to other blocks");

                add(Main.ID + "." + Constants.Anvils.t1 + ".tip",
                                "§7Repair items using metallic ingots");

                add(Main.ID + "." + Constants.Anvils.t2 + ".tip",
                                "§7Repair items using hot fluids");

                add(Main.ID + "." + Constants.Anvils.t3 + ".tip",
                                "§7Repair items using Forge Energy");

                add(Main.ID + "." + Constants.Anvils.t4 + ".tip",
                                "§7Repair items using Forge Energy");

                add(Main.ID + "." + Constants.Anvils.t4 + ".boost",
                                "§7Can be boosted using Fluid Time");

                add(Main.ID + "." + Constants.BuddingType + ".tip",
                                "§7Grow clusters using Fluid Time and Forge Energy");

                // creative tab
                add(Main.ID + "." + Constants.CreativeTab, "Just Dyna Things");

                // wip
                add(Main.ID + ".clock.wip", "N.Y.I. -> shift-click the block to toggle");

                // missing guideme
                add(ID + ".guideme.missing", "§aFor more info install GuideMe");

                // generic type tooltips
                add(Main.ID + "." + Constants.GooType + "." + Constants.Goo.Energized + ".tip",
                                "§cProvide energy to active it");
                add(Main.ID + "." + Constants.GooType + "." + Constants.Goo.Creative + ".tip",
                                "§cRight click with a wrench to toggle alive state");
                add(Main.ID + "." + Constants.SolarPanelType + ".tip", "§7Generate Energy from ambiental situations");
                add(Main.ID + "." + Constants.Blocks.ThermoGen + ".tip",
                                "§7Generate Energy from heat sources and coolants");

                // registry keys
                zBlocks.zBlock.getEntries().forEach(b -> addBlock(b, named(b.getRegisteredName())));
                zBlocks.zGoo.getEntries().forEach(b -> addBlock(b, named(b.getRegisteredName())));
                zBlocks.zOres.getEntries().forEach(b -> addBlock(b, named(b.getRegisteredName())));
                zBlocks.zBuddings.getEntries().forEach(b -> addBlock(b, named(b.getRegisteredName())));
                zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, named(b.getRegisteredName())));
                // zBlocks.zBlockFluids.getEntries().forEach(b -> addBlock(b,
                // named(b.getRegisteredName())));
                zItems.zItem.getEntries().forEach(b -> addItem(b, named(b.getRegisteredName())));
                zItems.zBucketItem.getEntries().forEach(b -> addItem(b, named(b.getRegisteredName())));
                zItems.zItemTinted.getEntries().forEach(b -> addItem(b, named(b.getRegisteredName())));
                zItems.zCoals.getEntries().forEach(b -> addItem(b, named(b.getRegisteredName())));
                zItems.zItemHanded.getEntries().forEach(b -> addItem(b, named(b.getRegisteredName())));

                zItems.zGooUpgraders.getEntries().forEach(b -> addItem(b, named(
                                b.getRegisteredName())));

                // compats
                List.of(
                                Constants.Budding.Certus,
                                Constants.Budding.Entro,
                                Constants.Budding.Phasorite)
                                .forEach(e -> add("block." + ID + "." + e, named(e)));

                List.of(
                                Constants.DataMaps.Anvils.ferricore_repair,
                                Constants.DataMaps.Anvils.blazegold_repair,
                                Constants.DataMaps.Anvils.eclipsealloy_repair,

                                Constants.DataMaps.Thermo.thermo_coolants,
                                Constants.DataMaps.Thermo.thermo_heat_sources,
                                Constants.DataMaps.Reforger.block_to_block,
                                Constants.DataMaps.Reforger.block_to_tag,
                                Constants.DataMaps.Reforger.tag_to_block

                ).forEach(j -> add(ID + ".jei.category." + j, named(j.replace("anvils/", ""))));

                add(ID + "." + Constants.GooUpgraders.base, "§7Right click on a goo to upgrade it to the next tier");
                                add(ID + "." + Constants.Items.Picker, "§7Allow to pickup simple blocks and place where you want");
                                add(ID + "." + Constants.Items.Swapper, "§7Allow to swap simple blocks without break it");

                add(Main.ID + "."+Constants.GooType+".tier", "§aGoo Tier: ");


        }

        private String named(String text) {

                StringBuilder result = new StringBuilder();
                for (String word : text.replace(ID + ":", "").replaceAll("_", " ").split(" ")) {
                        if (!word.isEmpty()) {
                                result.append(Character.toUpperCase(word.charAt(0)))
                                                .append(word.substring(1))
                                                .append(" ");
                        }
                }
                return result.toString().trim();
        }

}
