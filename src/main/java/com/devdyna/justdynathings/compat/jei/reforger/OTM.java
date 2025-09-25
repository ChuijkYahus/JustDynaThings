package com.devdyna.justdynathings.compat.jei.reforger;

import com.devdyna.justdynathings.Constants;
import com.devdyna.justdynathings.recipetypes.type.*;
import com.devdyna.justdynathings.utils.DataGenUtil;

import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;

public class OTM extends BaseReforgerCategory<ReforgerOTMRecipe> {

    public static final RecipeType<ReforgerOTMRecipe> TYPE = new RecipeType<>(
            DataGenUtil.getResource(Constants.DataMaps.Reforger.block_to_tag),
            ReforgerOTMRecipe.class);

    public OTM(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<ReforgerOTMRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public String setTitle() {
        return Constants.DataMaps.Reforger.block_to_tag;
    }

    @Override
    public void setInput(IRecipeSlotBuilder slot, ReforgerOTMRecipe recipe) {
        slot.addIngredients(Ingredient.of(recipe.getInputState().getBlock().asItem()));
    }

    @Override
    public void setCatalyst(IRecipeSlotBuilder slot, ReforgerOTMRecipe recipe) {
        slot.addIngredients(recipe.getCatalyst());
    }

    @Override
    public void setOutput(IRecipeSlotBuilder slot, ReforgerOTMRecipe recipe) {
        slot.addItemStacks(recipe.getOutputState().getItems().toList());
    }

    @Override
    public int setChance(ReforgerOTMRecipe recipe) {
        return recipe.getChanceToUse();
    }

}
