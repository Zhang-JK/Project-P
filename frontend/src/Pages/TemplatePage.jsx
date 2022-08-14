import React from "react";
import {Layout} from 'antd';
import "./TemplatePage.css"
import LogoWhite from '../assets/logo-c-n.png'
import SideBar from "../Components/SideBar";

const {Header, Content, Footer, Sider} = Layout;

class TemplatePage extends React.Component<> {
    render() {
        return (
            <Layout style={{minHeight: '100vh',}}>
                <Sider collapsible>
                    <div className="logo d-flex flex-row p-0">
                        <img style={{marginRight: 15, marginLeft: 13}} src={LogoWhite} alt={"LOGO"} height={"100%"} />
                        <div className="d-flex flex-column">
                            <div className="m-0 p-0" style={{color: "White", fontSize: 19, fontFamily: "sans-serif"}}><b>HKUST</b></div>
                            <div className="m-0 p-0" style={{color: "White", fontSize: 16, fontFamily: "sans-serif"}}><b>RoboMaster</b></div>
                        </div>
                    </div>
                    <SideBar selected={this.props.page} permissions={this.props.permissions} projects={this.props.projects} />
                </Sider>
                <Layout >
                    <Header style={{padding: 0}}/>
                    <Content className="site-layout-background" style={{ margin: '16px 16px', }} >
                        <div style={{ padding: 24, minHeight: 360}}>
                            {this.props.children}
                        </div>
                    </Content>
                    <Footer style={{ textAlign: 'center', }} >
                        HKUST RoboMaster Manage System ©2018 Created by RM2022
                    </Footer>
                </Layout>
            </Layout>
        )
    }
}

export default TemplatePage