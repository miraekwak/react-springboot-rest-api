import 'bootstrap/dist/css/bootstrap.css';
import React, {useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";


export function Login() {
    const [username, setUsername] = useState()
    const [passwd, setPasswd] = useState()

    const handleLogin = () => {
        console.log(username, passwd);
        axios.post('http://localhost:8080/api/v1/login', {
            username: username,
            passwd: passwd
        })
            .then(v => {
                    alert("로그인 되었습니다.");
                    sessionStorage.setItem('username', v.data.username);
                    sessionStorage.setItem('name', v.data.name);
                    sessionStorage.setItem('role', v.data.role);
                    if(v.data.role === "USER") {
                        window.location.href="http://localhost:3001/";
                    }
                    else {
                        window.location.href="http://localhost:3001/product";
                    }
                },
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
                <div className="card">
                    <div className="row d-md-flex justify-content-center">
                        <div className="col-md-10 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <h5 className="flex-grow-0"><b>로그인</b></h5>
                            <form>
                                <div className="form-group mt-4">
                                    <label htmlFor="username">Name</label>
                                    <input type="text" className="form-control" id="username" onChange={(e)=>{setUsername(e.target.value)}}/>
                                </div>
                                <div className="form-group mt-4 mb-4">
                                    <label htmlFor="passwd">Password</label>
                                    <input type="text" className="form-control" id="passwd" onChange={(e)=>{setPasswd(e.target.value)}}/>
                                </div>
                                <button type="submit" className="btn btn-primary btn-sm m-2" onClick={handleLogin}>Login</button>
                                <Link to="/signup"><button type="button" className="btn btn-secondary btn-sm">Go to the SignUp!</button></Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
