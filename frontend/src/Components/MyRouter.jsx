import FeedbackPage, {Feedbacks} from "../Pages/FeedbackPage";
import {createElement} from "react";
import UserManagePage from "../Pages/UserManagePage";
import FreshManagePage from "../Pages/FreshManagePage";


class RouterComponent {
    constructor(type, path, component) {
        this.type = type
        this.path = path
        this.component = component
        this.children = []
    }
}

const routerDic = {
    type: "Father",
    path: "home",
    component: undefined,
    children: [
        {
            type: "Child_With_Arg",
            path: "feedback",
            component: FeedbackPage,
            children: []
        },{
            type: "Child_With_Arg",
            path: "member",
            component: UserManagePage,
            children: []
        },{
            type: "Child_With_Arg",
            path: "fresh",
            component: FreshManagePage,
            children: []
        }]
};
const getPage = (routerDicArray, paths) => {
    console.log("In get page: ",routerDicArray, paths)
    if (paths instanceof Array) {
        for (const routerDic in routerDicArray) {
            console.log("routerDic:", routerDicArray[routerDic]);
            if (routerDicArray[routerDic].path === paths[0]) {
                switch (routerDicArray[routerDic].type) {
                    case "Father":
                        return getPage(routerDicArray[routerDic].children, paths.slice(1))
                    case "Child_No_Arg":
                        return createElement(routerDicArray[routerDic].component, {});
                    case "Child_With_Arg":
                        if (paths[1])
                            return createElement(routerDicArray[routerDic].component, {arg: paths[1]});
                        else
                            return createElement(routerDicArray[routerDic].component, {});
                    default:
                        alert("Wrong router type!");
                        return undefined;
                }
            }
        }
    } else {
        console.log("Path is no an array");
        return undefined;
    }
}


export const MyRouter = (props) => {
    console.log("MyRouter props", props)
    const url = props.location;
    const paths = url.trim().split("/")
    const routerDicArray = [routerDic];
    let page = getPage(routerDicArray, paths.slice(1));
    console.log(page)
    return page;
}
