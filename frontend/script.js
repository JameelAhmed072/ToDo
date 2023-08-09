

let addUserBtn = document.getElementById('addUser');
let userNametextField = document.getElementById('username');
let displayRecords = document.getElementById('records');

// Initialize edit_id
let edit_id = null;

// Load existing tasks when the page loads
window.onload = () => {
    fetchAndDisplayTasks();
};
addUserBtn.onclick = () => {
    let taskText = userNametextField.value;
    if(edit_id != null){
        var obj = {
            myTasks: taskText
        };
        fetch(`http://localhost:8080/api/tasks/${edit_id}`, {
            method: 'PUT',
            body: JSON.stringify(obj),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
        .then((response) => {
            return response.json()
            })
        .then((json) => {
            console.log(json);
            // Fetch and display tasks again after editing a task
            fetchAndDisplayTasks();
        });
        edit_id = null;
    }else{
            var obj = {
                myTasks: taskText
            };
            fetch('http://localhost:8080/api/task', {
                method: 'POST',
                body: JSON.stringify(obj),
                headers: {
                    'Content-type': 'application/json',
                },
            })
            .then((response) => response.json())
            .then((json) => {
                console.log(json);
                // Fetch and display tasks again after adding a new task
                fetchAndDisplayTasks();
            })
            .catch((error) => {
                console.error('Error:', error);
            });
            userNametextField.value = '';
         }
};
function DisplayInfo(tasks) {
    apiData = tasks
    console.log(tasks);
    let html = '';
    tasks.forEach((data, i) => {
        html += `
            <tr class="user-row">
                <td>${i + 1}</td>
                <td>${data.myTasks}</td>
                <td>
                    <i class="btn text-white fa fa-edit btn-info mx-2" onclick="EditInfo(${data.id})"></i> 
                    <i class="btn btn-danger text-white fa fa-trash-o" onclick="DeleteInfo(${data.id})"></i>
                </td>
                <td>
                    <input class="form-check-input" type="checkbox" onchange="ToggleRowBackgroundColor(this, ${data.id})">
                </td>
            </tr>
        `;
    });
    displayRecords.innerHTML = html;
}

function ToggleRowBackgroundColor(checkbox, index) {
    const row = checkbox.closest('.user-row');
    if (checkbox.checked) {
        row.classList.add('checked-row');
        // Add a delay before calling DeleteInfo
        setTimeout(function() {
            DeleteInfo(index);
        }, 2000); // Adjust the delay time as needed
    } else {
        row.classList.remove('checked-row');
    }
}
var data;
function fetchAndDisplayTasks() {
    fetch('http://localhost:8080/api/allTasks')
    .then((response) => response.json())
    .then((data) => {
        DisplayInfo(data);
    });
}
function DeleteInfo(id) {
    fetch(`http://localhost:8080/api/tasks/${id}`, {
        method: 'DELETE',
    })
    .then(() => {
        // Fetch and display tasks again after deleting a task
        fetchAndDisplayTasks();
    });
}
function EditInfo(id) {
    edit_id = id;
    // debugger
    let findIndex = apiData.findIndex(el => el.id === id)
    userNametextField.value = apiData[findIndex].myTasks;
    // event.preventDefault();
    addUserBtn.innerText = "Save Changes";
}
