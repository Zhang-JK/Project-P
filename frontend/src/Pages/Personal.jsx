import "./Personal.css"
import {CheckOutlined} from '@ant-design/icons';
import { Button, Modal, Form, Input } from 'antd';
import React from 'react';
import MD5 from "crypto-js/md5";
import getRequest from "../Request/GetRequest";


export default class Personal extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            stage: 0,
            loadings: []
        }
    }

    showModal = () => {
        this.setState({isModalVisible: true})
    };

    handleCancel = () => {
        this.setState({isModalVisible: false})
    };


    onFinish = (values) => {
        this.setState({stage:1})

        const {old_password, new_password, re_new_password} = values;

        if(new_password !== re_new_password){
            this.setState({stage:3})
            return
        }
        if(old_password === re_new_password){
            this.setState({stage:4})
            return
        }
        let md5old = MD5(old_password).toString();
        let md5new = MD5(new_password).toString();

        getRequest("user/changePassword?oldPassword=" + md5old +
            "&newPassword=" + md5new).then(res => {

            if(res.code === 200){
                this.setState({stage:2})
                window.location.replace('/home')
            } else {
                this.setState({stage:5})
            }
        })
    };

    onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    render(){
        return (
            <>
                <Button type="primary" onClick={this.showModal}>
                    Change Password here
                </Button>
                <Modal title="Change Password" visible={this.state.isModalVisible} onCancel={this.handleCancel}
                       footer={[
                           <Button key="Cancel" onClick={this.handleCancel}>
                               Cancel
                           </Button>]}
                >

                    <Form
                        name="basic"
                        labelCol={{
                            span: 8,
                        }}
                        wrapperCol={{
                            span: 16,
                        }}
                        initialValues={{
                            remember: true,
                        }}
                        onFinish={this.onFinish}
                        onFinishFailed={this.onFinishFailed}
                        autoComplete="off"
                    >
                        <Form.Item
                            label="Old Password"
                            name="old_password"
                            rules={[
                                {
                                    required: true,
                                    message: 'Please input your old password',
                                },
                            ]}
                        >
                            <Input.Password/>
                        </Form.Item>

                        <Form.Item
                            label="New Password"
                            name="new_password"
                            rules={[
                                {
                                    required: true,
                                    message: 'Please input your new password',
                                },
                                {
                                    message: 'please type in at least 6 characters',
                                    type: "string",
                                    min: 6,
                                },
                            ]}
                            hasFeedback
                        >
                            <Input.Password/>
                        </Form.Item>

                        <Form.Item
                            label="New Password Again"
                            name="re_new_password"
                            dependencies={["new_password"]}
                            hasFeedback
                            rules={[
                                {
                                    required: true,
                                    message: 'please type in your password correctly',
                                },
                                ({getFieldValue}) => ({
                                    validator(_, value) {
                                        if (!value || getFieldValue('new_password') === value) {
                                            return Promise.resolve();
                                        }

                                        return Promise.reject(new Error('The two passwords that you entered do not match!'));
                                    },
                                }),
                            ]}
                        >
                            <Input.Password/>
                        </Form.Item>

                        <Form.Item
                            wrapperCol={{
                                offset: 8,
                                span: 16,
                            }}
                        >
                            {
                                (this.state.stage === 0||this.state.stage ===3||this.state.stage ===4||this.state.stage ===5) &&
                                <Button type="primary" htmlType="submit">
                                    Submit
                                </Button>}

                            {
                                this.state.stage === 1 &&
                                <Button type="primary" loading>
                                    Loading
                                </Button>
                            }

                            {
                                this.state.stage === 2 &&
                                <CheckOutlined/>
                            }

                            {
                                this.state.stage === 3 &&
                                <div style={{color: "red"}}> new password incorrect, please type in again</div>
                            }

                            {
                                this.state.stage === 4 &&
                                <div style={{color: "red"}}> you do not change the password, please type in a new password</div>
                            }

                            {
                                this.state.stage === 5 &&
                                <div style={{color: "red"}}> old password incorrect, please type in again</div>
                            }
                        </Form.Item>
                    </Form>

                </Modal>
            </>
        );
    }

}