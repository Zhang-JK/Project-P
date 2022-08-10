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
                            <div className="m-0 p-0" style={{color: "White", fontSize: 19}}>HKUST</div>
                            <div className="m-0 p-0" style={{color: "White", fontSize: 16}}>RoboMaster</div>
                        </div>
                    </div>
                    <SideBar selected={'1'} />
                </Sider>
                <Layout className="site-layout">
                    <Header className="site-layout-background" style={{padding: 0,}}/>
                    <Content style={{ margin: '0 16px', }} >
                        <div
                            className="site-layout-background"
                            style={{
                                padding: 24,
                                minHeight: 360,
                            }}
                        >
                            Bill is a cat.
                        </div>
                    </Content>
                    <Footer style={{ textAlign: 'center', }} >
                        Footer
                    </Footer>
                </Layout>
            </Layout>
        )
    }
}

export default TemplatePage