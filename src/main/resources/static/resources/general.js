'use strict';

let formToJson = (formData) => {
    let object = {};
    formData.forEach(function (value, key) {
        object[key] = value;
    });
    return JSON.stringify(object);
}

let imgLoader = (img) => {
    return (img !== undefined) ? img : 'resources/img/no-img.png';
}

let setSelectedElement = (elem, check, color) => {
    elem.setAttribute("checked", check);
    elem.setAttribute("style", "background: " + color + ";");
}

let getSelector = () => {
    document.querySelectorAll('.rows')
        .forEach(item => item.addEventListener('click', function() {

            if (this.getAttribute("checked") === "true") {
                setSelectedElement(this, "false", "none");
                return;
            }

            let previous = document.querySelector('.rows[checked="true"]');
            if (previous !== null) {
                setSelectedElement(previous, "false", "none");
            }
            setSelectedElement(this, "true", "rgba(183, 201, 109,.3)");

        }));
}

let token = document.querySelector('meta[name="_csrf"]').content;
let errorMsg = 'smth goes wrong on server side';

let send = async (where, jsonString, errorMsg) => {
    fetch(where, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type' : 'application/json',
            'X-CSRF-TOKEN': token
        },
        async: true,
        processData: false,
        body: jsonString
    }).then((response) => {
        return response;
    }).then((data) => {
        console.log(data);
        if (data.ok) {
            location.reload();
        } else {
            alert(errorMsg);
        }
    }).catch((e) => {
        console.log(e);
        alert("cannot send");
    });
}

