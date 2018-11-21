//Setting cookie for an hour
function setCookie(key, value){
    let date = new Date(new Date().getTime() + 3600 * 1000);
    document.cookie = key + "=" + value + "; expires=" + date.toUTCString();
}

//return value out of cookie using a key
function getCookie(key){
    let array = document.cookie.split("; ");
    for(let pair of array){
        let pairKey = pair.split("=")[0];
        if(key == pairKey)
            return pair.split("=")[1];
    }
    return null;
}