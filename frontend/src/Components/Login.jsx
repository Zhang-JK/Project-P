import React, {useState} from "react";
import "./Login.css"
import {Button} from "antd";
import {ExclamationCircleOutlined, LoginOutlined} from "@ant-design/icons";
import postRequest from "../Request/PostRequest";
import MD5 from "crypto-js/md5";

const Login = () => {
    const [error, setError] = useState(0);
    const [errorMsg, setErrorMsg] = useState("");
    const usernameI = React.createRef();
    const passwordI = React.createRef();

    return (
        <div className="d-flex flex-column" style={{height: "100%"}}>
            <div style={{margin: "auto"}}><h1 style={{color: "white"}}>Login</h1></div>
            <form style={{height: "65%"}}>
                <input className="loginInputBox" placeholder="ITSC Account" ref={usernameI} type="text"/>
                <br />
                <input className="loginInputBox" placeholder="Password" inputMode="password" ref={passwordI} type="password"/>
                <br />
                <div className="loginErrorMessage">
                    {error===1 && <ExclamationCircleOutlined />}
                    <div className="m-1" style={{display: "inline-block", verticalAlign: "middle"}}> {errorMsg} </div>
                </div>
                <br />
                <Button type="primary" icon={<LoginOutlined style={{marginBottom: 3}} />} size="large" shape="round" className="loginFormButton" onClick={async () => {
                    if (usernameI.current.value.toString() === "" || passwordI.current.value.toString() === "") {
                        setError(1)
                        setErrorMsg("Username or Password is Empty")
                        return
                    }
                    setError(0)
                    setErrorMsg("")
                    postRequest("user/login", {
                        'username': usernameI.current.value.toString(),
                        'password': MD5(passwordI.current.value).toString()
                    }).catch(error => {
                        console.log('ERROR: ', error)
                        setError(1)
                        setErrorMsg("Network Fail or Server Fail")
                    }).then(res => {
                        const {code, msg} = res;
                        if (code === 200) {
                            window.location.replace("/home")
                        } else {
                            setError(1)
                            setErrorMsg(msg)
                            if (code === undefined || code == null) {
                                setErrorMsg("Server Error")
                            }
                        }
                    })
                }}>
                    <span>Login</span>
                </Button>
            </form>
        </div>
    );
}
export default Login;
