var memory = {};

export const setMemory = (key, data) => {
    memory[key] = data
}

export const deleteMemory = (key) => {
    if (memory.hasOwnProperty(key)) {
        delete memory[key];
    }
}

export const getMemory = (key) => {
    return memory[key]
}

export const checkMemory = (key) => {
    return getMemory(key) === undefined
}
