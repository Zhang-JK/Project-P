import ClampLines from "react-clamp-lines";
import {Button, Card} from "antd";
import {DeleteOutlined, EditOutlined} from "@ant-design/icons";
import {deleteFeedback, reverseState, Editor, editFeedback} from "../Utils/Utils";
import React, {useState} from "react";


const Feedback = (props) => {
    const {maxLine, data, callback} = props
    const [edit, setEdit] = useState(false)
    const [editValue, setEditValue] = useState(data.content)
    return (
        <div>
            <Card
                title={"Id: " + data.id + " FromUid:" + data.fromUid + " Status: " + data.status + "time: " + data.time}
                extra={[<Button onClick={reverseState.bind(undefined, edit, setEdit)} type={"link"} size={"small"}
                                icon={<EditOutlined/>}>{"Edit"}</Button>,
                    <Button onClick={deleteFeedback.bind(undefined, data.id, callback)} type={"link"}
                            size={"small"}
                            icon={<DeleteOutlined/>}>{"Delete"}</Button>]}>
                <p>
                    {edit ? <Editor
                        value={editValue}
                        onSubmit={editFeedback.bind(undefined, data.id, editValue, () => {
                            callback();
                            setEdit(false);
                        })}
                        onChange={(e) => setEditValue(e.target.value)}
                        submitText={"Submit"}
                    /> : <ClampLines
                        text={data.content}
                        id={data.id}
                        lines={maxLine}
                        buttons={false}
                    />}
                </p>
            </Card>
        </div>
    )
}

export default Feedback;
