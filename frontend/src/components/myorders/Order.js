import React from "react";
import {OrderItems} from "./OrderItems";

export function Order(props) {
    const orderId = props.orderId;
    const email = props.email.address;
    const address = props.address;
    const postcode = props.postcode;
    const orderStatus = props.orderStatus;
    const orderItems = props.orderItems;
    let quantity = 0;
    orderItems.forEach((orderItem) => {
        quantity += orderItem.quantity;
    });

    const handleDeleteButtonClicked = e => {
        props.onDeleteClick(orderId);
    };

    return (
        <>
            <div className="row d-flex">
                <div className="row" id={orderId}>
                    <div className="col-7">
                        <div className="row text-muted">OrderStatus : {orderStatus}</div>
                        <div className="row">{email}</div>
                        <div className="row">{address} | {postcode}</div>
                    </div>
                    <div className="col text-end price">수량 : {quantity}</div>
                    <div className="col text-end action">
                        <button onClick={handleDeleteButtonClicked} className="btn btn-small btn-outline-dark">삭제</button>
                    </div>
                </div>
                <div className="row mt-4">
                    <OrderItems orderId={orderId} orderItems={orderItems} />
                </div>
            </div>

        </>
    )
}