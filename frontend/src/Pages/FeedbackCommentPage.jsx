import CommentTree from "../Components/Comment";
import {Affix, Button, Comment, Modal, Skeleton} from "antd";
import postRequest from "../Request/PostRequest";
import {useState} from "react";
import Feedback from "../Components/Feedback";
import {PlusCircleOutlined} from "@ant-design/icons"
import TextArea from "antd/es/input/TextArea";
import {useParams} from "react-router-dom";
import {createComment} from "../Utils/Requests";


const FeedbackCommentPage = (props) => {
    const {fbId} = useParams();
    let {fbDataOld} = props
    if (fbDataOld&&fbDataOld.id !== fbId){
        fbDataOld = undefined;
    }
    let [loading, setLoading] = useState(true)
    let [loadingFb, setLoadingFb] = useState(fbDataOld === undefined)
    let [data, setData] = useState(undefined)
    let [fbData, setFbData] = useState(fbDataOld)
    const [visible, setVisible] = useState(false);
    const [confirmLoading, setConfirmLoading] = useState(false);
    const [comment, setComment] = useState("");
    if (loadingFb) {
        postRequest("fetchOneFeedback", {fbId: fbId}).then(result => {
            if (result.code === 200) {
                console.log("Data:", data)
                setFbData(result.data.fbPojoList[0]);
                setLoadingFb(false);
            } else {
                alert("Fetching feedback error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
    if (!loadingFb && loading) {
        postRequest("fetchComment", {fbId: fbId}).then(result => {
            console.log("Fetch result: ", result)
            if (result.code === 200) {
                setData(result.data);
                setLoading(false);
            } else {
                alert("Fetching comment error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }


    return (
        <div>
            <h1>Hello FeedbackCommentPage</h1>
            <Skeleton loading={loadingFb} active avatar>
                {!loadingFb && <Feedback data={fbData} callback={setLoadingFb.bind(undefined, true)}/>}
            </Skeleton>
            <Skeleton loading={loading} active avatar>
                {!loading && <CommentTree comments={data.fbCommentList} fbId={fbId} setLoading={setLoading}/>}
            </Skeleton>
            <Modal
                title={"Create a new comment"}
                visible={visible}
                onOk={() => {
                    createComment(fbId, -1, comment, () => {
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
                    setComment(e.target.value)
                }} value={comment}/>

            </Modal>
            <div style={{float:"right"}}>
                <Affix offsetBottom={100}>
                    <Button style={{background: "grey"}} icon={<PlusCircleOutlined/>} size={"large"} ghost
                            onClick={() => {
                                setVisible(true)
                            }}
                    />
                </Affix>
            </div>
        </div>
    );
}

export default FeedbackCommentPage;
