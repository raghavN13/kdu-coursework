const mobileMessageIcon = document.getElementById('mobile-message-icon');
const sidebar = document.getElementById('sidebar');
const chat = document.getElementById('chat');
const mobileView = document.getElementById('mobile-view');
const mainMobileContainer = document.getElementById('desktop-message-bigger-container')
const mobileNaviSection = document.getElementById('mobile-navi-section');
const mobileMainIcons = document.getElementById('mobile-main-icons');
const mobileFeedIcons = document.getElementById('mobile-feed-icons');
const mobilePosts = document.getElementById('mobile-posts');
const mobileAddPosts = document.getElementById('mobile-add-posts');
const plusBtn = document.getElementById('plus-btn');
const mobileBottomBar = document.getElementById('mobile-bottom-bar');
const desktopMessages = document.getElementById('desktop-messages');
console.log(desktopMessages.innerHTML);
const desktopFeed = document.querySelector('.feed');
const desktopMessageBiggerContainer = document.getElementById("desktop-message-bigger-container");
desktopMessages.addEventListener('click',()=>{
    desktopFeed.style.display = 'none';
    desktopMessageBiggerContainer.style.display = "block";
    desktopMessages.style.fontWeight = 'bolder'; 
})

const urlParams = new URLSearchParams(window.location.search);
const username = urlParams.get('username');
console.log(username);

let personName;
let personUrl;
let personUsername;

const desktopImage = document.getElementById('image-for-desktop');
console.log(desktopImage.src);
const desktopName = document.getElementById('name-for-desktop');
const desktopUsername = document.getElementById('attherate');
const mobileImage = document.getElementById('profile-photo');
const mobileName = document.getElementById('name-for-mobile');
const mobileUsername = document.getElementById('username-for-mobile');
const mobileImageTwo = document.getElementById('image-for-mobile');


if(username === 'raghav.39'){
    personName = "Raghav Nandwana";
    personUsername = "raghav.39"
    personUrl = "http://localhost:3000/profilepic.png";
    desktopImage.src = "http://localhost:3000/profilepic.png";
    desktopName.innerText = "Raghav Nandwana";
    desktopUsername.innerText = "raghav.39"
    mobileImage.src = "http://localhost:3000/profilepic.png"
    mobileImageTwo.src = "http://localhost:3000/profilepic.png";  // they all could be given a single class name and can be changed so see that once
    mobileName.innerText = "Raghav Nandwana";
    mobileUsername.innerText = "raghav.39";

}
else if(username === 'dewang_06'){
    personName = "Dewang Khandelwal";
    personUsername = "dewang_06";
    personUrl = "http://localhost:3000/profilepic.png";
    desktopImage.src = "http://localhost:3000/profilepic.png";
    desktopName.innerText = "Dewang Khandelwal";
    desktopUsername.innerText = "dewang.06";
    mobileImage.src = "http://localhost:3000/profilepic.png";
    mobileImageTwo.src = "http://localhost:3000/profilepic.png";  // they all could be given a single class name and can be changed so see that once
    mobileName.innerText = "Dewang Khandelwal";                     // see if we can fetch it from the server on request;
    mobileUsername.innerText = "dewang_06";
    
}


// document.getElementById('tweet-btn').addEventListener('click', function(event) {
//     event.preventDefault();
//     let postContent = document.getElementById('post-input').value;
//     console.log(postContent);
//     let newPost = document.createElement('div');
//     newPost.classList.add('general-posts');
//     newPost.innerHTML = `
//         <div class="post-picture">
//             <img src="${personUrl}" alt="Profile Picture">
//         </div>
//         <div class="profile-info">
//             <div class="profile-holder">
//                 <p id="post-name">${personName}</p>
//                 <p class="username" id="postusername">${username}</p>
//             </div>
//             <div class="post-text"><p>${postContent}</p></div>
//             <div class="post-icons">
//             <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z"></path></g></svg>
//             <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"></path></g></svg>
//             <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.30-.504-.30c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
//             <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"></path></g></svg>
//             <div class="bookmark-upload">
//             <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z"></path></g></svg>
//             <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z"></path></g></svg>
//             </div>
//         </div>
//         </div>
//     `;
//     document.querySelector('.posts').appendChild(newPost);
//     document.getElementById('post-input').value = '';
// });
// // JavaScript code


