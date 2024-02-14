const express = require('express');
const cors  = require('cors');

const app = express();
app.use(express.json());
app.use(cors());
const http =  require('http');
const socketIO = require('socket.io');

const server = http.createServer(app);
const io = new socketIO.Server(server,{
    cors:{
        origin:'http://127.0.0.1:5500'
    }
});
app.get("/",(req,res)=>{
    res.json({
        msg:"hello world"
    });
});

io.on("connection",(socket)=>{
    console.log("Connection Created")
    socket.on('message',(msg)=>{
        console.log("the message is ",msg);
        socket.broadcast.emit("message", msg)
    });
});

server.listen(3000,()=>{
    console.log("App Started on port 3000");
})

