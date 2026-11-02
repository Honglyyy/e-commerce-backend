package com.ly.orderservice.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubInventoryCall(String sku, Integer quantity) {
        stubInventoryCall(sku, quantity, true);
    }

    public static void stubInventoryCall(String sku, Integer quantity, boolean inStock) {
        stubFor(get(urlEqualTo("/api/inventory?sku=" + sku + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(String.valueOf(inStock))));
    }
}
