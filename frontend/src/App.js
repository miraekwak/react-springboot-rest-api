import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import {Home} from "./components/home/Home";
import {MyOrders} from "./components/myorders/MyOrders";
import {ProductRegistration} from "./components/productManager/ProductRegistration";
import {ProductManager} from "./components/productManager/ProductManager";
import {Signup} from "./components/entrance/Signup";
import {Login} from "./components/entrance/Login";



function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/signup" element={<Signup/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/" element={<Home/>}/>
            <Route path="/myOrders" element={<MyOrders/>}/>
            <Route path="/product" element={<ProductManager/>}/>
            <Route path="/product/new" element={<ProductRegistration/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
