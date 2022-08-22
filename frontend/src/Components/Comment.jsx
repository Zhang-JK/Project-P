import {Avatar, Button, Comment, List, Form} from "antd";
import {CommentOutlined, EditOutlined, DeleteOutlined} from "@ant-design/icons"
import {PriorityQueue} from "@datastructures-js/priority-queue"
import React, {useState} from "react";
import TextArea from "antd/es/input/TextArea";
import postRequest from "../Request/PostRequest";
import {Editor, getUserDataList, getUserInfo, getUserName, reverseState} from "../Utils/Utils";
import {createComment, deleteComment, editComment} from "../Utils/Requests";
import {InfoAvatar} from "./InfoAvatar";
import {checkMemory, getMemory} from "../Utils/Memory";


class CommentEntity {
    constructor(id, fbId, content, parentId, fromUid, time) {
        this.id = id;
        this.fbId = fbId;
        this.content = content;
        this.parentId = parentId;
        this.fromUid = fromUid;
        this.time = Date.parse(time);
        this.adj = []
    }
}

const compareComment = function (a, b) {
    return a.id - b.id
};

class CommentNode {
    constructor(comment) {
        this.comment = comment;
        this.queue = new PriorityQueue(compareComment);
    }

    addChildComment(childComment) {
        this.queue.enqueue(childComment);
    }
}


const CommentDOM = (props) => {
    const [replyValue, setReplayValue] = useState("");
    const [editValue, setEditValue] = useState(props.node.comment.content);
    const [reply, setReply] = useState(false)
    const [edit, setEdit] = useState(false)
    const [loadingUser, setLoadingUser] = useState(checkMemory("userDataList"))
    let fbId = props.fbId
    let children = [];
    let childNodes = props.node.queue.toArray()
    for (let i = 0; i < childNodes.length; i++) {
        let dom = CommentDOM({
            key: childNodes[i].comment.id,
            node: childNodes[i],
            fbId: fbId,
            setLoading: props.setLoading
        });
        children.push(dom);
    }
    const callback = () => {
        props.setLoading(true)
    }
    getUserDataList();
    let finalComment = <Comment
        key={props.node.comment.id + "Comment"}
        actions={[<Button onClick={reverseState.bind(undefined, reply, setReply)} type={"link"} size={"small"}
                          icon={<CommentOutlined/>}>{"Reply"}</Button>,
            <Button onClick={reverseState.bind(undefined, edit, setEdit)} type={"link"} size={"small"}
                    icon={<EditOutlined/>}>{"Edit"}</Button>,
            <Button onClick={deleteComment.bind(undefined, props.node.comment.id, callback)} type={"link"}
                    size={"small"}
                    icon={<DeleteOutlined/>}>{"Delete"}</Button>]}
        author={
            <a>{getUserName(props.node.comment.fromUid, setLoadingUser.bind(undefined, false))}</a>}
        avatar={<InfoAvatar userId={props.node.comment.fromUid}/>}
        content={
            <div>
                <p>
                    {edit ?
                        <Editor
                            value={editValue}
                            onSubmit={editComment.bind(null, props.node.comment.id, editValue, callback)}
                            onChange={(e) => setEditValue(e.target.value)}
                            submitText={"Finished"}/>
                        : props.node.comment.content}
                </p>
            </div>
        }
        datetime={props.node.comment.time}
    >
        <div style={reply ? undefined : {maxHeight: "0px", overflow: "hidden"}}>
            <Editor
                // className={styles.replyEditor}
                value={replyValue}
                onSubmit={createComment.bind(null, fbId, props.node.comment.id, replyValue, callback)}
                onChange={(e) => setReplayValue(e.target.value)}
                submitText={"Reply"}
            />
        </div>
        {children}
        {/*<Comment content={<p>test</p>}/>*/}
    </Comment>;
    return finalComment
}


const compareCommentUsingId = (a, b) => {
    return a.comment.id - b.comment.id
}

class CommentTree extends React.Component {

    constructor(props) {
        super(props)
        let {comments} = this.props
        this.setLoading = this.props.setLoading
        console.log("comments: ", comments)
        this.fbId = this.props.fbId
        this.root = new CommentNode(null);
        let commentNodes = []
        for (let i = 0; i < comments.length; i++) {
            commentNodes.push(new CommentNode(comments[i]))
        }
        let tComments = [...commentNodes]
        let queue = PriorityQueue.fromArray(tComments, compareCommentUsingId);
        while (queue.size() > 0) {
            let comment = queue.dequeue();
            if (comment.comment.parentId < 0) {
                this.root.addChildComment(comment);
            } else {
                for (let i = 0; i < commentNodes.length; i++) {
                    if (commentNodes[i].comment.id === comment.comment.parentId) {
                        commentNodes[i].addChildComment(comment);
                        break;
                    }
                }
            }
        }
    }

    render() {
        let comments = this.root.queue.toArray()
        return (
            <List
                className="comment-list"
                header={`${comments.length} replies`}
                itemLayout="horizontal"
                dataSource={comments}
                renderItem={item => (
                    <li>
                        <CommentDOM
                            key={item.comment.id}
                            node={item}
                            fbId={this.fbId}
                            setLoading={this.setLoading}
                        />
                    </li>
                )}
            />
        );
    }
}

export default CommentTree;
