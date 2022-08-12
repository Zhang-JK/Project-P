import React, {useState} from 'react';
import TemplatePage from "./TemplatePage";
import getRequest from "../Request/GetRequest";

function HomePage() {
    const [data, setData] = useState(null);
    if (data == null) {
        getRequest("user/getInfo")
            .then((res) => {
                if (res.code !== 200) {
                    window.location.replace("/login")
                }
                setData(res.data)
            })
    }
    return (
        <TemplatePage page={"home"} permissions={data == null ? null : data.permissions}
                      projects={data == null ? null : data.projects}/>
    );
}

export default HomePage;
