import postRequest from "../Request/PostRequest";

let sendingComment = false;
let sendingFeedback = false;
let editingComment = false;
let editingFeedback = false;
let deletingComment = false;
let deletingFeedback = false;
export const createComment = (fbId, parentId, value, callback, failedCallback) => {
    console.log("Sending comment : fbId", fbId, "commentId", parentId, "value", value)
    if (!sendingComment) {
        sendingComment = true;
        postRequest("createComment", {fbId: fbId, msg: value, commentId: parentId}).then(result => {
            if (result.code === 200) {
                alert("create meg success!")
                if (!(callback === undefined)) {
                    callback()
                }
                sendingComment = false;
                //refresh page here
            } else {
                if (!(failedCallback === undefined)) {
                    failedCallback()
                }
                sendingComment = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
export const createFeedback = (value, title, callback, failedCallback) => {
    if (!sendingFeedback) {
        sendingFeedback = true;
        postRequest("createFeedback", {msg: value, title: title}).then(result => {
            if (result.code === 200) {
                alert("create meg success!")
                if (!(callback === undefined)) {
                    callback()
                }
                sendingFeedback = false;
                //refresh page here
            } else {
                if (!(failedCallback === undefined)) {
                    failedCallback()
                }
                sendingFeedback = false;
                alert("Create feedback error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
export const editComment = (commentId, value, callback, failedCallback) => {
    console.log("Sending Editing comment :", "commentId", commentId, "value", value)
    if (!editingComment) {
        editingComment = true;
        postRequest("editComment", {msg: value, commentId: commentId}).then(result => {
            if (result.code === 200) {
                alert("edit comment success!")
                console.log(callback)
                if (!(callback === undefined)) {
                    console.log("Calling Callback")
                    callback()
                }
                editingComment = false;
                //refresh page here
            } else {
                if (!(failedCallback === undefined)) {
                    failedCallback()
                }
                editingComment = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
export const editFeedback = (fbId, value, title, status, callback, failedCallback) => {
    if (!editingFeedback) {
        editingFeedback = true;
        postRequest("editFeedback", {msg: value, fbId: fbId, title: title, status: status}).then(result => {
            if (result.code === 200) {
                alert("edit feedback success!")
                console.log(callback)
                if (!(callback === undefined)) {
                    console.log("Calling Callback")
                    callback()
                }
                editingFeedback = false;
                //refresh page here
            } else {
                if (!(failedCallback === undefined)) {
                    failedCallback()
                }
                editingFeedback = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
export const deleteComment = (commentId, callback, failedCallback) => {
    console.log("Sending delete comment :", "commentId", commentId)
    if (!deletingComment) {
        deletingComment = true;
        postRequest("deleteComment", {commentId: commentId}).then(result => {
            if (result.code === 200) {
                alert("delete comment success!")
                if (!(callback === undefined)) {
                    callback()
                }
                deletingComment = false;
                //refresh page here
            } else {
                if (!(failedCallback === undefined)) {
                    failedCallback()
                }
                deletingComment = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
export const deleteFeedback = (fbId, callback, failedCallback) => {
    if (!deletingFeedback) {
        deletingFeedback = true;
        postRequest("deleteFeedback", {fbId: fbId}).then(result => {
            if (result.code === 200) {
                alert("delete feedback success!")
                if (!(callback === undefined)) {
                    callback()
                }
                deletingFeedback = false;
                //refresh page here
            } else {
                if (!(failedCallback === undefined)) {
                    failedCallback()
                }
                deletingFeedback = false;
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
}
