import 'bootstrap/dist/css/bootstrap.css';
import React, {useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";


export function Signup() {
    const [name, setName] = useState()
    const [username, setUsername] = useState()
    const [passwd, setPasswd] = useState()

    const handleSignup = () => {
        console.log(name, username, passwd);
        axios.post('http://localhost:8080/api/v1/signUp', {
            name: name,
            username: username,
            passwd: passwd
        })
            .then(v => {
                    alert("회원가입 되었습니다.")
                    window.location.href="http://localhost:3000/login";
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
                            <h5 className="flex-grow-0"><b>회원가입</b></h5>
                            <form>
                                <div className="form-group mt-4">
                                    <label htmlFor="name">Name</label>
                                    <input type="text" className="form-control" id="name" onChange={(e)=>{setName(e.target.value)}}/>
                                </div>
                                <div className="form-group mt-4">
                                    <label htmlFor="username">Username(Id)</label>
                                    <input type="text" className="form-control" id="username" onChange={(e)=>{setUsername(e.target.value)}}/>
                                </div>
                                <div className="form-group mt-4 mb-4">
                                    <label htmlFor="passwd">Password</label>
                                    <input type="text" className="form-control" id="passwd" onChange={(e)=>{setPasswd(e.target.value)}}/>
                                </div>
                                <button type="submit" className="btn btn-primary btn-sm m-2" onClick={handleSignup}>Sign Up!</button>
                                <Link to="/login"><button type="button" className="btn btn-secondary btn-sm">Go to the Login!</button></Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
