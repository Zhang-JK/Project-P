import {PlayCircleOutlined} from "@ant-design/icons";

export const FeedbackStatus = (props) => {
    const {status} = props;
    let result = undefined;
    switch (status) {
        case
        "STARTED"
        :
            result = (
                <div>
                    <strong>Status:</strong> <PlayCircleOutlined style={{color: "green"}}/> STARTED
                </div>)
            break;
        default:
            result = (
                <div>
                    <strong>Status symbol not defined yet</strong> Status: {status}
                </div>
            )
    }
    return result;
}
