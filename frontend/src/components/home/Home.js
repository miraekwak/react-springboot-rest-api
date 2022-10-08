import '../../App.css';
import 'bootstrap/dist/css/bootstrap.css';
import React, {useEffect, useState} from "react";
import {ProductList} from "./ProductList";
import {Summary} from "./Summary";
import axios from "axios";
import { Link } from "react-router-dom";


export function Home() {
    const [products, setProducts] = useState([
        {productId: 'uuid-1', productName: '콜롬비아 커피 1', category: '커피빈', price: 5000},
        {productId: 'uuid-2', productName: '콜롬비아 커피 2', category: '커피빈', price: 5000},
        {productId: 'uuid-3', productName: '콜롬비아 커피 3', category: '커피빈', price: 5000}
    ]);

    const [items, setItems] = useState([]);

    const handleAddClicked = productId => {
        const product = products.find(v => v.productId === productId);
        const found = items.find(v=> v.productId=== productId);
        const updatedItem =
            found ?
                items.map(v => (v.productId === productId) ? {...v, count: v.count + 1 } : v) : [ ...items, { ...product, count : 1}]
        setItems(updatedItem);
        console.log(products.find(v => v.productId === productId), "added!");
    }
    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/products")
            .then(v => setProducts(v.data))
    }, [])

    const handleOrderSubmit = (order) => {
        if(items.length === 0) {
            alert("아이템을 추가해주세요!");
        } else {
            axios.post('http://localhost:8080/api/v1/orders', {
                email: order.email,
                address: order.address,
                postcode: order.postcode,
                orderItems: items.map(v => ({
                    productId: v.productId,
                    category: v.category,
                    price: v.price,
                    quantity: v.count
                }))
            }).then(v => alert("주문이 정상적으로 접수되었습니다."),
                e => {
                    alert("서버 장애");
                    console.error(e);
                })
        }
    }

    return (
        <div className="App">
            <div className="container-fluid">
                <div className="row justify-content-center m-4">
                    <h1 className="text-center">Future's Cafe</h1>
                </div>
                <div className="buttonNav">
                    <div className=" row justify-content-center">
                        <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                            <Link to='/myOrders'><button type="button" className="btn btn-primary btn-sm">My Orders</button></Link>
                            <Link to='/product'><button type="button" className="btn btn-secondary btn-sm">Product Manager</button></Link>
                        </div>
                    </div>
                </div>
                <div className="card">
                    <div className="row">
                        <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <ProductList products={products} onAddClick={handleAddClicked}/>
                        </div>
                        <div className="col-md-4 summary p-4">
                            <Summary items={items} onOrderSubmit={handleOrderSubmit}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
