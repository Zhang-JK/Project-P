import React from "react";
import {Menu} from 'antd';
import {HomeOutlined, UserSwitchOutlined, ScheduleOutlined, TeamOutlined, AccountBookOutlined, BulbOutlined, AlertOutlined, IdcardOutlined} from "@ant-design/icons";

function getItem(label, key, icon, children) {
    return {
        key,
        icon,
        children,
        label,
    };
}

class SideBar extends React.Component<> {
    items = [
        getItem('Home', '1', <HomeOutlined />),
         getItem('Member Manage', '2', <UserSwitchOutlined />),
        getItem('Project Manage', '3', <ScheduleOutlined />),
        getItem('Finance', '4', <AccountBookOutlined />),
        getItem('Announcement', '5', <BulbOutlined />),
        getItem('Project', 'name', <TeamOutlined />, [
            getItem('Announcement', 'name-1'),
            getItem('Calendar', 'name-2'),
            getItem('Purchase', 'name-3'),
        ]),
        getItem('Personal', '6', <IdcardOutlined />),
        getItem('Feedback', '7', <AlertOutlined />),
    ];

    render() {
        return (
            <Menu theme="dark" defaultSelectedKeys={this.props.selected} mode="inline" items={this.items}/>
        );
    }
}

export default SideBar

