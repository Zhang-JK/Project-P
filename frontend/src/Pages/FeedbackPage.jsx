import {Route, Routes, useParams, Link} from "react-router-dom";
import Feedback from "../Components/Feedback";
import {useState} from "react";
import CommentTree from "../Components/Comment";
import {Affix, Button, List, Modal, Skeleton} from "antd";
import postRequest from "../Request/PostRequest";
import React from "react";
import FeedbackCommentPage from "./FeedbackCommentPage";
import {createComment, createFeedback} from "../Utils/Utils";
import TextArea from "antd/es/input/TextArea";
import {PlusCircleOutlined} from "@ant-design/icons";

let fbDataOld = undefined;
const setFbData = (data) => {
    fbDataOld = data
}
const Feedbacks = () => {
    setFbData(undefined);
    const [loading, setLoading] = useState(true)
    const [fbData, setFbDataS] = useState(undefined)
    const [visible, setVisible] = useState(false);
    const [confirmLoading, setConfirmLoading] = useState(false);
    const [feedback, setFeedback] = useState("");
    if (loading) {
        postRequest("fetchFeedback").then(result => {
            if (result.code === 200) {
                setFbDataS(result.data.fbPojoList);
                setLoading(false);
            } else {
                alert("Fetching feedback error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
    console.log("fbData:", fbData)
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
                            <Link to={"" + item.id}>
                                <Feedback maxLine={2} data={item} callback={setLoading.bind(undefined, true)}/>
                            </Link>
                        </li>
                    )}
                />}
            </Skeleton>
            <Modal
                title={"Create a new comment"}
                visible={visible}
                onOk={() => {
                    createFeedback(feedback, () => {
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
                <TextArea row={4} onChange={(e) => {
                    setFeedback(e.target.value)
                }} value={feedback}/>

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
    return (
        <div>
            <Routes>
                <Route path={""} element={<Feedbacks/>}/>
                <Route path={":fbId"} element={<FeedbackCommentPage fbDataOld={fbDataOld}/>}/>
            </Routes>
        </div>
    )
};

export default FeedbackPage;
