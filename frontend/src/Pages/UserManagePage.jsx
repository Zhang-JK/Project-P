import React, {useState} from 'react';
import getRequest from "../Request/GetRequest";
import UserTable from "../Components/UserTable";
import {Spin} from "antd";

function UserManagePage(props) {
    const [users, setUsers] = useState(null)
    if (props.userInfo != null && props.userInfo.permissions['humanResource'] !== undefined && users == null) {
        getRequest("humanResource/list")
            .catch(error => {
                console.log('ERROR: ', error)
            })
            .then((res) => {
                if (res.code === 1003) {
                    window.location.replace("/login")
                }
                setUsers(res.data)
            })
    }

    if (users != null)
        return (
            <div className="d-flex flex-column">
                <UserTable data={users} reload={() => setUsers(null)}/>
            </div>
        )
    else return <Spin />
}

export default UserManagePage
