package net.thegaminghuskymc.futopia.blocks.test.misc;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;
import net.thegaminghuskymc.futopia.client.render.PlatingPressRenderer;
import net.thegaminghuskymc.futopia.init.FTCreativeTabs;
import net.thegaminghuskymc.futopia.tiles.TilePlatingPress;
import net.thegaminghuskymc.futopia.utils.BlockNames;
import net.thegaminghuskymc.huskylib.items.ItemBase;
import net.thegaminghuskymc.huskylib.items.ItemIngotBase;

public class BlockPlatingPress extends Block implements ITileEntityProvider {

    public BlockPlatingPress() {
        super(Material.IRON);
        setUnlocalizedName(BlockNames.platingPress);
        setRegistryName(BlockNames.platingPress);
        GameData.register_impl(this);
        GameData.register_impl(new ItemBlock(this).setRegistryName(getUnlocalizedName()));
        GameRegistry.registerTileEntity(TilePlatingPress.class, "platingpress");
        setCreativeTab(FTCreativeTabs.machines);
    }

    private TilePlatingPress getTE(World world, BlockPos pos) {
        return (TilePlatingPress) world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
    		EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TilePlatingPress te = getTE(world, pos);
            if (te.getStack() == null) {
              if (player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() instanceof ItemIngotBase || player.getHeldItem(hand).getItem() instanceof ItemBase) {
                if (player.getHeldItem(hand).isEmpty()) {
                	
                    //Inn
                    ItemStack stack = player.getHeldItem(hand).copy();
                    int stackSize  = stack.getMaxStackSize();

                    if (player.getHeldItem(hand).getCount() > 9) {
                        stack.setCount(9);
                        te.setStack(stack);

                        ItemStack returnStack = player.getHeldItem(hand).copy();
                        returnStack.setCount(9);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, returnStack);
                    } else {
                        stack.setCount(stackSize);
                        te.setStack(stack);

                        ItemStack returnStack = player.getHeldItem(hand).copy();
                        returnStack.setCount(stackSize);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, returnStack);

                    }
                    player.openContainer.detectAndSendChanges();
                }
            } else {
                ItemStack stack = te.getStack();

                te.setStack(null);
                if (!player.inventory.addItemStackToInventory(stack)) {
                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntity(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
            }
        }}
        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
    	return false;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(TilePlatingPress.class, new PlatingPressRenderer());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TilePlatingPress();
    }
    
}