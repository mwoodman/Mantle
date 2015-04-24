package mantle.client;

import java.util.Collection;

import mantle.blocks.util.BlockVariant;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelVariant
{
    private String MOD_ID;

    private Minecraft mc;

    public ModelVariant(String modId, Minecraft mc)
    {
        this.MOD_ID = modId;
        this.mc = mc;
    }

    public void registerBlockModelVariants(Block block, Collection<BlockVariant> variants)
    {
        for (BlockVariant variant : variants)
        {
            Item item = Item.getItemFromBlock(block);

            this.registerItemModel(item, variant.getName(), variant.getMeta());
            ModelBakery.addVariantName(item, (MOD_ID + ":") + variant.getName());
        }
    }

    public void registerBlockModel(Block block)
    {
        this.registerBlockModel(block, block.getUnlocalizedName().substring(5), 0);
    }

    public void registerBlockModel(Block block, String name, int meta)
    {
        this.registerItemModel(Item.getItemFromBlock(block), name, meta);
    }

    public void registerItemModel(Item item)
    {
        this.registerItemModel(item, item.getUnlocalizedName().substring(5), 0);
    }

    public void registerItemModel(Item item, String name, int meta)
    {
        String NAME = name.replace(this.MOD_ID + ".", "");
        this.mc.getRenderItem().getItemModelMesher().register(item, meta, this.getModelResource(NAME, "inventory"));
    }

    private ModelResourceLocation getModelResource(String name, String type)
    {
        return new ModelResourceLocation((MOD_ID + ":") + name, type);
    }
}