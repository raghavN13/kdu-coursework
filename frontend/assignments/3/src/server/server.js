const http = require('http');
const express = require('express');
const socketIo = require('socket.io');
const cors = require('cors'); // Import the cors package

const app = express();
const server = http.createServer(app);

// Add CORS middleware to allow requests from all origins
app.use(cors());

const io = socketIo(server, {
  cors: {
    origin: '*',
  }
});

const PORT = process.env.PORT || 5000;

const transactions = [];

io.on('connection',(socket)=>{
    console.log("A User is connected");

    const sendRandomNumber = () => {
        const randomNumber = Math.floor(Math.random() * (500 - 200 + 1) + 200); // Generate random number between 200 and 500
        io.emit('newRandomNumber', randomNumber); // Emit 'newRandomNumber' event instead of 'randomNumber'
    };

    // Send random number every 5 seconds
    const interval = setInterval(sendRandomNumber, 10000);


    socket.on('buyRequest',(data) => {
        transactions.push({...data,type:'buy'});
        console.log('buy', data);
        io.emit('transaction',{...data,type:'buy'});
    });


    socket.on('sellRequest',(data) => {
        transactions.push({...data,type:'sell'});
        console.log("selling the data");
        console.log('sell', data);
        io.emit('transaction',{...data,type:'sell'});
    });

    socket.on('disconnect', () => {
        console.log('A user disconnected');
        clearInterval(interval);
      });
    
})

server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
