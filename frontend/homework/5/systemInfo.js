const os = require('os');
const fs = require('fs');
const hostname = os.hostname();
const osUtils = require('os-utils');

function systemInformation() {
    return {
        HostName: hostname,
        OperatingSystem: os.type(),
        Architecture: os.arch(),
        OSRelease: os.release(),
        Uptime: os.uptime(),
        NumberOfCPUCores: os.cpus().length,
        TotalMemory: os.totalmem(),
        FreeMemory: os.freemem(),
        CurrentWorkingDirectory: process.cwd(),
    };
}

function systemInfoToJson(filePath) {
    const systemInfo = systemInformation();
    const jsonData = JSON.stringify(systemInfo, null, 2);
    fs.writeFileSync(filePath, jsonData);
}

module.exports = { systemInformation, systemInfoToJson };
