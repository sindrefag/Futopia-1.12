package net.thegaminghuskymc.futopia.utils.test.interfaces;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockRenderer {
    @SideOnly(Side.CLIENT)
    void registerBlockRenderer();

    @SideOnly(Side.CLIENT)
    void registerBlockItemRenderer();
}