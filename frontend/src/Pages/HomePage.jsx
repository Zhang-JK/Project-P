import React, {useState} from 'react';
import TemplatePage from "./TemplatePage";
import postRequest from "../Request/PostRequest";
import MD5 from "crypto-js/md5";
import getRequest from "../Request/GetRequest";

function HomePage() {
    const [data, setData] = useState(null);
    if (data == null) {
        postRequest("user/login", {
            username: "test",
            password: MD5("123456").toString()
        }).then()
        getRequest("user/getInfo")
            .then((res) => {
                setData(res.data)
            })
    }
    return (
        <TemplatePage page={"home"} permissions={data == null ? null : data.permissions}
                      projects={data == null ? null : data.projects}/>
    );
}

export default HomePage;
