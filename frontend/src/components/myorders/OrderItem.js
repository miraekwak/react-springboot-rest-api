import React from "react";

export function OrderItem(props) {
    const category = props.category;
    const quantity = props.quantity;
    const price = props.price;

    return (
        <>
            <div className="col-2"><img className="img-fluid" src="https://i.imgur.com/HKOFQYa.jpeg" alt=""/></div>
            <div className="col">
                <div className="row text-muted">{category}</div>
                <div className="row">{price}</div>
            </div>
            <div className="col text-center price">{quantity}</div>
        </>
    )
}