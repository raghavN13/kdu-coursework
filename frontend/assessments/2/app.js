const express = require('express');
const http = require('http');
const app = express();
const path = require('path');
const socketio = require('socket.io');
const fetchprice = require('./fetchprice');
const server = http.createServer(app);
const io = socketio(server);


app.get('/price/zomato',(req,res)=>{
    let randomnum = Math.floor(Math.random() * (50000 - 1) + 100) / 100;

    res.send({price:randomnum});
    
})

app.use(express.static(path.join(__dirname,'public')));

io.on('connection',socket => {
    console.log('Connection is estabilished');

    setInterval(async() => {
        socket.emit('get-stock-prices', await fetchprice.pushUpdates().catch(err => {console.log(err)}));
    },10000);
})


server.listen(3000,()=> console.log("Server is listening on port 3000`"));

