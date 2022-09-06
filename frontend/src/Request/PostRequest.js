async function postRequest(url, body) {
    return await fetch(
        (process.env.REACT_APP_ENV === "production" ? "http://laojk.club:8080/api/" : "http://10.89.51.52:8080/api/") +url,
        {
            mode: 'cors',
            credentials: 'include',
            redirect: 'follow',
            method: 'POST',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': true,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        }
    ).then(res => res.json())
}

export default postRequest