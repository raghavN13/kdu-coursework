// implementing the socket
const socket = io();

const usersList = document.getElementById('users-list');
const chatHeader = document.getElementById('chat-header');
const chatMessages = document.getElementById('chat-messages');
const messageInput = document.getElementById('message-input');
const sendButton = document.getElementById('send-button');

let currentConversation = null;
const messageHistory = {}; // Object to store message history for each user
const unreadMessages = {}; // Object to store unread messages for each user

/**
 * this even handler is for loading the conversation of the user on the page when the user is clicked
 */

usersList.addEventListener('click', event => {
    const selectedUser = event.target.textContent;
    const para = document.getElementById("chat-header-para");
    para.textContent = `${selectedUser}`;
    chatMessages.innerHTML = '';
    loadConversation(selectedUser);
    currentConversation = selectedUser;
    event.target.classList.remove('highlight');
    unreadMessages[selectedUser] = [];
});

sendButton.addEventListener('click', () => {
    const message = messageInput.value.trim();
    if(message==''){
        throw  error("Message is Empty");
    }
    if (message !== '') {
        socket.emit('private message', { to: currentConversation, message });
        appendMessage('You', message, true); 
        updateMessageHistory(currentConversation, 'You', message);
        messageInput.value = '';
    }
});

// Socket events
/**
 * this is for updating the user on the page when a new user joins 
 */

socket.on('update users', users => {
    usersList.innerHTML = '';
    users.forEach(user => {
        if (user !== socket.username) {
            const userItem = document.createElement('li');
            const userItemDiv = document.createElement('div');
            userItemDiv.classList.add('user-desktop-list');
            const userItemimage = document.createElement('img');
            const userItemName = document.createElement('p');
            const userItemUsername = document.createElement('p');
            userItemName.textContent = user;
            let personUsername;
            if (user === "Raghav Nandwana") {
                
                personUsername = "raghav.39";
            } else if (user === 'Dewang Khandelwal') {
                
                personUsername = "dewang_06";
            }
            else if(user=='Nikhil Bhalerao'){
                personUsername = "sputnik_07"
            }
            userItemUsername.textContent = `${personUsername}`;
            userItemName.classList.add('user-msg-desktop-name');
            userItemName.id = 'user-msg-name-id';
    
            userItemUsername.classList.add('user-msg-desktop-username');
            userItemimage.src = `${personUrl}`;
            userItemimage.classList.add('user-msg-desktop-image');
            userItem.appendChild(userItemimage);
            userItem.appendChild(userItemName);
            userItem.appendChild(userItemUsername);
            userItem.classList.add('user-msg-desktop-item');

            
            if (unreadMessages[user] && unreadMessages[user].length > 0) {
                userItem.classList.add('highlight');
            }
            usersList.appendChild(userItem);
        }
    });
});

/**
 * This ensures that the message is sent privately to user that is being targetted
 */

socket.on('private message', ({ from, to, message }) => {
    updateMessageHistory(from, from, message);
    if (to === currentConversation || from === currentConversation) {
        appendMessage(from, message, false); 
    } else {
        if (!unreadMessages[from]) {
            unreadMessages[from] = [];
        }
        unreadMessages[from].push(message);
        const userItem = Array.from(usersList.children).find(user => user.textContent === from);
        if (userItem) {
            userItem.classList.add('highlight');
        }
    }
});

socket.on('connect', () => {
    let username = `${personName}`;
    while (username.trim() === '') {
        username = prompt('Username cannot be empty. Please enter your username:');
    }
    socket.emit('set username', username.trim());
});

socket.on('disconnect', () => {
    alert('Disconnected from server.');
    location.reload();
});

function appendMessage(from, message, isSent) {
    const messageElement = document.createElement('div');
    const isSentByCurrentUser = isSent || from === 'You';
    messageElement.classList.add('message');
    if (isSentByCurrentUser) {
        messageElement.classList.add('sent-message');
    } else {
        messageElement.classList.add('received-message');
    }

    messageElement.textContent = `${message}`;

    chatMessages.appendChild(messageElement);
}

function updateMessageHistory(user, from, message) {
    if (!messageHistory[user]) {
        messageHistory[user] = [];
    }
    messageHistory[user].push({ from, message });
}

/**
 * loads the conversation for the user checking the unread messages for the user and if there are any it loads them
 * @param {*} user 
 */
function loadConversation(user) {
    chatMessages.innerHTML = '';
    if (messageHistory[user]) {
        messageHistory[user].forEach(({ from, message }) => {
            appendMessage(from, message, from === 'You'); 
        });
    }
    if (unreadMessages[user]) {
        unreadMessages[user].forEach(message => {
            appendMessage(user, message, false); 
        });
        unreadMessages[user] = [];
    }
}

/**
 * this event listener is for the functionality of the back button in messages
 */

mobileMessageIcon.addEventListener('click',()=>{
    app.style.display = 'block';
    mainMobileContainer.style.display = 'block';
    chat.style.display = 'none';
    mobileNaviSection.style.display = 'none';
    mobileMainIcons.style.display = 'none';
    mobileFeedIcons.style.display = 'none';
    mobilePosts.style.display = 'none';
    mobileAddPosts.style.display = 'none';
    plusBtn.style.display = 'none';
    
    const mobileListItems = document.querySelectorAll('.user-msg-desktop-name');
    mobileListItems.forEach(mobileListItem => {
        mobileListItem.addEventListener('click',()=>{
            chat.style.display = 'block';
            sidebar.style.display = 'none';
            mobileBottomBar.style.display = 'none';
            
            const backButtonHeader = document.getElementById('back-button-in-header');
            backButtonHeader.addEventListener('click',()=>{
                chat.style.display = 'none';
                sidebar.style.display = 'block';
            });

            
        });

        const messageBarProfilePicture = document.getElementById('message-bar-profile-picture');
        messageBarProfilePicture.addEventListener('click',()=>{
            app.style.display = 'none';
            mainMobileContainer.style.display = 'none';
            mobileMainIcons.style.display = 'flex';
            mobileFeedIcons.style.display = 'flex';
            mobilePosts.style.display = 'block';
            mobileAddPosts.style.display = 'none';
            plusBtn.style.display = 'block';
        })
    });
});