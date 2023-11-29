package com.uroria.betterflowers.flowers;

import lombok.Getter;

import org.bukkit.Material;

@Getter
public abstract class AgebleFlower extends SingleFlower{

    private final int age;

    public AgebleFlower(Material display, Material blockMaterial, String displayName, int age) {
        super(display, blockMaterial, displayName);
        this.age = age;
    }

    public AgebleFlower(Material blockMaterial, String displayName, int age) {
        super(blockMaterial, displayName);
        this.age = age;
    }
}