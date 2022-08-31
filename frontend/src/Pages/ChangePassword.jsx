import React, {createRef} from "react";
import "./ChangePassword.css";
import MD5 from "crypto-js/md5";
import getRequest from "../Request/GetRequest";

export default class ChangePassword extends React.Component {

    constructor(props) {
        super(props);
        this.old_password = React.createRef();
        this.first_type = React.createRef();
        this.retype = React.createRef();
    }


    handleSubmit = () => {
        // console.log(this.old_password.current.value);
        // console.log(this.first_type.current.value);
        // console.log(this.retype.current.value);

        if(this.first_type.current.value !== this.retype.current.value){
            alert("Enter different new password, please check")
            return
        }
        if(this.old_password.current.value === this.first_type.current.value){
            alert("Password unchanged, please type in a new password")
            return
        }

        let md5old = MD5(this.old_password.current.value).toString();
        let md5new = MD5(this.first_type.current.value).toString();

        getRequest("user/changePassword?oldPassword=" + md5old +
            "&newPassword=" + md5new).then(res => {
            console.log(res)

            if(res.code === 200){
                alert("password changed successfully")
                window.location.replace('/home')
            } else {
                alert(res.msg)
                window.location.reload()
            }
        })


    }


    render() {
        return (
            <div className={"changeBox"}>
                <header> please type in the OLD PASSWORD</header>
                <input type={"password"} placeholder={"Old Password"} ref={this.old_password}/>
                <br/><br/>

                <header> please type in the NEW PASSWORD</header>
                <input type={"password"} placeholder={"new Password"} ref={this.first_type}/>
                <br/><br/>

                <header> please retype in the NEW PASSWORD</header>
                <input type={"password"} placeholder={"new Password"} ref={this.retype}/>

                <br/><br/>

                <button onClick={this.handleSubmit}> submit change</button>
            </div>
        )
    }
}