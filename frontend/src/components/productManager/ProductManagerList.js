import React from "react";
import {Product} from "./Product";
import {Link} from "react-router-dom";

export function ProductManagerList({products = [], onDeleteClick}) {
    return (
        <React.Fragment>
            <div className="row">
                <h5 className="col mb-4"><b>상품 목록</b></h5>
            </div>
            <div className="row justify-content-end">
                <Link to="/product/new"><button type="button" className="btn btn-primary btn-sm ">상품 등록</button></Link>
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