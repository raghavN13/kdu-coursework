
const express = require('express');
const uuid = require('uuid');
const tweets = [


]
const router = express.Router();
router.get("/",(req,res)=>{
    console.log("enterig here ");
    res.json(tweets);
});

router.get("/:id" , (req,res)=>{
    const found = tweets.some(tweet => tweet.id === parseInt(req.params.id));
    if(found){
        res.json(tweets.filter(tweet=>tweet.id === parseInt(req.params.id)));

    }
    else{
        res.status(400).json({msg:"No user with this id is present"});
    }
})

// post tweet
router.post('/',(req,res)=>{
    const newTweet = {
        id:uuid.v4(),
        name:req.body.name,
        username:req.body.username,
        tweet:req.body.tweet


    };
    if(!newTweet.name || !newTweet.username || !newTweet.tweet){
        return res.status(400).send("Please include all the necessary details");
    }
    tweets.push(newTweet);
    res.send("The new tweet has been added");
    
})

module.exports = router;