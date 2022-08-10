import TemplatePage from "./TemplatePage";
import getRequest from "../Request/GetRequest";
import postRequest from "../Request/PostRequest";
import MD5 from "crypto-js/md5";

const HomePage = ()=>{
    console.log("home")
    postRequest("user/login", {
        username: "test",
        password: MD5("123456").toString()
    })
        .then(res => {
            console.log(res)
        })
    getRequest("user/getInfo")
        .then((res) => {
            this.setState({ data: res.data, dataReady: true })
            console.log(res)
        })
    return (
        <TemplatePage />
    );
}

export default HomePage;
