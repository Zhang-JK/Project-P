import React, {useState} from 'react';
import getRequest from "../Request/GetRequest";
import FreshTable from "../Components/FreshTable";
import {Spin} from "antd";

function FreshManagePage(props) {
    const [fresh, setFresh] = useState(null)
    if (props.userInfo != null && props.userInfo.permissions['humanResource'] !== undefined && fresh == null) {
        getRequest("fresh/list")
            .catch(error => {
                console.log('ERROR: ', error)
            })
            .then((res) => {
                if (res.code === 1003) {
                    window.location.replace("/login")
                }
                setFresh(res.data)
            })
    }

    if (fresh != null)
        return (
            <div className="d-flex flex-column">
                <FreshTable data={fresh} filter={true}/>
            </div>
        )
    else return <Spin />
}

export default FreshManagePage
