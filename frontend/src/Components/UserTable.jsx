import React from "react";
import {Button, Space, Table, Tag} from 'antd';
import RoleToColor from "../Utils/RoleToColor";

const {Column} = Table;

class UserTable extends React.Component<> {
    constructor(props) {
        super(props);
        this.state = {
            userData: this.props.data == null ? null : this.props.data.map(d => {
                return {
                    id: d.user.id,
                    name: d.user.name,
                    username: d.user.username,
                    email: d.user.email,
                    roles: d.roles.map(r => r.name),
                    rolesID: Math.min(...d.roles.map(r => r.id)),
                    projects: d.projects.map(p => p.name)
                }
            })
        }
    }

    componentWillReceiveProps(nextProps, _) {
        this.setState({
            userData: nextProps.data == null ? null : nextProps.data.map(d => {
                return {
                    id: d.user.id,
                    name: d.user.name,
                    username: d.user.username,
                    email: d.user.email,
                    roles: d.roles.map(r => r.name),
                    rolesID: Math.min(d.roles.map(r => r.id)),
                    projects: d.projects.map(p => p.name)
                }
            })
        })
    }

    render() {
        return (
            <div className="d-flex flex-column" style={{width: "100%"}}>
                <div className="m-2 d-flex flex-row" style={{marginBottom: 20}}>
                    <Button>1</Button>
                    <Button>2</Button>
                    <Button>3</Button>
                </div>
                <Table dataSource={this.state.userData}>
                    <Column fixed="left" title="ID" dataIndex="id" key="id" defaultSortOrder="ascend" sorter={{compare: (a, b) => a.id - b.id, multiple: 1}}/>
                    <Column fixed="left" title="Name" dataIndex="name" key="name"/>
                    <Column title="ITSC" dataIndex="username" key="username"/>
                    <Column title="Email" dataIndex="email" key="email"/>
                    <Column title="Roles" dataIndex="roles" key="roles" sorter={{compare: (a, b) => a.rolesID -b.rolesID}}
                            render={(tags) =>
                                (<Space> {tags.map((tag) =>
                                    (<Tag color={RoleToColor(tag)} key={tag}> {tag} </Tag>)
                                )}</Space>)}/>
                    <Column title="Projects" dataIndex="projects" key="projects"
                            render={(tags) =>
                                (<Space>{tags.map((tag) =>
                                    (<Tag key={tag}>{tag}</Tag>)
                                )}</Space>)}/>
                    <Column fixed="right" title="Action" key="action" render={(_) => (<Space size="middle">
                        <Button size="small" type="primary">Edit</Button>
                        <Button size="small" type="primary" danger>Delete</Button>
                    </Space>)}
                    />
                </Table>
            </div>
        )
    }
}

export default UserTable