import ClampLines from "react-clamp-lines";
import {Button, Card} from "antd";
import {DeleteOutlined, EditOutlined} from "@ant-design/icons";
import {reverseState, Editor} from "../Utils/Utils";
import React, {useState} from "react";
import {deleteFeedback, editFeedback} from "../Utils/Requests";
import {Link} from "react-router-dom";
import {InfoAvatar} from "./InfoAvatar";
import {FeedbackStatus} from "./FeedbackStatus";
import TextArea from "antd/es/input/TextArea";


const Feedback = (props) => {
    const {maxLine, data, callback, linked} = props
    const [edit, setEdit] = useState(false)
    const [editValue, setEditValue] = useState(data.content)
    const [title, setTitle] = useState(data.title)
    return (
        <div>
            <Card
                title={
                    <div style={{display: "flex"}}>
                        <h3>
                            <div style={{float: "left"}}>
                                <InfoAvatar userId={data.fromUid} size={"large"}/>
                            </div>
                        </h3>
                        <h5>
                            <div style={{float: "left"}}>
                                <FeedbackStatus status={data.status}/>
                            </div>
                        </h5>
                        <div style={{margin:"auto"}}>
                            {edit ? <TextArea placeholder={"Title"} row={1} onChange={(e) => {
                            setTitle(e.target.value)
                        }} value={title} maxLength={100}/> : <h1>
                            {data.title}
                        </h1>}
                        </div>
                        Last edit at:{data.time}
                    </div>}
                extra={[<Button onClick={reverseState.bind(undefined, edit, setEdit)} type={"link"} size={"small"}
                                icon={<EditOutlined/>}>{"Edit"}</Button>,
                    <Button onClick={deleteFeedback.bind(undefined, data.id, callback)} type={"link"}
                            size={"small"}
                            icon={<DeleteOutlined/>}>{"Delete"}</Button>]}>
                <div>
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
                            <Link to={"" + data.id}>
                                <div style={{
                                    maxWidth: '100%',
                                    display: '-webkit-box',
                                    WebkitBoxOrient: 'vertical',
                                    WebkitLineClamp: 2,
                                    overflow: 'hidden',
                                    textOverflow: 'ellipsis',
                                }}>{data.content}</div>
                            </Link> : <div style={{
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
