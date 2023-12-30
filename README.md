![image](https://github.com/raphael-goetz/betterflowers/assets/52959657/893f35be-8793-4a8a-bf0c-bbaacce80a62)

# BetterFlowers
But what does it do?

- Open an inventory for creating placeable flower patterns.
- Open an inventory for creating a flower-brush for easier and bigger operations.
- Undo any unwanted flower-placements of yourself or anyone else.
- Design flower patterns with a height capability of up to 9 blocks.
- Introduce a randomization element to decide whether a single flower should be placed or not, adding variety to patterns.
- Utilize entire flower groups that consist of various flower types, providing options for diverse and visually appealing designs.
- Implement the ability to undo one or multiple flower placements, allowing for easy correction of mistakes or experimentation with different arrangements.

# Specs:

### Supported Minecraft-Version: 1.20.4 (for Version 1.0.1)
### Current Java-Version: 17
### Developed for Paper

# Commands
```
/flower
```
opens the inventory to create custom flowers
+ alias: /f

```
/flowerbrush
```

opens the inventory to create a brush of custom flowers
 - alias: /fb

```
/undoflower
```
to undo your last flower placement
 - alias: /uf
```
/undoflower 5
```
to undo your last 5 
```
/undoflower 5 <player>
```
to undo the last 5 flower placements of the given player

# Permissions
```
betterflowers.use
```
to open the flower creation inventory

```
betterflowers.undo
```
to undo a flower placement

# Updates
- [X] flower-brush 
- [X] flower-history
- [ ] async context for flower placing 
### If you have any ideas to add contact my discord: konfuzius
# Preview

***An explanatory video will be coming soon***

# Creation-Inventory
![image](https://github.com/raphael-goetz/betterflowers/assets/52959657/d77f539a-c078-44f8-8353-5a924869374e)

If open, you can choose a category that contains more specific flowers with different block states to use in your flower pattern. To select a flower you first click on a category and then click on a specific flower to add them to your pattern. If you want to add the whole category then just shift-click on a category at the beginning.

If you left-click it will be added without a randomizer. Else it will be added with a randomzier.

 - SHERD: Create a flower --> get a BLAZE_POWDER to place the flower
 - STRUCTURE_VOID: get back to the main menu
 - BARRIER: To remove all selected flowers
 - REDSTONE: To remove the last added flower

![image](https://github.com/raphael-goetz/betterflowers/assets/52959657/25dce0b8-8ec0-468d-8b64-0b7d2d986e1c)
 - green glass indicates the selection of a single flower to be placed randomly
 - red glass indicates the selection of a single flower to always be placed

![image](https://github.com/raphael-goetz/betterflowers/assets/52959657/9b8b5a56-b4e6-46e8-b88e-902e0dcea8c0)
 - blue glass indicates the selection of a whole flower group that will always will randomly
 - magenta glass indicates the selection of a whole flower group that always will be placed

# Brush-Inventory

![image](https://github.com/raphael-goetz/betterflowers/assets/52959657/77a44134-2601-4bc0-9723-2b5410dcbf7f)

Put a flower-placer in the bottom row. If you want to add a mask insert a block on top of the flower-placer. 
Then the flower will only be placed on the given mask!
 - SHERD: create a brush --> get a BLAZE_ROD to use the flower brush
 - ENDER_EYE: to set the range of the brush [1 - 20]
 - STRUCTURE_VOID: to set the air percentage [0% - 100%]
