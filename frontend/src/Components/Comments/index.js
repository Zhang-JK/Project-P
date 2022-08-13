import {Avatar, Comment, List} from "antd";
import {PriorityQueue} from "@datastructures-js/priority-queue"
import React from "react";

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
    return a.time - b.time
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

const CommentDOM = (node) => {
    console.log("CommentDom", node)
    if (node === undefined){
        return null
    }
    let children = [];
    while (node.node.queue.size() > 0) {
        let child = node.node.queue.dequeue();
        let dom = CommentDOM({node:child});
        children.push(dom);
    }
    let finalcomment = <><Comment
        actions={[<span key="comment-nested-reply-to">Reply to</span>]}
        author={<a>{node.node.comment.fromUid}</a>}
        avatar={<Avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo"/>}
        content={
            <p>
                {node.node.comment.content}
            </p>
        }
    >
        {children}
    </Comment></>;
    console.log(finalcomment);
    return finalcomment
}


const compareCommentUsingId = (a, b) => {
    return a.comment.id - b.comment.id
}

class CommentTree extends React.Component {
    constructor(props) {
        super(props)
        let {comments} = this.props
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
                        />
                    </li>
                )}
            />
        );
    }
}

export default CommentTree;
