import React from "react";
import {Product} from "./Product";
import {Link} from "react-router-dom";

export function ProductManagerList({products = [], onDeleteClick}) {
    return (
        <React.Fragment>
            <div className="row">
                <h5 className=" flex-grow-0"><b>상품 목록</b></h5>
                <div className="row justify-content-end">
                    <Link to="/product/new"><button type="button" className="btn btn-primary btn-sm">상품 등록</button></Link>
                </div>
            </div>

            <ul className="list-group products">
                {products.map(v =>
                    <li key={v.productId} className="list-group-item d-flex mt-3">
                        <Product {...v} onDeleteClick={onDeleteClick}/>
                    </li>
                )}
            </ul>
        </React.Fragment>
    )
}