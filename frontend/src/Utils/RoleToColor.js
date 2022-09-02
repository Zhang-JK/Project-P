export function RoleToColor(role) {
    switch (role){
        case "Captain":
            return "magenta"
        case "Admin":
            return "red"
        case "Senior":
            return "volcano"
        case "Junior":
            return "orange"
        case "Intern":
            return "cyan"
        case "Visitor":
            return "blue"
        case "Advisor":
            return "green"
        case "Logistics":
            return "purple"
        case "Fresh":
        default:
            return "lime"
    }
}

export function PositionToColor(p) {
    switch (p){
        case "SOFTWARE":
            return "magenta"
        case "HARDWARE":
            return "volcano"
        case "MECHANICAL":
            return "cyan"
        case "LOGISTICS":
            return "green"
        case "WEBSITE":
        default:
            return "lime"
    }
}

export function StageToColor(p) {
    switch (p){
        case "Disqualified":
            return "magenta"
        case "Interview Ready":
            return "orange"
        case "Interview PASS":
            return "geekblue"
        case "Tutorial PASS":
            return "blue"
        case "Internal PASS":
            return "cyan"
        case "Official Member":
            return "green"
        case "Not Started":
        default:
            return "lime"
    }
}
