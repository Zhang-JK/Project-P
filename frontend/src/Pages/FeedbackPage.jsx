import Feedback from "../Components/Feedback";
import {useState} from "react";
import {Affix, Button, List, Modal, Skeleton} from "antd";
import postRequest from "../Request/PostRequest";
import React from "react";
import FeedbackCommentPage from "./FeedbackCommentPage";
import TextArea from "antd/es/input/TextArea";
import {PlusCircleOutlined} from "@ant-design/icons";
import {createFeedback} from "../Utils/Requests";
import getRequest from "../Request/GetRequest";

let fbDataOld = undefined;
const setFbData = (data) => {
    fbDataOld = data
}
export const Feedbacks = () => {
    setFbData(undefined);
    const [data, setData] = useState(null)
    const [loading, setLoading] = useState(true)
    const [fbData, setFbDataS] = useState(undefined)
    const [visible, setVisible] = useState(false);
    const [confirmLoading, setConfirmLoading] = useState(false);
    const [feedback, setFeedback] = useState("");
    const [title, setTitle] = useState("");
    if (data == null) {
        getRequest("user/getInfo")
            .catch(error => {
                console.log('ERROR: ', error)
                window.location.replace("/login")
            })
            .then((res) => {
                if (res.code !== 200) {
                    window.location.replace("/login")
                }
                setData(res.data)
            })
    }
    if (loading && data != null) {
        postRequest("fetchFeedback").then(result => {
            if (result.code === 200) {
                setFbDataS(result.data.fbPojoList);
                setLoading(false);
            } else {
                alert("Fetching feedback error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
    return (
        <div>
            <Skeleton loading={loading} active avatar>
                {!loading && <List
                    className="feedback-list"
                    header={`${fbData.length} feedbacks`}
                    itemLayout="horizontal"
                    dataSource={fbData}
                    renderItem={item => (
                        <li>
                            <Feedback key={item.id} maxLine={2} data={item} callback={setLoading.bind(undefined, true)}
                                      linked={true}/>
                            <br/>
                        </li>
                    )}
                />}
            </Skeleton>
            <Modal
                title={"Create a new feedback"}
                visible={visible}
                onOk={() => {
                    createFeedback(feedback, title,() => {
                        setConfirmLoading(false);
                        setVisible(false)
                        setLoading(true)
                    }, () => {
                        setConfirmLoading(false)
                    })
                }}
                confirmLoading={confirmLoading}
                onCancel={() => {
                    setVisible(false)
                }}
            >
                <TextArea placeholder={"Title"} row={1} onChange={(e) => {
                    setTitle(e.target.value)
                }} value={title} maxLength={100}/>
                <TextArea placeholder={"Content"} row={4} onChange={(e) => {
                    setFeedback(e.target.value)
                }} value={feedback} maxLength={500}/>

            </Modal>
            <div style={{float: "right"}}>
                <Affix offsetBottom={100}>
                    <Button style={{background: "grey"}} icon={<PlusCircleOutlined/>} size={"large"} ghost
                            onClick={() => {
                                setVisible(true)
                            }}
                    />
                </Affix>
            </div>
        </div>
    )
}

const FeedbackPage = (props) => {
    const {arg} = props;
    if (arg===undefined||arg===""){
        return (<Feedbacks/>)
    } else
        return <FeedbackCommentPage fbId={Number.parseInt(arg)}/>
};

export default FeedbackPage;
