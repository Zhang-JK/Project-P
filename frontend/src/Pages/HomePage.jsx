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
                      projects={data == null ? null : data.projects}>
            {data != null &&
                <div className="d-flex flex-column">
                    <div className="m-4"><h2>Hi, {data.user.name}</h2></div>
                    {data.projects.length > 0 && <div className="m-2">
                        <h4>You have enrolled in the following projects:</h4>
                        {data.projects.map(p => {
                            return <div>{p.roleName} in <strong>{p.project.name}</strong></div>
                        })}
                    </div>}
                    {data.roles.length > 0 && <div className="m-2">
                        <h4>You have the following roles:</h4>
                        {data.roles.map(r => {
                            return <div><strong>{r.name}</strong>: {r.description}</div>
                        })}
                    </div>}
                </div>
            }
        </TemplatePage>
    );
}

export default HomePage;
