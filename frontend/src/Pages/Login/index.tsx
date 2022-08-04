import React, {FC, ReactElement} from "react";
import Login  from "../../Components/Login";

interface AppProps {
}

const LoginPage: FC<AppProps> = ({}: {}): ReactElement => {
    return (
        <div>
            <Login/>
        </div>
    );
}

export default LoginPage;
