package com.mauchin.mining_tracker;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Objects;

public class Counter {
    public int currentCount = 0;
    public int startCount = 0;
    public static int breaker_length = 440;
    public static int breaker_cooldown = 2380;
    public String type;

    public Counter(String type){
        this.type = type;
    }
    public void start(ClientPlayerEntity player){
        this.startCount = 0;
        if (Objects.equals(this.type, "item_diamond")){
            this.startCount += getItemCount(Items.DIAMOND,player);
        }
        if (Objects.equals(this.type, "item_diamond_ore")){
            this.startCount += getItemCount(Items.DIAMOND_ORE,player);
            this.startCount += getItemCount(Items.DEEPSLATE_DIAMOND_ORE,player);
        }
        if (Objects.equals(this.type,"time")){
            this.startCount = 0;
        }
        if (Objects.equals(this.type,"durability")){
            this.startCount = 0;
        }
        if (Objects.equals(this.type,"breaker")){
            this.startCount = 0;
        }
    }
    public void tick(ClientPlayerEntity player){
        if (Objects.equals(this.type, "item_diamond")){
            this.currentCount += getItemCount(Items.DIAMOND,player);
        }
        if (Objects.equals(this.type, "item_diamond_ore")){
            this.currentCount += getItemCount(Items.DIAMOND_ORE,player);
            this.currentCount += getItemCount(Items.DEEPSLATE_DIAMOND_ORE,player);
        }
        if (Objects.equals(this.type, "time")){
            this.currentCount += 1;
        }
        if (Objects.equals(this.type, "durability") && player != null){
            this.currentCount = (player.getMainHandStack().getMaxDamage() - player.getMainHandStack().getDamage()) * 100 / player.getMainHandStack().getMaxDamage();
        }
        if (Objects.equals(this.type, "breaker") && player != null){
            int efficiency_level = 0;
            for (int j = 0; j < 10; j++) {
                if (efficiency_level == 0 && Objects.equals(player.getMainHandStack().getEnchantments().getCompound(j).getString("id"), "minecraft:efficiency")
                        && (player.getMainHandStack().getItem() == Items.NETHERITE_PICKAXE||
                        player.getMainHandStack().getItem() == Items.DIAMOND_PICKAXE||
                        player.getMainHandStack().getItem() == Items.IRON_PICKAXE||
                        player.getMainHandStack().getItem() == Items.STONE_PICKAXE||
                        player.getMainHandStack().getItem() == Items.GOLDEN_PICKAXE||
                        player.getMainHandStack().getItem() == Items.WOODEN_PICKAXE)) {
                    efficiency_level = player.getMainHandStack().getEnchantments().getCompound(j).getInt("lvl");
                }
            }
            if (efficiency_level >= 10 && this.currentCount >= 0){
                this.currentCount = -breaker_length;
            }
            else if (efficiency_level >= 10){
                this.currentCount += 1;
            }
            else if (this.currentCount > 0){
                this.currentCount -= 1;
            }
            else {
                this.currentCount = breaker_cooldown;
            }
        }
    }

    public int getItemCount(Item item, ClientPlayerEntity player){
        int count = 0;
        for (int i = 0; i < 36; i++) {
            if (player != null && player.getInventory().getStack(i).getItem() == item) {
                count += player.getInventory().getStack(i).getCount();
            }
        }
        return count;
    }
}
