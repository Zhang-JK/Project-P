import Login from "../Components/Login";
import Robot1 from "../assets/poster/robot1-c.jpg"
import "./LoginPage.css"

const LoginPage = ()=>{
    return (
        <div style={{ minHeight: '100vh', backgroundImage: `url(${Robot1})`, backgroundSize: "cover", backgroundPosition: "center" }}>
            <div style={{ minHeight: '100vh'}} className={"loginPage"}>
                <div className="loginSection d-flex flex-row">
                    <div style={{width: "60%", backgroundImage: `url(${Robot1})`, backgroundSize: "cover", backgroundPosition: "center" }} />
                    <div className="LoginInputArea" style={{width: "40%"}}>
                        <Login />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginPage;
