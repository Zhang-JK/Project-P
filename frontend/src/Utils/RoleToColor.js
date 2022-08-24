function RoleToColor(role) {
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

export default RoleToColor