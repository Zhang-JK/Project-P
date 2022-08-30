import React from "react";
import {Alert, Button, Form, Input, Modal, Select, Space, Table, Tag} from 'antd';
import {RoleToColor} from "../Utils/RoleToColor";
import getRequest from "../Request/GetRequest";
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
            }),
            deleteState: 0,
            deleteId: 0,
            errorMsg: ""
        }
    }

    filterRes(users, filter) {
        if (users == null || filter == null) return null
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
            filterData: this.filterRes(u, {
                name: "",
                email: "",
                role: []
            })
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

    handleDelete = (r) => {
        getRequest(`humanResource/deleteOne?id=${r.id}`).then(r => {
            if (r.code !== 200)
                this.setState({deleteId: 0, errorMsg: r.msg, deleteState: -1})
            else {
                this.setState({deleteId: 0, errorMsg: r.msg, deleteState: 0})
                this.props.reload()
            }
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
                {this.state.deleteState < 0 && <Alert type="error" message="Error text" banner style={{marginBottom: 20}}/>}
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
                    <Column fixed="right" title="Action" key="action" render={(record) => (<Space size="middle">
                        <Button size="small" type="primary">Edit</Button>
                        <Button size="small" type="primary" danger onClick={() => {
                            this.setState({deleteState: 1, deleteId: record.id})
                        }}>Delete</Button>
                        <Modal title="Basic Modal" visible={this.state.deleteState && this.state.deleteId === record.id} onOk={() => this.handleDelete(record)} onCancel={() => this.setState({deleteState: 0, deleteId: 0})}>
                            <p>ARE YOU SURE to delete user "{record.username}" ({record.name})? </p>
                            <p><strong>This action is NOT invertible!</strong></p>
                            {record.roles.length > 0 && <p>Roles: {record.roles.toString()}</p>}
                            {record.projects.length > 0 && <p><strong>He/She have joined some projects, it is NOT recommend to do so!</strong></p>}
                            {record.projects.length > 0 && <p>Projects: {record.projects.toString()}</p>}
                        </Modal>
                    </Space>)}
                    />
                </Table>
            </div>
        )
    }
}

export default UserTable