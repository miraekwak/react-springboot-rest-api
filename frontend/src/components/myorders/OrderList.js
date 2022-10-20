import React from "react";
import {Order} from "./Order";

export function OrderList({orders = [], onDeleteClick}) {
    return (
        <React.Fragment>
            <h5 className="flex-grow-0 mb-4"><b>주문 목록</b></h5>
            <ul className="list-group products">
                {orders.map(v =>
                    <li key={v.orderId} className="list-group-item justify-content-center d-md-flex">
                        <Order {...v} onDeleteClick={onDeleteClick}/>
                    </li>
                )}
            </ul>
        </React.Fragment>
    )
}