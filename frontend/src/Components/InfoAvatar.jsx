import React, {useState} from "react";
import {checkMemory, getMemory} from "../Utils/Memory";
import {getUserDataList, getUserInfo} from "../Utils/Utils";
import {Avatar, Popover, Spin} from "antd";

const avatarColorArray = ["#87d068", "#1e81b0", "#e28743", "#21130d", "#873e23", "#232a87"];
const getCapital = (name) => {
    if (name === undefined) {
        return undefined;
    }
    let trimName = name.trim();
    if (trimName.length > 2) {
        let names = trimName.split(" ");
        if (names.length >= 2) {
            return names[0].charAt(0).toUpperCase() + names[1].charAt(0).toUpperCase();
        } else {
            return trimName.substring(0, 2);
        }
    } else {
        return trimName
    }
}
export const InfoAvatar = (props) => {
    const [loading, setLoading] = useState(getUserDataList() === undefined);
    const {userId, size = "default"} = props
    let data = getUserInfo(userId, setLoading.bind(undefined, false))
    return (
        <div>
            {loading ? <Spin/> : <Popover content={
                (<div className="d-flex flex-column">
                    <div className="m-4">
                        <h4><Avatar style={{backgroundColor: avatarColorArray[data.user.id % avatarColorArray.length]}}
                                    size={"large"}>{getCapital(data.user.name)}</Avatar>{data.user.name}</h4>
                    </div>
                    {data.projects.length > 0 && <div className="m-1">
                        <h5>He have enrolled in the following projects:</h5>
                        {data.projects.map(p => {
                            return <div><strong>{p.name}</strong>: {p.description}</div>
                        })}
                    </div>}
                    {data.roles.length > 0 && <div className="m-1">
                        <h5>He have the following roles:</h5>
                        {data.roles.map(r => {
                            return <div><strong>{r.name}</strong>: {r.description}</div>
                        })}
                    </div>}
                    <div className="m-2"><h5>Email:</h5>
                        <div>{data.user.email}</div>
                    </div>
                </div>)
            } trigger={"hover"}>
                {<Avatar size={size}
                    style={{backgroundColor: avatarColorArray[data.user.id % avatarColorArray.length]}}>{getCapital(data.user.name)}</Avatar>}
                <a>{data.user.username}</a>
            </Popover>}
        </div>
    )
}
