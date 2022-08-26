import React, {useEffect, useState} from 'react';
import TemplatePage from "./TemplatePage";
import getRequest from "../Request/GetRequest";
import {Routes, Route, useLocation, useNavigate} from "react-router-dom";
import FeedbackPage from "./FeedbackPage";
import {MyRouter} from "../Components/MyRouter";
import {addNavigateCallback} from "../Utils/Utils";

function HomePage() {
    const [data, setData] = useState(null);
    const navigate = useNavigate();
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
        <TemplatePage page={urlLocation.trim().split("/")[2]} permissions={data == null ? null : data.permissions}
                      projects={data == null ? null : data.projects} setLocation={setUrlLocation}>
            {/*{data != null &&*/}
            {/*    <div className="d-flex flex-column">*/}
            {/*        <div className="m-4"><h2>Hi, {data.user.name}</h2></div>*/}
            {/*        {data.projects.length > 0 && <div className="m-2">*/}
            {/*            <h4>You have enrolled in the following projects:</h4>*/}
            {/*            {data.projects.map(p => {*/}
            {/*                return <div>{p.roleName} in <strong>{p.project.name}</strong></div>*/}
            {/*            })}*/}
            {/*        </div>}*/}
            {/*        {data.roles.length > 0 && <div className="m-2">*/}
            {/*            <h4>You have the following roles:</h4>*/}
            {/*            {data.roles.map(r => {*/}
            {/*                return <div><strong>{r.name}</strong>: {r.description}</div>*/}
            {/*            })}*/}
            {/*        </div>}*/}
            {/*    </div>*/}
            {/*}*/}
            <MyRouter location={urlLocation}/>
        </TemplatePage>
    );
}

export default HomePage;
