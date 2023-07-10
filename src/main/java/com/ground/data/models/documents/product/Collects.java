package com.ground.data.models.documents.product;

/**
 * 수집
 *
 * Collects are meant for managing the relationship between products and custom collections.
 * For every product in a custom collection there is a collect that tracks the ID of both the product and the custom collection.
 * A product can be in more than one collection, and will have a collect connecting it to each collection.
 * Unlike many Shopify resources, collects aren't apparent to store owners.
 *
 * Collects are for placing products in custom collections only.
 * Smart collections use rules to determine which products are their members.
 * Creating a collect that links a product to a smart collection results in a 403 Forbidden error.
 *
 * For more information on custom collections, see the CustomCollection resource.
 */
@Deprecated
public class Collects {
}
