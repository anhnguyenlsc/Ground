package com.ground.data.models.documents.inventory;

/**
 * An inventory item represents a physical good. It holds essential information about the physical good, including its SKU and whether its inventory is tracked.
 * There is a 1:1 relationship between a product variant and an inventory item. Each product variant includes the ID of its related inventory item. You can use the inventory item ID to query the InventoryLevel resource to retrieve the location and quantity for an inventory item.
 * Use the InventoryItem resource together with the InventoryLevel and Location resources to manage a store's inventory across multiple locations.
 */
public class Item {

}
