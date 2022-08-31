import React from "react"
import './Personal.css'

export default class Personal extends React.Component{

    handleClick = () => {
        window.location.replace(`/ChangePassword`)
    }

    render() {
        return(
            <div className={"Person"}>
                <button onClick={this.handleClick}>Change Password</button>
            </div>
        )
    }
}