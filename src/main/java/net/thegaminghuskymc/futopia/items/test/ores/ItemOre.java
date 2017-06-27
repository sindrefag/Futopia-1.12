package net.thegaminghuskymc.futopia.items.test.ores;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.thegaminghuskymc.futopia.network.EnumOres;

public class ItemOre extends ItemBlock {

    public ItemOre(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String materialName = EnumOres.byMeta(stack.getItemDamage()).getUnlocalizedName();
        return name + "." + materialName;
    }
}