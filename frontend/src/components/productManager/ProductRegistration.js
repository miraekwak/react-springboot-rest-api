import 'bootstrap/dist/css/bootstrap.css';
import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";


export function ProductRegistration() {
    const [categories, setCategories] = useState([])
    const [productName, setProductName] = useState()
    const [category, setCategory] = useState("")
    const [price, setPrice] = useState()

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/products/category")
            .then(v => {
                setCategories(v.data)
                setCategory(v.data[0])
            })
    }, [])


    const handleSelect = (e) => {
        setCategory(e.target.value);
        console.log('setCategory: ', category);
    }

    const handleProductSubmit = () => {
        console.log(productName, category, price);
        axios.post('http://localhost:8080/api/v1/products', {
            productName: productName,
            category: category,
            price: price
        })
        .then(v => alert("상품이 정상적으로 등록되었습니다."),
            e => {
                alert("서버 장애");
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
                            <Link to="/home"><button type="button" className="btn btn-primary btn-sm">Home</button></Link>
                            <button type="button" className="btn btn-secondary btn-sm">Product List</button>
                        </div>
                    </div>
                </div>
                <div className="card">
                    <div className="row d-md-flex justify-content-center">
                        <div className="col-md-10 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <h5 className="flex-grow-0"><b>상품 등록</b></h5>
                            <form>
                                <div className="form-group mt-4">
                                    <label htmlFor="productName">Product Name</label>
                                    <input type="text" className="form-control" id="productName" name="productName"
                                           aria-describedby="emailHelp" onChange={(e)=>{setProductName(e.target.value)}}/>
                                </div>
                                <div className="form-group mt-4">
                                    <label htmlFor="category">Category</label>
                                    <select className="form-control form-control-sm" id="category" name="category" value={category} onChange={handleSelect}>
                                        {categories.map((v) => (
                                            <option key={v} value={v}>{v}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className="form-group mt-4 mb-4">
                                    <label htmlFor="price">Price</label>
                                    <input type="text" className="form-control" id="price" onChange={(e)=>{setPrice(e.target.value)}}/>
                                </div>
                                <button type="submit" className="btn btn-primary" onClick={handleProductSubmit}>Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
