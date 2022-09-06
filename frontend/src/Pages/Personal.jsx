import "./Personal.css"
import {CheckOutlined, LoadingOutlined} from '@ant-design/icons';
import {Button, Modal, Form, Input, Spin, Card, Steps, Result, Descriptions, Radio} from 'antd';
import React from 'react';
import MD5 from "crypto-js/md5";
import getRequest from "../Request/GetRequest";
import {FreshStageList, stageToNumber} from "../Utils/StringEnum";

const {Step} = Steps;

export default class Personal extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            stage: 0,
            loadings: [],
            interviewTime: [],
            interviewData: null,
            interviewNotSelected: null,
            interviewTimeShow: false,
            interviewSubmitState: 0, //0 edit, 1 loading, 2 success, 3 fail
            interviewSubmitMsg: "",
        }
    }

    getInterview() {
        if (this.props.userInfo != null) {
            if (this.state.interviewTime.length === 0)
                getRequest("interview/getSections")
                    .then((r) => {
                        if (r.code === 1003)
                            window.location.replace("/login")
                        else if (r.code === 200) {
                            this.setState({interviewTime: r.data})
                        }
                    })
            if ((this.state.interviewData == null && this.state.interviewNotSelected == null) && this.state.interviewTime.length !== 0)
                getRequest(`interview/getInfo?freshId=${this.props.userInfo.freshInfo.id}`)
                    .then((r) => {
                        if (r.code === 1004)
                            this.setState({
                                interviewNotSelected: true,
                                interviewData: {date: "Not Selected", startTime: "Not Selected", room: "Not Selected"}
                            })
                        else if (r.code !== 200)
                            window.location.replace("/login")
                        else
                            this.setState({interviewData: r.data, interviewNotSelected: null})
                    })
        }
    }

    onFinishInterview = (v) => {
        this.setState({interviewSubmitState: 1, interviewSubmitMsg: ""})
        getRequest(`interview/update?freshId=${this.props.userInfo.freshInfo.id}&interviewId=${v.t}`)
            .catch(r => {
                console.log(r)
                this.setState({interviewSubmitState: 3, interviewSubmitMsg: r})
            })
            .then(r => {
                if (r.code === 200)
                    this.setState({interviewSubmitState: 2})
                else
                    this.setState({interviewSubmitState: 3, interviewSubmitMsg: r.msg})
            })

    }

    onCancelInterview = () => {
        this.setState({interviewTimeShow: false})
        if (this.state.interviewSubmitState >= 2)
            window.location.replace('personal')
    }

    showModal = () => {
        this.setState({isModalVisible: true})
    };

    handleCancel = () => {
        this.setState({isModalVisible: false})
    };


    onFinish = (values) => {
        this.setState({stage: 1})

        const {old_password, new_password, re_new_password} = values;

        if (new_password !== re_new_password) {
            this.setState({stage: 3})
            return
        }
        if (old_password === re_new_password) {
            this.setState({stage: 4})
            return
        }
        let md5old = MD5(old_password).toString();
        let md5new = MD5(new_password).toString();

        getRequest("user/changePassword?oldPassword=" + md5old +
            "&newPassword=" + md5new).then(res => {

            if (res.code === 200) {
                this.setState({stage: 2})
                window.location.replace('/login')
            } else {
                this.setState({stage: 5})
            }
        })
    };

    onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    render() {
        if (this.props.userInfo != null) {
            if (this.props.userInfo.freshInfo != null)
                this.getInterview()
            return (
                <div>
                    <h2 style={{
                        borderBottom: "solid 3px",
                        width: "60%",
                        marginLeft: "auto",
                        marginRight: "auto",
                        marginBottom: 20,
                        minWidth: 200
                    }}>Personal Info</h2>
                    <div style={{marginBottom: 50, width: "70%", minWidth: 250, marginLeft: "auto", marginRight: "auto"}}>
                        <Descriptions >
                            <Descriptions.Item label="Name"><strong>{this.props.userInfo.user.name}</strong></Descriptions.Item>
                            <Descriptions.Item label="Email"><strong>{this.props.userInfo.user.email}</strong></Descriptions.Item>
                            <Descriptions.Item label="Username"><strong>{this.props.userInfo.user.username}</strong></Descriptions.Item>
                        </Descriptions >
                        {this.props.userInfo.freshInfo != null &&
                            <Descriptions >
                                <Descriptions.Item label="Gender"><strong>{this.props.userInfo.freshInfo.gender}</strong></Descriptions.Item>
                                <Descriptions.Item label="Grade"><strong>{this.props.userInfo.freshInfo.grade}</strong></Descriptions.Item>
                                <Descriptions.Item label="Major"><strong>{this.props.userInfo.freshInfo.major}</strong></Descriptions.Item>
                                <Descriptions.Item label="Position"><strong>{this.props.userInfo.freshInfo.positions}</strong></Descriptions.Item>
                                <Descriptions.Item label="Stage"><strong>{this.props.userInfo.freshInfo.stage}</strong></Descriptions.Item>
                            </Descriptions>
                        }
                    </div>
                    {this.props.userInfo.freshInfo != null &&
                        <h2 style={{
                            borderBottom: "solid 3px",
                            width: "60%",
                            marginLeft: "auto",
                            marginRight: "auto",
                            marginBottom: 20,
                            minWidth: 200
                        }}>Interview Time</h2>
                    }
                    {this.props.userInfo.freshInfo != null && this.state.interviewData == null &&
                        <div className="m-2">
                            <Spin/>
                        </div>
                    }
                    {this.props.userInfo.freshInfo != null && this.state.interviewData != null &&
                        <div className="d-flex flex-row justify-content-center"
                             style={{marginLeft: "auto", marginRight: "auto", marginTop: 15, marginBottom: 20}}>
                            <Card title="Interview Info" style={{width: 300}}>
                                <p>Date: <strong>{this.state.interviewData.date}</strong></p>
                                <p>Start Time: <strong>{this.state.interviewData.startTime}</strong></p>
                                <p>Venue: <strong>{this.state.interviewData.room}</strong></p>
                                <Button type="primary" onClick={() => this.setState({interviewTimeShow: true})}>
                                    {this.state.interviewNotSelected === true ? "Choose" : "Edit"} Time
                                </Button>
                            </Card>
                            <Modal title="Select Interview Timeslot" visible={this.state.interviewTimeShow} keyboard={true}
                                   onCancel={this.onCancelInterview}
                                   footer={[
                                        <Button key="Cancel" onClick={this.onCancelInterview}>
                                            Cancel
                                        </Button>]
                            }>
                                <div>
                                    {(this.state.interviewSubmitState === 0 || this.state.interviewSubmitState === 1) &&
                                        <Form onFinish={this.onFinishInterview}>
                                            <h3>Please Select a start time below</h3>
                                            <Form.Item name="t" label="Start Time"
                                                       rules={[{required: true, message: "Please select a timeslot"}]}>
                                                <Radio.Group>
                                                    {this.state.interviewTime.map(i =>
                                                        <Radio disabled={i.full}
                                                               value={i.id}>{`${i.date} ${i.startTime}`}</Radio>
                                                    )}
                                                </Radio.Group>
                                            </Form.Item>
                                            <Form.Item>
                                                {this.state.interviewSubmitState === 0 &&
                                                    <Button type="primary" htmlType="submit">
                                                        Submit
                                                    </Button>
                                                }
                                                {this.state.interviewSubmitState === 1 &&
                                                    <LoadingOutlined/>
                                                }
                                            </Form.Item>
                                        </Form>
                                    }
                                    {this.state.interviewSubmitState === 2 &&
                                        <Result
                                            title="Registered Successfully"
                                            status="success"
                                            extra={
                                                <div>
                                                    <h4>Refresh the page to check your interview venue</h4>
                                                    <Button type="primary" onClick={this.onCancelInterview}>
                                                        OK
                                                    </Button>
                                                </div>
                                            }
                                        />
                                    }
                                    {this.state.interviewSubmitState === 3 &&
                                        <Result
                                            title="Registered Fail"
                                            status="warning"
                                            extra={
                                                <div>
                                                    <p>Error Info: {this.state.interviewSubmitMsg}</p>
                                                    <Button type="primary" onClick={() => {this.setState({interviewSubmitState: 0})}}>
                                                        Try Again
                                                    </Button>
                                                </div>
                                            }
                                        />
                                    }
                                </div>
                            </Modal>
                        </div>
                    }
                    {this.props.userInfo.freshInfo != null &&
                        <h2 style={{
                            borderBottom: "solid 3px",
                            width: "60%",
                            marginLeft: "auto",
                            marginRight: "auto",
                            marginBottom: 20,
                            minWidth: 200
                        }}>Progress</h2>
                    }
                    {this.props.userInfo.freshInfo != null && this.props.userInfo.freshInfo.stage !== "Disqualified" &&
                        <Steps direction="vertical" current={stageToNumber(this.props.userInfo.freshInfo.stage) + 1} status="wait"
                               style={{width: 230, marginLeft: "auto", marginRight: "auto", marginBottom: 30}}>
                            {FreshStageList.map(i => <Step title={i.name} description={i.description}/>)}
                        </Steps>
                    }
                    {this.props.userInfo.freshInfo != null && this.props.userInfo.freshInfo.stage === "Disqualified" &&
                        <Result
                            status="error"
                            title="Thanks for choosing HKUST RoboMaster Team."
                            extra={
                                <div>Should you have any question, please <a href = "mailto:robomasterhkust@gmail.com">send an email</a> to us</div>
                            }
                        />
                    }
                    <h2 style={{
                        borderBottom: "solid 3px",
                        width: "60%",
                        marginLeft: "auto",
                        marginRight: "auto",
                        marginBottom: 20,
                        minWidth: 200
                    }}>Change Password</h2>
                    <div>
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
                                        (this.state.stage === 0 || this.state.stage === 3 || this.state.stage === 4 || this.state.stage === 5) &&
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
                                        <div style={{color: "red"}}> you do not change the password, please type in a
                                            new password</div>
                                    }

                                    {
                                        this.state.stage === 5 &&
                                        <div style={{color: "red"}}> old password incorrect, please type in again</div>
                                    }
                                </Form.Item>
                            </Form>
                        </Modal>
                    </div>
                </div>
            );
        } else return <Spin/>
    }

}