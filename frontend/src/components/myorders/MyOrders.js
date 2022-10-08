import 'bootstrap/dist/css/bootstrap.css';
import React, {useEffect, useState} from "react";
import {OrderList} from "./OrderList";
import axios from "axios";
import {Link} from "react-router-dom";


export function MyOrders() {
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
                            <Link to="/home"><button type="button" className="btn btn-primary btn-sm">Home</button></Link>
                            <button type="button" className="btn btn-secondary btn-sm">Product List</button>
                        </div>
                    </div>
                </div>
                <div className="card">
                    <div className="row d-md-flex justify-content-center">
                        <div className="col-md-10 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <OrderList orders={orders} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
