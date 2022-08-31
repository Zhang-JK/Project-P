import FeedbackPage from "../Pages/FeedbackPage";
import {createElement} from "react";
import UserManagePage from "../Pages/UserManagePage";
import FreshManagePage from "../Pages/FreshManagePage";
import HomePage from "../Pages/HomePage";

const routerDic = [
    {
        type: "Child_With_Arg",
        path: "feedback",
        component: FeedbackPage,
        children: []
    }, {
        type: "Child_With_Arg",
        path: "member",
        component: UserManagePage,
        children: []
    }, {
        type: "Child_With_Arg",
        path: "fresh",
        component: FreshManagePage,
        children: []
    }, {
        type: "Child_No_Arg",
        path: "home",
        component: HomePage,
        children: []
    }
];
const getPage = (routerDicArray, paths, userInfo) => {
    if (paths instanceof Array) {
        for (const routerDic in routerDicArray) {
            if (routerDicArray[routerDic].path === paths[0]) {
                switch (routerDicArray[routerDic].type) {
                    case "Father":
                        return getPage(routerDicArray[routerDic].children, paths.slice(1))
                    case "Child_No_Arg":
                        return createElement(routerDicArray[routerDic].component, {userInfo: userInfo});
                    case "Child_With_Arg":
                        if (paths[1])
                            return createElement(routerDicArray[routerDic].component, {userInfo: userInfo, arg: paths[1]});
                        else
                            return createElement(routerDicArray[routerDic].component, {userInfo: userInfo});
                    default:
                        alert("Wrong router type!");
                        return undefined;
                }
            }
        }
    } else {
        return undefined;
    }
}


export const MyRouter = (props) => {
    if (props.location ===  "/")
        window.location.replace("/home")
    const url = props.location;
    const paths = url.trim().split("/")
    return getPage(routerDic, paths.slice(1), props.userInfo);
}
