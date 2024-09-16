document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('addButton').addEventListener('click', addItem);
});

function addItem() {
    var input = document.getElementById('todoInput');
    var inputValue = input.value.trim();
    if (inputValue !== '') {
        var list = document.getElementById('todoList');
        var listItem = document.createElement('li');
        listItem.textContent = inputValue;
        var deleteButton = document.createElement('button');
        deleteButton.textContent = 'DELETE';
        deleteButton.onclick = function() {
            list.removeChild(listItem);
        };
        listItem.appendChild(deleteButton);

        list.appendChild(listItem);
        input.value = '';
    }
}
