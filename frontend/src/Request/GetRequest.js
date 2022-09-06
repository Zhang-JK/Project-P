async function getRequest(url) {
    return await fetch(
        (process.env.REACT_APP_ENV === "production" ? "http://laojk.club:8080/api/" : "http://10.89.51.52:8080/api/") +url,
        {
            mode: 'cors',
            credentials: 'include',
            redirect: 'follow',
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': true,
            },
        }
    ).then(res => res.json())
}

export default getRequest