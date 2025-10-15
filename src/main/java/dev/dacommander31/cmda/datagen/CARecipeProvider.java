package dev.dacommander31.cmda.datagen;

import dev.dacommander31.cmda.item.CAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class CARecipeProvider extends RecipeProvider implements IConditionBuilder {
    public CARecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.HEART_OF_THE_WARDEN.get(), 2)
                .pattern("SES")
                .pattern("EHE")
                .pattern("SES")
                .define('S', Blocks.SCULK)
                .define('E', Items.ECHO_SHARD)
                .define('H', CAItems.HEART_OF_THE_WARDEN.get())
                .unlockedBy("has_warden_heart", has(CAItems.HEART_OF_THE_WARDEN.get()))
                .save(recipeOutput);
    }
}
