import React from "react";
import {Button, Form, Input, Select, Statistic, Table, Tag} from 'antd';
import {PositionToColor, StageToColor} from "../Utils/RoleToColor";
const { Option } = Select;

const {Column} = Table;

class FreshTable extends React.Component<> {
    constructor(props) {
        super(props);
        const u = this.props.data == null ? null : this.props.data
        this.state = {
            freshData: u,
            filterData: this.filterRes(u, {
                name: "",
                email: "",
                gender: [],
                grade: [],
                position: [],
                stage: ""
            })
        }
    }

    filterRes(users, filter) {
        if (users == null || filter == null) return null
        let f = users
        if (filter.name != null && filter.name!== "")
            f = f.filter(i => i.name.indexOf(filter.name) !== -1)
        if (filter.email != null && filter.email!== "")
            f = f.filter(i => i.itsc.indexOf(filter.email) !== -1)
        if (filter.stage != null && filter.stage!== "")
            f = f.filter(i => i.stage.indexOf(filter.stage) !== -1)
        if (filter.gender != null && filter.gender.length !== 0)
            f = f.filter(i => {
                let match = false
                filter.gender.forEach(r => {
                    if (i.gender === r) {
                        match = true
                    }
                })
                return match
            })
        if (filter.grade != null && filter.grade.length !== 0)
            f = f.filter(i => {
                let match = false
                filter.grade.forEach(r => {
                    if (i.grade.indexOf(r) !== -1) {
                        match = true
                    }
                })
                return match
            })
        if (filter.position != null && filter.position.length !== 0)
            f = f.filter(i => {
                let match = false
                filter.position.forEach(r => {
                    if (i.positions.indexOf(r) !== -1) {
                        match = true
                    }
                })
                return match
            })
        return f
    }

    componentWillReceiveProps(nextProps, _) {
        const u = nextProps.data == null ? null : nextProps.data
        this.setState({
            freshData: u,
            filterData: this.filterRes(u, {
                name: "",
                email: "",
                gender: [],
                grade: [],
                position: [],
                stage: ""
            })
        })
    }

    filterClick = (v) => {
        this.setState({
            filterData: this.filterRes(this.state.freshData, {
                name: v.name,
                email: v.email,
                gender: v.gender,
                grade: v.grade,
                position: v.position,
                stage: v.stage
            })
        })
    }

