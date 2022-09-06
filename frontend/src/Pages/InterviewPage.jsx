import React, {useState} from 'react';
import getRequest from "../Request/GetRequest";
import FreshTable from "../Components/FreshTable";
import {Collapse, Spin} from "antd";

const { Panel } = Collapse;

function InterviewPage(props) {
    const [interview, setInterview] = useState(null)
    const [tree, setTree] = useState([])
    if (props.userInfo != null && props.userInfo.permissions['humanResource'] !== undefined && interview == null) {
        getRequest("interview/getAll")
            .catch(error => {
                console.log('ERROR: ', error)
            })
            .then((res) => {
                if (res.code === 1003) {
                    window.location.replace("/login")
                }
                setInterview(res.data)
                let localTree = []
                // eslint-disable-next-line array-callback-return
                res.data.map(d => {
                    if (localTree[d.interview.date] === undefined)
                        localTree[d.interview.date] = [d]
                    else
                        localTree[d.interview.date].push(d)
                })
                setTree(localTree)
            })
    }

    if (interview != null) {
        console.log(tree)
        return (
            <div>
                <Collapse accordion>
                    {Object.keys(tree).map(k =>
                        <Panel header={k} key={k}>
                            <Collapse accordion>
                                {tree[k].map(i =>
                                    <Panel header={i.interview.startTime} key={i.interview.startTime}>
                                        <Collapse >
                                            {Object.keys(i.fresh).map(ik =>
                                                <Panel header={ik} key={ik}>
                                                    <FreshTable data={i.fresh[ik]} filter={false}/>
                                                </Panel>
                                            )}
                                        </Collapse>
                                    </Panel>
                                )}
                            </Collapse>
                        </Panel>
                    )}
                </Collapse>
            </div>
        )
    }
    else
        return <Spin />
}

export default InterviewPage