import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import React, {useEffect, useState} from "react";
import {ProductList} from "./components/ProductList";
import {Summary} from "./components/Summary";
import axios from "axios";
import { BrowserRouter, Routes, Route, Switch } from "react-router-dom";
import {Home} from "./components/Home";
import {MyOrders} from "./components/MyOrders";


function App() {
  return (
    <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="myOrders" element={<MyOrders/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
