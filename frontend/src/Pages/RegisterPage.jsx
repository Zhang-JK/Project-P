import React from 'react';
import { Button, Form, Input, Select } from 'antd';
const { Option } = Select;
const { TextArea } = Input;

class RegisterPage extends React.Component<> {
    formRef = React.createRef()

    onFinish = (values) => {
        console.log(values)
    }

    onReset = () => {
        this.formRef.current.resetFields();
    };

    onChange = (checkedValues) => {
        console.log('checked = ', checkedValues);
    };

    render() {
        return (
            <div className="d-flex flex-column justify-content-center">
                <div className={"justify-content-center"} style={{maxWidth: "100%", width: 1200}}>
                    <Form style={{width: "100%"}} labelCol={{span: 4}} wrapperCol={{span: 14}} onFinish={this.onFinish}>
                        <Form.Item name={"name"} label={"Name"} rules={[{required: true, message: "please enter your name"}]}>
                            <Input placeholder="Your name" />
                        </Form.Item>
                        <Form.Item name={"chineseName"} label={"Chinese Name"} >
                            <Input placeholder="If you have a Chinese name, please enter it"/>
                        </Form.Item>
                        <Form.Item name={"nickName"} label={"Nick Name"} >
                            <Input placeholder="Your nick name"/>
                        </Form.Item>
                        <Form.Item name={"gender"} label={"Gender"} rules={[{required: true, message: "please select your gender"}]}>
                            <Select>
                                <Option value="MALE">male</Option>
                                <Option value="FEMALE">female</Option>
                                <Option value="NO">prefer not to say</Option>
                            </Select>
                        </Form.Item>
                        <Form.Item label={"ITSC Email"} >
                            <Input.Group compact>
                                <Form.Item name={["itsc", "itsc"]} rules={[{required: true, message: "please enter your ITSC account"}]}>
                                    <Input placeholder="Your ITSC account, this will be your username" />
                                </Form.Item>
                                <Form.Item name={["itsc", "domain"]} rules={[{required: true, message: "please select your ITSC domain"}]}>
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
                        <Form.Item name={"major"} label={"Major"}>
                            <Input placeholder="Leave it empty if you have not declared your major" />
                        </Form.Item>
                        <Form.Item name={"position"} label={"Position"} rules={[{required: true, type: 'array', message: "please select at least one position"}]}>
                            <Select mode="multiple" placeholder="Select all positions you want">
                                <Option value="SOFTWARE">Software</Option>
                                <Option value="HARDWARE">Hardware</Option>
                                <Option value="MECHANICAL">Mechanical</Option>
                                <Option value="LOGISTICS">Logistics</Option>
                                <Option value="WEBSITE">Website</Option>
                            </Select>
                        </Form.Item>
                        <Form.Item name={"info"} label={"Additional Info"} rules={[{max: 500, type: "string", message: "no more than 500 characters"}]} >
                            <TextArea rows={4} placeholder="Tell us more about you, limited to 500 characters" />
                        </Form.Item>
                        <Form.Item name={"password"} label={"Set Password"} rules={[{required: true, message: "please set your password"}, {min: 6, type: "string", message: "at least 6 characters"}]}>
                            <Input type="password" placeholder="This will be the password for you to login our system" />
                        </Form.Item>
                        <Form.Item >
                            <Button type="primary" htmlType="submit">
                                Submit
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        )
    }
}

export default RegisterPage