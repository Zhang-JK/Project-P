import React from "react";
import {config} from "../Config";

class Login extends React.Component {
    usernameI: any;
    passwordI: any;
    render() {
        return (
            <div>
                <h1>Login</h1>
                <form>
                    <label>
                        Username:
                        <input ref={c => this.usernameI = c} type="text"/>
                    </label>
                    <label>
                        Password:
                        <input ref={c => this.passwordI = c} type="password"/>
                    </label>
                    <button type="button" onClick={() => {
                        const requestOptions = {
                            method: 'POST',
                            headers: {'Content-Type': 'application/json'},
                            body: JSON.stringify({
                                'username': this.usernameI.value.toString(),
                                'password': this.passwordI.value.toString()
                            })
                        }
                        console.log(" sending request");
                        fetch(config.apiUrl + "login", requestOptions).then(response => response.json()).then(data => {
                            const {code, msg} = data;
                            if (code === 200) {
                                alert(" login success");
                            }
                            else {
                                alert("Error: " + msg);
                            }
                        })
                    }}>Login
                    </button>
                </form>
            </div>
        );
    }
}

export default Login;
