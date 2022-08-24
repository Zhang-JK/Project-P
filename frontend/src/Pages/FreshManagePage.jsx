import React, {useState} from 'react';
import TemplatePage from "./TemplatePage";
import getRequest from "../Request/GetRequest";
import FreshTable from "../Components/FreshTable";

function FreshManagePage() {
    const [data, setData] = useState(null)
    const [fresh, setFresh] = useState(null)
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
    if (data != null && data.permissions['humanResource'] !== undefined && fresh == null) {
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

    return (
        <TemplatePage page={"fresh"} permissions={data == null ? null : data.permissions}
                      projects={data == null ? null : data.projects}>
            {fresh != null &&
                <div className="d-flex flex-column">
                    <FreshTable data={fresh} />
                </div>
            }
        </TemplatePage>
    )
}

export default FreshManagePage