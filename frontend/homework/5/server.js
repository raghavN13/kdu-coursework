const http = require('http');
const fs = require('fs');
const { systemInfoToJson } = require('./systemInfo');

const jsonFilePath = './systemInfo.json';

systemInfoToJson(jsonFilePath);

const server = http.createServer((req, res) => {
    fs.readFile(jsonFilePath, (err, data) => {
        if (err) {
            res.writeHead(500);
            res.end('Internal Server Error');
            return;
        }
        res.writeHead(200);
        res.end(data);
    });
});

server.listen(5110, () => {
    console.log('Server is running on http://localhost:5110');
});
