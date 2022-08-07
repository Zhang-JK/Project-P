# API description

## 0. General Description

#### Response Structure

```json
{
  "code": 200,
  "msg": "Success",
  "data": null
}
```  

- code: status of the request
- msg: message of the request
- data: maybe null, depend on the type of request

#### Error code

- 20x: Success

## 1. User and Login

#### Login

Type: POST  
URL: ```/api/user/login```  
Param:

| Param     | Type   | Description        |
|-----------|--------|--------------------|
| username* | string | name of the user   |
| password* | string | password, md5 once |

Response Code and Message:

| Code | Message          | Description |
|------|------------------|-------------|
| 1001 | "User not exist" |             |
| 1002 | "Wrong password" |             |
| 200  | "Success"        |             |

#### Logout

Type: POST  
URL: ```/api/user/logout```

#### Change Password

Type: GET  
URL: ```/api/user/changePassword```  
Param:

| Param        | Type   | Description            |
|--------------|--------|------------------------|
| oldPassword* | string | old password, md5 once |
| newPassword* | string | new password, md5 once |

## 2.Feedback and comment

#### Create Feedback

Type: POST  
URL: ```/api/createFeedback```  
Param:

| Param        | Type   | Description                                |
|--------------|--------|--------------------------------------------|
| msg*         | string | the message of the feedback(only raw data) |

Response Code and Message:

| Code | Message     | Description |
|------|-------------|-------------|
| 1003 | "Not login" |             |
| 200  | "Success"   |             |

#### Fetch Feedback

Type: POST  
URL: ```/api/fetchFeedback```

Response Code and Message:

| Code | Message     | Description |
|------|-------------|-------------|
| 1003 | "Not login" |             |
| 200  | "Success"   |             |

Response Body Example:

```json
 {
  "code": 200,
  "msg": "Success",
  "data": {
    "fbPojoList": [
      {
        "id": 5,
        "content": "this is an edited feedback2",
        "fromUid": 4,
        "status": "STARTED",
        "time": "2022-08-07T13:25:41Z"
      },
      {
        "id": 6,
        "content": "{\"msg\":\"new test feedback\"}",
        "fromUid": 4,
        "status": "STARTED",
        "time": "2022-08-07T14:19:55Z"
      }
    ]
  }
}
```

#### Edit Feedback

Type: POST  
URL: ```/api/editFeedback```  
Param:

| Param | Type   | Description               |
|-------|--------|---------------------------|
| fbId  | Long   | the Id of target Feedback |
| msg   | String | the message for change    |

Response Code and Message:

| Code | Message               | Description |
|------|-----------------------|-------------|
| 1003 | "Not login"           |             |
| 1011 | "Feedback not exist!" |             |
| 200  | "Success"             |             |

#### Delete Feedback

Type: POST  
URL: ```/api/deleteFeedback```  
Param:

| Param | Type   | Description                         |
|-------|--------|-------------------------------------|
| fbId  | Long   | the Id of target Feedback(Raw data) |

Response Code and Message:

| Code | Message               | Description |
|------|-----------------------|-------------|
| 1003 | "Not login"           |             |
| 1011 | "Feedback not exist!" |             |
| 200  | "Success"             |             |

Description:
delete feedback with id "fbId" and all the comments below

#### Create Comment

Type: POST  
URL: ```/api/createComment```  
Param:

| Param     | Type    | Description                                                     |
|-----------|---------|-----------------------------------------------------------------|
| fbId      | Long    | the id of the Feedback that this comment under                  |
| msg       | String  | the  message of the Comment                                     |
| commentId | Integer | the id of the Comment that this comment is reply to(-1 if None) |

Response Code and Message:

| Code | Message              | Description |
|------|----------------------|-------------|
| 1003 | "Not login"          |             |
| 1011 | "Feedback not exist" |             |
| 200  | "Success"            |             |

#### Fetch Comment

Type: POST  
URL: ```/api/fetchComment```

Param:

| Param     | Type    | Description                                                     |
|-----------|---------|-----------------------------------------------------------------|
| fbId      | Long    | The id of feedback that the comments you want to fetch is under |

Response Code and Message:

| Code | Message              | Description |
|------|----------------------|-------------|
| 1003 | "Not login"          |             |
| 200  | "Success"            |             |


Response Body Example:

```json
 {
  "code": 200,
  "msg": "Success",
  "data": {
    "fbCommentList": [
      {
        "id": 11,
        "fbId": 5,
        "content": "111",
        "parentId": -1,
        "fromUid": 4,
        "time": "2022-08-07T14:39:57Z"
      },
      {
        "id": 12,
        "fbId": 5,
        "content": "11122222",
        "parentId": -1,
        "fromUid": 4,
        "time": "2022-08-07T15:32:32Z"
      }
    ]
  }
}
```

#### Edit Comment

Type: POST  
URL: ```/api/editComment```  
Param:

| Param     | Type    | Description              |
|-----------|---------|--------------------------|
| commentId | Integer | the Id of target Comment |
| msg       | String  | the message for change   |

Response Code and Message:

| Code | Message              | Description |
|------|----------------------|-------------|
| 1003 | "Not login"          |             |
| 1013 | "Comment not exist!" |             |
| 200  | "Success"            |             |

#### Delete Comment

Type: POST  
URL: ```/api/deleteComment```  
Param:

| Param      | Type    | Description                        |
|------------|---------|------------------------------------|
| commentId  | Integer | the Id of target Comment(Raw data) |

Response Code and Message:

| Code | Message              | Description |
|------|----------------------|-------------|
| 1003 | "Not login"          |             |
| 1013 | "Comment not exist!" |             |
| 200  | "Success"            |             |

Description:
delete comment with id "commentId" and all the comments below
