const express = require("express");
const app = express();
const path = require('path');
const logger = require('./middleware/logger')

app.use(logger);

app.use(express.json());
app.use(express.urlencoded({extended:false}))
app.use('/api/tweets',require('./routes/twitterroute'));

app.listen(5120,()=>{
    console.log("Application started on port 5120");
});