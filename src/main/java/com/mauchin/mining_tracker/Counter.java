package com.mauchin.mining_tracker;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Objects;

public class Counter {
    public int count;
    public int startCount = 0;
    public String type;
    public ClientPlayerEntity player;
    public Counter(String type,ClientPlayerEntity player){
        this.type = type;
        this.player = player;
    }
    public void start(){
        this.startCount = 0;
        if (Objects.equals(this.type, "item_diamond")){
            this.startCount += getItemCount(Items.DIAMOND);
        }
        if (Objects.equals(this.type, "item_diamond_ore")){
            this.startCount += getItemCount(Items.DIAMOND_ORE);
            this.startCount += getItemCount(Items.DEEPSLATE_DIAMOND_ORE);
        }

    }
    //TODO make tick fuc
    public void tick(){}
    public int getItemCount(Item item){
        int count = 0;
        for (int i = 0; i < 36; i++) {
            if (this.player != null && this.player.getInventory().getStack(i).getItem() == item) {
                count += this.player.getInventory().getStack(i).getCount();
            }
        }
        return count;
    }
}