    render() {
        return (
            <div className="d-flex flex-column" style={{width: "100%"}}>
                {this.props.filter &&
                    <div className="m-2 d-flex flex-row" style={{marginBottom: 20, width: "100%"}}>
                        <div className="d-flex flex-row justify-content-around" style={{width: "100%"}}>
                            <Form onFinish={this.filterClick} >
                                <Form.Item style={{marginBottom: 0}}>
                                    <Input.Group compact>
                                        <Form.Item label="Name" name="name" style={{marginRight: 30}}>
                                            <Input />
                                        </Form.Item>
                                        <Form.Item label="Email" name="email" style={{marginRight: 30}}>
                                            <Input />
                                        </Form.Item>
                                        <Form.Item label="Gender" name="gender" style={{width: 300, marginRight: 30}}>
                                            <Select mode="multiple" >
                                                <Option value="MALE">male</Option>
                                                <Option value="FEMALE">female</Option>
                                                <Option value="NOT_TELL">prefer not to say</Option>
                                            </Select>
                                        </Form.Item>
                                        <Form.Item>
                                            <Button htmlType="submit" type="primary">Filter</Button>
                                        </Form.Item>
                                    </Input.Group>
                                </Form.Item>
                                <Form.Item style={{marginBottom: 0}}>
                                    <Input.Group compact>
                                        <Form.Item name={"grade"} label={"Year of Study"} style={{width: 320, marginRight: 30}}>
                                            <Select mode="multiple">
                                                <Option value="UG_1">UG-Year1</Option>
                                                <Option value="UG_2">UG-Year2</Option>
                                                <Option value="UG_3">UG-Year3</Option>
                                                <Option value="UG_4">UG-Year4</Option>
                                                <Option value="PG">PG</Option>
                                                <Option value="OTHER">Other</Option>
                                            </Select>
                                        </Form.Item>
                                        <Form.Item label="Position" name="position" style={{width: 350, marginRight: 30}}>
                                            <Select mode="multiple" >
                                                <Option value="SOFTWARE">Software</Option>
                                                <Option value="HARDWARE">Hardware</Option>
                                                <Option value="MECHANICAL">Mechanical</Option>
                                                <Option value="LOGISTICS">Logistics</Option>
                                                <Option value="WEBSITE">Website</Option>
                                            </Select>
                                        </Form.Item>
                                        <Form.Item label="Stage" name="stage" style={{width: 200}}>
                                            <Select>
                                                <Option value="Disqualified">Disqualified</Option>
                                                <Option value="Interview Ready">Interview Ready</Option>
                                                <Option value="Interview PASS">Interview PASS</Option>
                                                <Option value="Tutorial PASS">Tutorial PASS</Option>
                                                <Option value="Internal PASS">Internal PASS</Option>
                                                <Option value="Official Member">Official Member</Option>
                                                <Option value="Not Started">Not Started</Option>
                                            </Select>
                                        </Form.Item>
                                    </Input.Group>
                                </Form.Item>
                            </Form>
                            <Statistic title="Total Count" value={this.state.filterData.length} style={{marginTop: "auto", marginBottom: "auto"}} />
                        </div>
                    </div>
                }
                <Table size="small" scroll={{x: "100%"}} style={{maxWidth: "100%"}} dataSource={this.state.filterData}>
                    <Column width={65} fixed="left" title="ID" dataIndex="userId" key="userId" defaultSortOrder="ascend" sorter={{compare: (a, b) => a.id - b.id, multiple: 1}}/>
                    <Column width={150} fixed="left" title="Name" dataIndex="name" key="name"/>
                    <Column width={150} fixed="left" title="Stage" dataIndex="stage" key="stage" render={(t) => {
                        return <Tag color={StageToColor(t)}>{t}</Tag>
                    }}/>
                    <Column width={90} title="C Name" dataIndex="chineseName" key="chineseName"/>
                    <Column width={120} title="Nickname" dataIndex="nickName" key="nickName"/>
                    <Column width={90} title="Gender" dataIndex="gender" key="gender" render={(tag) => {
                        switch(tag) {
                            case ("MALE"):
                                return <Tag color="blue">Male</Tag>
                            case ("FEMALE"):
                                return <Tag color="red">Female</Tag>
                            default:
                                return <Tag color="lime">Unknown</Tag>
                        }
                    }} />
                    <Column width={250} title="ITSC" dataIndex="itsc" key="itsc"/>
                    <Column width={120} title="Grade" dataIndex="grade" key="grade" render={(tag) => {
                        switch(tag) {
                            case ("UG_1"):
                                return <Tag color="geekblue">Year 1</Tag>
                            case ("UG_2"):
                                return <Tag color="blue">Year 2</Tag>
                            case ("UG_3"):
                                return <Tag color="cyan">Year 3</Tag>
                            case ("UG_4"):
                                return <Tag color="green">Year 4</Tag>
                            case ("PG"):
                                return <Tag color="magenta">PG</Tag>
                            default:
                                return <Tag color="lime">Other</Tag>
                        }
                    }}/>
                    <Column width={120} title="Major" dataIndex="major" key="major"/>
                    <Column width={300} title="Positions" dataIndex="positions" key="positions"
                            render={(tags) =>
                                (<span> {tags.map((tag) =>
                                    (<Tag color={PositionToColor(tag)} key={tag}> {tag} </Tag>)
                                )}</span>)}/>
                    <Column width={800} title="Info" dataIndex="info" key="info" />
                    <Column width={200} title="Reg Time" dataIndex="registerTime" key="registerTime" />
                    <Column width={150} title="Operation" fixed="right" key="op" render={_ =>
                        <Button type="primary">Change Stage</Button>
                    } />
                </Table>
            </div>
        )
    }
}

export default FreshTable