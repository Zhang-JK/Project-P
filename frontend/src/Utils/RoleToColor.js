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
