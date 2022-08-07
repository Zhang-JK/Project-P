import {config} from "../../Config";
import React from "react";
import {md5} from "hash-wasm";


const Login = () => {
    const usernameI = React.createRef();
    const passwordI = React.createRef();
    let encodedPassword;
    return (
        <div>
            <h1>Login</h1>
            <form>
                <label>
                    Username:
                    <input ref={usernameI} type="text"/>
                </label>
                <label>
                    Password:
                    <input ref={passwordI} type="password"/>
                </label>
                <button type="button" onClick={async () => {
                    console.log(111);
                    await md5(passwordI.current.value).then(res => {
                        encodedPassword = res;
                        console.log(res);
                    });
                    const requestOptions = {
                        method: 'POST',
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify({
                            'username': usernameI.current.value.toString(),
                            'password': encodedPassword
                        })
                    };
                    console.log(" sending request1");
                    fetch(config.userApiUrl + "login", requestOptions, {withCredentials: true}).then(response => response.json()).then(data => {
                        const {code, msg} = data;
                        console.log("raw data: " + JSON.stringify(data));
                        if (code === 200) {
                            alert(" login success");
                            // navigate('/index', {replace: false});
                        } else {
                            alert("Error: " + msg);
                        }
                    })
                }}>Login
                </button>
            </form>
        </div>
    );
}
export default Login;
