import CommentTree from "../../Components/Comments";
import {Comment} from "antd";

const TestPage = () => {
    let data = "[\n" +
        "      {\n" +
        "        \"id\": 11,\n" +
        "        \"fbId\": 5,\n" +
        "        \"content\": \"11\",\n" +
        "        \"parentId\": -1,\n" +
        "        \"fromUid\": 4,\n" +
        "        \"time\": \"2022-08-07T14:39:57Z\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"id\": 12,\n" +
        "        \"fbId\": 5,\n" +
        "        \"content\": \"12\",\n" +
        "        \"parentId\": -1,\n" +
        "        \"fromUid\": 4,\n" +
        "        \"time\": \"2022-08-07T15:32:32Z\"\n" +
        "      },\n" +
        "\t{\n" +
        "        \"id\": 13,\n" +
        "        \"fbId\": 5,\n" +
        "        \"content\": \"111\",\n" +
        "        \"parentId\": -1,\n" +
        "        \"fromUid\": 4,\n" +
        "        \"time\": \"2022-08-07T14:39:57Z\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"id\": 14,\n" +
        "        \"fbId\": 5,\n" +
        "        \"content\": \"11122222\",\n" +
        "        \"parentId\": 11,\n" +
        "        \"fromUid\": 4,\n" +
        "        \"time\": \"2022-08-07T15:32:32Z\"\n" +
        "      },\n" +
        "\t{\n" +
        "        \"id\": 15,\n" +
        "        \"fbId\": 5,\n" +
        "        \"content\": \"111\",\n" +
        "        \"parentId\": 11,\n" +
        "        \"fromUid\": 4,\n" +
        "        \"time\": \"2022-08-07T14:39:57Z\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"id\": 16,\n" +
        "        \"fbId\": 5,\n" +
        "        \"content\": \"11122222\",\n" +
        "        \"parentId\": 14,\n" +
        "        \"fromUid\": 4,\n" +
        "        \"time\": \"2022-08-07T15:32:32Z\"\n" +
        "      }\n" +
        "]"
    let commentsList = JSON.parse(data);
    console.log(commentsList)
    return (
        <div>
            <h1>Hello testPage</h1>
            <CommentTree comments={commentsList}/>
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
