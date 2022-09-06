import React, {useEffect, useState} from 'react';
import TemplatePage from "./TemplatePage";
import getRequest from "../Request/GetRequest";
import {useLocation} from "react-router-dom";
import {MyRouter} from "../Components/MyRouter";
import {addNavigateCallback} from "../Utils/Utils";

function MainPage() {
    const [data, setData] = useState(null);
    const [urlLocation, setUrlLocation] = useState(useLocation().pathname);
    useEffect(() => {
        addNavigateCallback((location) => {
            setUrlLocation(location)
        })
    }, [])
    if (data == null) {
        getRequest("user/getInfo")
            .catch(error => {
                console.log('ERROR: ', error)
                window.location.replace("/login")
            })
            .then((res) => {
                if (res.code !== 200) {
                    window.location.replace("/login")
                }
                setData(res.data)
            })
    }
    return (
        <TemplatePage page={urlLocation.trim().split("/")[1]} permissions={data == null ? null : data.permissions}
                      projects={data == null ? null : data.projects} setLocation={setUrlLocation}>
            <MyRouter location={urlLocation} userInfo={data}/>
        </TemplatePage>
    );
}

export default MainPage;
