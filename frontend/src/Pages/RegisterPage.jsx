import React from 'react';
import {Alert, Button, Form, Input, Result, Select} from 'antd';
import {LoadingOutlined, RightCircleOutlined} from "@ant-design/icons";
import postRequest from "../Request/PostRequest";
import MD5 from "crypto-js/md5";
const { Option } = Select;
const { TextArea } = Input;

class RegisterPage extends React.Component<> {
    constructor(props) {
        super(props);
        this.state = {
            // 0 for edit, 1 for loading, 2 for fail, 3 for success
            stage: 0,
            errorMsg: "",
            errorCode: 0
        }
    }

    onFinish = (values) => {
        this.setState({stage: 1, errorMsg: "", errorCode: 0})
        postRequest("fresh/create", {
            name: values.name,
            chineseName: values.chineseName,
            nickName: values.nickName,
            gender: values.gender,
            itsc: values.itsc.itsc+values.itsc.domain,
            grade: values.grade,
            major: values.major,
            info: values.info,
            positions: values.position,
            password: MD5(values.password).toString(),
        })
            .catch(e => {
                this.setState({stage: 2, errorMsg: "Network Fail or Server Fail: "+ e.toString() , errorCode: 1})
            })
            .then(res => {
                if (res.code === 200)
                    this.setState({stage: 3, errorMsg: "", errorCode: 0})
                else
                    this.setState({stage: 2, errorMsg: res.msg, errorCode: res.code})
            })
    }

