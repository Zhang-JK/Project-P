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

Response:

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