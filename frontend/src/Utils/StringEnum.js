export function stageToNumber(stage)
{
    if (stage==="Disqualified") return 0
    const list = ["Not Started", "Interview Ready", "Interview PASS", "Internal PASS", "Official Member"]
    return list.findIndex(i => i===stage)
}

export let FreshStageList;
FreshStageList = [
    {
        id: 0,
        name: "Not Started",
        description: "Successfully Registered",
        dbName: "NONE"
    },
    {
        id: 1,
        name: "Interview Ready",
        description: "Interview Booked",
        dbName: "STARTED"
    },
    {
        id: 2,
        name: "Interview PASS",
        description: "Interview Passed",
        dbName: "INTERVIEW"
    },
    {
        id: 3,
        name: "Tutorial PASS",
        description: "Tutorial Passed",
        dbName: "TUTORIAL"
    },
    {
        id: 4,
        name: "Internal PASS",
        description: "Internal Passed",
        dbName: "INTERNAL"
    },
    {
        id: 5,
        name: "Official Member",
        description: "Successfully Passed All Tests",
        dbName: "OFFER"
    }

]

