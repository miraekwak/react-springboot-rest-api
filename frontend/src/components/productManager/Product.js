import React from "react";
import COFFEE_BEAN_PACKAGE from "../../img/COFFEE_BEAN_PACKAGE.jpg"
import MACAROON from "../../img/MACAROON.jpg"
import SCONE from "../../img/SCONE.jpg"

export function Product(props) {
    const productId = props.productId;
    const productName = props.productName;
    const category = props.category;
    const price = props.price;
    const handleDeleteButtonClicked = e => {
        props.onDeleteClick(productId);
    };

    const handleImgByCategory = () => {
        if( category === "COFFEE_BEAN_PACKAGE") {
            return <img className="img-fluid" src={COFFEE_BEAN_PACKAGE} alt=""/>
        }
        else if( category === "MACAROON"){
            return <img className="img-fluid" src={MACAROON} alt=""/>
        }
        else if( category === "SCONE") {
            return <img className="img-fluid" src={SCONE} alt=""/>
        }
    };

    return (
        <>
            <div className="col-2">{handleImgByCategory()}</div>
            <div className="col">
                <div className="row text-muted">{category}</div>
                <div className="row">{productName}</div>
            </div>
            <div className="col text-center price">{price}</div>
            <div className="col text-end action">
                <button onClick={handleDeleteButtonClicked} className="btn btn-small btn-outline-dark">삭제</button>
            </div>
        </>
    )
}