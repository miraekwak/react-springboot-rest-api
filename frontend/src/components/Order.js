import React from "react";
import {OrderItems} from "./OrderItems";

export function Order(props) {
    console.log(props);
    const orderId = props.orderId;
    const email = props.email.address;
    const address = props.address;
    const postcode = props.postcode;
    const orderStatus = props.orderStatus;
    const orderItems = props.orderItems;
    return (
        <>
            <div>
                <div className="row">
                    <div className="col">
                        <div className="row text-muted">OrderStatus : {orderStatus}</div>
                        <div className="row">{email}</div>
                        <div className="row">{address} | {postcode}</div>
                    </div>
                    <div className="col text-end price">{orderItems.length}</div>
                </div>
                <div className="row">
                    <OrderItems orderItems={orderItems} />
                </div>
            </div>

        </>
    )
}