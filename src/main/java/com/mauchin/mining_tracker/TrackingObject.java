package com.mauchin.mining_tracker;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class TrackingObject{
    public Counter counter;
    public ItemRenderer itemRenderer;
    public ClientPlayerEntity player;
    public boolean enabled = true;
    public List<Integer> historyValue;
    public TrackingObject(Counter counter, ItemRenderer itemRenderer, ClientPlayerEntity player){
        this.counter = counter;
        this.itemRenderer = itemRenderer;
        this.player = player;
    }
    //TODO make this
    public void tick(){};
    public void render(){};
}
