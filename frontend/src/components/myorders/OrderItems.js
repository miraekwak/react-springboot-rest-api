import React from "react";
import {OrderItem} from "./OrderItem";

export function OrderItems({orderItems=[]}) {
    return (
        <>
            <ul className="list-group products">
                {orderItems.map(v =>
                    <li key={v.productId} className="list-group-item d-flex">
                        <OrderItem {...v} />
                    </li>
                )}
            </ul>
        </>
    )
}