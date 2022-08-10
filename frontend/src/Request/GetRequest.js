async function getRequest(url) {
    return await fetch(
        'http://localhost:8080/api/'+url,
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