document.getElementById("plus-btn").addEventListener('click',()=>{
    
    app.style.display = 'block';
    mainMobileContainer.style.display = 'none';
    chat.style.display = 'none';
    mobileNaviSection.style.display = 'none';
    mobileMainIcons.style.display = 'none';
    mobileFeedIcons.style.display = 'none';
    mobilePosts.style.display = 'none';
    mobileAddPosts.style.display = 'block';
    plusBtn.style.display = 'none';
    

    document.querySelector(".reverse").addEventListener('click',()=>{
        app.style.display = 'block';
        mainMobileContainer.style.display = 'none';
        chat.style.display = 'none';
        mobileNaviSection.style.display = 'none';
        mobileMainIcons.style.display = 'flex';
        mobileFeedIcons.style.display = 'flex';
        mobilePosts.style.display = 'block';
        mobileAddPosts.style.display = 'none';
        plusBtn.style.display = 'block';
        
    })
    
})
function appendInDesktop(tweetContent){
    console.log("entering in the append in the desktop the tweet content is ", tweetContent);
    let postContent = document.getElementById('post-input-one').value;
    console.log(postContent);
    let newPost = document.createElement('div');
    newPost.classList.add('general-posts-one');
    newPost.innerHTML = `
        <div class="post-picture-one">
            <img src="${personUrl}" alt="Profile Picture">
        </div>
        <div class="profile-info-one">
            <div class="profile-holder-one">
                <p id="post-name-one">${personName}</p>
                <p class="username-one" id="postusername-one">${username} 1s</p>
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi" id="threedoticon"><g><path d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z"></path></g></svg>
            </div>
            <div class="post-text-one"><p>${tweetContent}</p></div>
            <div class="post-icons">
            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z"></path></g></svg>
            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"></path></g></svg>
            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.30-.504-.30c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"></path></g></svg>
            <div class="bookmark-upload">
            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z"></path></g></svg>
            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z"></path></g></svg>
            </div>
        </div>
        </div>
    `;
    document.querySelector('.posts-one').appendChild(newPost);
    document.getElementById('post-input-one').value = '';
}

async function postTweet(username, tweetContent) {
    const requestBody = {
        username: username,
        tweetContent: tweetContent
    };
    
    try {
        const response = await fetch('/users/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        if (response.ok) {
            const data = await response.json();
            const latestTweet = data.tweet; // Assuming the server sends back the latest tweet
            appendInDesktop(latestTweet.content);
        } else {
            throw new Error('Failed to post tweet');
        }
    } catch (error) {
        console.error('Error posting tweet:', error);
    }
}

let page = 1; // Initial page number
const pageSize = 5; // Number of tweets per page

// Function to fetch tweets from the server
async function fetchTweets(username, page) {
    try {
        const response = await fetch(`/users/posts?username=${username}&page=${page}&pageSize=${pageSize}`);
        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            throw new Error('Failed to fetch tweets');
        }
    } catch (error) {
        console.error('Error fetching tweets:', error);
    }
}

// Function to append tweets to the DOM
function appendTweets(tweets) {

    tweets.forEach(tweet => {
        const newtweetContent = tweet.content;
        let postContent = document.getElementById('post-input-one').value;
        console.log(postContent);
        let newPost = document.createElement('div');
        newPost.classList.add('general-posts-one');
        newPost.innerHTML = `
            <div class="post-picture-one">
                <img src="${personUrl}" alt="Profile Picture">
            </div>
            <div class="profile-info-one">
                <div class="profile-holder-one">
                    <p id="post-name-one">${personName}</p>
                    <p class="username-one" id="postusername-one">${username} 1s</p>
                    <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi" id="threedoticon"><g><path d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z"></path></g></svg>
                </div>
                <div class="post-text-one"><p>${newtweetContent}</p></div>
                <div class="post-icons">
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z"></path></g></svg>
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"></path></g></svg>
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.30-.504-.30c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"></path></g></svg>
                <div class="bookmark-upload">
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z"></path></g></svg>
                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z"></path></g></svg>
                </div>
            </div>
            </div>
        `;
        document.querySelector('.posts-one').appendChild(newPost);
        document.getElementById('post-input-one').value = '';
    });
}


// Function to handle scroll event
window.addEventListener('scroll', async () => {
    const { scrollTop, clientHeight, scrollHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 5) { // User reached bottom of the page
        page++; // Increment page number
        const data = await fetchTweets(username, page);
        appendTweets(data.tweets);
    }
});

// On page load, fetch initial set of tweets and render them
window.addEventListener('load', async () => {
    const data = await fetchTweets(username, page);
    appendTweets(data.tweets);
});





