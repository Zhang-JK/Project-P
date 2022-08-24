import React from "react";
import {Button, Form, Input, Select, Space, Table, Tag} from 'antd';
import RoleToColor from "../Utils/RoleToColor";
const { Option } = Select;

const {Column} = Table;

class UserTable extends React.Component<> {
    constructor(props) {
        super(props);
        const u = this.props.data == null ? null : this.props.data.map(d => {
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
        this.state = {
            userData: u,
            filterData: this.filterRes(u, {
                name: "",
                email: "",
                role: []
            })
        }
    }

    filterRes(users, filter) {
        if (users == null) return null
        let f = users
        if (filter.name != null && filter.name!== "")
            f = f.filter(i => i.name.indexOf(filter.name) !== -1)
        if (filter.email != null && filter.email!== "")
            f = f.filter(i => i.email.indexOf(filter.email) !== -1)
        if (filter.role != null && filter.role.length !== 0)
            f = f.filter(i => {
                let match = false
                filter.role.forEach(r => {
                    if (i.roles.indexOf(r) !== -1) {
                        match = true
                    }
                })
                return match
            })
        return f
    }

    componentWillReceiveProps(nextProps, _) {
        const u = nextProps.data == null ? null : nextProps.data.map(d => {
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
        this.setState({
            userData: u,
            filterData: this.filterRes(u)
        })
    }

    filterClick = (v) => {
        this.setState({
            filterData: this.filterRes(this.state.userData, {
                name: v.name,
                email: v.email,
                role: v.role
            })
        })
    }

    render() {
        return (
            <div className="d-flex flex-column" style={{width: "100%"}}>
                <div className="m-2 d-flex flex-row" style={{marginBottom: 20}}>
                    <Form onFinish={this.filterClick} >
                        <Form.Item style={{marginBottom: 0}}>
                            <Input.Group compact>
                                <Form.Item label="Name" name="name" style={{marginRight: 30}}>
                                    <Input />
                                </Form.Item>
                                <Form.Item label="Email" name="email" style={{marginRight: 30}}>
                                    <Input />
                                </Form.Item>
                                <Form.Item>
                                    <Button htmlType="submit" type="primary">Filter</Button>
                                </Form.Item>
                            </Input.Group>
                        </Form.Item>
                        <Form.Item label="Role" name="role" style={{width: 500}}>
                            <Select mode="multiple" >
                                <Option value="Captain">Captain</Option>
                                <Option value="Admin">Admin</Option>
                                <Option value="Senior">Senior</Option>
                                <Option value="Junior">Junior</Option>
                                <Option value="Intern">Intern</Option>
                                <Option value="Visitor">Visitor</Option>
                                <Option value="Advisor">Advisor</Option>
                                <Option value="Logistics">Logistics</Option>
                                <Option value="Fresh">Fresh</Option>
                            </Select>
                        </Form.Item>
                    </Form>
                </div>
                <Table dataSource={this.state.filterData}>
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