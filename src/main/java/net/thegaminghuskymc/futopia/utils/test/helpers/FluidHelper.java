package net.thegaminghuskymc.futopia.utils.test.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.registries.GameData;
import net.thegaminghuskymc.futopia.Reference;

public class FluidHelper {

	public static Fluid createFluid(String name, String textureName, boolean hasFlowIcon) {

		ResourceLocation still = new ResourceLocation(textureName + "_still");
		ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(textureName + "_flow") : still;

		Fluid fluid = new Fluid(name, still, flowing);
		fluid.setUnlocalizedName(Reference.MODID + "." + name);
		FluidRegistry.registerFluid(fluid);

		return fluid;
	}

	public static void registerBucket(Fluid fluid) {

	}

	public static <T extends Block & IFluidBlock> T registerFluidBlock(T block) {
		String fluidName = block.getFluid().getUnlocalizedName();
		block.setUnlocalizedName(fluidName);
		block.setRegistryName(fluidName);
		GameData.register_impl(block);

		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(fluidName);
		GameData.register_impl(itemBlock);

		return block;
	}

	// public static BlockFluidBlock registerBlockFluid(Fluid fluid) {
	// BlockFluidBlock fluidBlock = new BlockFluidBlock(fluid);
	// return
	// }
	//
	// protected static <T extends Block> T registerBlock(T block, String name)
	// {
	// ItemBlock itemBlock = new ItemBlock(block);
	// return registerBlock(block, itemBlock, name);
	// }
	//
	// protected static <T extends Block> T registerBlock(T block, ItemBlock
	// itemBlock, String name) {
	//
	// }
	//
	// protected static <T extends IForgeRegistryEntry<?>> T register(T thing,
	// String name) {
	// thing.setRegistryName(name);
	// GameRegistry.register(thing);
	// return thing;
	// }
}