import ClampLines from "react-clamp-lines";
import {Button, Card} from "antd";
import {DeleteOutlined, EditOutlined, CommentOutlined} from "@ant-design/icons";
import {reverseState, Editor, getUserInfo, getUserName, navigateWithoutRefresh} from "../Utils/Utils";
import React, {useState} from "react";
import {deleteFeedback, editFeedback} from "../Utils/Requests";
import {Link, useLocation} from "react-router-dom";
import {InfoAvatar} from "./InfoAvatar";
import {FeedbackStatus} from "./FeedbackStatus";
import TextArea from "antd/es/input/TextArea";
import Meta from "antd/es/card/Meta";
import {checkMemory} from "../Utils/Memory";


const Feedback = (props) => {
    const {maxLine, data, callback, linked, showCommentDialog} = props
    const [edit, setEdit] = useState(false)
    const [editValue, setEditValue] = useState(data.content)
    const [title, setTitle] = useState(data.title)
    const [loadingUser, setLoadingUser] = useState(checkMemory("userDataList"))
    console.log("loadinguser", loadingUser)
    return (
        <div>
            <Card
                bordered
                title={
                    <div style={{display: "flex"}}>
                        <h5>
                            <div style={{float: "left"}}>
                                <FeedbackStatus status={data.status}/>
                            </div>
                        </h5>
                        <div style={{margin: "auto"}}>
                            {edit ? <TextArea placeholder={"Title"} row={1} onChange={(e) => {
                                setTitle(e.target.value)
                            }} value={title} maxLength={100}/> : <h1>
                                {data.title}
                            </h1>}
                        </div>
                    </div>}
                actions={showCommentDialog === undefined ? [<EditOutlined
                    onClick={reverseState.bind(undefined, edit, setEdit)}/>,
                    <DeleteOutlined onClick={deleteFeedback.bind(undefined, data.id, callback)}/>] : [<CommentOutlined
                    onClick={showCommentDialog}/>,
                    <EditOutlined onClick={reverseState.bind(undefined, edit, setEdit)}/>,
                    <DeleteOutlined onClick={deleteFeedback.bind(undefined, data.id, callback)}/>]}>
                <div>
                    <Meta
                        avatar={<InfoAvatar userId={data.fromUid}
                                            size={"large"}/>}
                        title={<strong style={{float: "left"}}>{getUserName(data.fromUid,
                            setLoadingUser.bind(undefined, false))}</strong>}
                        description={<small style={{margin: "auto"}}>{data.time}</small>}/>
                    {edit ? <Editor
                            value={editValue}
                            onSubmit={editFeedback.bind(undefined, data.id, editValue, title, undefined, () => {
                                callback();
                                setEdit(false);
                            })}
                            onChange={(e) => setEditValue(e.target.value)}
                            submitText={"Submit"}
                        /> :
                        (linked ?
                            <div onClick={() => {
                                navigateWithoutRefresh("/feedback/" + data.id)
                            }}>
                                <div style={{
                                    maxWidth: '100%',
                                    display: '-webkit-box',
                                    WebkitBoxOrient: 'vertical',
                                    WebkitLineClamp: 2,
                                    overflow: 'hidden',
                                    textOverflow: 'ellipsis',
                                }}>{data.content}</div>
                            </div> : <div style={{
                                maxWidth: '100%',
                                display: '-webkit-box',
                                WebkitBoxOrient: 'vertical',
                                WebkitLineClamp: 2,
                                overflow: 'hidden',
                                textOverflow: 'ellipsis',
                            }}>{data.content}</div>)
                    }
                </div>
            </Card>
        </div>
    )
}

export default Feedback;