    render() {
        if (this.state.stage === 3)
            return (
                <Result
                    status="success"
                    title="You Have Successfully Registered!"
                    subTitle="You can go to our manage system to see more info about interview and tutorial"
                    extra={[
                        <Button type="primary" onClick={() => window.location.replace("/login")}>
                            Login to Manage System
                        </Button>,
                        <div>(Recommended to use computer to access)</div>
                    ]}
                />
            )
        else
            return (
                <div className="d-flex flex-column justify-content-center" style={{width: "100%"}}>
                    <h1 className={"m-4"}>HKUST RoboMaster Team Recruitment 2023</h1>
                    <Alert
                        style={{maxWidth: "95%", width: 1200, marginLeft: "auto", marginRight: "auto", marginBottom: 20}}
                        message="Welcome, Please complete this form to register"
                        type="success"
                    />
                    <Alert
                        style={{maxWidth: "95%", width: 1200, marginLeft: "auto", marginRight: "auto", marginBottom: 20}}
                        message={
                            <div className="d-flex flex-row justify-content-center">
                                <div className="m-1">If you have completed this form, please click the button and redirect to the manage system (Recommended to use computer to access)</div>
                                <Button onClick={() => window.location.replace("/home")} style={{marginTop: "auto", marginBottom: "auto"}} type="primary" shape="circle" icon={<RightCircleOutlined />} />
                            </div>
                        }
                        type="info"
                    />
                    <div className={"justify-content-center"} style={{maxWidth: "95%", width: 1200, marginLeft: "auto", marginRight: "auto", marginTop: 10}}>
                        <Form style={{width: "100%"}} labelCol={{span: 4, offset: 2}} wrapperCol={{span: 14}} onFinish={this.onFinish}>
                            <Form.Item name={"name"} label={"Name"} rules={[{required: true, message: "please enter your name"}, {max: 50, type: "string", message: "Too long, max 50"}]}>
                                <Input placeholder="Your name" />
                            </Form.Item>
                            <Form.Item name={"chineseName"} label={"Chinese Name"} rules={[{max: 20, type: "string", message: "Too long, max 20"}]} >
                                <Input placeholder="If you have a Chinese name, please enter it"/>
                            </Form.Item>
                            <Form.Item name={"nickName"} label={"Nick Name"} rules={[{max: 50, type: "string", message: "Too long, max 50"}]} >
                                <Input placeholder="Your nick name"/>
                            </Form.Item>
                            <Form.Item name={"gender"} label={"Gender"} rules={[{required: true, message: "please select your gender"}]}>
                                <Select>
                                    <Option value="MALE">male</Option>
                                    <Option value="FEMALE">female</Option>
                                    <Option value="NOT_TELL">prefer not to say</Option>
                                </Select>
                            </Form.Item>
                            <Form.Item label={"ITSC Email"} >
                                <Input.Group compact>
                                    <Form.Item style={{width: "55%"}} name={["itsc", "itsc"]} rules={[{required: true, message: "please enter your ITSC account"}, {max: 35, type: "string", message: "Too long, max 35"}]}>
                                        <Input placeholder="Your ITSC account, this will be your username" />
                                    </Form.Item>
                                    <Form.Item style={{width: "45%"}} name={["itsc", "domain"]} rules={[{required: true, message: "please select your ITSC domain"}]}>
                                        <Select>
                                            <Option value="@connect.ust.hk">@connect.ust.hk</Option>
                                            <Option value="@ust.hk">@ust.hk</Option>
                                        </Select>
                                    </Form.Item>
                                </Input.Group>
                            </Form.Item>
                            <Form.Item name={"grade"} label={"Year of Study"} rules={[{required: true, message: "please select your year of study"}]}>
                                <Select>
                                    <Option value="UG_1">UG-Year1</Option>
                                    <Option value="UG_2">UG-Year2</Option>
                                    <Option value="UG_3">UG-Year3</Option>
                                    <Option value="UG_4">UG-Year4</Option>
                                    <Option value="PG">PG</Option>
                                    <Option value="OTHER">Other</Option>
                                </Select>
                            </Form.Item>
                            <Form.Item name={"major"} label={"Major"} rules={[{max: 50, type: "string", message: "Too long, max 50"}]}>
                                <Input placeholder="Leave it empty if you have not declared your major" />
                            </Form.Item>
                            <Form.Item name={"position"} label={"Position"} rules={[{required: true, type: 'array', message: "please select at least one position"}]}>
                                <Select mode="multiple" placeholder="Select all positions you want">
                                    <Option value="SOFTWARE">Software</Option>
                                    <Option value="HARDWARE">Hardware</Option>
                                    <Option value="MECHANICAL">Mechanical</Option>
                                    <Option value="LOGISTICS">Design & Media</Option>
                                    <Option value="WEBSITE">Website</Option>
                                </Select>
                            </Form.Item>
                            <Form.Item name={"info"} label={"Additional Info"} rules={[{max: 500, type: "string", message: "no more than 500 characters"}]} >
                                <TextArea rows={4} placeholder="Tell us more about you, limited to 500 characters" />
                            </Form.Item>
                            <Form.Item name={"password"} label={"Set Password"} rules={[{required: true, message: "please set your password"}, {min: 6, type: "string", message: "at least 6 characters"}]}>
                                <Input.Password placeholder="This will be the password for you to login our system" />
                            </Form.Item>
                            <Form.Item name="confirm" label={"Confirm Password"} dependencies={['password']}
                                       rules={[{required: true, message: "please set your password"}, ({ getFieldValue }) => ({
                                           validator(_, value) {
                                               if (!value || getFieldValue('password') === value) {
                                                   return Promise.resolve();
                                               }
                                               return Promise.reject(new Error('the two passwords that you entered do not match'));
                                           },
                                       })]}>
                                <Input.Password placeholder="please enter your password again" />
                            </Form.Item>
                            <Form.Item >
                                {this.state.stage !== 1  &&
                                    <Button type="primary" htmlType="submit">
                                        Submit
                                    </Button>
                                }
                                {this.state.stage === 1  &&
                                    <Button disabled shape="circle" type="primary" htmlType="submit">
                                        <LoadingOutlined />
                                    </Button>
                                }
                            </Form.Item>
                            <Form.Item >
                                {this.state.stage === 2  &&
                                    <Alert
                                        message="Error"
                                        description={this.state.errorMsg}
                                        type="error"
                                        showIcon
                                    />
                                }
                            </Form.Item>
                        </Form>
                    </div>
                </div>
            )
    }
}

export default RegisterPage