document.getElementById('tweet-btn-one').addEventListener('click', function(event) {
    event.preventDefault();
    let postContent = document.getElementById('post-input-one').value;
    console.log("the value in the form  is ", postContent);
    let usernameone = `${username}`;
    console.log("entering here anc posting the data");
    postTweet(usernameone, postContent);
    
});

document.getElementById('profile-photo').addEventListener('click', function() {
    showNavigation();
});
document.getElementById('#go-back').addEventListener('click', function() {
    hideNavigation();
});
function showNavigation() {
    let navigationSection = document.querySelector('.mobile-navigation-section');
    navigationSection.style.display = 'block';
    document.querySelector(".main-icons").style.display = "none";
    document.querySelector(".feed-icons").style.display = "none"
    document.querySelector(".posts").style.display = "none"
}
function hideNavigation() {
    let navigationSection = document.querySelector('.mobile-navigation-section');
    navigationSection.style.display = 'none';
    document.querySelector(".main-icons").style.display = "flex";
    document.querySelector(".feed-icons").style.display = "flex";
    document.querySelector(".posts").style.display = "block";
}





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

// Event listener for when a user is clicked
usersList.addEventListener('click', event => {
    const selectedUser = event.target.textContent;
    // Update the chat window header to display the selected user
    const para = document.getElementById("chat-header-para");
    
    para.textContent = `${selectedUser}`;
    // Clear any previous messages from the chat window
    chatMessages.innerHTML = '';
    // Load conversation history for the selected user
    loadConversation(selectedUser);
    // Set current conversation to the selected user
    currentConversation = selectedUser;
    // Remove highlight from selected user
    event.target.classList.remove('highlight');
    // Clear unread messages for the selected user
    unreadMessages[selectedUser] = [];
});

// Event listener for sending a message
sendButton.addEventListener('click', () => {
    const message = messageInput.value.trim();
    if (message !== '') {
        // Send the message to the selected user
        socket.emit('private message', { to: currentConversation, message });
        // Update the chat window with the sent message
        appendMessage('You', message, true); // Pass true to indicate sent message
        // Update message history
        updateMessageHistory(currentConversation, 'You', message);
        // Clear the message input field
        messageInput.value = '';
    }
});

// Socket events

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
            console.log("the user is ", user);
            let personUsername;
            if (user === "Raghav Nandwana") {
                console.log("i am entering here");
                personUsername = "raghav.39";
            } else if (user === 'Dewang Khandelwal') {
                console.log("I am entering here too ");
                personUsername = "dewang_06";
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

            // Highlight the user in the list if they have unread messages
            if (unreadMessages[user] && unreadMessages[user].length > 0) {
                userItem.classList.add('highlight');
            }
            usersList.appendChild(userItem);
        }
    });
});


socket.on('private message', ({ from, to, message }) => {
    // Update message history
    updateMessageHistory(from, from, message);
    // Check if the message is for the current conversation
    if (to === currentConversation || from === currentConversation) {
        // Update the chat window with the received message
        appendMessage(from, message, false); // Pass false to indicate received message
    } else {
        // Add the message to unread messages
        if (!unreadMessages[from]) {
            unreadMessages[from] = [];
        }
        unreadMessages[from].push(message);
        // Highlight the sender in the users list
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
    
    // Apply different classes based on whether the message is sent or received
    messageElement.classList.add('message');
    if (isSentByCurrentUser) {
        messageElement.classList.add('sent-message');
    } else {
        messageElement.classList.add('received-message');
    }
    
    // Set the message text content
    messageElement.textContent = `${message}`;
    
    // Append the message element to the chatMessages container
    chatMessages.appendChild(messageElement);
}

function updateMessageHistory(user, from, message) {
    if (!messageHistory[user]) {
        messageHistory[user] = [];
    }
    messageHistory[user].push({ from, message });
}

function loadConversation(user) {
    chatMessages.innerHTML = '';
    if (messageHistory[user]) {
        messageHistory[user].forEach(({ from, message }) => {
            appendMessage(from, message, from === 'You'); // Pass true for sent messages
        });
    }
    if (unreadMessages[user]) {
        unreadMessages[user].forEach(message => {
            appendMessage(user, message, false); // Pass false for received messages
        });
        unreadMessages[user] = [];
    }
}



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
            console.log("here in this event listener");
            chat.style.display = 'block';
            sidebar.style.display = 'none';
            mobileBottomBar.style.display = 'none';
            
            const backButtonHeader = document.getElementById('back-button-in-header');
            backButtonHeader.addEventListener('click',()=>{
                chat.style.display = 'none';
                sidebar.style.display = 'block';
            });
        });
    });
});