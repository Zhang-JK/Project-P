import React from "react";
import {Menu} from 'antd';
import {
    HomeOutlined,
    UserSwitchOutlined,
    ScheduleOutlined,
    TeamOutlined,
    AccountBookOutlined,
    BulbOutlined,
    AlertOutlined,
    IdcardOutlined, LogoutOutlined
} from "@ant-design/icons";
import {navigateWithoutRefresh} from "../Utils/Utils";

function getItem(label, key, icon, children) {
    return {
        key,
        icon,
        children,
        label,
    };
}

class SideBar extends React.Component<> {
    render() {
        const items = [
            getItem('Home', 'home', <HomeOutlined/>),
            this.props.permissions == null || this.props.permissions['humanResource'] == null ? null :
                getItem('Member Manage', 'memberM', <UserSwitchOutlined/>, [
                    getItem('All Users', `member`),
                    getItem('Fresh Member', `fresh`),
                ]),
            this.props.permissions == null || this.props.permissions['projectManage'] == null ? null :
                getItem('Project Manage', 'project', <ScheduleOutlined/>),
            this.props.permissions == null || this.props.permissions['finance'] == null ? null :
                getItem('Finance', 'finance', <AccountBookOutlined/>),
            this.props.permissions == null || this.props.permissions['announcement'] == null ? null :
                getItem('Announcement', 'announcement', <BulbOutlined/>),

            this.props.projects == null || this.props.projects.length === 0 ? null :
                this.props.projects.map(p => {
                    return getItem(p['project']['name'], p['project']['name'], <TeamOutlined/>, [
                        getItem('Dashboard', `${p['project']['name']}-D`),
                        getItem('Announcement', `${p['project']['name']}-A`),
                        getItem('Calendar', `${p['project']['name']}-C`),
                        getItem('Purchase', `${p['project']['name']}-P`),
                    ])
                }),

            getItem('Personal', 'personal', <IdcardOutlined/>),
            this.props.permissions == null || this.props.permissions['feedback'] == null ? null :
                getItem('Feedback', 'feedback', <AlertOutlined/>),
            getItem('Logout', 'logout', <LogoutOutlined />),
        ].flat();

        return (
            <Menu theme="dark" defaultOpenKeys={['memberM']} selectedKeys={this.props.selected} inlineCollapsed={false} mode="inline" items={items} onClick={(i) => {
                switch (i.key) {
                    case ('logout'):
                        window.location.replace(`/login`)
                        break;
                    default:
                        navigateWithoutRefresh(`/${i.key}`)
                }
            }}/>
        );
    }
}

export default SideBar

