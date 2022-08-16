import CommentTree from "../Components/Comment";
import {Comment, Skeleton} from "antd";
import postRequest from "../Request/PostRequest";
import {useState} from "react";

const TestPage = () => {
    // let data = "[\n" +
    //     "      {\n" +
    //     "        \"id\": 11,\n" +
    //     "        \"fbId\": 5,\n" +
    //     "        \"content\": \"11\",\n" +
    //     "        \"parentId\": -1,\n" +
    //     "        \"fromUid\": 4,\n" +
    //     "        \"time\": \"2022-08-07T14:39:57Z\"\n" +
    //     "      },\n" +
    //     "      {\n" +
    //     "        \"id\": 12,\n" +
    //     "        \"fbId\": 5,\n" +
    //     "        \"content\": \"12\",\n" +
    //     "        \"parentId\": -1,\n" +
    //     "        \"fromUid\": 4,\n" +
    //     "        \"time\": \"2022-08-07T15:32:32Z\"\n" +
    //     "      },\n" +
    //     "\t{\n" +
    //     "        \"id\": 13,\n" +
    //     "        \"fbId\": 5,\n" +
    //     "        \"content\": \"111\",\n" +
    //     "        \"parentId\": -1,\n" +
    //     "        \"fromUid\": 4,\n" +
    //     "        \"time\": \"2022-08-07T14:39:57Z\"\n" +
    //     "      },\n" +
    //     "      {\n" +
    //     "        \"id\": 14,\n" +
    //     "        \"fbId\": 5,\n" +
    //     "        \"content\": \"11122222\",\n" +
    //     "        \"parentId\": 11,\n" +
    //     "        \"fromUid\": 4,\n" +
    //     "        \"time\": \"2022-08-07T15:32:32Z\"\n" +
    //     "      },\n" +
    //     "\t{\n" +
    //     "        \"id\": 15,\n" +
    //     "        \"fbId\": 5,\n" +
    //     "        \"content\": \"111\",\n" +
    //     "        \"parentId\": 11,\n" +
    //     "        \"fromUid\": 4,\n" +
    //     "        \"time\": \"2022-08-07T14:39:57Z\"\n" +
    //     "      },\n" +
    //     "      {\n" +
    //     "        \"id\": 16,\n" +
    //     "        \"fbId\": 5,\n" +
    //     "        \"content\": \"11122222\",\n" +
    //     "        \"parentId\": 14,\n" +
    //     "        \"fromUid\": 4,\n" +
    //     "        \"time\": \"2022-08-07T15:32:32Z\"\n" +
    //     "      }\n" +
    //     "]"
    let [loading, setLoading] = useState(true)
    let [data, setData] = useState(undefined)
    if (loading) {
        postRequest("fetchComment", {fbId: 5}).then(result => {
            console.log("Fetch result: ", result)
            if (result.code === 200) {
                // alert("fetching comment success!")
                setData(result.data);
                console.log(result.data);
                setLoading(false);
            } else {
                alert("Error!/nError code:" + result.code + "Error msg:" + result.msg)
            }
        })
    }
    // console.log("Fetched comments: ", data)
    // let commentsList = JSON.parse(data);
    // console.log(commentsList)
    return (
        <div>
            <h1>Hello testPage</h1>
            <Skeleton loading={loading}>
                {!loading&&<CommentTree comments={data.fbCommentList} fbId={5} setLoading = {setLoading}/>}
            </Skeleton>

            {/*<Comment content={<p>1</p>}>*/}
            {/*    <Comment content={<p>2</p>}>*/}
            {/*        <Comment content={<p>3</p>}>*/}
            {/*        </Comment>*/}
            {/*    </Comment>*/}
            {/*    <Comment content={<p>22</p>}>*/}
            {/*        <Comment content={<p>33</p>}>*/}
            {/*        </Comment>*/}
            {/*    </Comment>*/}
            {/*</Comment>*/}
        </div>
    );
}

export default TestPage;
