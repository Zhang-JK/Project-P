import React, {useState} from 'react';
import TemplatePage from "./TemplatePage";
import getRequest from "../Request/GetRequest";
import UserTable from "../Components/UserTable";

function UserManagePage() {
    const [data, setData] = useState(null)
    const [users, setUsers] = useState(null)
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
    if (data != null && data.permissions['humanResource'] !== undefined && users == null) {
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

    return (
        <TemplatePage page={"member"} permissions={data == null ? null : data.permissions}
                      projects={data == null ? null : data.projects}>
            {users != null &&
                <div className="d-flex flex-column">
                    <UserTable data={users} reload={() => setUsers(null)} />
                </div>
            }
        </TemplatePage>
    )
}

export default UserManagePage