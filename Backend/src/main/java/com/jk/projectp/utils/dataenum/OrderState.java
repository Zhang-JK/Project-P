package com.jk.projectp.utils.dataenum;

public enum OrderState {

    CREATED("Created"),
    REQUESTED("Requested"),
    APPROVED("Approved"),
    PURCHASED("Purchased"),
    SHIPPING("Shipping"),
    ARRIVED("Arrived"),
    COLLECTED("Collected"),
    SETTLED("Settled"),
    SUCCESS("Success"),
    CANCELLED("Cancelled"),
    DECLINED("Declined"),
    PURCHASE_FAIL("Purchase Fail"),
    LOST("Lost"),
    OTHER_ERROR("Other Error"),
    FAIL("Fail");

    private String orderState;

    OrderState(String orderState) {}
}
