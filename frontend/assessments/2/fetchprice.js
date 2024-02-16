const request = require('request');

exports.pushUpdates = ()=>{
    return new Promise((resolve,reject)=>{
        let pricearr = [];
        fetchAPI('http://localhost:3000/price/zomato')
        .then(function(res){
            pricearr.push(JSON.parse(res));
            resolve(pricearr);
        });
    });
}

function fetchAPI(apiPath){
    return new Promise((resolve,reject)=>{
        request(apiPath,function(error,response,body){
            if(!error && response.statusCode == 200){
                resolve(body);
            }
            else{
                reject(error);
            }
        });
    });
}