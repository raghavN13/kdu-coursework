const bcrypt = require('bcrypt');

const userDB = [
  {
    id: "1",
    user_name: "raghav.39",
    password: bcrypt.hashSync("raghav@123", 10), 
    user_email_id: "raghav@gmail.com",
    profile_url: "www.raghav.com",
  },
  {
    id: "2",
    user_name: "dewang_06",
    password: bcrypt.hashSync("dewang@123", 10), 
    user_email_id: "dewang@gmail.com",
    profile_url: "www.dewang.com",
  },
  {
    id: "3",
    user_name: "sputnik_7",
    password: bcrypt.hashSync("nikhil@123", 10), 
    user_email_id: "dewang@gmail.com",
    profile_url: "www.dewang.com",
  }
];

module.exports = { userDB };