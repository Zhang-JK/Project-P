import {Button, Form} from "antd";
import TextArea from "antd/es/input/TextArea";
import React from "react";
import getRequest from "../Request/GetRequest";
import {checkMemory, getMemory, setMemory} from "./Memory";

export const Editor = ({onChange, onSubmit, submitting, value, submitText}) => (
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

export const reverseState = (state, setState) => setState(!state)


let gettingUserDataList = false
let callbackList = []
let failedCallbackList = []
export const getUserDataList = (callback, failedCallback) => {
    if (!gettingUserDataList) {
        if (checkMemory("userDataList")) {
            gettingUserDataList = true
            if (callback !== undefined)
                callbackList.push(callback);
            if (failedCallback !== undefined)
                failedCallbackList.push(failedCallback);
            getRequest("humanResource/list").then(result => {
                if (result.code === 200) {
                    let resultData = {}
                    console.log(callbackList)
                    for (let i = 0; i < result.data.length; i++) {
                        resultData[parseInt(result.data[i].user.id)] = result.data[i]
                    }
                    setMemory("userDataList", resultData)
                    callbackList.forEach((callback) => {
                        callback()
                    })
                    callbackList = []
                    gettingUserDataList = false
                } else {
                    failedCallbackList.forEach((callback) => {
                        callback()
                    })
                    callbackList = []
                    alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
                    gettingUserDataList = false
                }
            })
        } else {
            return getMemory("userDataList")
        }
    } else {
        if (callback !== undefined)
            callbackList.push(callback);
        if (failedCallback !== undefined)
            failedCallbackList.push(failedCallback);
    }
}
export const getUserInfo = (id, callback, failedCallback) => {
    let userDataList = getUserDataList(callback, failedCallback);
    if (userDataList !== undefined) {
        return userDataList[id];
    }
}

export const getUserName = (id, callback, failedCallback) => {
    let userDataList = getUserDataList(callback, failedCallback);
    if (userDataList !== undefined) {
        return userDataList[id].user.username;
    }
}
