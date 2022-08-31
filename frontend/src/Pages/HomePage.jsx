import React from 'react';
import {Spin} from "antd";

function HomePage(props) {
    if (props.userInfo != null)
        return (
            <div className="d-flex flex-column">
                <div className="m-4"><h2>Hi, {props.userInfo.user.name}</h2></div>
                {props.userInfo.projects.length > 0 && <div className="m-2">
                    <h4>You have enrolled in the following projects:</h4>
                    {props.userInfo.projects.map(p => {
                        return <div>{p.roleName} in <strong>{p.project.name}</strong></div>
                    })}
                </div>}
                {props.userInfo.roles.length > 0 && <div className="m-2">
                    <h4>You have the following roles:</h4>
                    {props.userInfo.roles.map(r => {
                        return <div><strong>{r.name}</strong>: {r.description}</div>
                    })}
                </div>}
            </div>
        )
    else return <Spin />
}

export default HomePage;