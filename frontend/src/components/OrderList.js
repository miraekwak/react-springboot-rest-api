import React from "react";
import {Order} from "./Order";

export function OrderList({orders = []}) {
    return (
        <React.Fragment>
            <h5 className="flex-grow-0"><b>주문 목록</b></h5>
            <ul className="list-group products">
                {orders.map(v =>
                    <li key={v.orderId} className="list-group-item d-flex mt-4">
                        <Order {...v} />
                    </li>
                )}
            </ul>
        </React.Fragment>
    )
}