import {Avatar, Button, Comment, List, Form} from "antd";
import {CommentOutlined, EditOutlined, DeleteOutlined} from "@ant-design/icons"
import {PriorityQueue} from "@datastructures-js/priority-queue"
import React, {useState} from "react";
import TextArea from "antd/es/input/TextArea";
import postRequest from "../Request/PostRequest";

let sendingComment = false;
let editingComment = false;
let deletingComment = false;

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

const Editor = ({onChange, onSubmit, submitting, value, submitText}) => (
    <>
        <Form.Item>
            <TextArea rows={4} onChange={onChange} value={value}/>
        </Form.Item>
        <Form.Item>
            <Button htmlType="submit" loading={submitting} onClick={onSubmit} type="primary">
                {submitText}
            </Button>
        </Form.Item>
    </>
);

const submitComment = (fbId, commentId, value, setLoading) => {
    console.log("Sending comment : fbId", fbId, "commentId", commentId, "value", value)
    if (!sendingComment) {
        sendingComment = true;
        postRequest("createComment", {fbId: fbId, msg: value, commentId: commentId}).then(result => {
            if (result.code === 200) {
                alert("create meg success!")
                setLoading(true)
                sendingComment = false;
                //refresh page here
            } else {
                sendingComment = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
const editComment = (commentId, value, setLoading) => {
    console.log("Sending Editing comment :", "commentId", commentId, "value", value)
    if (!editingComment) {
        editingComment = true;
        postRequest("editComment", {msg: value, commentId: commentId}).then(result => {
            if (result.code === 200) {
                alert("edit comment success!")
                setLoading(true)
                editingComment = false;
                //refresh page here
            } else {
                editingComment = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
const deleteComment = (commentId, setLoading) => {
    console.log("Sending delete comment :", "commentId", commentId)
    if (!deletingComment) {
        deletingComment = true;
        postRequest("deleteComment", {commentId: commentId}).then(result => {
            if (result.code === 200) {
                alert("delete comment success!")
                setLoading(true)
                deletingComment = false;
                //refresh page here
            } else {
                deletingComment = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
const reverseState = (state, setState) => setState(!state)
const CommentDOM = (node) => {
    const [replyValue, setReplayValue] = useState("");
    const [editValue, setEditValue] = useState(node.node.comment.content);
    const [reply, setReply] = useState(false)
    const [edit, setEdit] = useState(false)
    if (node === undefined) {
        return null
    }
    let fbId = node.fbId
    let children = [];
    let childNodes = node.node.queue.toArray()
    for (let i = 0; i < childNodes.length; i++) {
        let dom = CommentDOM({node: childNodes[i], fbId: fbId, setLoading: node.setLoading});
        children.push(dom);
    }

    let finalComment = <Comment
        actions={[<Button onClick={reverseState.bind(undefined, reply, setReply)} type={"link"} size={"small"}
                          icon={<CommentOutlined/>}>{"Reply"}</Button>,
            <Button onClick={reverseState.bind(undefined, edit, setEdit)} type={"link"} size={"small"}
                    icon={<EditOutlined/>}>{"Edit"}</Button>,
            <Button onClick={deleteComment.bind(undefined, node.node.comment.id, node.setLoading)} type={"link"} size={"small"}
                    icon={<DeleteOutlined/>}>{"Delete"}</Button>]}
        author={<a>{node.node.comment.fromUid}</a>}
        avatar={<Avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo"/>}
        content={
            <div>
                <p>
                    {edit ?
                        <Editor
                            value={editValue}
                            onSubmit={editComment.bind(null, node.node.comment.id, editValue, node.setLoading)}
                            onChange={(e) => setEditValue(e.target.value)}
                            submitText={"Finished"}/>
                        : node.node.comment.content}
                </p>
            </div>
        }
        datetime={node.node.comment.time}
    >
        <div style={reply ? undefined : {maxHeight: "0px", overflow: "hidden"}}>
            <Editor
                // className={styles.replyEditor}
                value={replyValue}
                onSubmit={submitComment.bind(null, fbId, node.node.comment.id, replyValue, node.setLoading)}
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
        console.log("props: ", comments)
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
        let comments = []
        while (this.root.queue.size() > 0) {
            let commentNode = this.root.queue.dequeue()
            comments.push(commentNode)
        }
        return (
            <List
                className="comment-list"
                header={`${comments.length} replies`}
                itemLayout="horizontal"
                dataSource={comments}
                renderItem={item => (
                    <li>
                        <CommentDOM
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
