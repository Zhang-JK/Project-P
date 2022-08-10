import {Comment} from "antd";
import {PriorityQueue} from "js-priority-queue"
class CommentEntity{
    constructor(id, fbId, content, parentId, fromUid, time) {
        this.id = id;
        this.fbId = fbId;
        this.content = content;
        this.parentId = parentId;
        this.fromUid = fromUid;
        this.time = time;
        this.adj = []
    }
}
const compareComment = function (a, b){return a.time - b.time};
class CommentNode{
    constructor(comment, adj) {
        this.comment = comment;
        this.queue = new PriorityQueue({comparator: compareComment, initialValues: adj});
    }
    addChildComment(childComment){
        this.queue.enqueue(childComment);
    }
}
const compareCommentUsingId = (a,b) =>{return a.id - b.id}
class CommentTree{
    constructor(comments) {
        this.root = new CommentNode(null);
        let commentNodes = []
        for (let comment in comments){
            commentNodes.push(new CommentNode(comment))
        }
        let queue = new PriorityQueue({comparator: compareCommentUsingId, initialValues:comments});
        while (queue.length() > 0){
            let comment = queue.dequeue();
            if (comment.id < 0){
                this.root.addChildComment(comment);
            }else {
                for (let comment1 in comments){
                    if (comment1.id === comment.parentId){
                        comment1.adj.push(comment)
                    }
                }
            }
        }
    }
}
