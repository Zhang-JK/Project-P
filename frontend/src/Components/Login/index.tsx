import React, {useRef, FC, ReactElement} from "react";
import {config} from "../../Config.js";
import {md5} from "hash-wasm";

class AppProps {
}

class AppState {
}

const Index: FC<AppProps> = ({}): ReactElement => {

    const usernameI = useRef<HTMLInputElement>(null);
    const passwordI = useRef<HTMLInputElement>(null);
    let encodedPassword: string = String("");
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
                    await md5(passwordI.current!.value).then(res => {
                        encodedPassword = res;
                        console.log(res);
                    });
                    const requestOptions = {
                        method: 'POST',
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify({
                            'username': usernameI.current!.value.toString(),
                            'password': encodedPassword
                        })
                    };
                    console.log(" sending request1");
                    fetch(config.apiUrl + "login", requestOptions).then(response => response.json()).then(data => {
                        const {code, msg} = data;
                        if (code === 200) {
                            alert(" login success");
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

export default Index;
