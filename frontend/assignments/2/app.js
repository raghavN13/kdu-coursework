const express = require('express');
const http = require('http');
const path = require('path');
const socketIo = require('socket.io');

const bodyParser = require('body-parser');
const bcrypt = require('bcrypt');
const userList = require('./data').userDB;

const app =  express();
const server = http.createServer(app);
const io = socketIo(server);
let tweets = [];
app.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*'); // Allow requests from any origin
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE'); // Allow specific HTTP methods
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization'); // Allow specific headers
    if (req.method === 'OPTIONS') {
        res.sendStatus(200); // Respond to preflight requests
    } else {
        next(); // Continue to the next middleware
    }
});

app.use(bodyParser.urlencoded({extended: false}));
app.use(express.static(path.join(__dirname,'public')));
app.use(bodyParser.json());


app.post('/users/feed', (req, res) => {
    console.log("the body is ", req.body);
    console.log("entering in the server side post");
    console.log(req.body.username);
    console.log(req.body.tweetContent);
    const { username, tweetContent } = req.body;

    const tweetId = generateTweetId();

    const newTweet = {
        id: tweetId,
        username,
        content: tweetContent,
        timestamp: new Date().toISOString()
    };

    tweets.push(newTweet);

    try {
        console.log(tweets);
        res.status(201).json({ message: 'Tweet posted successfully', tweet: newTweet });
    } catch (error) {
        console.error('Error sending response:', error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
});


app.get('/users/feed', (req, res) => {
    const username = req.query.username;
    const page = parseInt(req.query.page) || 1;
    const pageSize = parseInt(req.query.pageSize) || 5;

    // Calculate the startIndex and endIndex based on the page and pageSize
    const startIndex = (page - 1) * pageSize;
    const endIndex = startIndex + pageSize;

    // Slice the tweets array to get the subset for the current page
    const userTweets = tweets.filter(tweet => tweet.username === username);
    const paginatedTweets = userTweets.slice(startIndex, endIndex);

    res.json({
        tweets: paginatedTweets,
        totalTweets: userTweets.length  // Include total number of tweets for pagination
    });
});


function generateTweetId() {
    return Math.floor(Math.random() * 1000000) + 1;
}


app.get('/users/login',(req,res) => {
    res.sendFile(path.join(__dirname,'./public/login.html'));
});
app.get('/users/posts', (req, res) => {
    res.sendFile(path.join(__dirname, './public/view.html'));
});

app.post('/users/login', async(req,res)=>{
    try{
        console.log("the username entered by the user is " , req.body.username);
        let foundUser = userList.find((data) => req.body.username === data.user_name);
        if(foundUser){

            let submittedPassword = req.body.password;
            let storedPassword = foundUser.password;

            console.log(submittedPassword);
            console.log(storedPassword);

            const passwordMatch = await bcrypt.compare(submittedPassword,storedPassword);
            if(passwordMatch){
                res.redirect(`/users/posts?username=${foundUser.user_name}`);
            }
            else{
                res.send("<div align ='center'><h2>Invalid email or password</h2></div><br><br><div align ='center'><a href='./login.html'>login again</a></div>");
            }
        }
        else {
            let fakePass = `$2b$$10$ifgfgfgfgfgfgfggfgfgfggggfgfgfga`;
            await bcrypt.compare(req.body.password, fakePass);
    
            res.send("<div align='center'><h2>Invalid email or password</h2></div><br><br><div align='center'><a href='http://localhost:3000/users/login'>Login again</a></div>");

        }

    }
    catch{
        res.send("Internal server error");
    }
});
const users = {};

io.on('connection', socket => {
    console.log('a user connected');

    socket.on('disconnect', () => {
        console.log('user disconnected');
        if (socket.username) {
            delete users[socket.username];
            updateUsers();
        }
    });

    socket.on('private message', ({ to, message }) => {
        const targetSocket = users[to];
        if (targetSocket) {
            targetSocket.emit('private message', { from: socket.username, message });
            // Also emit the message to the sender so they can see their own message
            socket.emit('private message', { from: 'You', message });
        }
    });

    socket.on('set username', username => {
        socket.username = username;
        users[username] = socket;
        updateUsers();
    });

    function updateUsers() {
        io.emit('update users', Object.keys(users));
    }
});

server.listen(3000,()=>{console.log("Server is listening on port : 3000")});