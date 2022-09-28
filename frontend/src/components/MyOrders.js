import 'bootstrap/dist/css/bootstrap.css';
import React, {useEffect, useState} from "react";
import {ProductList} from "./ProductList";
import {Summary} from "./Summary";
import {OrderList} from "./OrderList";
import axios from "axios";


export function MyOrders() {
    const [products, setProducts] = useState([
        {productId: 'uuid-1', productName: '콜롬비아 커피 1', category: '커피빈', price: 5000},
        {productId: 'uuid-2', productName: '콜롬비아 커피 2', category: '커피빈', price: 5000},
        {productId: 'uuid-3', productName: '콜롬비아 커피 3', category: '커피빈', price: 5000}
    ]);
    const [orders, setOrders] = useState()

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/orders")
            .then(v => setOrders(v.data))
    }, [])

    return (
        <div className="App">
            <div className="container-fluid">
                <div className="row justify-content-center m-4">
                    <h1 className="text-center">Future's Cafe</h1>
                </div>
                <div className="buttonNav">
                    <div className=" row justify-content-center">
                        <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button type="button" className="btn btn-primary btn-sm">My Orders</button>
                            <button type="button" className="btn btn-secondary btn-sm">Small button</button>
                        </div>
                    </div>
                </div>
                <div className="card">
                    <div className="row">
                        <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <OrderList orders={orders} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
