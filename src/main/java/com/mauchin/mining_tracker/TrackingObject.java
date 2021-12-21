package com.mauchin.mining_tracker;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class TrackingObject{
    public Counter counter;
    public ItemRenderer itemRenderer;
    public boolean enabled = true;
    public List<Integer> historyValue;
    public int ticksSinceLastUpdate = 0;
    public TrackingObject(Counter counter){
        this.counter = counter;

    }
    public void tick(ClientPlayerEntity player, ItemRenderer itemRenderer){
        if (this.enabled){
            this.counter.tick(player);
            this.ticksSinceLastUpdate += 1;
        }
        if (this.enabled && this.ticksSinceLastUpdate >= 10){
            //TODO finish this
            this.ticksSinceLastUpdate = 0;
        }
    }
    public void render(){}
}
