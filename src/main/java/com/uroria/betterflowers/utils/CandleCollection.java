package com.uroria.betterflowers.utils;

import com.uroria.betterflowers.flowers.placable.CandleFlower;
import com.uroria.betterflowers.flowers.placable.FlowerGroup;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

@Getter
public enum CandleCollection {

    CANDLE(new FlowerGroup(Material.CANDLE, "Candle", List.of(
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.CANDLE, "Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    WHITE(new FlowerGroup(Material.WHITE_CANDLE, "White Candle", List.of(
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.WHITE_CANDLE, "White Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    LIGHT_GRAY(new FlowerGroup(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle", List.of(
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.LIGHT_GRAY_CANDLE, "LIGHT_GRAY Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    GRAY(new FlowerGroup(Material.GRAY_CANDLE, "GRAY Candle", List.of(
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.GRAY_CANDLE, "GRAY Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    BLACK(new FlowerGroup(Material.BLACK_CANDLE, "BLACK Candle", List.of(
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.BLACK_CANDLE, "BLACK Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    BROWN(new FlowerGroup(Material.BROWN_CANDLE, "BROWN Candle", List.of(
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.BROWN_CANDLE, "BROWN Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    RED(new FlowerGroup(Material.RED_CANDLE, "RED Candle", List.of(
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.RED_CANDLE, "RED Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    ORANGE(new FlowerGroup(Material.ORANGE_CANDLE, "ORANGE Candle", List.of(
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.ORANGE_CANDLE, "ORANGE Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    YELLOW(new FlowerGroup(Material.YELLOW_CANDLE, "YELLOW Candle", List.of(
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.YELLOW_CANDLE, "YELLOW Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    LIME(new FlowerGroup(Material.LIME_CANDLE, "LIME Candle", List.of(
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.LIME_CANDLE, "LIME Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    GREEN(new FlowerGroup(Material.GREEN_CANDLE, "GREEN Candle", List.of(
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.GREEN_CANDLE, "GREEN Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    CYAN(new FlowerGroup(Material.CYAN_CANDLE, "CYAN Candle", List.of(
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.CYAN_CANDLE, "CYAN Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    LIGHT_BLUE(new FlowerGroup(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle", List.of(
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.LIGHT_BLUE_CANDLE, "LIGHT_BLUE Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    BLUE(new FlowerGroup(Material.BLUE_CANDLE, "BLUE Candle", List.of(
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.BLUE_CANDLE, "BLUE Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    PURPLE(new FlowerGroup(Material.PURPLE_CANDLE, "PURPLE Candle", List.of(
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.PURPLE_CANDLE, "PURPLE Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    MAGENTA(new FlowerGroup(Material.MAGENTA_CANDLE, "MAGENTA Candle", List.of(
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.MAGENTA_CANDLE, "MAGENTA Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    ))),

    PINK(new FlowerGroup(Material.PINK_CANDLE, "PINK Candle", List.of(
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 1]", false, false, 1),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 2]", false, false, 2),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 3]", false, false, 3),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 4]", false, false, 4),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 1] [WATERLOGGED]", true, false, 1),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 2] [WATERLOGGED]", true, false, 2),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 3] [WATERLOGGED]", true, false, 3),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 4] [WATERLOGGED]", true, false, 4),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 1] [LIT]", false, true, 1),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 2] [LIT]", false, true, 2),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 3] [LIT]", false, true, 3),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 4] [LIT]", false, true, 4),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 1] [LIT] [WATERLOGGED]", true, true, 1),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 2] [LIT] [WATERLOGGED]", true, true, 2),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 3] [LIT] [WATERLOGGED]", true, true, 3),
            new CandleFlower(Material.PINK_CANDLE, "PINK Candle [AMOUNT: 4] [LIT] [WATERLOGGED]", true, true, 4)
    )));

private final FlowerGroup flowerGroup;

    CandleCollection(FlowerGroup flowerGroup) {
        this.flowerGroup = flowerGroup;
    }
}