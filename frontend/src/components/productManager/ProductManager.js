import 'bootstrap/dist/css/bootstrap.css';
import React, {useEffect, useState} from "react";
import axios from "axios";
import {ProductManagerList} from "./ProductManagerList";
import {Link} from "react-router-dom";


export function ProductManager() {
    const [products, setProducts] = useState([]);
    const [deleted, setDeleted] = useState(0);

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/products")
            .then(v => setProducts(v.data))
        setDeleted(0)
    }, [deleted])

    const handleDeleteClicked = productId => {
        axios.delete(`http://localhost:8080/api/v1/products/${productId}`)
            .then(v => {
                    alert("상품이 정상적으로 삭제되었습니다.")
                    setDeleted(1)
                },
            e => {
                alert("주문을 취소해주세요.");
                console.error(e);
            })
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
                            <Link to='/login'><button type="button" className="btn btn-secondary btn-sm">Logout</button></Link>
                        </div>
                    </div>
                </div>
                <div className="card">
                    <div className="row d-md-flex justify-content-center">
                        <div className="col-md-10 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <ProductManagerList products={products} onDeleteClick={handleDeleteClicked}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
