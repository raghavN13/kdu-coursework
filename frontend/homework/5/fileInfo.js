const path = require('path');
const filePaths = [
    '/home/lenovo/nodeJs/data.txt',
    '/home/lenovo/nodeJs/systemInfo.json'
];

function extractFileInfo(filePath) {
    return {
        extension: path.extname(filePath),
        baseName: path.basename(filePath),
        directory: path.dirname(filePath)
    };
}
function mapFilePaths(filePaths) {
    const fileInfoArray = [];
    for (const filePath of filePaths) {
        const fileInfo = extractFileInfo(filePath);
        fileInfoArray.push(fileInfo);
    }
    return fileInfoArray;
}


const infoArray = mapFilePaths(filePaths);
console.log(infoArray);
